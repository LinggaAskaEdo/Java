<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : CellDataDecoderMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class CellDataDecoderMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\DecodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;


class CellDataDecoderMessage extends DecodedMessageBase {
    
    const SIZE_OF_SESSION_ID       = 7;
    const SIZE_OF_REQUEST_ID       = 14;
    const SIZE_OF_MNC_PAIR         = 20;
    const SIZE_OF_LAC              = 16;
    const SIZE_OF_CELL_ID          = 16;
    
    const CHAR_OF_CHECKSUM         = 1;
    
    public static $cellDataFormat = array(
        'crc7' => self::CHAR_OF_CHECKSUM,
        'sessionId' => self::SIZE_OF_SESSION_ID,
        'requestId' => self::SIZE_OF_REQUEST_ID,
        'mncPair' => self::SIZE_OF_MNC_PAIR,
        'lac' => self::SIZE_OF_LAC,
        'cellId' => self::SIZE_OF_CELL_ID
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
            self::$cellDataFormat['crc7'] = $this->dataDecoder->appendString(self::$cellDataFormat['crc7']);
            /*
             * token
             */
            self::$cellDataFormat['sessionId'] = $this->dataDecoder->appendInt(self::$cellDataFormat['sessionId']);
            self::$cellDataFormat['requestId'] = $this->dataDecoder->appendInt(self::$cellDataFormat['requestId']);
            /*
             * message body
             */
            self::$cellDataFormat['mncPair'] = $this->dataDecoder->appendInt(self::$cellDataFormat['mncPair']);
            self::$cellDataFormat['lac'] = $this->dataDecoder->appendInt(self::$cellDataFormat['lac']);
            self::$cellDataFormat['cellId'] = $this->dataDecoder->appendInt(self::$cellDataFormat['cellId']);
            
            QuickLogger::debug(__CLASS__ . ': Request data: ' . print_r(self::$cellDataFormat, true));
            
            return self::$cellDataFormat;
            
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
