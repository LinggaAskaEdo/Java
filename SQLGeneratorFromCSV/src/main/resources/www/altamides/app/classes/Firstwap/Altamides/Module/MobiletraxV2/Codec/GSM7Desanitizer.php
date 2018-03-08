<?php 
/**
 * @author Dwikky Maradhiza
 */
namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

class GSM7Desanitizer {
	private $mMessage;
        
        const BYTE_SIZE = 8;
        
	public function __construct($message) {
		$this->mMessage = $message;
	}

	public function getDesanitizedString() {
                $converterUtil = new ConverterUtil();
		$payload = mb_substr($this->mMessage , 0 , null , 'UTF-8');
		$binary = $this->decodeToBinaryString($payload);
		$bit8List = $converterUtil->binaryStringTo8bitList($binary);
                $desanitizedString = '';
                foreach ($bit8List as $bit){
                    $desanitizedString .= sprintf("%02x", (float) bindec($bit));
//                    $desanitizedString .= str_pad(strtoupper(dechex((float) bindec($bit))) , 2 , '0' , STR_PAD_LEFT);
                    
                }
		return $desanitizedString;
	}
        
	private function decodeToBinaryString($payload) {
                $converterUtil = new ConverterUtil();
		$sb = '';
                $charLength = mb_strlen($payload, 'UTF-8');
		for ($i = 0; $i < $charLength; $i++) {
                        $gsm7Char = mb_substr( $payload , $i , 1 , 'UTF-8')  ;
			$gsm7Hex = $converterUtil->gsm7Char2int($gsm7Char);
			$septet = $converterUtil->int2Binary7BitString($gsm7Hex);
			$sb .= $septet;
                        
		}
		$length = mb_strlen($sb, 'utf-8');
		$mod = $length % self::BYTE_SIZE;
		$sb = mb_substr($sb , 0 , ($length - $mod) , 'utf-8');
		return $sb;
	}


}
?>