<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : SmsResponse.php
 *  File Version     : 1.000.000
 *  Initial Creation : 13-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  13-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

use Firstwap\Altamides\Sms\TerminatedMessage;
use Firstwap\Altamides\Module\Mobiletrax\Message\EncodedMessageBase;

/**
 * Description of SmsResponse
 *
 * @author Setia Budi Halim
 */
class EncodedResponse extends Response
{   
    /**
     *
     * @var EncodedMessageBase
     */
    protected $message;
    
    public function send()
    {
        if (!$this->sender) {
            throw new \RuntimeException('No available SMS sender');
        }
        
        $smsText = $this->createSmsText();
        
        if (QuickLogger::isDebug()) {
            QuickLogger::debug(get_class($this).': SMS Text: '.$smsText->getContent());
        }
        
        $this->sender->setMessage($smsText);
        $this->sender->send();
    }
    
    public function setMessage(EncodedMessageBase $message)
    {
        $this->message = $message;
    }


    /**
     * Create SMS message object containing the response
     * @return \Firstwap\Altamides\Sms\TerminatedMessage
     */
    protected function createSmsText()
    {
        $smsText = new TerminatedMessage();
        $content = $this->createSignature() . $this->message->getMessage();
        
        $smsText->setContent($content);
        
        return $smsText;
    }

    /**
     * Generate message signature for the message, this includes MT header,
     * message type ID, request ID and session ID
     * @return type
     */
    protected function createSignature()
    {
        return self::SIGNATURE . $this->type . $this->request->getRequestId();
    }

}