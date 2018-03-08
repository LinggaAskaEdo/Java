<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : GenericErrorMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class GenericErrorMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Message;

/**
 * Description of GenericErrorMessage
 *
 * @author Setia Budi Halim
 */
class SimpleTextMessage extends EncodedMessageBase
{

    const DATA_FIELD = 'text';
    
    public function __construct()
    {
        parent::__construct();
        $this->dataTemplate = array (
            'text' => ''
        );
    }
    
    public function getMessage()
    {
        return $this->data['text'];
    }
    
    public function setText($text)
    {
        $data = array (
            self::DATA_FIELD => (string) $text
        );
        
        return $this->setData($data);
    }
    
}