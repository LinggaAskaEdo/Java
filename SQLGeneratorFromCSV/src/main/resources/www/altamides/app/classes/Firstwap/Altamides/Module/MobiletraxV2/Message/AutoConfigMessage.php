<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : AutoConfigMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class AutoConfigMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2014-12-05   Dwikky Maradhiza Y        1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\EncodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\AgeOfLocationMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\LocationApiErrorCodeMapper;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\LocationQualityMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\PhoneStatusMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7EscOverflowException;

/**
 * Description of AutoConfigMessage
 *
 * @author Dwikky Maradhiza Yudakusuma
 */
class AutoConfigMessage extends EncodedMessageBase
{
    const SIZE_OF_SESSION_ID    = 7;
    const SIZE_OF_REQUEST_ID    = 14;
    const SIZE_OF_NUMBER_ISMSC  = 7;
    
    const CHAR_OF_ISMSC  = 5;
    
    /**
     * 
     * @return type
     * @throws \RuntimeException
     * @throws Gsm7EscOverflowException
     */
    public function getMessage()
    {
        
        try {
            $this->dataEncoder->reset();
            QuickLogger::debug(__CLASS__ . ': Response data: ' . print_r($this->data, true));
            
            // token
            $this->dataEncoder->appendInt($this->data['sessionId'], self::SIZE_OF_SESSION_ID);
            $this->dataEncoder->appendInt($this->data['requestId'], self::SIZE_OF_REQUEST_ID);
            
            $this->dataEncoder->appendInt($this->data['ismscCount'], self::SIZE_OF_NUMBER_ISMSC);

            if(is_array($this->data['ismsc']) && !empty($this->data['ismsc'])){
                foreach ($this->data['ismsc'] as $value){
                    $this->dataEncoder->appendString($value, self::CHAR_OF_ISMSC);
                }
            }
         
            QuickLogger::debug(__CLASS__ . ': Extra septet: ' . $this->dataEncoder->countExtraSeptet());

            return $this->dataEncoder->getResult();
        } catch (Gsm7EscOverflowException $e) {
            QuickLogger::error("Esc counter error:\nEncoded:" .
                    $this->dataEncoder->getResult() . "\nError:$e");
            return self::ESC_OVERFLOW_RESULT;
        } catch (\Exception $e) {
            QuickLogger::error("An error has occured while encoding result: $e");
        }
    }

}
