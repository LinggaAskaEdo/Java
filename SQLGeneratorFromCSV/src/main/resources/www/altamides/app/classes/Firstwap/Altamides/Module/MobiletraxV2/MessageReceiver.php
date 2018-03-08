<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : MessageReceiver.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class MessageReceiver
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

use Firstwap\Altamides\Module\MobiletraxV2\Codec\MobiletraxAuth;

class MessageReceiver {
   
    /**
     *
     * @var MobiletraxBridge
     */
    public $MobiletraxBridge;    // Altamides Mobiletrax bridge
    /**
     *
     * @var MobiletraxAuth
     */
    public $MobiletraxAuth;    // Altamides Mobiletrax auth

    /**
     *
     * @var MessageHandler
     */
    public $MessageHandler;
    
    /**
     *
     * @var SmsLogger
     */
    protected $smsLogger;
    
    protected $clientMsisdn;
    protected $virtualNumber;
    protected $messageContent;

    const MESSAGE_CHARSET = 'SMS-GSM-7';
    
    public function setInstance($bridge) {
        $this->MobiletraxBridge = $bridge;
        $this->MobiletraxAuth = new MobiletraxAuth();

        $this->MobiletraxAuth->moMessageId = $this->MobiletraxBridge->getMessageId();
    }
    
    /**
     * 
     * @return 
     */
    public function setSmsLogger() {
        $this->smsLogger = new SmsLogger(\MOBILETRAX_MESSAGE_LOG);
    }
    
    /**
     * 
     * @return SmsLogger
     */
    public function getSmsLogger() {
        return $this->smsLogger;
    }

    public function setClientMsisdn($msisdn) {
        $this->clientMsisdn = $msisdn;
    }

    public function setVirtualNumber($msisdn) {
        $this->virtualNumber = $msisdn;
    }

    public function setMessageContent($content) {
        $this->messageContent = $content;
    }

    public function getClientMsisdn() {
        return $this->clientMsisdn;
    }

    public function getVirtualNumber() {
        return $this->virtualNumber;
    }

    public function getMessageContent() {
        return $this->messageContent;
    }

    public function saveRequest() {
        $handler = $this->MessageHandler;
        $userDetails = $this->MobiletraxBridge->getUserDetails();
        $isSocket = $this->MobiletraxBridge->getIsSocketRequest();
	$requestToken = $handler->parsedMessage['sessionId'] . $handler->parsedMessage['requestId'];
        $data = array(
            'messageTag' => $handler->RequestType,
            'requestToken' => $requestToken != "" ? $requestToken : (isset($_GET['requestToken']) ? $_GET['requestToken'] : ""),
            'requestStatus' => $handler->RequestStatus,
            'ftUserId' => $userDetails['ftUserUuid'],
            'messageContent' => $this->MobiletraxBridge->getRequestSms(),
        );

        $this->MobiletraxAuth->insertRequest($data);
    }

    public function processMessage() {
        //decode sms content
        $handler = $this->MessageHandler;
        $decode = $handler->decode();

        $requestId = !empty($handler->parsedMessage['requestId']) ? $handler->parsedMessage['requestId'] : 0;

        if (!$decode) {
            $this->saveRequest();
            $text = $handler->unAuthenticatedMsgEncode();
            QuickLogger::debug("decode: Failed to decode the request message");
        } else {
            //write log incoming message
            $this->getSmsLogger()->logIncoming($this->getClientMsisdn(), $this->getVirtualNumber(), $requestId, $this->getMessageContent(), $handler->getIncomingMessage());

            $requestData = $handler->getRequest();

            if (!$requestData) {
                $this->saveRequest();
                if ($handler->ResponseType == MessageHandler::MESSAGE_TAG_SESSION_TIMEOUT) {
                    $handler->setSessionId($handler->parsedMessage['sessionId']);
                    $text = $handler->encode($handler->parsedMessage);
                } else {
                    $text = $handler->unAuthenticatedMsgEncode();
                    QuickLogger::debug("getRequest: Request is not valid");
                }
            } elseif ($handler->RequestType !== MessageHandler::MESSAGE_TAG_SESSION_TEARDOWN_REQUEST) {
                //if request is valid then update request log 
                $this->MobiletraxAuth->updateLogRequest($handler->parsedMessage['sessionId']);

                $text = $this->MessageHandler->encode($requestData);
            }
        }

        if (isset($text) && $text !== '') {
            $isSocket = $this->MobiletraxBridge->getIsSocketRequest();

            $smsId = !$isSocket ? $this->MobiletraxBridge->sendReplySms($text) : 0;
            
            //write log outgoing message
            $this->getSmsLogger()->logOutgoing($this->getVirtualNumber() ,$this->getClientMsisdn() ,$requestId, $text, $handler->getOutgoingMessage());
                                  
            /*
             * insert response sms
             */            
            $data = array(
                'messageTag' => $handler->ResponseType,
                'messageContent' => $text,
                'smsDispatcherId' => $smsId
            );

            $this->MobiletraxAuth->insertResponse($data);
        }

        return true;
    }

}
