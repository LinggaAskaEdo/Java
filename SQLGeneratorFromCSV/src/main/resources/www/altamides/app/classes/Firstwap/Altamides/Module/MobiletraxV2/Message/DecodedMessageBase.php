<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : DecodedMessageBase.php
 * File Version     : 2.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class DecodedMessageBase
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza Y        2.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7Decoder;

/**
 * Description of ResponseMessageBase
 *
 * @author Dwikky Maradhiza Y
 */
abstract class DecodedMessageBase implements ResponseMessageInterface
{

    const ESC_OVERFLOW_RESULT = 'DECODINGERROR';

    protected $data = array();
    protected $dataTemplate = array();
    protected $escCounter = 0;
    protected $binaryData;

    /**
     * Codec tool
     * @var Gsm7Encoder
     */
    protected $dataDecoder;

    public function __construct()
    {
        $this->dataDecoder = new Gsm7Decoder();
    }

    public function setData(array $data)
    {
        $this->data = $this->validateData($data);
    }
    
    public function setBinaryData($data){
        $this->binaryData = $data;
    }
    
    public function getData(){
        return $this->data;
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