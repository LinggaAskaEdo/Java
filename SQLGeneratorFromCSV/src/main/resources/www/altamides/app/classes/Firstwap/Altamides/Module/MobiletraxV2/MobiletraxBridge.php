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

namespace Firstwap\Altamides\Module\MobiletraxV2;

use Firstwap\Altamides\Sms\MessageSender;
use Firstwap\Altamides\Sms\TerminatedMessage;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocateResultField;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;
use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Altamides\Module\MobiletraxV2\Request;
use Firstwap\Altamides\Db\Ref;

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
    protected $logId;
    
    protected $loginFailCode;
    protected $dataResponse;
    protected $user_id;
    protected $creator;
    protected $pass_user;
    protected $destClientId;
    protected $isSocketRequest;



    /**
     *
     * @var @var Request 
     */
    protected $request;
    /**
     *
     * @var array
     */
    protected $userDetails = null;

    public function __construct($isSocket, $user_id, $pass_user, $creator, $requestSms, $requestIp, $clientMsisdn = "", $serverMsisdn = "", $moMessageId = "", $isGroupVn = "")
    {
        $this->isSocketRequest  = $isSocket;
        $this->requestIp        = $requestIp;
        $this->requestSms       = $requestSms;        
        $this->request          = new Request();
        $this->loginFailCode    = 0;
        $this->logId            = 0;
        $this->creator=$creator;
        $this->user_id=$user_id;
        $this->pass_user=$pass_user;

        if (!$isSocket) {
            $this->clientMsisdn = \ltrim($clientMsisdn, '+');
            $this->serverMsisdn = \ltrim($serverMsisdn, '+');
            $this->destClientId = $isGroupVn;
            $this->logId        = $moMessageId;
        }
    }
    
    /**
     * Get Mo Message ID
     * @return string Mo Message ID
     */
    public function getMessageId()
    {
        return $this->logId;
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
     * Get status request type
     * @return boolean 
     * @Desc: return true if request type are socket communication
     */
    public function getIsSocketRequest()
    {
        return $this->isSocketRequest;
    }
    
    /**
     * Get Owner id
     * @return string owner id
     */
    public function getOwnerId()
    {
        return $this->creator;
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
            'Sec' => 0,
            'Pri' => 1,
            'Enh' => 2,
            'Fas' => 3,
            'Aut' => 4
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
        $senderData = \getSenderData($this->request->getSender(), \MOBILETRAX_TRACE_LOG, $this->user_id);
        $creator=$senderData['user_id'];
        $rbwStatus= \rbwMolistFilterTarget($this->request->getSender(),$targetNumber,$creator,
            \MOBILETRAX_TRACE_LOG);

        $resultArray = \getResultRestriction($this->clientMsisdn, $db, \MOBILETRAX_TRACE_LOG);
        $resultArrayCount = count($resultArray);
        
        $ftPass = getFtData($this->user_id);
        $locatePrivilege = checkIfIgnoreOwnerWhitelist($this->creator);

        QuickLogger::info("Request status:
            Target   : $targetNumber
            rbwStatus: $rbwStatus
            Ignore Privilege : $locatePrivilege     
            Result Options : $resultArrayCount\n");
        
        //check whitelist
        if ($rbwStatus == RBWLIST_DENIED_WHITELIST) {
            $wsOriginalReply = "303";
            QuickLogger::info("Target number <$targetNumber> failed to track ", $rbwStatus);
        }
        //check blacklist
        else if ($rbwStatus == RBWLIST_DENIED_BLACKLIST) {
            $wsOriginalReply = "302";
            QuickLogger::info("Target number <$targetNumber> failed to track ", $rbwStatus);
        } else {
            if ($locatePrivilege) {

                $wsOriginalReply = \getLocationFromAPI($this->user_id, $ftPass['PASSWORD'], $targetNumber, $queryMethodId, $smsNode, \MOBILETRAX_TRACE_LOG);
            } else {

                $rbwStatus_sso = \rbwlistFilterTarge_SSO($targetNumber, $this->user_id, $this->creator);
                //check whitelist in profiletrax
                if ($rbwStatus_sso == RBWLIST_DENIED_WHITELIST) {
                    $wsOriginalReply = "303";
                    QuickLogger::info("Target number <$targetNumber> failed to track ", $rbwStatus);
                }
                //check blacklist  in profiletrax
                else if ($rbwStatus_sso == RBWLIST_DENIED_BLACKLIST) {
                    $wsOriginalReply = "302";
                    QuickLogger::info("Target number <$targetNumber> failed to track ", $rbwStatus);
                } else {
                    $wsOriginalReply = \getLocationFromAPI($this->user_id, $ftPass['PASSWORD'], $targetNumber, $queryMethodId, $smsNode, \MOBILETRAX_TRACE_LOG);
                }
            }
        }
        
        if (is_numeric($wsOriginalReply)) {
            $wsOriginalReply = ';' . $wsOriginalReply . ';';
        }
        $maxReplyFields = 34;
        $semicolonCount = substr_count($wsOriginalReply, ';');
        if ($semicolonCount < ($maxReplyFields - 1)) {
            $wsOriginalReply.= str_repeat(';', ($maxReplyFields - 1) - $semicolonCount);
        }

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

        if ($replyParts[LocateResultField::ADDITIONAL_ERROR_CODE] == "???" || empty($replyParts[LocateResultField::ADDITIONAL_ERROR_CODE])) {
            $errorCode = LocateResultField::ERROR_CODE;
        } else {
            $errorCode = LocateResultField::ADDITIONAL_ERROR_CODE;
        }

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
            'phoneStatus'         => LocateResultField::PHONE_STATUS,
            'roamingNumber'       => LocateResultField::ROAMING_NUMBER,
            'vlrGlobalTitle'      => LocateResultField::VLR_GLOBAL_TITLE,
            'ageOfLocation'       => LocateResultField::AGE_OF_LOCATION,
            'errorCode'           => $errorCode,
            'vlrName'             => $this->sanitizeTextForMobileTrax(LocateResultField::VLR_NAME),
            'region'              => $this->sanitizeTextForMobileTrax(LocateResultField::REGION),
            'zipCode'             => $this->sanitizeTextForMobileTrax(LocateResultField::ZIP_CODE),
            'cellAddress1'        => null,
            'cellAddress2'        => null,
            'cellAddress3'        => null,
            'cellElevation'       => null,
            'comment'       => null,
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
                $resultData['cellAddress1'] = $this->sanitizeTextForMobileTrax($cellData['LOC_1']);
                $resultData['cellAddress2'] = $this->sanitizeTextForMobileTrax($cellData['LOC_2']);
                $resultData['cellAddress3'] = $this->sanitizeTextForMobileTrax($cellData['LOC_3']);
                $resultData['comment'] = $this->sanitizeTextForMobileTrax($cellData['COMMENT']);
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
        QuickLogger::debug(__METHOD__ . ": SenderID=<$senderId>; Dest=<$dest>");
        QuickLogger::debug(__METHOD__ . ': Plain text = ' . $text);
        QuickLogger::debug(__METHOD__ . ': Hex text = ' . \bin2hex($text));
        return $this->sendSms($text, $dest, $senderId);
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
        $smsSender->setFtUserId($this->user_id);
        $smsSender->setCreator($this->creator);
        $smsSender->setClientId($this->destClientId);

        $message = new TerminatedMessage();
        $message->setContent($text);
        $smsSender->setMessage($message);
        return $smsSender->send();
        
    }

    /**
     * 
     * @param string $password
     * @param string $imei
     * @return boolean
     */
    public function validateUser($password, $imei, $username = '')
    {
        $details = $this->getUserDetails();
        if (!$details) {
            $this->loginFailCode = 2;
            QuickLogger::info("User <{$this->user_id}> is not found");
            return false;
        }
        if (!$details['active']) {
            $this->loginFailCode = 1;
            QuickLogger::info("User <{$this->user_id}> is not active");
            return false;
        }
        if ($password !== $this->pass_user) {
            $this->loginFailCode = 2;
            QuickLogger::info("User <{$this->user_id}> password does not match");
            return false;
        }
        if($username !== $details['name'] && $username != ''){
            $this->loginFailCode = 2;
            QuickLogger::info("User <{$this->clientMsisdn}> username not found");
            return false;
        }
        
        if ($this->isSocketRequest) {
            if ($details['imeiRequired'] && $imei !== $details['imei']) {
                $this->loginFailCode = 3;
                QuickLogger::info("User <{$this->user_id}> imei does not match. IMEI: <$imei> vs <{$details['imei']}>");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 
     * @return number
     */
    public function getLoginFailCode(){
        return $this->loginFailCode;
    }

    /**
     * Retrieve user password from db
     * @param string $msisdn
     * @return string
     */
    public function getUserPassword()
    {
        $details = $this->pass_user;
        return $details ? $details : null;
    }

    /**
     * Get the details about
     * @return array the details as an array:
     * - name
     * - password
     * - imei
     * - checkImei
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

        $query = "SELECT 
                    NAME AS name, 
                    `PASSWORD` AS `password`, 
		    hex(FT_USER_UUID) AS ftUserUuid,
                    IMEI AS imei,";
        
        if ($this->isSocketRequest) {
            $query .= " IF (IP_ADDRESS = NULL OR IP_ADDRESS = '', TRUE, FALSE) AS imeiRequired,";
        }
        
        $query .= "(ACTIVE=1 AND NOW() BETWEEN START_DATE AND END_DATE) AS active  
                    FROM $moDbName.AUTHORIZED_MSISDN 
                    WHERE ID=:id ";

        $stmt = $pdo->prepare($query);
        $stmt->bindValue(':id', $this->user_id);
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
        
        $mccCheck = $mccIsEmpty ? 'is null' : ':mcc';
        $mncCheck = $mncIsEmpty ? 'is null' : ':mnc';
        $lacCheck = $lacIsEmpty ? 'is null' : ':lac';
        $cellIdCheck = $cellIdIsEmpty ? 'is null' : ':cellId';

        $query = "select MCC,MNC,LAC, CELL_ID, CELL_REF, LAT_DEC, LONG_DEC, ELEVATION, "
            . "LOC_1, LOC_2, LOC_3, COMMENT "
            . "from $cellDb.CELL_DB "
            . "where MCC = $mccCheck "
            . "and MNC = $mncCheck "
            . "and LAC = $lacCheck "
            . "and CELL_ID = $cellIdCheck";
        
        $stmt = $pdo->prepare($query);
        if (!$mccIsEmpty) {
            $stmt->bindValue($mccCheck, $mcc, \PDO::PARAM_INT);
        }
        
        if (!$mncIsEmpty) {
            $stmt->bindValue($mncCheck, $mnc, \PDO::PARAM_INT);
        }
        
        if (!$lacIsEmpty) {
            $stmt->bindValue($lacCheck, $lac, \PDO::PARAM_STR);
        }
        
        if (!$cellIdIsEmpty) {
            $stmt->bindValue($cellIdCheck, $cellId, \PDO::PARAM_STR);
        }
        
        $stmt->execute();
        $result = $stmt->fetch(\PDO::FETCH_ASSOC);
        return $result ? $result : null;
        
    }
    public function sanitizeTextForMobileTrax($line){
        if (empty($line)) {
            return '';
        }
        else if (is_numeric($line)) {
            return $line;
        }
        else if (is_string($line)) {
            //$data = recode_string("us..flat", $line);
            $data = trim(preg_replace('/[^\x{0000}-\x{007F}A-Za-z !@#$%^&*()]/u','', $line));
            if (strcmp($data, $line)!=0) {
                QuickLogger::info(__METHOD__ . ": Data sanatized from $line to $data.");
            }
            return $data;
        }
        else {
            QuickLogger::info(__METHOD__ . ": unexpected format in line " . print_r($line, true));
            return '';
        }
    }
}