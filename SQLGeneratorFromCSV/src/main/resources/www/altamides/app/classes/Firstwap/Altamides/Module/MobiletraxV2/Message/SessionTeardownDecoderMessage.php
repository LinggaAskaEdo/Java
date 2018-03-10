<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : SessionTeardownDecoderMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-03-09
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class SessionTeardownDecoderMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-03-09   Dwikky Maradhiza Y        1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\DecodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;


class SessionTeardownDecoderMessage extends DecodedMessageBase {
    
    const SIZE_OF_SESSION_ID       = 7;
    const SIZE_OF_REQUEST_ID       = 14;
    
    const CHAR_OF_CHECKSUM         = 1;
    
    public static $sessionTeardownFormat = array(
        'crc7' => self::CHAR_OF_CHECKSUM,
        'sessionId' => self::SIZE_OF_SESSION_ID,
        'requestId' => self::SIZE_OF_REQUEST_ID,
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
            self::$sessionTeardownFormat['crc7'] = $this->dataDecoder->appendString(self::$sessionTeardownFormat['crc7']);
            /*
             * token
             */
            self::$sessionTeardownFormat['sessionId'] = $this->dataDecoder->appendInt(self::$sessionTeardownFormat['sessionId']);
            self::$sessionTeardownFormat['requestId'] = $this->dataDecoder->appendInt(self::$sessionTeardownFormat['requestId']);
            

            QuickLogger::debug(__CLASS__ . ': Request data: ' . print_r(self::$sessionTeardownFormat, true));
            
            return self::$sessionTeardownFormat;
            
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
