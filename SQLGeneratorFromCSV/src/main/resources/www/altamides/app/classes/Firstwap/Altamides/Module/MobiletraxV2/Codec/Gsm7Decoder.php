<?php
/*
 * (c) 2014 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Mobiletrax V2
 * Version          : 2.0 SP19
 * Filename         : GSM7Decoder.php
 * Fileversion      : 2.000.001
 * Initial Creation : 
 * Initial Author   : Dwikky Maradhiza
 * Purpose          : 
 * ================================================
 * Initial Request  : #
 * ================================================
 * Change Log
 * Date         Author              Version     Request     Comment    
 * 2015-02-23   Dwikky Maradhiza    2.osp21     #4116       initial creation.
 *
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;

class GSM7Decoder extends Gsm7CodecBase {
    protected $decoded      = '';
    protected $textEncoding = 'UTF-8';
    protected $reminderValue = 0;
    protected $binaryData;


    public function getResult()
    {
        if (QuickLogger::isDebug()) {
            QuickLogger::debug(__CLASS__.': Result in binary = ' . $this->getResultAsStringBinary());
        }
        return $this->decoded;
    }
    
    public function setBinaryData($data)
    {
        $this->binaryData = $data;
    }

    public function getResultAsStringBinary()
    {
        return $this->mapper->decodeToStringBinary($this->encoded);
    }

    public function reset()
    {
        $this->decoded = '';
    }

    public function appendInt($length)
    {
        //empty data
        if ($length < 1) {
            QuickLogger::debug("Decoding empty data: LENGTH=$length, DATA=$data");
            return;
        }

        if ($length > self::INT64_SIZE) {
            throw new \InvalidArgumentException("Too large binary, size=$length, max={".self::INT64_SIZE."}");
        }
        $result = bindec(substr( $this->binaryData , $this->reminderValue , $length )) ;
        $this->reminderValue += $length;
        
        $this->decoded .= $result;
        
        return $result;
    }

    /**
     * Decode string data
     * @param int $length The length of this field (number of characters)
     * @return string Encoded form
     */
    public function appendString($length)
    {
        $result = array();
        
        if (!$length) {
            return;
        }
        
        $binaryString = substr($this->binaryData , $this->reminderValue , ($length * Gsm7CodecMap::CODE_SIZE));
        $binarySplit = str_split($binaryString , Gsm7CodecMap::CODE_SIZE);

        foreach ($binarySplit as $binary){
            
            if($binary < Gsm7CodecMap::CODE_SIZE){
                $binary = str_pad($binary, Gsm7CodecMap::CODE_SIZE , '0', STR_PAD_RIGHT);
            }
            
            $decValue = bindec($binary);
            $result[] = $this->mapper->encode($decValue);
        }

        $this->reminderValue += ($length * Gsm7CodecMap::CODE_SIZE);
        $result = implode('', $result);
        
        $this->decoded .= $result;
        return $result;
    }

    public function appendMncPair($mcc, $mnc)
    {
        $mncPair = $mcc . \str_pad($mnc, 3, '0', \STR_PAD_LEFT);
        $this->appendInt(ltrim($mncPair, '0'), self::SIZE_OF_MNC_PAIR);
    }
    
    public function appendData($data){
        return $this->mapper->decode($data);
    }
}
?>
