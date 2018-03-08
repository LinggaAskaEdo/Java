<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Tools;

/**
 * Description of Console
 *
 * @author setiabudi
 */
class Console {
    public static function write($string = '')
    {
        echo $string, PHP_EOL;
    }
    
    public static function putFile($file)
    {
        \readfile($file);
    }
    
    public static function info($string)
    {
        self::write("\033[1;37mINFO:\033[0m " . $string);
    }
    
    public static function debug($string)
    {
        self::write('DEBUG: ' . $string);
    }
    
    public static function warn($string)
    {
        self::write("\033[43mWARN:\033[0m " . $string);
    }
    
    public static function error($string)
    {
        self::write("\033[1;31mERROR:\033[0m " . $string);
    }
}
