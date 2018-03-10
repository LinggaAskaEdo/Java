<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : DataEncoderBase.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-07
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class DataEncoderBase
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-07   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;

/**
 * Description of DataEncoderBase
 *
 * @author Setia Budi Halim
 */
class Gsm7Encoder extends Gsm7CodecBase
{

    protected $encoded      = '';
    protected $textEncoding = 'UTF-8';

    public function getResult()
    {
        if (QuickLogger::isDebug()) {
            QuickLogger::debug(__CLASS__.': Result in binary = ' . $this->getResultAsStringBinary());
        }
        return $this->encoded;
    }

    public function getResultAsStringBinary()
    {
        return $this->mapper->decodeToStringBinary($this->encoded);
    }

    public function truncateReminder()
    {
        $this->reminderSize  = 0;
        $this->reminderValue = 0;
    }

    public function padReminder()
    {
        if ($this->reminderSize < 1) {
            $this->reminderValue = 0;
            $this->reminderSize  = 0;
            return;
        }
        
        $value = $this->trimValue($this->reminderValue);
        $char .= $this->mapper->encode($value);
        $this->encoded .= $char;

        if ($char === Gsm7CodecMap::ESC_REPLACEMENT) {
            $this->escCounter++;
        }

        $this->reminderValue = 0;
        $this->reminderSize  = 0;
    }

    public function reset()
    {
        $this->encoded = '';
        parent::reset();
    }

    /**
     * Encode unsigned integer data. The unsigned integer is actually
     * PHP signed integer but only 31 left-most bits used.
     * 
     * @param int $data The data
     * @param int $length The bits count of data.
     * @return string Encoded form
     * 
     * @throws \InvalidArgumentException 
     */
    public function appendInt($data, $length)
    {
        //empty data
        if ($length < 1) {
            QuickLogger::debug("Encoding empty data: LENGTH=$length, DATA=$data");
            return;
        }

        if ($length > self::INT64_SIZE) {
            throw new \InvalidArgumentException("Too large binary, size=$length, max={$this->intSize}");
        }

        $data    = (int) $data;
        $encoded = '';

        // In case of adding data to reminder is not sufficient to form
        // a septet/char then we will only add the data to reminder
        // without producing encoded string
        if (Gsm7CodecMap::CODE_SIZE > ($this->reminderSize + $length)) {
            //give space for new bits
            $this->reminderValue <<= $length;
            // append new data to reminder
            $this->reminderValue |= $data;
            // increase reminder size
            $this->reminderSize += $length;
            // no produced string
            return;
        }

        $reminderSize  = $this->reminderSize;
        $reminderValue = $this->reminderValue;

        // first, we trim the reminder by adding missing bit to form 
        // a septet, so the next can be simplified
        if ($reminderSize > 0) {
            // determine how many bits for reminder to form a septet
            $missingLength = Gsm7CodecMap::CODE_SIZE - $reminderSize;
            // take only first n bits as much as missing bits
            $appendSeptet  = $data >> ($length - $missingLength);

            // give space to missing bits 
            $appendSeptet |= $reminderValue << $missingLength;

            $appendChar = $this->mapper->encode($appendSeptet);

            if ($appendChar === Gsm7CodecMap::ESC_REPLACEMENT) {
                $this->escCounter++;
            }
            $encoded = $appendChar;

            // deduct data size
            $length -= $missingLength;
            $data = $this->trimInt($data, $length);

            // at this point the remainder is empty
        }

        // read eevery septet
        while ($length >= Gsm7CodecMap::CODE_SIZE) {
            $appendSeptet = $data >> ($length - Gsm7CodecMap::CODE_SIZE);

            $appendChar = $this->mapper->encode($appendSeptet);

            if ($appendChar === Gsm7CodecMap::ESC_REPLACEMENT) {
                $this->escCounter++;
            }
            $encoded .= $appendChar;
            $length -= Gsm7CodecMap::CODE_SIZE;
            $data = $this->trimInt($data, $length);
        }

        // there are left-out bits
        if ($length) {
            $this->reminderValue = $this->trimInt($data, $length);
            $this->reminderSize  = $length;

            // prefectly aligned into septets so, no left-outs
        } else {
            $this->reminderValue = 0;
            $this->reminderSize  = 0;
        }

        $this->encoded .= $encoded;
    }

    /**
     * Encode string data
     * @param string $string The data
     * @param int $length The length of this field (number of characters)
     * @return string Encoded form
     */
    public function appendString($string, $length, $forceBasic = false /* Internal: Basic charset */)
    {
        // <editor-fold defaultstate="collapsed" desc="Workflow Summary">
        // Here's the idea
        // We scan the caracters in the data string. Foreach character we
        // read, get the GSM7 integer representation.
        // 
        // We treat it as new data, we took some bits from it and combie
        // with the reminder bits to form a septet. The septet then 
        // converted to the representing character and be appended to 
        // result/message.
        // 
        // This operation leaves some bits from the new data which can not 
        // be appended to message because it is not enough to form a
        // septet. Those bits will be treated as reminder in next loop
        // 
        // EXAMPLE:
        // reminder is 3 bits so the result would be
        // reminder       : 000 0RRR (3 bits)
        // shift reminder : RRR 0000 (shift right <<)
        // data           : DDD DDDD (septet)
        // shift data     : 000 DDDD (shift left >>)
        // 
        // concat result  : RRR DDDD (reminder OR data) 
        //                  this will be appended after converted 
        //                  to representing character
        //                  
        // reminder mask  : 0000 0111 ; bits 1 as much as reminder length
        // data           : 0DDD DDDD
        // new reminder   : 0000 0RRR ; has-been-used right bits are chopped
        // </editor-fold>
        if (!$length) {
            return;
        }


        // Remove extra char from input string
        $string = $this->fixStringLength($string, $length);

        $encoded       = '';
        $reminderValue = $this->reminderValue;
        $reminderSize  = $this->reminderSize;

        // calc shift strategy
        if ($reminderSize) {
            $reminderMask = $this->createBitMask($reminderSize);
            $spaceSize    = Gsm7CodecMap::CODE_SIZE - $reminderSize;
        } else {
            $reminderMask = 0;
            $spaceSize    = 0;
        }

        $charIndex  = 0;
        $availSpace = $length;
        // scan the input string
        while ($availSpace--) {
            // get a character from the string
            $char = mb_substr($string, $charIndex++, 1, $this->textEncoding);

            // get the gsm7 int representation of the character
            $charSeptet = $this->mapper->getCodepoint($char);

            // extended character has two septets
            // first is 0x1b (ESC) and the escaped septet
            if ($this->mapper->isExtCodepoint($charSeptet)) {
                // if we are forced to use basic/single septet charset
                // or we still have room for two septets we will 
                // replace it with illegal character
                if ($forceBasic || ($availSpace === 1)) {
                    $charSeptet = Gsm7CodecMap::ERROR_CODE;

                    // otherwise append the first septet
                    // and let the second septet handled in normal flow
                } else {
                    $escSeptet    = $charSeptet >> Gsm7CodecMap::CODE_SIZE;
                    $appendSeptet = $reminderValue << $spaceSize;
                    $appendSeptet |= $escSeptet >> $reminderSize;

                    $appendChar = $this->mapper->encode($appendSeptet);
                    if ($appendChar === Gsm7CodecMap::ESC_REPLACEMENT) {
                        $this->escCounter++;
                    }
                    $encoded .= $appendChar;

                    $reminderValue = $reminderMask & $charSeptet;
                    $charSeptet    = $this->trimValue($charSeptet);
                    $availSpace--;
                }
            }

            // give room
            $appendSeptet = $reminderValue << $spaceSize;

            // concat reminder with shifted data
            $appendSeptet |= $charSeptet >> $reminderSize;

            // get the char representation and add it to existing data
            $appendChar = $this->mapper->encode($appendSeptet);
            if ($appendChar === Gsm7CodecMap::ESC_REPLACEMENT) {
                $this->escCounter++;
            }
            $encoded .= $appendChar;

            // turn off already used bits and set the reminding bits as
            $reminderValue = $reminderMask & $charSeptet;
        }

        if ($charIndex != $length) {
            QuickLogger::info("[LatinCodec::encodeString] String GSM7 representation is longer than actual string length. Some char has been trimmed (actual=$length, processed=$charIndex, length=$availSpace)");
        }

        $this->reminderSize  = $reminderSize;
        $this->reminderValue = $reminderValue;
        $this->encoded .= $encoded;
    }

    public function appendBasicString($string, $length)
    {
        $this->appendString($string, $length, true);
    }

    /**
     * 
     * @param string $string
     * @param length $length
     * @return int
     */
    public function appendEscTradeOffString($string, $length)
    {
        $reducement = $this->countExtraSeptet();

        // nothing needs to be trimmed
        if (!$reducement) {
            $this->appendBasicString($string, $length);
            return;
        }

        $reducedFieldSize = $length - $reducement;

        // too much reduced chars, no value for VLR name
        // yet we still owe some reduction
        if ($reducedFieldSize < 0) {
            $this->escTradeOff += $reducedFieldSize;
            return;
        }

        // the trim does not actually have any effect, except on the field size
        if (mb_strlen($string) < $reducedFieldSize) { // no reduction
            $this->appendBasicString($string, $reducedFieldSize);
            $this->escTradeOff += $reducement;
            return;
        }

        // otherwise, there is reduction but we can still acomodate it
        $reducedString = mb_substr($string, 1, $reducedFieldSize);

        // we are debt-free now
        $this->appendBasicString($reducedString, $reducedFieldSize);
        $this->escTradeOff += $reducement;
    }

    /**
     * 
     * @param type $long
     */
    public function appendLongitude($long)
    {
        $masked = round($long * 10000) + self::LONGITUDE_MASK;
        $this->appendInt($masked, self::SIZE_OF_LONGITUDE);
    }

    /**
     * 
     * @param type $lat
     */
    public function appendLatitude($lat)
    {
        $masked = round($lat * 10000) + self::LATITUDE_MASK;
        $this->appendInt($masked, self::SIZE_OF_LATITUDE);
    }

    public function appendMncPair($mcc, $mnc)
    {
        $mncPair = $mcc . \str_pad($mnc, 3, '0', \STR_PAD_LEFT);
        $this->appendInt(ltrim($mncPair, '0'), self::SIZE_OF_MNC_PAIR);
    }

    public function appendEscCounter()
    {
        if ($this->escCounter > self::ESC_COUNTER_MAX) {
            throw new Gsm7EscOverflowException($this->escCounter);
        }

        // avoid ESC value (0x1b) in the encoed value
        $counter = $this->escCounter < Gsm7CodecMap::ESC_CODE ? $this->escCounter : ($this->escCounter + 1);

        $this->appendInt($counter, self::SIZE_OF_ESC_COUNTER);
    }
    
    /**
     * Append an integer degree. The value will  be 'normalised' to its 'first turn' value.
     * see http://en.wikipedia.org/wiki/Turn_%28geometry%29
     * The degree will then be stored as integer encoded as 
     * 0-359 for positive degree
     * 360+ for negative.
     * 
     * Warning, this function currently does no overflow checking for negative degree!
     * You are at your own risk
     * 
     * @param int $degree The angle degree value 
     * @param int $fieldSize the width of the data field
     * @param type $fixedNegative if this value is set then it will be used when the degree is < 0
     */
    public function appendDegree($degree, $fieldSize, $fixedNegative = null)
    {
        $degree = (int) $degree;
        if ($degree === 0) {
            $this->appendInt(0, $fieldSize);
            return;
        }
        
        $isNegative = $degree < 0;        
        
        // get the degree in the 'first turn'
        // see http://en.wikipedia.org/wiki/Turn_%28geometry%29
        if ($isNegative && ($fixedNegative !== null)) {
            $absDegree =  abs((int) $fixedNegative);
        } else {
            $absDegree =  abs($degree);
        }
        
        if ($absDegree >= 360) {
            $absDegree = $absDegree % 360;
        }
        
        $encodedValue = $isNegative 
            ? ($absDegree + 359)
            : $absDegree;
        
        $this->appendInt($encodedValue, $fieldSize);
    }
    
    public function appendData($data){
        return $this->mapper->encode($data);
    }

}
