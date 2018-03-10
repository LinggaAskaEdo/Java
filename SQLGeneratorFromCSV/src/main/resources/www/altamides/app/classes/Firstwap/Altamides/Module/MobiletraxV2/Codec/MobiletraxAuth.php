<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : MobiletraxAuth.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-03-04
 * Initial Author   : Waluyo Sejati <waluyo.sejati@1rstwap.com>
 * Purpose          : Definition of class MobiletraxAuth
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-03-04   Waluyo Sejati             1.000.000  #4192    Initial Creation
 * 2015-03-09   Dwikky Maradhiza          1.000.000  #4216    Adding session tear down functionality
 * 2015-03-10   Dwikky Maradhiza          1.000.000  #4332    Adding session timeout functionality
 */

/**
 * Description of MobiletraxAuth
 *
 * @author waluyo
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7CodecMap;
use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Altamides\Db\DbNameRef;

class MobiletraxAuth {
    
    public $mobiletraxRequestId;
    public $moMessageId;

    const MAX_CHAR_CODE = 127;
    
    public function __construct()
    {
        $this->mobiletraxRequestId = '';
        $this->moMessageId = '';
    }
    
    public function getSessionToken($ftUserUuid){
        $sql = "";
        try {
            $stat = true;
            $ret = array('token'=> '' , 'isActive' => $stat);
            
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW' , $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $codecMap = new Gsm7CodecMap();
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            $dbSms = DbNameRef::getActualName(DbNameRef::SMS_DISTRIBUTOR);
            
            $uuid = $db->quote($ftUserUuid);
            
            $sql ="SELECT MS.SESSION_TOKEN, MS.INVALIDATED_AT, MS.MODIFIED_AT AS RECEIVED "
                    . "FROM "
                    . "$dbName.MOBILETRAX_SESSION AS MS LEFT JOIN "
                    . "$dbName.MOBILETRAX_REQUEST AS MR ON (MS.LAST_REQUEST_LOG = MR.MOBILETRAX_REQUEST_SID) "
                    . "WHERE "
                    . "MS.FT_USER_UUID = unhex($uuid) AND "
                    . "MS.INVALIDATED_AT IS NULL";

            $dataSession = $db->query($sql)->fetch();
            
            if(!($dataSession)){
                $stat = false;
            }
            else{
                $token = $dataSession['SESSION_TOKEN'];
                $ret['token'] = $codecMap->decode($token);
            }
            
            //get expired time based on configuration
            $invalidDate = new \DateTime($dataSession['RECEIVED']);
            $invalidDate->setTimezone($UTC);
            
            //iso8601
            $sessionMaxIdle = new \DateInterval(MOBILETRAX_IDLE_TIME);
            
            $invalidDate->add($sessionMaxIdle);
            $expired = $invalidDate->format('Y-m-d H:i:s');
            
            if($expired < $now){
                //set expired time
                $this->tearDownSession($ftUserUuid);
                
                $stat = false;
            }
            
            $ret['isActive'] = $stat;
            
            return $ret;
            
        } catch(\Exception $e) {
            QuickLogger::error("getSessionToken: Failed while getting session. \n $sql");
            throw new \Exception('Error while getting session. '.$e->getMessage());
        }
    }
    
    public function isNotActiveSession($session){
        $sql = "";
        try {
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW', $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $codecMap = new Gsm7CodecMap();
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            $dbSms = DbNameRef::getActualName(DbNameRef::SMS_DISTRIBUTOR);
            
            $session = $codecMap->encode($session);
            
            $sql ="SELECT hex(MS.MOBILETRAX_SESSION_UUID) AS UUID, MS.MODIFIED_AT AS RECEIVED "
                    . "FROM "
                    . "$dbName.MOBILETRAX_SESSION AS MS LEFT JOIN "
                    . "$dbName.MOBILETRAX_REQUEST AS MR ON (MS.LAST_REQUEST_LOG = MR.MOBILETRAX_REQUEST_SID) "
                    . "WHERE "
                    . "MS.SESSION_TOKEN='$session' AND MS.INVALIDATED_AT IS NULL";
                        
            $dataSession = $db->query($sql)->fetch();
            if(!($dataSession)){
                return true;
            }
            
            //get expired time based on configuration
            $invalidDate = new \DateTime($dataSession['RECEIVED']);
            $invalidDate->setTimezone($UTC);
            
            //iso8601
            $sessionMaxIdle = new \DateInterval(MOBILETRAX_IDLE_TIME);
            
            $invalidDate->add($sessionMaxIdle);
            $expired = $invalidDate->format('Y-m-d H:i:s');
            
            if($expired >= $now){
                return false;
            }
            
            if($expired < $now){
                //set expired time
                $this->tearDownSession($dataSession['UUID']);
                
                return true;
            }
        } catch(\Exception $e) {
            QuickLogger::error("isNotActiveSession: Failed Check not active session. \n $sql");
            throw new \Exception('Error while checking active session. '.$e->getMessage());
        }
    }
    
    public function updateLogRequest($sessId){
        $updateLog="";
        try {
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW', $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $codecMap = new Gsm7CodecMap();
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            
            $sessToken = $codecMap->encode($sessId);
            $sessToken = $db->quote($sessToken);
            
            $updateLog ="UPDATE $dbName.MOBILETRAX_SESSION SET LAST_REQUEST_LOG='".$this->mobiletraxRequestId."', MODIFIED_AT='$now' WHERE SESSION_TOKEN = $sessToken AND INVALIDATED_AT IS NULL";
            $db->query($updateLog);
                
        } catch(\Exception $e) {
            QuickLogger::error("updateLogRequest: Failed Update LAST_AUTH_LOG to MOBILETRAX_SESSION. \n $updateLog");
            throw new \Exception('Error while updating request log. '.$e->getMessage());
        }
    }
    
    public function isActiveSession($session){
        $sql = "";
        try {
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW' , $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            
            $sql ="SELECT hex(MOBILETRAX_SESSION_UUID) AS UUID, INVALIDATED_AT, CREATED_AT AS RECEIVED FROM $dbName.MOBILETRAX_SESSION WHERE SESSION_TOKEN='$session' AND INVALIDATED_AT IS NULL";
            $dataSession = $db->query($sql)->fetch();
            
            if(!($dataSession)){
                return false;
            }
            
            //get expired time based on configuration
            $invalidDate = new \DateTime($dataSession['RECEIVED']);
            $invalidDate->setTimezone($UTC);
            
            //iso8601
            $sessionMaxIdle = new \DateInterval(MOBILETRAX_IDLE_TIME);
            
            $invalidDate->add($sessionMaxIdle);
            $expired = $invalidDate->format('Y-m-d H:i:s');
            
            if($expired >= $now){
                return true;
            }
            
            if($expired < $now){
                //set expired time
                $this->tearDownSession($dataSession['UUID']);
                
                return false;
            }
        } catch(\Exception $e) {
            QuickLogger::error("isActiveSession: Failed Checking Session from MOBILETRAX_SESSION. \n $sql");
            throw new \Exception('Error while checking active session. '.$e->getMessage());
        }
    }
    
    public function insertRequest ($request)
    {
        $sql = "";
        try {
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);

            $msgTag      = $db->quote($request['messageTag']);
            $reqToken    = $db->quote($request['requestToken']);
            $reqStatus   = $db->quote($request['requestStatus']);
            $userUid     = $db->quote($request['ftUserId']);
            $msgContent  = $db->quote($request['messageContent']);

            $response = "";
            $sql   ="INSERT INTO $dbName.MOBILETRAX_REQUEST (MO_MESSAGE_SID, MESSAGE_TAG, REQUEST_TOKEN, REQUEST_STATUS,  FT_USER_UUID, MESSAGE_CONTENT)"
                    . " VALUES ($this->moMessageId, $msgTag, $reqToken, $reqStatus, unhex($userUid), $msgContent)";
            
            $result = $db->query($sql);
            
            if($result){
                $this->mobiletraxRequestId = $db->lastInsertId();
                QuickLogger::debug("insertRequest: completed. request data details: \n ".print_r($request , true));
            }
        } catch(\Exception $e) {
            QuickLogger::error("InsertRequest: Failed insert to MOBILETRAX_REQUEST. \n $sql");
            throw new \RuntimeException("InsertRequest :Failed querying database", 0, $e);
        }
    }
    
    public function insertResponse ($response)
    {
        $sql = "";
        try {
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            
            $request = isset($response['requestId']) ? $response['requestId'] : $this->mobiletraxRequestId;
            
            $msgTag     = $db->quote($response['messageTag']);
            $requestId  = $db->quote($request);
            $smsId      = $db->quote($response['smsDispatcherId']);
            $msgContent = $db->quote($response['messageContent']);

            $sql   ="INSERT INTO $dbName.MOBILETRAX_RESPONSE (MESSAGE_TAG, MOBILETRAX_REQUEST_SID, SMS_DISPATCHER_ID, MESSAGE_CONTENT)"
                    . " VALUES ($msgTag, $requestId, $smsId, $msgContent)";
            $result = $db->query($sql);
            if($result){
                QuickLogger::debug("insertResponse: completed. response data details: \n ".print_r($response , true));
                $response = $db->lastInsertId();
            }
        } catch(\Exception $e) {
            QuickLogger::error("InsertResponse: Failed insert to MOBILETRAX_RESPONSE. \n $sql");
            throw new \RuntimeException("InsertResponse :Failed querying database", 0, $e);
        }
    }
    
    public function generateSession ($request)
    {
        $sql = "";
        try {
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            
            /*
             * make sure there is no other session token for the same imei
             */
            $this->tearDownSession($request['ftUserId']);
                    
            $sessToken  = $this->mtRandStr();
            if($this->isActiveSession($sessToken)){
                return $this->generateSession($request);
            }
            
            $sessInsert = $db->quote($sessToken);
            $sessImei  = $db->quote($request['sessionImei']);
            $ftUserId  = $db->quote($request['ftUserId']);
            $requestId = $db->quote($this->mobiletraxRequestId);
            
            $sql   ="INSERT INTO $dbName.MOBILETRAX_SESSION "
                        . "(MOBILETRAX_SESSION_UUID, "
                        . "SESSION_TOKEN, "
                        . "SESSION_IMEI,"
                        . "FT_USER_UUID,"
                        . "AUTH_REQUEST_LOG,"
                        . "LAST_REQUEST_LOG,"
                        . "MODIFIED_AT)"
                    . " VALUES "
                        . "(unhex(replace(uuid(),'-','')), "
                        . "$sessInsert, "
                        . "$sessImei, "
                        . "unhex($ftUserId),"
                        . "$requestId,"
                        . "$requestId,"
                        . "NOW())";
            
            $db->query($sql);
            
        } catch(\Exception $e) {
            QuickLogger::error("generateSession: Failed insert to MOBILETRAX_SESSION. \n $sql");
            throw new \Exception('Insert session is failed. '.$e->getMessage());
        }
        
        return ($sessToken);
    }
    
    protected function mtRandStr () {
        $codecMap = new Gsm7CodecMap();
        
        $index = \mt_rand(0, self::MAX_CHAR_CODE); 
        if ($index == Gsm7CodecMap::ESC_CODE)  {
            ++$index;
        }
        
        return $codecMap->encode($index);
    }
    
    public function tearDownSession($ftUserUuid){
        $sql= "";
        try {
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW' , $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            
            $ftUserUuid = $db->quote($ftUserUuid);
            $now = $db->quote($now);
            
            $sql = "UPDATE $dbName.MOBILETRAX_SESSION SET INVALIDATED_AT = $now WHERE INVALIDATED_AT IS NULL AND FT_USER_UUID = unhex($ftUserUuid)";
            
            $result = $db->query($sql);
            if($result){
                return true;
            }
            else{
                return false;
            }
            
        } catch(\Exception $e) {
            QuickLogger::error("tearDownSession: Failed update MOBILETRAX_SESSION. \n $sql");
            throw new \RuntimeException("tearDownSession :Failed querying database", 0, $e);
        }
    }
    
    public function getAllNumberExpiredSession(){
        try {
            $expiredNumbers = array();
            
            $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
            $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);
            $dbSms = DbNameRef::getActualName(DbNameRef::SMS_DISTRIBUTOR);
        
            $UTC = new \DateTimeZone("UTC");
            $dateTime = new \DateTime('NOW' , $UTC);
            $now = $dateTime->format('Y-m-d H:i:s');
            
            $sql ="SELECT "
                    .   "MS.SESSION_TOKEN AS token,  "
                    .   "MS.LAST_REQUEST_LOG AS request, "
                    .   "MS.SESSION_IMEI AS imei, "
                    .   "hex(MS.FT_USER_UUID) AS ftUserUuid, "
                    .   "MM.SENDER_ADDRESS AS receiver,  "
                    .   "MM.RECEIVER_ADDRESS AS sender, "
                    .   "MM.RECEIVED_AT AS timeReceived "
                    .   "FROM  "
                    .   "$dbName.MOBILETRAX_SESSION AS MS  "
                    .   "LEFT JOIN "
                    .   "$dbName.MOBILETRAX_REQUEST AS MR  "
                    .   "ON (MS.LAST_REQUEST_LOG = MR.MOBILETRAX_REQUEST_SID) "
                    .   "LEFT JOIN "
                    .   "$dbSms.MO_MESSAGE AS MM  "
                    .   "ON(MR.MO_MESSAGE_SID = MM.MO_MESSAGE_SID) "
                    .   "WHERE INVALIDATED_AT IS NULL";
            
            $numbers = $db->query($sql)->fetchAll();
            
            foreach ($numbers as $val){
                //get expired time based on configuration
                $invalidDate = new \DateTime($val['timeReceived']);
                $invalidDate->setTimezone($UTC);

                //iso8601
                $sessionMaxIdle = new \DateInterval(MOBILETRAX_IDLE_TIME);

                $invalidDate->add($sessionMaxIdle);
                $expired = $invalidDate->format('Y-m-d H:i:s');
                
                //if expired store in array
                if($expired < $now){
                    $expiredNumbers[] = $val;
                }
            }
          
            return $expiredNumbers;
            
        } catch(\Exception $e) {
            QuickLogger::error("getAllNumberExpiredSession: Failed getting all number from expired session. \n $sql");
            throw new \Exception('Error while getting all number from expired session. '.$e->getMessage());
        }
    }
    
    public function getUserPassword($clientMsisdn){
        
        $db = ClassicApp::getPdo(DbNameRef::DIRECT_TRACKING);
        $dbName = DbNameRef::getActualName(DbNameRef::DIRECT_TRACKING);

        $query = "SELECT 
                    `PASSWORD` AS `password`
                FROM $dbName.AUTHORIZED_MSISDN 
                WHERE MSISDN=:msisdn ";

        $stmt = $db->prepare($query);
        $stmt->bindValue(':msisdn', $clientMsisdn);
        $stmt->execute();

        if (!$stmt->rowCount()) {
            return null;
        }
        
        $result = $stmt->fetch(\PDO::FETCH_ASSOC);
        
        return $result['password'];
    }
}

