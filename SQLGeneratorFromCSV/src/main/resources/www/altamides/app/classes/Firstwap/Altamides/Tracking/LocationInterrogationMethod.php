<?php

/*
 * (c) 2014 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : All
 * Version          : 2.0 SP20
 * Filename         : LocationInterrogationMethod.php
 * Fileversion      : 2.000.001
 * Initial Creation : 
 * Initial Author   : Setia Budi Halim
 * Purpose          : Managing Location Interrogation Method
 * ================================================
 * Initial Request  : #
 * ================================================
 * Change Log
 * Date         Author          Version     Request     Comment         
 * 2014-08-25   Naaban Maryo    2.0SP20     #1824       Fixing tracking method value.
 * 
 */

namespace Firstwap\Altamides\Tracking;

/**
 *
 * @author Setia Budi Halim
 */
class LocationInterrogationMethod
{
    const PSI = 'PSI';
    const ATI = 'ATI';
    const ENH = 'ENH';
    const FST = 'FST';
    const AUT = 'AUT';
    
    private static $codeToValueMap = array(
        1 => self::PSI,
        2 => self::ATI, 
        3 => self::ENH,
        4 => self::FST,
        5 => self::AUT
    );
    
    private static $valueToCodeMap = array(
        self::PSI => 1,
        self::ATI => 2,
        self::ENH => 3,
        self::FST => 4,
        self::AUT => 5
    );
    
    public static function getValue($code)
    {
        return isset(self::$codeToValueMap[$code])?
            self::$codeToValueMap[$code] : self::$codeToValueMap[self::PSI];
    }
    
    public static function getCode($value)
    {
        return isset(self::$valueToCodeMap[$value])?
            self::$valueToCodeMap[$value] : self::PSI;
    }
}