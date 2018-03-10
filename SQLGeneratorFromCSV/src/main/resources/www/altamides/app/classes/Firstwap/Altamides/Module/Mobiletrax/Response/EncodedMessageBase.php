<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : ResponseMessageBase.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class ResponseMessageBase
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-06   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Message;

use Firstwap\Altamides\Module\Mobiletrax\Codec\Gsm7Encoder;

/**
 * Description of ResponseMessageBase
 *
 * @author Setia Budi Halim
 */
abstract class EncodedMessageBase implements ResponseMessageInterface
{

    const ESC_OVERFLOW_RESULT = 'ENCODINGERROR';

    protected $data = array();
    protected $dataTemplate = array();
    protected $escCounter = 0;

    /**
     * Codec tool
     * @var Gsm7Encoder
     */
    protected $dataEncoder;

    public function __construct()
    {
        $this->dataEncoder = new Gsm7Encoder();
    }

    public function setData(array $data)
    {
        $this->data = $this->validateData($data);
    }

    /**
     * 
     * @param array $data
     * @return array
     */
    protected function validateData(array $data)
    {
        return $this->dataTemplate ? array_merge($this->dataTemplate, $data) : $data;
    }

}