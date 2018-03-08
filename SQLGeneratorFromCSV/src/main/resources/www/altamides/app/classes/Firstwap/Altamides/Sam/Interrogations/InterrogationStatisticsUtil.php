<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sam\Interrogations;

/**
 * Description of InterrogationStatisticUtil
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class InterrogationStatisticsUtil
{


    /**
     * Calculate timeslot shift from GMT offset
     * Example:
     * +09:15 = +  915
     * -10:20 = - 1020
     * @param string $offset
     * @return int
     */
    public static function getTimeSlotKeyShift($offset)
    {
        $isNegative = 0 === \strpos('-', $offset);
        $significantPart = \ltrim($offset, 'GMTUC +-0');
        $shiftValue = \str_replace(':', '', $significantPart);
        return $isNegative ? (int) $shiftValue : -((int) $shiftValue);
    }

}
