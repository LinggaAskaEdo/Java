<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Time;

/**
 * Description of TimeZoneUtil
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class TimeZoneUtil
{

    /**
     * 
     * @param \DateTimeZone $tz
     */
    public static function calculateSecondOffset(\DateTimeZone $tz)
    {
        return (int) $tz->getOffset(TimeUtil::now('UTC'));
    }

}
