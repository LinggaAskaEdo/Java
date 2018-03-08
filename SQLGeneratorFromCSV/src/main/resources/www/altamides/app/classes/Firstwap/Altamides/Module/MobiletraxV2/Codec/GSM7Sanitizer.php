<?php
/**
 * @author Dwikky Maradhiza
 */
namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

class GSM7Sanitizer {
        
	private $mData = array();
        
        const BYTE_SIZE = 8;
        
        public function __construct($data) {
		$this->mData = $data;
	}

	public function getSanitizedString() {
                $converterUtil = new ConverterUtil();
		$binaryString = $this->getBinaryFromString();
		$bit7List = $converterUtil->binaryStringTo7bitList($binaryString);
		$sanitizedBuilder = '';
		foreach ($bit7List as $string) {
			$gsm7Value = bindec($string);
			$gsm7Char = $converterUtil->int2Gsm7Char($gsm7Value);
			$sanitizedBuilder .= $gsm7Char;
		}
		return $sanitizedBuilder;
	}
        
        public function getBinaryFromString(){
                $binary = array();
                for($i = 0; $i < mb_strlen($this->mData,'UTF-8'); $i=$i+2){
                     $hex = mb_substr($this->mData , $i, 2 , 'UTF-8');
                     $binary[] = str_pad(decbin(hexdec($hex)), self::BYTE_SIZE, '0', STR_PAD_LEFT);
                }
                $binary = implode('', $binary);
                return $binary;
        }
}
?>