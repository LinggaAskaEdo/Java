<?php

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

class RC4{
   const INT_256 = 256;
   
   public function encrypt($a,$b){
      for($i =0,$c = '' ; $i < self::INT_256 ; $i++){
          $c[$i] = $i;
      }
      
      for($i=0, $d = '', $e = '', $g = strlen($a) ; $i < self::INT_256; $i++){
         $d = ($d+$c[$i] + ord($a[$i%$g])) % self::INT_256;
         $e = $c[$i];
         $c[$i] = $c[$d];
         $c[$d] = $e;
      }
      
      for($y = 0, $i = '', $d = 0, $f = ''; $y < strlen($b) ; $y++){
         $i = ($i+1) % self::INT_256;
         $d = ($d + $c[$i]) % self::INT_256;
         $e = $c[$i];
         $c[$i] = $c[$d];
         $c[$d] = $e;
         $chod = chr(ord($b[$y]) ^ $c[($c[$i] + $c[$d]) % self::INT_256]);
         $f.= $chod;
      }
      return $f;
   }
   
   public function decrypt($a,$b){
       return RC4::encrypt($a, $this->hexToStr($b));
   }

   public function hexEncrypt($a,$b){
       return bin2hex($this->encrypt($a, $b));
   }
   
   public static function hexToStr($hex){
        $string='';
        for ($i=0 ; $i < strlen($hex) - 1; $i += 2){
            $string .= chr(hexdec($hex[$i].$hex[$i + 1]));
        }
        return $string;
    }
   
}
   
   



?>