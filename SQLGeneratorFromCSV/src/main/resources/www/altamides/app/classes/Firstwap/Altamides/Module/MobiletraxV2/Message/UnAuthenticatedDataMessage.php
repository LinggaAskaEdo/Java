<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocateEcoMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-26
 * Initial Author   : Waluyo Sejati <waluyo.sejati@1rstwap.com>
 * Purpose          : Definition of class UnAuthenticatedDataMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-26   Sejati                    1.000.000           Initial Creation
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
 * Description of UnAuthenticatedDataMessage
 *
 * @author waluyo
 */
class UnAuthenticatedDataMessage  extends EncodedMessageBase {
    const SIZE_OF_SESSION_ID    = 7;
    const SIZE_OF_REQUEST_ID    = 14;
    const SIZE_OF_MESSAGE_ID    = 7;
    const SIZE_OF_LOGIN_STATUS  = 7;
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
            $this->dataEncoder->appendInt($this->data['messageId'], self::SIZE_OF_LOGIN_STATUS);

            QuickLogger::debug(__CLASS__ . ': Extra septet: ' . $this->dataEncoder->countExtraSeptet());

            // At this point there should not be any reminder more
            if ($this->dataEncoder->hasReminder()) {
                $reminder = dechex($this->dataEncoder->getReminderValue());
                throw new \RuntimeException('Unexpected reminder bits: ' . $reminder);
            }

            $escCount = $this->dataEncoder->countExtraSeptet();

            if ($escCount) {
                throw new Gsm7EscOverflowException($escCount);
            }

            $this->dataEncoder->appendEscCounter();
            $this->dataEncoder->padReminder();

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
