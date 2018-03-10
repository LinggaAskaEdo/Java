<?php
/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : MessageHandler.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class MessageHandler
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

require_once __DIR__.'../../../../../../web/ftrack/lib_location_mo/smsnode.lib.php';

use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7Encoder;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7Decoder;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\Checksum;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\GSM7Sanitizer;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\GSM7Desanitizer;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\RC4;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\IBitArrayContainer;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\AbstractBinaryGsm0338EncodeBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\AbstractBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\BinaryReplaceGsm0338EncodeBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\BinaryGsm0338EncodeBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\ByteStoredBitArrayContainer;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\CharacterEncodeBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\HexStringBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Encoding\IStringBitArrayContainerAdapter;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LoginMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LocateFastMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LocateEcoMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LocateFullMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\CellDataMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\AutoConfigMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LoginDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\LocateDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\UnSupportedProtocolDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\CellDataDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\AutoConfigDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\SessionTeardownDecoderMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\SessionTimeoutMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\UnAuthenticatedDataMessage;
use Firstwap\Altamides\Module\MobiletraxV2\Message\UnSupportedProtocolMessage;
use Firstwap\Altamides\Module\MobiletraxV2\LoginStatusCode;
use Firstwap\Altamides\Module\MobiletraxV2\RequestStatus;

class MessageHandler extends MessageReceiver {
    
    const MOBILETRAX_PROTOCOL = 'mT2';

    const CHAR_OF_TAG = 4;
    const BIT_LENGTH = 7;
    
    const LOGIN_FAIL_INVALID_IP = 4;
    const LOGIN_FAIL_INVALID_IMEI = 3;
    const LOGIN_FAIL_INVALID_PASSWORD = 2;
    const LOGIN_FAIL_ACCOUNT_DISABLED = 1;
    /*
     * Unauthenticated Message Tag
     */
    const MESSAGE_TAG_UNAUTHENTICATED = 'mT2z';
    
    /*
     * Session Tear Down Request
     */
    const MESSAGE_TAG_SESSION_TEARDOWN_REQUEST = 'mT2T';
    /*
     * Session Timeout Response
     */
    const MESSAGE_TAG_SESSION_TIMEOUT = 'mT2t';
    /*
     * Unsupported Protocol
     */
    const MESSAGE_TAG_UNSUPPORTED_PROTOCOL = 'mT2p';
    /*
     * Login Action
     */
    const MESSAGE_TAG_LOGIN = 'mT2A';
    const MESSAGE_TAG_LOGIN_REQUEST = 'mT2a';
    /*
     * Auto Config Action
     */
    const MESSAGE_TAG_AUTO_CONFIG = 'mT2C';
    const MESSAGE_TAG_AUTO_CONFIG_REQUEST = 'mT2c';

    /*
     * Locate Action
     */
    const MESSAGE_TAG_LOCATE = 'mT2L';
    const MESSAGE_TAG_LOCATE_REQUEST = 'mT2l';
    const LOCATE_FAST_CODE = 'F';
    const LOCATE_ECO_CODE = 'E';
    const LOCATE_FULL_CODE = 'C';

    /*
     * Cell Data Action
     */
    const MESSAGE_TAG_CELL_DATA = 'mT2G';
    const MESSAGE_TAG_CELL_DATA_REQUEST = 'mT2g';
    /*
     * SMS Length
     */
    const SMS_FAST_LOCATE_LENGTH  = 160;
    const SMS_ECO_LOCATE_LENGTH   = 305;

    public static $REQUEST_TAGS = array(
        self::MESSAGE_TAG_LOGIN_REQUEST,
        self::MESSAGE_TAG_LOCATE_REQUEST,
        self::MESSAGE_TAG_CELL_DATA_REQUEST,
        self::MESSAGE_TAG_AUTO_CONFIG_REQUEST,
        self::MESSAGE_TAG_SESSION_TEARDOWN_REQUEST
    );

    public $textEncoding = 'UTF-8';
    
    public $encoder;
    public $decoder;

    public $RequestType;
    public $RequestBody;
    public $RequestStatus;
    public $ResponseType;
    
    public $parsedMessage;
    
    public $authStatus;
    
    protected $sessionId;
    
    protected $incomingMessage;
    protected $outgoingMessage;

    public function __construct ()
    {        
        $this->encoder = new Gsm7Encoder();
        $this->encoder->reset();
        
        $this->decoder = new Gsm7Decoder();
        $this->decoder->reset();
        
        $this->setSmsLogger();
    }
    
    public function setSessionId($session){
        $this->sessionId = $session;
    }
    
    public function getSessionId(){
        return $this->sessionId;
    }        
        
    /**
     * @return outgoing message
     */
    public function getOutgoingMessage(){
        return $this->outgoingMessage;
    }
    
    /**
     * @return incoming message
     */
    public function getIncomingMessage(){
        return $this->incomingMessage;
    }
    
    /**
     * set incoming message
     */
    public function setIncomingMessage($message){
        if(empty($message)){
            throw new \Exception(__METHOD__. 'Message is empty when setting decrypted incoming message');
        }
        $this->incomingMessage = $this->RequestType.$message;
    }
    
    /**
     * set outgoing message
     */
    public function setOutgoingMessage($message){
        if(empty($message)){
            throw new \Exception(__METHOD__. 'Message is empty when setting the message before encrypted');
        }
        $this->outgoingMessage = $this->ResponseType.$message;
    }
    
    /**
     * set auth message
     * @param type $status
     * @param type $requestToken
     */
    public function setAuthStatus($status){
        $this->authStatus = $status;
    }
       
    /**
     * for computed keys for encrypt/decrypt using rc4 algorithm
     */
    protected function generateKey(){
        if(($this->RequestType == self::MESSAGE_TAG_LOGIN_REQUEST || $this->RequestStatus == RequestStatus::ERROR_PROTOCOL) && empty($this->parsedMessage)){
            return sha1($this->RequestType.$this->MobiletraxBridge->getUserPassword());
        }
        else if(($this->ResponseType == self::MESSAGE_TAG_LOGIN || $this->RequestStatus == RequestStatus::ERROR_PROTOCOL) && !empty($this->parsedMessage)){
            return sha1($this->ResponseType.$this->MobiletraxBridge->getUserPassword());
        }

        return sha1($this->getSessionId().$this->MobiletraxBridge->getUserPassword());
    }


    public function encode(array $responseData , $key = '',$endShort=false){
        header('Content-type: text/html; charset=UTF-8');
        $message = "";
        $maxSmsLength = 0;
        if($this->ResponseType == self::MESSAGE_TAG_LOCATE){
            if(isset($this->parsedMessage['resultOption'])){
                switch ($this->parsedMessage['resultOption']){
                    case self::LOCATE_FAST_CODE:
                        $response = new LocateFastMessage();
                        $maxSmsLength = self::SMS_FAST_LOCATE_LENGTH;
                        break;
                    case self::LOCATE_ECO_CODE:
                        $response = new LocateEcoMessage();
                        $maxSmsLength = self::SMS_ECO_LOCATE_LENGTH;
                        break;
                    case self::LOCATE_FULL_CODE:
                        $response = new LocateFullMessage();
                        break;
                    default:
                        QuickLogger::debug("encode: Result option is invalid. <".$this->parsedMessage['resultOption'].">");
                        break;
                }
            }
        }
        else if($this->ResponseType == self::MESSAGE_TAG_LOGIN){
            $response = new LoginMessage();
        }
        else if($this->ResponseType == self::MESSAGE_TAG_CELL_DATA){
            $response = new CellDataMessage();
        }
        else if($this->ResponseType == self::MESSAGE_TAG_AUTO_CONFIG){
            $response = new AutoConfigMessage();
        }
        else if($this->ResponseType == self::MESSAGE_TAG_SESSION_TIMEOUT){
            $response = new SessionTimeoutMessage();
        }
        else if($this->ResponseType == self::MESSAGE_TAG_UNSUPPORTED_PROTOCOL){
            $response = new UnSupportedProtocolMessage();
        }
        
        //get message
        if($response !== null && is_object($response)){
            
            $response->setData($responseData);
            $message = $response->getMessage();
            
            $this->encoder->reset();
            
            /**
             * if login request, the session id is already encoded when we generate session. 
             * so we must add it manually to the generated encoded message result.
             */
            if($this->ResponseType == self::MESSAGE_TAG_LOGIN){
                $message = $this->parsedMessage['sessionId'].$message;
            }

            //Generate Checksum and encode
            $this->encoder->appendInt($this->getChecksum($message) , self::BIT_LENGTH);
            $checksum = $this->encoder->getResult();
            $this->encoder->reset();
            
            //combine with checksum
            $message = $checksum.$message;
            
            /**
            * set the message before encrypted
            */
           $this->setOutgoingMessage($message);

            if(MOBILETRAX_ENCRYPTION_ENABLED && ($this->ResponseType !== self::MESSAGE_TAG_UNSUPPORTED_PROTOCOL)){
                if($key == ''){
                    $key = $this->generateKey();
                }
                
                $encryption = new RC4();
                $message = $encryption->hexEncrypt( $key, $message );
                $bitStream = new ByteStoredBitArrayContainer();
                $reader = new HexStringBitArrayContainerAdapter($bitStream);
                $reader->setAsString($message);
            
                switch (ENCODING_METHOD) {
                    case 0: // Use the SDS GSM7 encryption
                        $writer = new BinaryGsm0338EncodeBitArrayContainerAdapter($bitStream);
                        break;
                    case 1: // Use GSM7 encryption, but replace <CR> <LF> <SP> to extended characters
                        $writer = new BinaryReplaceGsm0338EncodeBitArrayContainerAdapter($bitStream);
                        break;
                    case 2:
                        $writer = new CharacterEncodeBitArrayContainerAdapter($bitStream);
                        break;
                    default:
                        $writer = new BinaryGsm0338EncodeBitArrayContainerAdapter($bitStream);                    
                }
                $message = $writer->getAsString();
            }
            
            //combine with message tag
            $message = $this->ResponseType.$message;
        }
        if ($maxSmsLength > 0 && ($this->ResponseType == self::MESSAGE_TAG_LOCATE) && (strlen($message) > $maxSmsLength)) {
            switch ($this->parsedMessage['resultOption']) {
                case self::LOCATE_FAST_CODE:
                    if (strlen($responseData['region']) > 0) {
                        $tempRegion = explode(" ", $responseData['region'], -1);
                        $responseData['region'] = implode(" ", $tempRegion);
                    }
                    elseif (strlen($responseData['cellAddress1']) > 0) {
                        $tempAddress = explode(" ", $responseData['cellAddress1'], -1);
                        $responseData['cellAddress1'] = implode(" ", $tempAddress);
                    } 
                    break;
                case self::LOCATE_ECO_CODE:
                    if (strlen($responseData['region']) > 0) {
                        $tempRegion = explode(" ", $responseData['region'], -1);
                        $responseData['region'] = implode(" ", $tempRegion);
                    }elseif (strlen($responseData['cellAddress3']) > 0) {
                        $tempAddress = explode(" ", $responseData['cellAddress3'], -1);
                        $responseData['cellAddress3'] = implode(" ", $tempAddress);
                    } elseif (strlen($responseData['cellAddress2']) > 0) {
                        $tempAddress = explode(" ", $responseData['cellAddress2'], -1);
                        $responseData['cellAddress2'] = implode(" ", $tempAddress);
                    }  elseif (strlen($responseData['cellAddress1']) > 0) {
                        $tempAddress = explode(" ", $responseData['cellAddress1'], -1);
                        $responseData['cellAddress1'] = implode(" ", $tempAddress);
                    } 
                    break;
            }
                                   
           $message= $this->encode($responseData, $key);
            
        }elseif (!$endShort) {
            if(isset($this->parsedMessage['resultOption'])){
                switch ($this->parsedMessage['resultOption']) {
                    case self::LOCATE_FAST_CODE:
                        if (isset($responseData['region']) && strlen($responseData['region']) > 0) {
                            $responseData['region'] = $responseData['region'] . "...";
                        } elseif (isset($responseData['cellAddress1']) && strlen($responseData['cellAddress1']) > 0) {
                            $responseData['cellAddress1'] = $responseData['cellAddress1'] . '...';
                        }
                        break;
                    case self::LOCATE_ECO_CODE:
                        if (isset($responseData['region']) && strlen($responseData['region']) > 0) {
                            $responseData['region'] = $responseData['region'] . "...";
                        } elseif (isset($responseData['cellAddress3']) && strlen($responseData['cellAddress3']) > 0) {
                            $responseData['cellAddress3'] = $responseData['cellAddress3'] . '...';
                        } elseif (isset($responseData['cellAddress2']) && strlen($responseData['cellAddress2']) > 0) {
                            $responseData['cellAddress2'] = $responseData['cellAddress2'] . '...';
                        } elseif (isset($responseData['cellAddress1']) && strlen($responseData['cellAddress1']) > 0) {
                            $responseData['cellAddress1'] = $responseData['cellAddress3'] . '...';
                        }
                        break;
                }
                $message = $this->encode($responseData, $key, true);
            }

        }
        return $message ;
    
    }

    public function decode()
    {
        $isExpired = false;
        
        $this->splitMessage();        
        $userDetails = $this->MobiletraxBridge->getUserDetails();
        
        if(!in_array($this->RequestType, self::$REQUEST_TAGS)){
            if (stripos($this->RequestType, self::MOBILETRAX_PROTOCOL) === false) {
                QuickLogger::debug("decode: Unsupported Protocol is detected <$this->RequestType>");
                $this->RequestStatus = RequestStatus::ERROR_PROTOCOL;
            }else{
                QuickLogger::debug("decode: Message tag is not valid <$this->RequestType>");
                $this->RequestStatus = RequestStatus::ERROR;
                return false;
            }
        }
        
        if(($this->RequestType !== self::MESSAGE_TAG_LOGIN_REQUEST) && ($this->RequestStatus !== RequestStatus::ERROR_PROTOCOL)){
            $sessionId = $this->MobiletraxAuth->getSessionToken($userDetails['ftUserUuid']);
            
            if(!$sessionId['isActive']){
                $isExpired = true;
                
                $this->RequestStatus = RequestStatus::ERROR;
                $this->ResponseType = self::MESSAGE_TAG_SESSION_TIMEOUT;
            }
            $this->setSessionId($sessionId['token']);
        }

        if(MOBILETRAX_ENCRYPTION_ENABLED){

            $bitStream = new ByteStoredBitArrayContainer();            
            switch (ENCODING_METHOD) {
                case 0: // Use the SDS GSM7 encryption
                    $reader = new BinaryGsm0338EncodeBitArrayContainerAdapter($bitStream);
                    break;
                case 1: // Use GSM7 encryption, but replace <CR> <LF> <SP> to extended characters
                    $reader = new BinaryReplaceGsm0338EncodeBitArrayContainerAdapter($bitStream);
                    break;
                case 2:
                    $reader = new CharacterEncodeBitArrayContainerAdapter($bitStream);
                    break;
                default:
                    $reader = new BinaryGsm0338EncodeBitArrayContainerAdapter($bitStream);                    
            }
            $reader->setAsString($this->RequestBody);
            $writer = new HexStringBitArrayContainerAdapter($bitStream);
            $this->RequestBody = $writer->getAsString();                                
            $encryption     = new RC4();
            $this->RequestBody = $encryption->decrypt($this->generateKey() , $this->RequestBody );
            
            // show the content in binary view
            $reader = new BinaryGsm0338EncodeBitArrayContainerAdapter($bitStream);
            $reader->setAsString($this->RequestBody);
     
        }

        /**
         * set the message after decrypted whatever the result
         */
        $this->setIncomingMessage($this->RequestBody);
        
        if(!$this->isValidChecksum()){
            $this->RequestStatus = RequestStatus::ERROR_CHECKSUM;
            return false;
        }

        switch ($this->RequestType){
            case self::MESSAGE_TAG_LOGIN_REQUEST :
                $this->ResponseType = self::MESSAGE_TAG_LOGIN;
                $decoder = new LoginDecoderMessage();
                break;
            case self::MESSAGE_TAG_LOCATE_REQUEST :
                $this->ResponseType = self::MESSAGE_TAG_LOCATE;
                $decoder = new LocateDecoderMessage();
                break;
            case self::MESSAGE_TAG_CELL_DATA_REQUEST :
                $this->ResponseType = self::MESSAGE_TAG_CELL_DATA;
                $decoder = new CellDataDecoderMessage();
                break;
            case self::MESSAGE_TAG_AUTO_CONFIG_REQUEST :
                $this->ResponseType = self::MESSAGE_TAG_AUTO_CONFIG;
                $decoder = new AutoConfigDecoderMessage();
                break;
            case self::MESSAGE_TAG_SESSION_TEARDOWN_REQUEST :
                $decoder = new SessionTeardownDecoderMessage();
                break;
            default:
                if($this->RequestStatus == RequestStatus::ERROR_PROTOCOL){
                    QuickLogger::debug("decode: UnSupported Protocol.");
                    $this->ResponseType = self::MESSAGE_TAG_UNSUPPORTED_PROTOCOL;
                    $decoder = new UnSupportedProtocolDecoderMessage();
                }
                elseif($isExpired){
                    QuickLogger::debug("decode: Session is expired.");
                    $decoder = new SessionTeardownDecoderMessage();
                }
                else{
                    QuickLogger::debug("decode: message tag is not valid. <$this->RequestType>");
                    $this->RequestStatus = RequestStatus::ERROR;
                }
                break;
        }
        
        $binaryString = $this->convertStringToBinary($this->RequestBody);

        $decoder->setBinaryData($binaryString);
        $this->parsedMessage = $decoder->getMessage();

        return true;
    }
    
    /*
     * get response / doing request depends on message tag request
     */
    public function getRequest(){
        $MobiletraxBridge = $this->MobiletraxBridge;
        $userDetails = $MobiletraxBridge->getUserDetails();
        if(empty($this->parsedMessage)){
            QuickLogger::debug("getRequest: The message is not decoded and parsed yet");
            
            $this->RequestStatus = RequestStatus::ERROR_PARSE;
            return false;
        }
        if($this->RequestType !== self::MESSAGE_TAG_LOGIN_REQUEST){
            if($this->RequestStatus !== RequestStatus::ERROR_PROTOCOL){
                $this->RequestStatus = RequestStatus::VALID_REQUEST;
            }
            $this->saveRequest();
            
            if(($this->MobiletraxAuth->isNotActiveSession($this->parsedMessage['sessionId'])) && ($this->RequestType !== self::MESSAGE_TAG_LOGIN_REQUEST) && ($this->RequestStatus !== RequestStatus::ERROR_PROTOCOL)){
                $this->ResponseType = self::MESSAGE_TAG_SESSION_TIMEOUT;
                
                QuickLogger::debug("getRequest: Session is expired");
                return false;
            }
        }
        
        if($this->RequestType == self::MESSAGE_TAG_LOGIN_REQUEST){
            try{
                $isValid = true;
                $checkValidation = $MobiletraxBridge->validateUser($this->parsedMessage['password'],$this->parsedMessage['imei'],$this->parsedMessage['username']);
                if(!$checkValidation){
                    $this->RequestStatus = RequestStatus::ERROR_ACCOUNT;
                    $loginCode = $MobiletraxBridge->getLoginFailCode();
                    if($loginCode == self::LOGIN_FAIL_INVALID_PASSWORD){
                        $this->authStatus = LoginStatusCode::LOGIN_UNAUTHENTICATED;
                        $isValid = false;
                        return false;
                    }
                    if($loginCode == self::LOGIN_FAIL_INVALID_IMEI){
                        $this->authStatus = LoginStatusCode::INVALID_IMEI;
                        $isValid = false;
                        return false;
                    }
                    if($loginCode == self::LOGIN_FAIL_ACCOUNT_DISABLED){
                        $isValid = false;
                        $loginStatus = LoginStatusCode::LOGIN_ACCOUNT_DISABLED;
                    }
                }
                else if($checkValidation){
                    $loginStatus = LoginStatusCode::LOGIN_AUTHENTICATED;
                }
                else{
                    $isValid = false;
                    $this->RequestStatus = RequestStatus::ERROR;
                    $loginStatus = LoginStatusCode::LOGIN_TECHNICAL_ERROR;
                }
                
                $ismscCount = 0;

                if($isValid){
                    //Generate Session and store to database
                    $this->RequestStatus = RequestStatus::VALID_REQUEST;
                    $this->saveRequest();
                    
                    $data = array(
                        'sessionImei' => $userDetails['imei'],
                        'ftUserId' => $userDetails['ftUserUuid'],
                    );
                    
                    $this->parsedMessage['sessionId'] = $this->MobiletraxAuth->generateSession($data);

                    //Get ISMSC
                    $_SESSION['user_id'] = $this->MobiletraxBridge->getOwnerId();
                    $ismsc = array();
                    $smsNodeList = new \SMSNodeList(WEB_SERVICE_URL, WEB_SERVICE_USER, WEB_SERVICE_PASSWORD);
                    $nodeList = $smsNodeList->getList();
                    
                    foreach ($nodeList as $node=>$queryMethodNode) {
                        if($node == 'Automatic' || $node == ''){
                            $node = 'AUT';
                        }
                        $ismsc[] = str_pad($node, 5, " ", STR_PAD_RIGHT);
                    }
                    $ismscCount = count($ismsc);
                }
                else{
                    $this->saveRequest();
                }
                
                $responseField = array (
                    'messageTag' => $this->ResponseType , 
                    'crc7' => '',
                    'sessionId' => $this->parsedMessage['sessionId'],
                    'requestId' => $this->parsedMessage['requestId'],
                    'loginStatus' => $loginStatus,
                    'ismscCount'=> $ismscCount,
                );

                if(isset($ismsc)){
                    $responseField['ismsc'] = $ismsc;
                }
                return $responseField;
            }
            catch (\Exception $e){
                throw new \Exception('Error while validating user. '.$e->getMessage());
            }
            
        }
        else if($this->RequestType == self::MESSAGE_TAG_AUTO_CONFIG_REQUEST){
            //Get ISMSC
            $_SESSION['user_id'] = $this->MobiletraxBridge->getOwnerId();
            $ismsc = array();
            $smsNodeList = new \SMSNodeList(WEB_SERVICE_URL, WEB_SERVICE_USER, WEB_SERVICE_PASSWORD);
            $nodeList = $smsNodeList->getList();

            foreach ($nodeList as $node=>$queryMethodNode) {
                if($node == 'Automatic' || $node == ''){
                    $node = 'AUT';
                }
                $ismsc[] = str_pad($node, 5, " ", STR_PAD_RIGHT);
            }
            $ismscCount = count($ismsc);
            
            $responseField = array (
                'messageTag' => $this->ResponseType , 
                'crc7' => '',
                'sessionId' => $this->parsedMessage['sessionId'],
                'requestId' => $this->parsedMessage['requestId'],
                'ismscCount'=> $ismscCount,
            );

            if(isset($ismsc)){
                $responseField['ismsc'] = $ismsc;
            }

            return $responseField;
        }
        else if($this->RequestType == self::MESSAGE_TAG_LOCATE_REQUEST || $this->RequestType == self::MESSAGE_TAG_CELL_DATA_REQUEST){
            try{
                $responseField = array( 
                    'messageTag' => $this->ResponseType,
                    'sessionId' => $this->parsedMessage['sessionId'], 
                    'requestId' => $this->parsedMessage['requestId'], 
                    'resultOption' => $this->parsedMessage['resultOption']
                );
                
                
                $this->parsedMessage['targetMsisdn'] = (!isset($this->parsedMessage['targetMsisdn'])) ? $this->getClientMsisdn() : $this->parsedMessage['targetMsisdn'] ;
                
                if(\array_key_exists('ismscNode' , $this->parsedMessage) && isset($this->parsedMessage['ismscNode'])){
                    $this->parsedMessage['ismscNode'] = \trim($this->parsedMessage['ismscNode']);
                }
                
                if($this->parsedMessage['ismscNode'] == "AUT"){
                    $this->parsedMessage['ismscNode'] = "";
                }

                $locateResult = $MobiletraxBridge->locate($this->parsedMessage['targetMsisdn'], $this->parsedMessage['ismscNode'], $this->parsedMessage['queryMethod']);
                foreach ($locateResult as $key => $value) {
                    if($value === null) {
                        $locateResult[$key] = "" ;
                    }
                }
                $locateResult = array_merge($responseField , $locateResult);
                
                return $locateResult;
            }
            catch (\Exception $e){
                throw new \Exception('Error while generate response. '.$e->getMessage());
            }
        }
        else if($this->RequestType == self::MESSAGE_TAG_SESSION_TEARDOWN_REQUEST){
            try{
                $stat = true;
                if(!$this->MobiletraxAuth->tearDownSession($userDetails['ftUserUuid'])){
                    $stat = false;
                }
                
                return $stat;
            }
            catch (\Exception $e){
                throw new \Exception('Tear down request : Error while tiring down session. '.$e->getMessage());
            }
        }
        else {
            if($this->RequestStatus === RequestStatus::ERROR_PROTOCOL){
                $responseField = array (
                    'messageTag' => $this->ResponseType , 
                    'crc7' => '',
                    'sessionId' => $this->parsedMessage['sessionId'],
                    'requestId' => $this->parsedMessage['requestId'],
                    'protocolTag'=> self::MOBILETRAX_PROTOCOL,
                );
                return $responseField;
            }else{
                return $responseField = $this->unAuthenticatedMsgEncode();
            }
        }
    }
    
    /*
     * get checksum
     */
    public function getChecksum($content) {
        $checksum = new Checksum( $content );
        return $checksum->crc7();
    }
    
    public function convertEncoding($text){
        return mb_convert_encoding($text, mb_detect_encoding(), $this->textEncoding);
    }
    
    public function splitMessage(){
        $this->RequestType = mb_substr($this->getMessageContent(), 0 , self::CHAR_OF_TAG , $this->textEncoding);
        
        $messageBody = mb_substr($this->getMessageContent(), self::CHAR_OF_TAG , null , $this->textEncoding);
        $this->RequestBody = str_replace( array("\\n","\\r"),array("\n","\r"), $messageBody);
    }
    
    public function unAuthenticatedMsgEncode(){
        header('Content-type: text/html; charset=UTF-8');
        
        $this->ResponseType = self::MESSAGE_TAG_UNAUTHENTICATED;
        $loginStatus = (isset($this->authStatus)) ? $this->authStatus : LoginStatusCode::LOGIN_UNAUTHENTICATED;
        $token = array('sessionId'=> '1' , 'requestId' => '23');
        $token = (isset($this->parsedMessage) ? $this->parsedMessage : $token);
        
        $responseField = array (
            'messageTag' => $this->ResponseType , 
            'crc7' => '',
            'sessionId' => $token['sessionId'],
            'requestId' => $token['requestId'],
            'messageId' => $loginStatus,
        );

        $message = "";
        $this->ResponseMethod = new UnAuthenticatedDataMessage();

        //get message
        if($this->ResponseMethod !== null && is_object($this->ResponseMethod)){

            $this->ResponseMethod->setData($responseField);
            $message = $this->ResponseMethod->getMessage();
            $this->encoder->reset();

            //Generate Checksum and encode
            $this->encoder->appendInt($this->getChecksum($message) , self::BIT_LENGTH);
            
            $checksum = $this->encoder->getResult();
            $this->encoder->reset();
            
            //combine with checksum
            $message = $checksum.$message;
            
            //combine with message tag
            $message = $this->ResponseType.$message;
        }
        return $message ;
    }
    
    public function convertStringToBinary(){
        $binaryString = array();
        $length = mb_strlen($this->RequestBody, 'UTF-8');
        
        for($i=0 ; $i < $length ; $i++){
            $char = mb_substr( $this->RequestBody , $i , 1 , 'UTF-8');
            $mappedChar = $this->decoder->appendData($char);
            
            $toBinary = decbin ($mappedChar);
            $binary7bit = str_pad($toBinary , self::BIT_LENGTH , 0 , STR_PAD_LEFT);
            
            $binaryString[] =  $binary7bit;
        }
        
        return implode('' , $binaryString);
    }
    
    public function isValidChecksum() {
        $checksum = mb_substr($this->RequestBody , 1 , null , $this->textEncoding);
        $checksum = $this->getChecksum($checksum);
        $checksum = $this->encoder->appendData($checksum);
        
        $requestChecksum = mb_substr($this->RequestBody, 0 , 1  , 'UTF-8');

        if( $checksum !== $requestChecksum ){
            QuickLogger::debug("isValidChecksum: checksum is not same. <$checksum> vs <$requestChecksum>");
            return false;
        }

        return true;
    }
    
}
