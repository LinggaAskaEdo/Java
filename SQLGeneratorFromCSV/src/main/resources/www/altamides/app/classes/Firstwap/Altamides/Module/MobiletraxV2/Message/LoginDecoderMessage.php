<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : LoginDecoderMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class LoginDecoderMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000  #3499    Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\DecodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;


class LoginDecoderMessage extends DecodedMessageBase {
    
    const SIZE_OF_SESSION_ID       = 7;
    const SIZE_OF_REQUEST_ID       = 14;
    const SIZE_OF_IMEI             = 56;
    const SIZE_OF_USERNAME_LENGTH  = 7;
    const SIZE_OF_PASSWORD_LENGTH  = 7;
    
    const CHAR_OF_CHECKSUM         = 1;
    
    public static $loginFormat = array(
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
            self::$loginFormat['crc7'] = $this->dataDecoder->appendString(self::$loginFormat['crc7']);
            /*
             * token
             */
            self::$loginFormat['sessionId'] = $this->dataDecoder->appendInt(self::$loginFormat['sessionId']);
            self::$loginFormat['requestId'] = $this->dataDecoder->appendInt(self::$loginFormat['requestId']);
            
            /*
             * Message Body
             */
            self::$loginFormat['imei'] = (string) $this->dataDecoder->appendInt(self::$loginFormat['imei']);
            self::$loginFormat['usernameLength'] = $this->dataDecoder->appendInt(self::$loginFormat['usernameLength']) / self::SIZE_OF_USERNAME_LENGTH;
            self::$loginFormat['username'] = $this->dataDecoder->appendString(self::$loginFormat['usernameLength']);
            self::$loginFormat['passwordLength'] = $this->dataDecoder->appendInt(self::$loginFormat['passwordLength']) / self::SIZE_OF_USERNAME_LENGTH;
            self::$loginFormat['password'] = $this->dataDecoder->appendString(self::$loginFormat['passwordLength']);
            

            QuickLogger::debug(__CLASS__ . ': Request data: ' . print_r(self::$loginFormat, true));
            
            return self::$loginFormat;
            
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
