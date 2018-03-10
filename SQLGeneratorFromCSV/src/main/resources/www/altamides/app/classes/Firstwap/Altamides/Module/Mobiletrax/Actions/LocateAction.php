<?php

/*
 * (c) 2014 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : MobileTrax
 * Version          : 2.0 SP19
 * Filename         : LocateAction.php
 * Fileversion      : 2.000.001
 * Initial Creation : 
 * Initial Author   : 
 * Purpose          : 
 * ================================================
 * Initial Request  : # 
 * ================================================
 * Change Log
 * Date         Author          Version     Request     Comment    
 * 2014-05-12   Nababan Maryo   2.osp19     #4116       Update group-spesific for blacklist user.
 * 2014-07-04   Yung Fei        2 SP 20     #267        Introduce group-specific blacklist for Mobiletrax interrogation
 * 2014-12-01   Yung Fei        2.0 SP20    #3371       Can not perform interrogation using Mobiletrax v1 with DemoC
 * 2014-12-23   Yung Fei        2.0 SP20    #3782       Wrong error code in mobiletrax Response
 *
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Actions;

use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Altamides\Db\DbNameRef;
use Firstwap\Altamides\Module\Mobiletrax\QuickLogger;
use Firstwap\Altamides\Module\Mobiletrax\EncodedResponse;
use Firstwap\Altamides\Module\Mobiletrax\InvalidRequestException;
use Firstwap\Altamides\Module\Mobiletrax\Message\LocateFastMessage;
use Firstwap\Altamides\Module\Mobiletrax\EncodedErrorResponse;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocateResultField;
use Firstwap\Altamides\Module\Mobiletrax\ResponseType;
use Firstwap\Altamides\Module\Mobiletrax\RequestType;
use Firstwap\Altamides\Module\Mobiletrax\MobiletraxException;
use Firstwap\Altamides\Module\Mobiletrax\MobiletraxErrorCode;
use Firstwap\Altamides\Module\Mobiletrax\Mcrypt;

/**
 * Description of LocateAction
 *
 * @author setia.budi
 */
class LocateAction extends Action
{   
    
    protected function perform()
    {
        try {
            $this->validateUser($this->data['password']);  
            $message = $this->createResponseMessage($this->locate());
            
            $response = new EncodedResponse($this->request);
            $response->setMessage($message);
            $response->setType($this->data['responseMode']);
            
            //error_log("MSG Locate : ".$message);
            //error_log("MSG setType : ".$this->data['responseMode']);
            
        } catch (MobiletraxException $e) {
            QuickLogger::error($e);
            $response = new EncodedErrorResponse($this->request);
            $response->setErrorMessage($e->getCode());
            //$response->setType("01");            
        } catch (\Exception $e) {
            QuickLogger::error($e);
            $response = new EncodedErrorResponse($this->request);
             $response->setErrorMessage(MobiletraxErrorCode::GENERAL);
            //$response->setType("01");            
        }
        
        return $response;
    }

    /**
     * @todo remove dependency to legacy MO functions
     * 
     * @staticvar array $queryMethodNumericCodes Numeric code Numeric code for query method
     * @staticvar array $replyMapping Web service reply fields mapping
     * @param array $data
     * @return type
     * @throws \Exception 
     */
    protected function locate()
    {
        static $queryMethodNumericCodes = array(
            'PSI' => 0,
            'ATI' => 1,
            'ENH' => 2,
            'FST' => 3
        );

        if (!function_exists('\getLocationFromAPI')
            || !function_exists('\db_log_request')
            || !function_exists('\db_store_response')) {

            throw new \Exception('Required LocationMO functions are not available');
        }
        
        ###################################################
        $data = $this->data;
        
                
        try {
            $this->validateUser($data['password']);
            $status = LoginActionResponse::STATUS_OK;
        } catch (\Exception $e) {
            $status = LoginActionResponse::STATUS_DENIED;
            QuickLogger::warn($e);
             throw new MobiletraxException("Invalid Access",MobiletraxErrorCode::INVALID_ACCESS);
        }
        
        ###################################################
        
        $queryMethodId = $queryMethodNumericCodes[$data['method']];

        // create a db connection for \db_log_request and \db_store_response
        $db = ClassicApp::getDbRes(DbNameRef::DIRECT_TRACKING);
        
        $requestTime = $this->request->getTimestamp();
        $requestTime->setTimezone(new \DateTimeZone('UTC'));

        // store request (as MO protocol)
        $requestLogId = \db_log_request($this->request->getReceiver(),
            $data['target'], $requestTime->format('Y-m-d H:i:s'),
            $this->request->getSender(), $data['password'],
            $this->request->getMessage(), 1, $this->request->getIp(), $db,
            \MOBILETRAX_TRACE_LOG, $data['sms_node']);
        
        $senderData = getSenderData($this->request->getSender(), \MOBILETRAX_TRACE_LOG);
        $clientId=$senderData['client_id'];
                
        $rbwStatus = \rbwMolistFilterTarget($this->request->getSender(), $data['target'], $clientId,
            \MOBILETRAX_TRACE_LOG);        

        $resultArray = getResultRestriction($this->request->getSender(), $db, \MOBILETRAX_TRACE_LOG);

        $resultArrayCount = count($resultArray);
   
          QuickLogger::info("Request status:
            Sender   : ".$this->request->getSender()."
            rbwStatus: $rbwStatus
            Result Options : $resultArrayCount\n");
        
        if ($rbwStatus !== \RBWLIST_ALLOWED) {
            
            if ($rbwStatus === \RBWLIST_DENIED_WHITELIST) {
                $error= MobiletraxErrorCode::TARGET_N0T_IN_WHITELIST;                
            } elseif ($rbwStatus === \RBWLIST_DENIED_BLACKLIST) {
                $error= MobiletraxErrorCode::TARGET_IN_BLACKLIST;                
            } elseif ($rbwStatus === \RBWLIST_ERROR) {
                $error= MobiletraxErrorCode::GENERAL;
            }
    
            throw new MobiletraxException("Target number failed to track ",$error);
        }
        
                
        // call web service
        $wsOriginalReply = \getLocationFromAPI(
            $senderData['user_name'], $senderData['password'], $data['target'],
            $queryMethodId, $data['sms_node'], \MOBILETRAX_TRACE_LOG);
        
        // in case of error response, store an error response
        if (empty($wsOriginalReply)) {
            $responseText = 'Empty interrogation result';
            // \db_store_response only recognise 34 fields
            $replyParts = array_fill(0, 34 - 1, '');
            \db_store_response($responseText, $replyParts, $requestLogId,
                $data['method'], \MOBILETRAX_TRACE_LOG, true);
            throw new \Exception($responseText);
        }
        
        
       
        $replyParts = explode(\DELIMITER_LOCATION, $wsOriginalReply);
        //$replyParts[LocateResultField::BTS_LAT] = '';
        //$replyParts[LocateResultField::BTS_LONG] = '';
        //$replyParts[LocateResultField::LOCATION_QUALITY_ID] = \Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality::BTS;
        //$replyParts[LocateResultField::LOCATION_QUALITY_DESC] = \Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality::BTS;
        
        // Once we removed dependency to legacy \db_store_response function
        // we can move the recording of response to class LocateResponse 
        // which also handle message generation so we can store also the message contents
        // Until that we are storing only <Encoded> as the response message.
        // User can not understand the encoded form anyway.
        // ~ Setia Budi
        \db_store_response('<Encoded Message>', $replyParts, $requestLogId,
            $queryMethodId, \MOBILETRAX_TRACE_LOG, false);
        
        return $replyParts;
    }
    
    /**
     * Parse request message and extract required informations
     * 
     * @return array
     * @throws \Exception
     * @throws InvalidRequestException 
     */
    protected function parseData()
    {
        $message = $this->request->getData();
        
        $requestResponseMap = array(
            RequestType::LOCATE_FAST     => ResponseType::LOCATE_RESULT_FAST,
            RequestType::LOCATE_ECONOMY  => ResponseType::LOCATE_RESULT_ECONOMY,
            RequestType::LOCATE_COMPLETE => ResponseType::LOCATE_RESULT_COMPLETE
        );

        $data = array();

        /*
         * SMS Format:
         * MOBILETRAX LOCATE MSISDN "password" ATI/PSI/ENH/FST SMSNODE #location_request_id
         * MOBILETRAX LOCATE 62123789456 "password" ATI LI #12345789
         */
        $pattern = '/^(?P<target>\d+) '.
                   '(?P<password>.*?) '.
                   '(?P<method>[A-Z]{3}) '.
                   '(?P<sms_node>[A-Za-z0-9]{2,3}) '.
                   '(?P<iv>.*)/';
        

        $match = preg_match($pattern, $message, $data);
                
        $mcrypt = new Mcrypt(MOBILETRAX_ENCRYPTION_KEY, $data['iv']);        
        if (MOBILETRAX_ENCRYPTION_ENABLED) {
            $ar = array('/','+');
            $ar2 = array("_",'-');
            //$encPass = $mcrypt->decrypt(str_replace($ar2,$ar1,$data['pass']));  
            $encPass = $mcrypt->decrypt(str_replace($ar2,$ar,$data['password']));
        } else {
            $encPass = $data['password'];
        }
        
        if ($match === false) {
            throw new InvalidRequestException('Failed doing pattern matching: '.  preg_last_error());
        }
        
        if ($match === 0) {
            QuickLogger::info("There's missing field in locate arguments: ".print_r($data, true));
            throw new InvalidRequestException('Invalid message format');
        }

        if (empty($data['target'])) {
            throw new \Exception('No target specified');
        }

        // auto sms node
        if (empty($data['sms_node'])) {
            $data['sms_node'] = '';
        }
        
        $requestType = $this->request->getType();
        if (isset($requestResponseMap[$requestType])) {
            $responseMode = $requestResponseMap[$requestType];
        } else {
            QuickLogger::error("Can not find mapped response for request type '$requestType'. Fall back to default ");
            $responseMode = ResponseType::LOCATE_RESULT_FAST;
        }
        

        
        $parsed = array(
            'responseMode' => $responseMode,
            'target' => $data['target'],
            'password' => $encPass,
            'method' => $data['method'],
            'sms_node' => $data['sms_node'],
            'iv' => $data['iv']
        );
        
        return $parsed;
    }
    
    /**
     * 
     * @param type $locateResult
     * @return \Firstwap\Altamides\Module\Mobiletrax\Message\LocateFastMessage
     */
    protected function createResponseMessage(array $locateResult)
    {
        
        $map = array (
            'homeMcc' => LocateResultField::HOME_MCC,
            'homeMnc' => LocateResultField::HOME_MNC,
            'cellRef' => LocateResultField::CELL_REF,
            'imsi' => LocateResultField::TARGET_IMSI,
            'imei' => LocateResultField::TARGET_IMEI,
            'cellAzimuth' => LocateResultField::CELL_AZIMUTH,
            'cellRadius' => LocateResultField::LOCATION_RADIUS,
            'cellEndAngle' => LocateResultField::CELL_END_ANGLE,
            'latitude' => LocateResultField::POSITION_LATITUDE,
            'longitude' => LocateResultField::POSITION_LONGITUDE,
            'btsLatitude' => LocateResultField::BTS_LAT,
            'btsLongitude' => LocateResultField::BTS_LONG,
            'locationQualityDbId' => LocateResultField::LOCATION_QUALITY_ID,
            'locationQualityDesc' => LocateResultField::LOCATION_QUALITY_DESC,
            'roamingNumber' => LocateResultField::ROAMING_NUMBER,
            'vlrGlobalTitle' => LocateResultField::VLR_GLOBAL_TITLE,
            'ageOfLocation' => LocateResultField::AGE_OF_LOCATION,
            'errorCode' => LocateResultField::ERROR_CODE,
            'vlrName' => LocateResultField::VLR_NAME,
            'region' => LocateResultField::REGION,
            'zipCode' => LocateResultField::ZIP_CODE,
            'phoneStatus' => LocateResultField::PHONE_STATUS,
        );
        
        $data = array();
        foreach ($map as $key => $fieldNo) {
            // ??? means data not available
            if (!isset($locateResult[$fieldNo]) || (trim($locateResult[$fieldNo]) === '???')) {
                $data[$key] = null;
            } else {
                $data[$key] = $locateResult[$fieldNo];
            }
            
        }
        
        $message = new LocateFastMessage();
        $message->setData($data);
        return $message;
    }

}