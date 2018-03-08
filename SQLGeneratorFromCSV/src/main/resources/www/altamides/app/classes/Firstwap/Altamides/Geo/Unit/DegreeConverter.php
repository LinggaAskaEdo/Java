<?php
/*
 * (c) 2012 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Omnitrax
 * Version          : 2.0 SP15
 * Filename         : DegreeConverter.php
 * Fileversion      : 2.000.001
 * Initial Creation : 2012-07-01
 * Initial Author   : buddy
 * Purpose          : Convert from degree to other value and vice versa
 * ================================================
 * Initial Request  : #807
 * ================================================
 * Change Log
 * Date         Author      Version     Request     Comment         
 * 2012-07-18   buddy       2.0         #807       Initial Creation
 */

namespace Firstwap\Altamides\Geo\Unit;

class DegreeConverter {
    /**
     * convert from miliseconds to degree int value
     * @param type $msDegree
     * @return int 
     */
    public static function msToDec($msDegree) {
        if($msDegree==0) return 0;
	$tmp = abs($msDegree) / 1000;       
	$degdec = number_format(round($tmp / 3600, 4), 4);
         
	if ($msDegree < 0)
		$degdec = $degdec * (-1);  
	return $degdec;
    }
    
    /**
     * convert from miliseconds to degree text
     * @param type $msDegree
     * @return string 
     */
    public static function msToDegree($msDegree)
    {
	if($msDegree==0) return '';
	$tmp = abs($msDegree) / 1000;       
	$tmpdeg = $tmp / 3600;
	$deg = floor($tmpdeg);
         
	$tmpmin = ($tmpdeg - $deg) * 60;
	$min = floor($tmpmin);
 
	$sec = ($tmpmin - $min) * 60;
	$sec = number_format($sec, 2);
         
	$degree = $deg . "* " . $min . "' " . $sec ."''";
	if ($ms < 0)
		$degree = "-" . $degree;  
	return $degree;
    }

}