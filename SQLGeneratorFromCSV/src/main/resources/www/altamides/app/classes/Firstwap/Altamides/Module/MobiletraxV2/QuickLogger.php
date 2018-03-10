<?php

/* 
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

/**
 * Temporary work around since we dont have dedicated logging function yet 
 */
final class QuickLogger {
    
    private static $level = array(
        'DEBUG' => 8000,
        'INFO'  => 4000,
        'WARN'  => 2000,
        'ERROR' => 1000,
        'OFF'   => 0
    );
    
    private static $treshold = 1000;

    public static function setTreshold($level)
    {
        self::$treshold = self::$level[$level];
    }
    
    public static function error($message)
    {
        self::log($message, 'ERROR');
    }
    
    public static function info($message)
    {
        self::log($message, 'INFO');
    }
    
    public static function warn($message)
    {
        self::log($message, 'WARN');
    }
    
    public static function debug($message)
    {
        self::log($message, 'DEBUG');
    }
    
    public static function isDebug()
    {
        return self::$treshold >= self::$level['DEBUG'];
    }
    
    private static function isAboveTreshold($level)
    {
        $levelWeight = self::$level[$level];
        return $levelWeight > self::$treshold;
    }

    private static function log($message, $level)
    {
        if (self::isAboveTreshold($level)) {
            return;
        }
        
        $date = date('Y-m-d H:i:s');
        $string = "[$date] [$level] $message\n";
        file_put_contents(\MOBILETRAX_TRACE_LOG, $string, \FILE_APPEND);
    }
}