<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : AgeOfLocationMapper.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class AgeOfLocationMapper
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Codec;

/**
 * Description of AgeOfLocationMapper
 *
 * @author Setia Budi Halim
 */
class AgeOfLocationMapper implements ValueMapperInterface
{
    /**
     * One minute, 0 based
     */

    const HOUR_TRESHOLD = 59;

    /**
     * 62 hour, 0 based
     */
    const INFINITY_TRESHOLD = 3719;

    /**
     * 
     */
    const INFINITY_CODE = 0x7f;
    
    /**
     * 62 hours
     */
    const INFINITY_VALUE = 3720;

    /**
     * 
     */
    const HOUR_MASK = 0x40;

    public function encode($value)
    {
        // out of range, beyond max mintes
        if ($value > self::INFINITY_TRESHOLD) {
            return self::INFINITY_CODE;
        }

        // below one minute use actual minute value
        if ($value <= self::HOUR_TRESHOLD) {
            return (int) $value;
        }

        // min to hour, in real hours
        $hours = ceil($value / 60);
        return self::HOUR_MASK | $hours;
    }

    public function decode($value)
    {
        // upper limit
        if ($value == self::INFINITY_CODE) {
            return self::INFINITY_VALUE;
        }
        
        // rounded hours
        if ($value & self::HOUR_MASK) {
            return 60 * $value;
        }
        
        // in minutes
        return (int) $value;
    }
}