<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Time;

/**
 * Description of DateTimeZoneUtil
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class AltamidesTimeZone
{

    const OFFSET_GMT = '+00:00';

    public static function getUserTimezone()
    {
        return new \DateTimeZone(self::getUserOffset());
    }

    public static function getUserOffset()
    {
        $userTimeZoneRegex = '/^UTC|(GMT? ?(\+|-)\d{2}:\d{2})$/';
        $userOffset = \filter_input(\INPUT_COOKIE, \WEBCLIENT_TZ_COOKIE_NAME,
                \FILTER_VALIDATE_REGEXP,
                ['options' => ['regexp' => $userTimeZoneRegex]]);
        if (empty($userOffset)) {
            return self::OFFSET_GMT;
        } else {
            return \ltrim($userOffset, 'GMTUC ');
        }
    }

    /**
     * 
     * @return \DateTimeZone
     */
    public static function getServerTimezone()
    {
        return new \DateTimeZone('UTC');
    }

}
