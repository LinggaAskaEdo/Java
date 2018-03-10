<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Tools;

/**
 * Description of StringUnicodeConverter
 *
 * @author waluyo
 */
class StringUnicodeConverter
{
    public function convertUnicodeToHex($in_charset, $out_charset, $msg)
    {
        try {
            return strtoupper(bin2hex(iconv($in_charset, $out_charset,  $msg)));
        } catch (\Exception $e) {
            \error_log("StringUnicodeConverter - convertUnicodeToHex".print_r($e,true));
            return "";
        }
    }
    
    public function convertHexToUnicode($in_charset, $out_charset, $msg)
    {
        try {
            return iconv($in_charset, $out_charset,hex2bin($msg));
        } catch (\Exception $e) {
            \error_log("StringUnicodeConverter - convertHexToUnicode".print_r($e,true));
            return "";
        }
    }
}
