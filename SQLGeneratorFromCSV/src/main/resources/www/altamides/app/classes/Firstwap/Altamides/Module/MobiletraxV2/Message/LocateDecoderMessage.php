<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : LocateDecoderMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class LocateDecoderMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000  #3500    Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\DecodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;


class LocateDecoderMessage extends DecodedMessageBase {
    
    const SIZE_OF_SESSION_ID       = 7;
    const SIZE_OF_REQUEST_ID       = 14;
    const SIZE_OF_TARGET_MSISDN_LENGTH  = 7;
    
    const CHAR_OF_CHECKSUM         = 1;
    const CHAR_OF_ISMSC            = 5;
    const CHAR_OF_QUERY_METHOD     = 3;
    const CHAR_OF_RESULT_OPTION    = 1;
    
    public static $locateFormat = array(
        'crc7' => self::CHAR_OF_CHECKSUM,
        'sessionId' => self::SIZE_OF_SESSION_ID,
        'requestId' => self::SIZE_OF_REQUEST_ID,
        'ismscNode' => self::CHAR_OF_ISMSC,
        'queryMethod' => self::CHAR_OF_QUERY_METHOD,
        'resultOption' => self::CHAR_OF_RESULT_OPTION,
        'targetMsisdnLength' => self::SIZE_OF_TARGET_MSISDN_LENGTH,
        'targetMsisdn' => null
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
            self::$locateFormat['crc7'] = $this->dataDecoder->appendString(self::$locateFormat['crc7']);
            
            /*
             * token
             */
            self::$locateFormat['sessionId'] = $this->dataDecoder->appendInt(self::$locateFormat['sessionId']);
            self::$locateFormat['requestId'] = $this->dataDecoder->appendInt(self::$locateFormat['requestId']);
            
            /*
             * message body
             */
            self::$locateFormat['ismscNode'] = $this->dataDecoder->appendString(self::$locateFormat['ismscNode']);
            self::$locateFormat['queryMethod'] = $this->dataDecoder->appendString(self::$locateFormat['queryMethod']);
            self::$locateFormat['resultOption'] = $this->dataDecoder->appendString(self::$locateFormat['resultOption']);
            self::$locateFormat['targetMsisdnLength'] = $this->dataDecoder->appendInt(self::$locateFormat['targetMsisdnLength']);
            self::$locateFormat['targetMsisdn'] = $this->dataDecoder->appendString(self::$locateFormat['targetMsisdnLength']);
            
            QuickLogger::debug(__CLASS__ . ': Request data: ' . print_r(self::$locateFormat, true));
            
            return self::$locateFormat;
            
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
