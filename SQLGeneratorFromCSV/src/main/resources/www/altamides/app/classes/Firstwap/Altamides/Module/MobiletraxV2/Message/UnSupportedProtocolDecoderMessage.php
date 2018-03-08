<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : UnSupportedProtocolDecoderMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2016-08-25
 * Initial Author   : Waluyo Sejati <waluyo.sejati@1rstwap.com>
 * Purpose          : Definition of class UnSupportedProtocolDecoderMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2016-08-25   Waluyo Sejati             1.000.000  #13918    Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\DecodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;


class UnSupportedProtocolDecoderMessage extends DecodedMessageBase {
    
    const SIZE_OF_SESSION_ID       = 7;
    const SIZE_OF_REQUEST_ID       = 14;
    const SIZE_OF_IMEI             = 56;
    const SIZE_OF_USERNAME_LENGTH  = 7;
    const SIZE_OF_PASSWORD_LENGTH  = 7;
    
    const CHAR_OF_CHECKSUM         = 1;
    
    public static $msgFormat = array(
        'crc7' => self::CHAR_OF_CHECKSUM,
        'sessionId' => self::SIZE_OF_SESSION_ID,
        'requestId' => self::SIZE_OF_REQUEST_ID,
        'imei' => self::SIZE_OF_IMEI,
        'usernameLength' => self::SIZE_OF_USERNAME_LENGTH,
        'username' => null,
        'passwordLength' => self::SIZE_OF_PASSWORD_LENGTH,
        'password' => null,
    );
    
    /**
     * 
     * @return type
     * @throws \RuntimeException
     * @throws Gsm7EscOverflowException
     */
    public function getMessage()
    {
        try {
            $this->dataDecoder->setBinaryData($this->binaryData);
            $this->dataDecoder->reset();
            
            /*
             * crc7
             */
            self::$msgFormat['crc7'] = $this->dataDecoder->appendString(self::$msgFormat['crc7']);
            /*
             * token
             */
            self::$msgFormat['sessionId'] = $this->dataDecoder->appendInt(self::$msgFormat['sessionId']);
            self::$msgFormat['requestId'] = $this->dataDecoder->appendInt(self::$msgFormat['requestId']);
            
            /*
             * Message Body
             */
            self::$msgFormat['imei'] = (string) $this->dataDecoder->appendInt(self::$msgFormat['imei']);
            self::$msgFormat['usernameLength'] = $this->dataDecoder->appendInt(self::$msgFormat['usernameLength']) / self::SIZE_OF_USERNAME_LENGTH;
            self::$msgFormat['username'] = $this->dataDecoder->appendString(self::$msgFormat['usernameLength']);
            self::$msgFormat['passwordLength'] = $this->dataDecoder->appendInt(self::$msgFormat['passwordLength']) / self::SIZE_OF_USERNAME_LENGTH;
            self::$msgFormat['password'] = $this->dataDecoder->appendString(self::$msgFormat['passwordLength']);
            

            QuickLogger::debug(__CLASS__ . ': Request data: ' . print_r(self::$msgFormat, true));
            
            return self::$msgFormat;
            
        } catch (Gsm7EscOverflowException $e) {
            QuickLogger::error("Esc counter error:\nDecoded:" .
                    $this->dataDecoder->getResult() . "\nError:$e");
            return self::ESC_OVERFLOW_RESULT;
        } catch (\Exception $e) {
            QuickLogger::error("An error has occured while decoding result: $e");
        }
    }
}

?>
