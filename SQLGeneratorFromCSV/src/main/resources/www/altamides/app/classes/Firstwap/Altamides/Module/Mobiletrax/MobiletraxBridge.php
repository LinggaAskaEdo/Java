<?php

/*
 * (c) 2014 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Mobiletrax
 * Version          : 2.0 SP19
 * Filename         : MobiletraxBridge.php
 * Fileversion      : 2.000.001
 * Initial Creation : 
 * Initial Author   : Setia Budi
 * Purpose          : 
 * ================================================
 * Initial Request  : #
 * ================================================
 * Change Log
 * Date         Author          Version     Request     Comment    
 * 2014-05-12   Nababan Maryo   2.osp19     #4116       Update group-spesific for blacklist user.
 * 2014-07-04   Yung Fei        2 SP 20     #267        Introduce group-specific blacklist for Mobiletrax interrogation
 *
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

use Firstwap\Altamides\Sms\MessageSender;
use Firstwap\Altamides\Sms\TerminatedMessage;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocateResultField;
use Firstwap\Altamides\Module\Mobiletrax\QuickLogger;
use Firstwap\Altamides\Controller\ClassicApp;

/**
 * Description of MobiletraxBridge
 *
 * @author setiabudi
 */
class MobiletraxBridge
{

    protected $clientMsisdn;
    protected $serverMsisdn;
    protected $requestSms;
    protected $requestIp;
    /**
     *
     * @var array
     */
    protected $userDetails = null;

    public function __construct($clientMsisdn, $serverMsisdn, $requestSms)
    {
        $this->clientMsisdn = \ltrim($clientMsisdn, '+');
        $this->serverMsisdn = \ltrim($serverMsisdn, '+');
        $this->serverMsisdn = $serverMsisdn;
        $this->requestIp    = $_SERVER['REMOTE_ADDR'];
        $this->requestSms  = $requestSms;
    }
    
    /**
     * Get client device MSISDN
     * @return string MSISDN
     */
    public function getClientMsisdn()
    {
        return $this->clientMsisdn;
    }

    /**
     * Get the server virtual number
     * @return string MSISDN
     */
    public function getServerMsisdn()
    {
        return $this->serverMsisdn;
    }

    /**
     * Get the request SMS
     * @return string
     */
    public function getRequestSms()
    {
        return $this->requestSms;
    }

    /**
     * Do interrogation and return the result as an array
     * @staticvar array $queryMethodNumericCodes
     * @param string $targetNumber the target MSISDN
     * @param string $smsNode The SMS node
     * @param string $queryMethod the query method
     * @return null|array nulL when interrogation fails, array of result when succes. The array consists of:
     * - homeMcc
     * - homeMnc
     * - cellRef
     * - imsi
     * - imei
     * - cellAzimuth
     * - cellRadius
     * - cellEndAngle
     * - latitude
     * - longitude
     * - btsLatitude
     * - btsLongitude
     * - locationQualityDbId
     * - locationQualityDesc
     * - roamingNumber
     * - vlrGlobalTitle
     * - ageOfLocation
     * - errorCode
     * - vlrName
     * - region
     * - zipCode
     * - cellElevation
     * - cellAddress1
     * - cellAddress2
     * - cellAddress3
     * - cellAddress3
     */
    public function locate($targetNumber, $smsNode, $queryMethod)
    {
        static $queryMethodNumericCodes = array(
            'PSI' => 0,
            'ATI' => 1,
            'ENH' => 2,
            'FST' => 3
        );

        if (!\function_exists('\getLocationFromAPI') || !\function_exists('\db_log_request') || !\function_exists('\db_store_response')) {

            throw new \Exception('Required LocationMO functions are not available');
        }

        $queryMethodId = $queryMethodNumericCodes[$queryMethod];

        // create a db connection for \db_log_request and \db_store_response
        $db = ClassicApp::getDbRes(DbNameRef::DIRECT_TRACKING);

        $requestTime = $this->request->getTimestamp();
        $requestTime->setTimezone(new \DateTimeZone('UTC'));

        // store request (as MO protocol)
        $requestLogId = \db_log_request($this->serverMsisdn, $targetNumber, null,
                                        $this->clientMsisdn, '', $this->requestSms, 1,
                                        $this->requestIp, $db, \MOBILETRAX_TRACE_LOG, $smsNode);

        $senderData = \getSenderData($this->request->getSender(), \MOBILETRAX_TRACE_LOG);
        $clientId=$senderData['client_id'];
        $rbwStatus= \rbwlistFilterTarget($this->request->getSender(),$targetNumber,$clientId,
            \MOBILETRAX_TRACE_LOG);
        
        $resultArray = \getResultRestriction($this->clientMsisdn, $db, \MOBILETRAX_TRACE_LOG);
        $resultArrayCount = count($resultArray);

        QuickLogger::info("Request status:
            Target   : $targetNumber
            rbwStatus: $rbwStatus
            Result Options : $resultArrayCount\n");

        if($rbwStatus<>RBWLIST_ALLOWED)
        {
            QuickLogger::info("Target number <$targetNumber> failed to track ",$rbwStatus);
        }

        // call web service
        $wsOriginalReply = \getLocationFromAPI(
            $senderData['user_name'], $senderData['password'], $targetNumber, $queryMethodId,
            $smsNode, \MOBILETRAX_TRACE_LOG);

        // in case of error response, store an error response
        if (empty($wsOriginalReply)) {
            $responseText = 'Empty interrogation result';
            // \db_store_response only recognise 34 fields
            $replyParts   = array_fill(0, 34 - 1, '');
            \db_store_response($responseText, $replyParts, $requestLogId, $queryMethod,
                               \MOBILETRAX_TRACE_LOG, true);
            QuickLogger::warn("Error while interrogating $targetNumber/$smsNode/$queryMethod: $responseText");
            return null;
        }

        $replyParts = explode(\DELIMITER_LOCATION, $wsOriginalReply);
        // Once we removed dependency to legacy \db_store_response function
        // we can move the recording of response to class LocateResponse 
        // which also handle message generation so we can store also the message contents
        // Until that we are storing only <Encoded> as the response message.
        // User can not understand the encoded form anyway.
        // ~ Setia Budi
        \db_store_response('<Encoded Message>', $replyParts, $requestLogId, $queryMethodId,
                           \MOBILETRAX_TRACE_LOG, false);


        $responseFieldMap = array(
            'homeMcc'             => LocateResultField::HOME_MCC,
            'homeMnc'             => LocateResultField::HOME_MNC,
            'cellRef'             => LocateResultField::CELL_REF,
            'imsi'                => LocateResultField::TARGET_IMSI,
            'imei'                => LocateResultField::TARGET_IMEI,
            'cellAzimuth'         => LocateResultField::CELL_AZIMUTH,
            'cellRadius'          => LocateResultField::LOCATION_RADIUS,
            'cellEndAngle'        => LocateResultField::CELL_END_ANGLE,
            'latitude'            => LocateResultField::POSITION_LATITUDE,
            'longitude'           => LocateResultField::POSITION_LONGITUDE,
            'btsLatitude'         => LocateResultField::BTS_LAT,
            'btsLongitude'        => LocateResultField::BTS_LONG,
            'locationQualityDbId' => LocateResultField::LOCATION_QUALITY_ID,
            'locationQualityDesc' => LocateResultField::LOCATION_QUALITY_DESC,
            'roamingNumber'       => LocateResultField::ROAMING_NUMBER,
            'vlrGlobalTitle'      => LocateResultField::VLR_GLOBAL_TITLE,
            'ageOfLocation'       => LocateResultField::AGE_OF_LOCATION,
            'errorCode'           => LocateResultField::ERROR_CODE,
            'vlrName'             => LocateResultField::VLR_NAME,
            'region'              => LocateResultField::REGION,
            'zipCode'             => LocateResultField::ZIP_CODE,
            'cellAddress1'        => null,
            'cellAddress2'        => null,
            'cellAddress3'        => null,
            'cellElevation'       => null,
        );
        
        $resultData = array();
        foreach ($responseFieldMap as $responseFieldName => $responseFieldNo) {
            // ??? means data not available
            if ($responseFieldNo === null || !isset($replyParts[$responseFieldNo]) || (trim($replyParts[$responseFieldNo]) === '???')) {
                $resultData[$responseFieldName] = null;
            } else {
                $resultData[$responseFieldName] = $replyParts[$responseFieldNo];
            }
        }
        
        if ($resultData['cellRef'] !== null) {
            $cellData = $this->getCellData($resultData['cellRef']);
            if ($cellData) {
                $resultData['cellElevation'] = $cellData['ELEVATION'];
                $resultData['cellAddress1'] = $cellData['LOC_1'];
                $resultData['cellAddress2'] = $cellData['LOC_2'];
                $resultData['cellAddress3'] = $cellData['LOC_3'];
            }
        }

        return $resultData;
    }

    /**
     * Send reply SMS using Altamides system
     * 
     * @param string $text The SMS text
     */
    public function sendReplySms($text)
    {
        $senderId = $this->serverMsisdn;
        $dest = $this->clientMsisdn;
        $this->sendSms($text, $dest, $senderId);
    }
    
    /**
     * Send SMS using Altamides system
     * 
     * @param string $text The SMS text
     * @param string $dest Destination MSISDN (no + prefix)
     * @param string $senderId Destination MSISDN (no + prefix)
     */
    public function sendSms($text, $dest, $senderId)
    {   
        $smsSender = new MessageSender();
        $smsSender->setSenderId("+$senderId");
        $smsSender->setDestination($dest);
        $smsSender->setDepartment('Mobiletrax');
        $smsSender->setUser('Mobiletrax');

        $message = new TerminatedMessage();
        $message->setContent($text);
        $smsSender->setMessage($message);
        $smsSender->send();
    }

    /**
     * 
     * @param string $password
     * @param string $imei
     * @return boolean
     */
    public function validateUser($password, $imei)
    {
        $details = $this->getUserDetails();
        if (!$details) {
            QuickLogger::info("User <{$this->clientMsisdn}> is not found");
            return false;
        }
        
        if ($details['active']) {
            QuickLogger::info("User <{$this->clientMsisdn}> is not active");
            return false;
        }
        
        if ($password !== $details['password']) {
            QuickLogger::info("User <{$this->clientMsisdn}> password does not match");
            return false;
        }
        
        if ($details['imeiRequired'] && $imei !== $details['imei']) {
            QuickLogger::info("User <{$this->clientMsisdn}> imei does not match. IMEI: <$imei> vs <{$details['imei']}>");
            return false;
        }
        
        return true;
    }

    /**
     * Retrieve user password from db
     * @param string $msisdn
     * @return string
     */
    public function getUserPassword()
    {
        $details = $this->getUserDetails();
        return $details ? $details['password'] : null;
    }

    /**
     * Get the details about
     * @return array the details as an array:
     * - name
     * - password
     * - imei
     * - imeiRequired
     * - active
     */
    public function getUserDetails()
    {
        if ($this->userDetails) {
            return $this->userDetails;
        }
        
        $dbManager = DbManager::getInstance();
        $moDbName  = DbNameRef::getName(DbNameRef::DIRECT_TRACKING);
        $pdo       = $dbManager->getPdo(DbNameRef::DIRECT_TRACKING);

        $query = "select NAME as name, `PASSWORD` as `password`, "
            . " IMEI as imei, IMEI_REQUIRED as imeiRequired, "
            . " (ACTIVE=1 and NOW() between START_DATE and END_DATE) as active "
            . " from $moDbName.AUTHORIZED_MSISDN "
            . " where MSISDN=:msisdn ";

        $stmt = $pdo->prepare($query);
        $stmt->bindValue(':msisdn', $this->clientMsisdn);
        $stmt->execute();

        if (!$stmt->rowCount()) {
            return null;
        }

        $result           = $stmt->fetch(\PDO::FETCH_ASSOC);
        $result['active'] = (bool) $result['active'];
        
        $this->userDetails = $result;

        return $result;
    }
    
    /**
     * Get information about a cell
     * @param string $cellRef The cell reference (MCC.MNC.LAC.CID)
     * @return null|array null when failed, associative array when success
     */
    protected function getCellData($cellRef)
    {
        if (empty($cellRef)) {
            return null;
        }

        $dbManager = DbManager::getInstance();
        $cellDb  = DbNameRef::getName(DbNameRef::TELCO);
        $pdo       = $dbManager->getPdo(DbNameRef::TELCO);

        list($mcc, $mnc, $lac, $cellId) = explode('.', $cellRef);
        
        
        $mccIsEmpty = ($mcc === null || $mcc === '');
        $mncIsEmpty = ($mnc === null || $mnc === '');
        $lacIsEmpty = ($lac === null || $lac === '');
        $cellIdIsEmpty = ($cellId === null || $cellId === '');
        
        $mccCheck = $mccIsEmpty ? 'is null' : '=:mcc';
        $mncCheck = $mncIsEmpty ? 'is null' : '=:mnc';
        $lacCheck = $lacIsEmpty ? 'is null' : '=:lac';
        $cellIdCheck = $cellIdCheck ? 'is null' : '=:cellId';

        $query = "select MCC,MNC,LAC, CELL_ID, CELL_REF, LAT_DEC, LONG_DEC, ELEVATION, "
            . "LOC_1, LOC_2, LOC_3 "
            . "from $cellDb.CELL_DB "
            . "where MCC $mccCheck "
            . "and MNC $mncCheck "
            . "and LAC $lacCheck "
            . "and CELL_ID $cellIdCheck";
        
        $stmt = $pdo->prepare($query);
        
        if (!$mccIsEmpty) {
            $stmt->bindValue($stmt, $mcc, \PDO::PARAM_INT);
        }
        
        if (!$mncIsEmpty) {
            $stmt->bindValue($stmt, $mnc, \PDO::PARAM_INT);
        }
        
        if (!$lacIsEmpty) {
            $stmt->bindValue($stmt, $lac, \PDO::PARAM_STR);
        }
        
        if (!$cellIdIsEmpty) {
            $stmt->bindValue($stmt, $cellId, \PDO::PARAM_STR);
        }
        
        $stmt->execute();
        $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
        return $result ? $result : null;
        
    }

}