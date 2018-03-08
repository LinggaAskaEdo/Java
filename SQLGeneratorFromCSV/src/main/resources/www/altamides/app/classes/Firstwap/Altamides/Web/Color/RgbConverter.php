<?php
/*
 * (c) 2012 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Omnitrax
 * Version          : 2.0 SP15
 * Filename         : RgbConverter.php
 * Fileversion      : 2.000.001
 * Initial Creation : 2012-07-01
 * Initial Author   : buddy
 * Purpose          : Convert from rgb to other value and vice versa
 * ================================================
 * Initial Request  : #807
 * ================================================
 * Change Log
 * Date         Author      Version     Request     Comment         
 * 2012-07-18   angela       2.0         #807       Initial Creation
 */

namespace Firstwap\Altamides\Web\Color;

class RgbConverter {
    /**
     * Hexadecimal to Decimal
     * @param type $hex
     * @return type array
     */
    public static function hexToRgb($hex){
        $hex = ereg_replace("#", "", $hex);
        $color = array();

        if(strlen($hex) == 3) {
            $color['red'] = hexdec(substr($hex, 0, 1));
            $color['green'] = hexdec(substr($hex, 1, 1));
            $color['blue'] = hexdec(substr($hex, 2, 1));
        }
        else if(strlen($hex) == 6) {
            $color['red'] = hexdec(substr($hex, 0, 2));
            $color['green'] = hexdec(substr($hex, 2, 2));
            $color['blue'] = hexdec(substr($hex, 4, 2));
        }
        return $color;
    }
    
    /**
     * Decimal to Hexadecimal
     * @param type $r 
     * @param type $g
     * @param type $b
     * @return type string
     */
    public static function rgbToHex($r, $g, $b){
        $hex = "#";
        $hex.= str_pad(dechex($r), 2, "0", STR_PAD_LEFT);
        $hex.= str_pad(dechex($g), 2, "0", STR_PAD_LEFT);
        $hex.= str_pad(dechex($b), 2, "0", STR_PAD_LEFT);
        return $hex;
    }
}
?>
