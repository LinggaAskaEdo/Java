<?php

/**
 * (c) 1rstWAP. All rights reserved.
 *
 * System           : Altamides
 * Module           :
 * Version          :
 * File Name        : CodecBase.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-07
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class CodecBase
 *
 * =====================================================================
 * Initial Request  :
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-07   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Codec;

/**
 * Description of CodecBase
 *
 * @author Setia Budi Halim
 */
abstract class Gsm7CodecBase
{

    const INT64_FULL      = 0xffffffffffffffff;
    const INT64_SIZE      = 64;

    // String field
    const STRING_PAD = ' ';

    /**
     * Bit: 0111 1111
     * full 7bit - 1
     */
    const ESC_COUNTER_MAX = 0x7e;

    // Special field types
    const LATITUDE_MASK       = 900000;
    const LONGITUDE_MASK      = 1800000;
    const SIZE_OF_LATITUDE    = 21;
    const SIZE_OF_LONGITUDE   = 22;
    const SIZE_OF_MNC_PAIR    = 20;
    const SIZE_OF_ESC_COUNTER = 7;

    /**
     *
     * @var Gsm7CodecMap
     */
    protected $mapper;
    protected $reminderValue = 0;
    protected $reminderSize  = 0;
    protected $escCounter    = 0;
    protected $escTradeOff   = 0;

    /**
     * Prerendered bit mask
     * @var array
     */
    private static $intBitMask = array(
        // <editor-fold defaultstate="collapsed" desc="Bit mask array">
        0  => 0x0000000000000000, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000
        1  => 0x0000000000000001, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001
        2  => 0x0000000000000003, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011
        3  => 0x0000000000000007, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111
        4  => 0x000000000000000f, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111
        5  => 0x000000000000001f, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111
        6  => 0x000000000000003f, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111
        7  => 0x000000000000007f, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111
        8  => 0x00000000000000ff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111
        9  => 0x00000000000001ff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111
        10 => 0x00000000000003ff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111
        11 => 0x00000000000007ff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111
        12 => 0x0000000000000fff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111
        13 => 0x0000000000001fff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111
        14 => 0x0000000000003fff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111
        15 => 0x0000000000007fff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111
        16 => 0x000000000000ffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111
        17 => 0x000000000001ffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111
        18 => 0x000000000003ffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111
        19 => 0x000000000007ffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111
        20 => 0x00000000000fffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111
        21 => 0x00000000001fffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111
        22 => 0x00000000003fffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111
        23 => 0x00000000007fffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111
        24 => 0x0000000000ffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111
        25 => 0x0000000001ffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111
        26 => 0x0000000003ffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111
        27 => 0x0000000007ffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111
        28 => 0x000000000fffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111
        29 => 0x000000001fffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111
        30 => 0x000000003fffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111
        31 => 0x000000007fffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111
        32 => 0x00000000ffffffff, // 0000 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111
        33 => 0x00000001ffffffff, // 0000 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111
        34 => 0x00000003ffffffff, // 0000 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111
        35 => 0x00000007ffffffff, // 0000 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111
        36 => 0x0000000fffffffff, // 0000 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111
        37 => 0x0000001fffffffff, // 0000 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111
        38 => 0x0000003fffffffff, // 0000 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111
        39 => 0x0000007fffffffff, // 0000 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        40 => 0x000000ffffffffff, // 0000 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        41 => 0x000001ffffffffff, // 0000 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        42 => 0x000003ffffffffff, // 0000 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        43 => 0x000007ffffffffff, // 0000 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        44 => 0x00000fffffffffff, // 0000 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        45 => 0x00001fffffffffff, // 0000 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        46 => 0x00003fffffffffff, // 0000 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        47 => 0x00007fffffffffff, // 0000 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        48 => 0x0000ffffffffffff, // 0000 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        49 => 0x0001ffffffffffff, // 0000 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        50 => 0x0003ffffffffffff, // 0000 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        51 => 0x0007ffffffffffff, // 0000 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        52 => 0x000fffffffffffff, // 0000 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        53 => 0x001fffffffffffff, // 0000 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        54 => 0x003fffffffffffff, // 0000 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        55 => 0x007fffffffffffff, // 0000 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        56 => 0x00ffffffffffffff, // 0000 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        57 => 0x01ffffffffffffff, // 0000 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        58 => 0x03ffffffffffffff, // 0000 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        59 => 0x07ffffffffffffff, // 0000 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        60 => 0x0fffffffffffffff, // 0000 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        61 => 0x1fffffffffffffff, // 0001 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        62 => 0x3fffffffffffffff, // 0011 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        63 => 0x7fffffffffffffff, // 0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
        64 => 0xffffffffffffffff, // 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
            // </editor-fold>
    );

    abstract public function getResult();

    public function __construct()
    {
        $this->mapper = new Gsm7CodecMap();
    }

    public function hasReminder()
    {
        return $this->getReminderSize() > 0;
    }

    public function getReminderValue()
    {
        $this->reminderValue = 0;
    }

    public function getReminderSize()
    {
        $this->reminderSize = 0;
    }

    public function countEsc()
    {
        return $this->escCounter;
    }

    public function countEscTradeOff()
    {
        return $this->escTradeOff;
    }

    public function countExtraSeptet()
    {
        return $this->escCounter - $this->escTradeOff;
    }

    public function hasExtraSeptet()
    {
        return $this->countExtraSeptet() > 0;
    }

    public function reset()
    {
        $this->reminderValue = 0;
        $this->reminderSize  = 0;
        $this->escCounter    = 0;
        $this->escTradeOff   = 0;
    }

    /**
     * Make sure an integer use only certain bits
     * @param int $int the data
     * @param int $length bit size
     * @return int
     */
    protected function trimInt($int, $length)
    {
        if ($length > self::INT64_SIZE) {
            throw new \InvalidArgumentException('Length argument exceeds integer size');
        }

        $mask = self::$intBitMask[$length] & $int;
        return $mask & $int;
    }

    protected function trimValue($value)
    {
        return $value & Gsm7CodecMap::CODE_MASK;
    }

    /**
     *
     * @param type $string
     * @param type $length
     * @return string
     */
    protected function fixStringLength($string, $length)
    {
        // length must be greater than 0
        if ($length < 1) {
            return '';
        }

        $actualLength = mb_strlen($string);

        // change nothing
        if ($length == $actualLength) {
            return $string;
        }
        
        if ($length > $actualLength) { // less than, pad it
            $pad = str_repeat(self::STRING_PAD, $length - $actualLength);
            return $string . $pad;
        } else { // greater than, trim it
            return mb_substr($string, 0, $length);
        }
    }

    /**
     *
     * @param type $length
     * @return type
     * @throws \OverflowException
     */
    protected function createBitMask($length)
    {
        if (isset(self::$intBitMask[$index])) {
            return self::$intBitMask[$index];
        }

        throw new \OverflowException("Invalid bit length: <$length>");
    }

}