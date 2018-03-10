<?php

/*
 * Copyright 2015 1rstWAP. All rights reserved.
 */

namespace Firstwap\Altamides\Time;

/**
 * Description of DateTimeUtil
 *
 * @author buddy
 */
class TimeUtil
{

    /**
     * Get 'now' in UTC
     * @param \DateTimeZone $timezone
     * @return \DateTime
     */
    public static function now($timezone = 'UTC')
    {
        return new \DateTime('now', new \DateTimeZone($timezone));
    }

    /**
     * Get Unix epoch as DateTime object
     * @link https://en.wikipedia.org/wiki/Unix_time Unix timestamp in Wikipedia
     * @return \DateTimeImmutable
     */
    public static function unixZero()
    {
        return new \DateTime('@0', new \DateTimeZone('UTC'));
    }

}
