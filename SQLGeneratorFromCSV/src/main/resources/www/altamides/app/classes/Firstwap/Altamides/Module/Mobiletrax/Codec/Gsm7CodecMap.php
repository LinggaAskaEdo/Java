<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : Gsm7Mapper.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-07
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class Gsm7Mapper
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-07   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Codec;

use Firstwap\Altamides\Module\Mobiletrax\QuickLogger;

/**
 * Description of Gsm7Mapper
 *
 * @author Setia Budi Halim
 */
class Gsm7CodecMap
{

    const ESC_REPLACEMENT = '^';

    /**
     * GSM 7 ESC code
     */
    const ESC_CODE = 0x1b;

    /**
     * Is '?'
     */
    const ERROR_CODE    = 0x3f;
    const ERROR_CHAR    = '?';
    const CODE_SIZE     = 7;
    const CODE_MASK     = 0x7f;
    const EXT_CODE_SIZE = 14;
    const EXT_CODE_MASK = 0x1b00;

    /**
     * See http://www.dreamfabric.com/sms/default_alphabet.html
     * With some modifications. Inverse of septetToUtf8Table
     * Please note that 0x1b is encoded as '\' which actually is 0x1b2f
     * @var array
     */
    protected static $encodingTable = array(
        0x00 => '@', 0x01 => '£', 0x02 => '$', 0x03 => '¥', 0x04 => 'è', 0x05 => 'é', 0x06 => 'ù', 0x07 => 'ì',
        0x08 => 'ò', 0x09 => 'Ç', 0x0a => "\n", 0x0b => 'Ø', 0x0c => 'ø', 0x0d => "\r", 0x0e => 'Å', 0x0f => 'å',
        0x10 => 'Δ', 0x11 => '_', 0x12 => 'Φ', 0x13 => 'Γ', 0x14 => 'Λ', 0x15 => 'Ω', 0x16 => 'Π', 0x17 => 'Ψ',
        0x18 => 'Σ', 0x19 => 'Θ', 0x1a => 'Ξ', 0x1b => self::ESC_REPLACEMENT, 0x1c => 'Æ', 0x1d => 'æ', 0x1e => 'ß', 0x1f => 'É',
        0x20 => ' ', 0x21 => '!', 0x22 => '"', 0x23 => '#', 0x24 => '¤', 0x25 => '%', 0x26 => '&', 0x27 => '\'',
        0x28 => '(', 0x29 => ')', 0x2a => '*', 0x2b => '+', 0x2c => ',', 0x2d => '-', 0x2e => '.', 0x2f => '/',
        0x30 => '0', 0x31 => '1', 0x32 => '2', 0x33 => '3', 0x34 => '4', 0x35 => '5', 0x36 => '6', 0x37 => '7',
        0x38 => '8', 0x39 => '9', 0x3a => ':', 0x3b => ';', 0x3c => '<', 0x3d => '=', 0x3e => '>', 0x3f => '?',
        0x40 => '¡', 0x41 => 'A', 0x42 => 'B', 0x43 => 'C', 0x44 => 'D', 0x45 => 'E', 0x46 => 'F', 0x47 => 'G',
        0x48 => 'H', 0x49 => 'I', 0x4a => 'J', 0x4b => 'K', 0x4c => 'L', 0x4d => 'M', 0x4e => 'N', 0x4f => 'O',
        0x50 => 'P', 0x51 => 'Q', 0x52 => 'R', 0x53 => 'S', 0x54 => 'T', 0x55 => 'U', 0x56 => 'V', 0x57 => 'W',
        0x58 => 'X', 0x59 => 'Y', 0x5a => 'Z', 0x5b => 'Ä', 0x5c => 'Ö', 0x5d => 'Ñ', 0x5e => 'Ü', 0x5f => '§',
        0x60 => '¿', 0x61 => 'a', 0x62 => 'b', 0x63 => 'c', 0x64 => 'd', 0x65 => 'e', 0x66 => 'f', 0x67 => 'g',
        0x68 => 'h', 0x69 => 'i', 0x6a => 'j', 0x6b => 'k', 0x6c => 'l', 0x6d => 'm', 0x6e => 'n', 0x6f => 'o',
        0x70 => 'p', 0x71 => 'q', 0x72 => 'r', 0x73 => 's', 0x74 => 't', 0x75 => 'u', 0x76 => 'v', 0x77 => 'w',
        0x78 => 'x', 0x79 => 'y', 0x7a => 'z', 0x7b => 'ä', 0x7c => 'ö', 0x7d => 'ñ', 0x7e => 'ü', 0x7f => 'à'
    );

    /**
     *
     * @var array
     */
    protected static $decodingTable = array(
        '@'  => 0x00, '£'  => 0x01, '$'  => 0x02, '¥'  => 0x03, 'è'  => 0x04, 'é'  => 0x05, 'ù'  => 0x06, 'ì'  => 0x07,
        'ò'  => 0x08, 'Ç'  => 0x09, "\n" => 0x0a, 'Ø'  => 0x0b, 'ø'  => 0x0c, "\r" => 0x0d, 'Å'  => 0x0e, 'å'  => 0x0f,
        'Δ'  => 0x10, '_'  => 0x11, 'Φ'  => 0x12, 'Γ'  => 0x13, 'Λ'  => 0x14, 'Ω'  => 0x15, 'Π'  => 0x16, 'Ψ'  => 0x17,
        'Σ'  => 0x18, 'Θ'  => 0x19, 'Ξ'  => 0x1a, /* ESC, */ 'Æ'  => 0x1c, 'æ'  => 0x1d, 'ß'  => 0x1e, 'É'  => 0x1f,
        ' '  => 0x20, '!'  => 0x21, '"'  => 0x22, '#'  => 0x23, '¤'  => 0x24, '%'  => 0x25, '&'  => 0x26, '\'' => 0x27,
        '('  => 0x28, ')'  => 0x29, '*'  => 0x2a, '+'  => 0x2b, ','  => 0x2c, '-'  => 0x2d, '.'  => 0x2e, '/'  => 0x2f,
        '0'  => 0x30, '1'  => 0x31, '2'  => 0x32, '3'  => 0x33, '4'  => 0x34, '5'  => 0x35, '6'  => 0x36, '7'  => 0x37,
        '8'  => 0x38, '9'  => 0x39, ':'  => 0x3a, ';'  => 0x3b, '<'  => 0x3c, '='  => 0x3d, '>'  => 0x3e, '?'  => 0x3f,
        '¡'  => 0x40, 'A'  => 0x41, 'B'  => 0x42, 'C'  => 0x43, 'D'  => 0x44, 'E'  => 0x45, 'F'  => 0x46, 'G'  => 0x47,
        'H'  => 0x48, 'I'  => 0x49, 'J'  => 0x4a, 'K'  => 0x4b, 'L'  => 0x4c, 'M'  => 0x4d, 'N'  => 0x4e, 'O'  => 0x4f,
        'P'  => 0x50, 'Q'  => 0x51, 'R'  => 0x52, 'S'  => 0x53, 'T'  => 0x54, 'U'  => 0x55, 'V'  => 0x56, 'W'  => 0x57,
        'X'  => 0x58, 'Y'  => 0x59, 'Z'  => 0x5a, 'Ä'  => 0x5b, 'Ö'  => 0x5c, 'Ñ'  => 0x5d, 'Ü'  => 0x5e, '§'  => 0x5f,
        '¿'  => 0x60, 'a'  => 0x61, 'b'  => 0x62, 'c'  => 0x63, 'd'  => 0x64, 'e'  => 0x65, 'f'  => 0x66, 'g'  => 0x67,
        'h'  => 0x68, 'i'  => 0x69, 'j'  => 0x6a, 'k'  => 0x6b, 'l'  => 0x6c, 'm'  => 0x6d, 'n'  => 0x6e, 'o'  => 0x6f,
        'p'  => 0x70, 'q'  => 0x71, 'r'  => 0x72, 's'  => 0x73, 't'  => 0x74, 'u'  => 0x75, 'v'  => 0x76, 'w'  => 0x77,
        'x'  => 0x78, 'y'  => 0x79, 'z'  => 0x7a, 'ä'  => 0x7b, 'ö'  => 0x7c, 'ñ'  => 0x7d, 'ü'  => 0x7e, 'à'  => 0x7f,
        // Extended characters
        '|'  => 0x1b40, '^'  => 0x1b14, '€'  => 0x1b65, '{'  => 0x1b28,
        '}'  => 0x1b29, '['  => 0x1b3c, '~'  => 0x1b3d, ']'  => 0x1b3e,
        '\\' => 0x1b2f
    );

    /**
     *
     * @var array
     */
    protected static $extendedCharTable = array(
        '|'  => 0x1b40, '^'  => 0x1b14, '€'  => 0x1b65, '{'  => 0x1b28,
        '}'  => 0x1b29, '['  => 0x1b3c, '~'  => 0x1b3d, ']'  => 0x1b3e,
        '\\' => 0x1b2f
    );

    public function isExtChar($char)
    {
        return isset(self::$extendedCharTable[(string) $char]);
    }

    public function isExtCodepoint($septet)
    {
        return ($septet & self::EXT_CODE_MASK) > 0;
    }

    /**
     * 
     * @param string $char
     * @return int
     */
    public function getCodepoint($char)
    {

        if (isset(self::$decodingTable[$char])) {
            return self::$decodingTable[$char];
        }

        return self::ERROR_CODE;
    }

    /**
     * 
     * @param string $char
     * @return int
     * @throws \RuntimeException
     */
    public function decode($char)
    {
        if ($char === self::ESC_REPLACEMENT) {
            return self::ESC_CODE;
        }

        if (isset(self::$decodingTable[$char])) {
            return self::$decodingTable[$char];
        }

        throw new \RuntimeException("Unrecognised character '$char'");
    }

    /**
     * 
     * @param int $septet
     * @return string
     * @throws \OutOfBoundsException
     */
    public function encode($septet)
    {
        $septet = self::CODE_MASK & $septet;
        if (isset(self::$encodingTable[$septet])) {
            return self::$encodingTable[$septet];
        }

        $septetHex = dechex($septet);
        $septetBin = decbin($septet);
        throw new \OutOfBoundsException("The value is in encoding table: DEC = $septet, HEX = 0x$septetHex, BIN = $septetBin.");
    }

    public function decodeToStringBinary($string)
    {
        $length    = mb_strlen($string);
        $stringBin = '';
        for ($i = 0; $i < $length; $i++) {
            try {
                $char      = mb_substr($string, $i, 1);
                $septet    = decbin($this->decode($char));
                $septetBin = str_pad($septet, self::CODE_SIZE, '0', STR_PAD_LEFT);

                $stringBin .= $septetBin . ' ';
            } catch (\Exception $e) {
                QuickLogger::error("$e");
                QuickLogger::debug("Failed decoding char-$i ($char)");
                $stringBin .= 'EEEEEEE ';
            }
        }

        return $stringBin;
    }

}