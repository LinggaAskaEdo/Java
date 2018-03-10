<?php

/*
 * Copyright 2015 1rstWAP. All rights reserved.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7CodecMap;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\ConverterUtil;

/**
 * Description of SmsLogger
 *
 * @author dwikky
 */
class SmsLogger {

    const TYPE_INCOMING = 'INCOMING';
    const TYPE_OUTGOING = 'OUTGOING';
    const ENCRYPTED = 'E';
    const DECRYPTED = 'D';

    protected $filePath;

    public function __construct($logFile) {
        if (!file_exists($logFile)) {
            \touch($logFile);
        }
        $this->filePath = (string) $logFile;
    }

    public function logIncoming($sender, $dest, $requestId, $encrypted, $decrypted) {
        $this->log(self::TYPE_INCOMING, $sender, $dest, $requestId, $encrypted, $decrypted);
    }

    public function logOutgoing($sender, $dest, $requestId, $encrypted, $decrypted) {
        $this->log(self::TYPE_OUTGOING, $sender, $dest, $requestId, $encrypted, $decrypted);
    }

    protected function log($type, $sender, $dest, $requestId, $encText, $decText) {
        $log = \date('[Y-m-d H:i:s]') . " $type $sender $dest <$requestId>\n";
        $encEncoding = \mb_detect_encoding($encText);
        $decEncoding = \mb_detect_encoding($decText);

        $log .= $this->dumpLength($encText, $encEncoding, self::ENCRYPTED) . "\n";
        $log .= $this->dumpString($encText);
        $log .= "\n";
        $log .= $this->dumpLength($decText, $decEncoding, self::DECRYPTED) . "\n";
        $log .= $this->dumpString($decText);
        $log .= "\n";

        $written = \file_put_contents($this->filePath, $log, \FILE_APPEND);
        if (!$written) {
            error_log(__METHOD__ . "Failed log SMS: $log");
            throw new \RuntimeException("Failed logging message");
        }
    }

    /**
     * 
     * @param string $string text
     * @param string $encoding String encoding
     * @param int $type self::DECRYPTED or self::ENCRYPTED
     * @return type
     */
    private function dumpLength($string, $encoding, $type) {
        $charCount = \mb_strlen($string, $encoding);
        $stringByteLength = \strlen($string);

        if ($type === self::DECRYPTED) { //when encrypting
            $decStringByte = $stringByteLength;
            $decStringBit = $decStringByte * 8;
            $stringBitMod7 = $decStringBit % 7;
            $padding = $stringBitMod7 ? (7 - $stringBitMod7) : 0;
            $encryptedGsm7Bit = $decStringBit + $padding;
            $encryptedGsm7Char = $encryptedGsm7Bit / 7;
            $esc = Gsm7CodecMap::ESC_REPLACEMENT;
        } else { //when decrypting
            $encryptedGsm7Char = $charCount;
            $encryptedGsm7Bit = $encryptedGsm7Char * 7;
            $padding = $encryptedGsm7Bit % 8;
            $decStringBit = $encryptedGsm7Bit - $padding;
            $decStringByte = $decStringBit / 8;
            $esc = ConverterUtil::ESC_REPLACEMENT;
        }

        $escCount = \mb_substr_count($string, $esc, $encoding);

        return "$charCount $stringByteLength $encoding $encryptedGsm7Bit $padding $escCount ";
    }

    protected function dumpString($string) {
        $byteArray = \unpack('C*', print_r($string, true));
        $line = '';
        $hex = '';
        $ascii = '';
        $eol = "\n";

        foreach ($byteArray as $byte) {
            if (\strlen($ascii) >= 20) {
                $line = $this->appendDumpLine($line, $hex, $ascii, $eol);
                $hex = '';
                $ascii = '';
            }

            $temp = \trim(\dechex($byte));
            while (\strlen($temp) < 2) {
                $temp = '0' . $temp;
            }
            $hex .= \substr($temp, -2);

            if ($byte >= 32 && $byte < 128) {
                $ascii .= \chr($byte);
            } else {
                $ascii .= '.';
            }
        }

        if (\strlen($ascii) <= 0) {
            return $line;
        }

        while (\strlen($ascii) < 20) {
            $hex .= '  ';
            $ascii .= ' ';
        }

        return $this->appendDumpLine($line, $hex, $ascii, $eol);
    }

    protected function appendDumpLine($line, $hex, $ascii, $eol) {
        $line .= \substr($hex, 0, 10) . ' ' .
                \substr($hex, 10, 10) . ' ' .
                \substr($hex, 20, 10) . ' ' .
                \substr($hex, 30, 10);

        $line .= ' -> ' . $ascii . $eol;
        return $line;
    }

}
