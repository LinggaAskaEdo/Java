<?php
/***********************
 * (c) 2014 FirstWAP. All rights reserved
 *
 * System			: ALTAMIDES
 * Module			: MobileTrax V2
 * Version			: 2.0 SP21
 * Filename			: checksum.php
 * Fileversion		: 
 * Initial Creation	: 2014-11-18
 * Initial Author	: Dwikky Maradhiza Y
 * Purpose			: - generate crc7 checksum
 * ---------------------
 * Initial Request:		
 * ---------------------
 ***********************/

/**
 * The Class Checksum.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

class Checksum {
	/*
	 * http://code.google.com/p/robotter/source/browse/code/crc7.py
	 * */
	
	/** The Constant DEFAULT_CRC7_POLYNOMIAL. */
	const DEFAULT_CRC7_POLYNOMIAL = 0x91;
	
	/** The Constant TABLE_LENGTH. */
	const TABLE_LENGTH = 256;
        
        /** The Message Content. */
        protected $message;
        
        /**
         * Construct
         * 
         * @param content message
         */
        public function __construct( $message ) {
            $this->message = $message;
        }
        
        /**
	 * Crc7.
	 *
	 * @param data the data
	 * @return the int
	 */
	public function crc7() {
		return $this->crc7Checksum(self::DEFAULT_CRC7_POLYNOMIAL);
	}
	
	/**
	 * Crc7.
	 *
	 * @param data the data
	 * @param polynomial the polynomial
	 * @return the int
	 */
	public function crc7Checksum($polynomial) {
                header('Content-type: text/html; charset=UTF-8');
		$crcValue = 0;
		$crc7Table = $this->generateTable($polynomial);

                $dataBytes = array();
                for($i = 0; $i < strlen($this->message); $i++){
                     $dataBytes[] = ord($this->message[$i]);
                }
                
		for($i=0; $i< count($dataBytes); $i++) {
			// signed int to unsigned int 
			$crcValue = $crc7Table[$crcValue ^ ($dataBytes[$i] & 0xFF)];

			//~ crcValue = crc7_syndrome_table[crcValue ^ dataBytes[i]];
		}

		return $crcValue;
	}
	
	/**
	 * Byte crc7.
	 *
	 * @param value the value
	 * @param polynomial the polynomial
	 * @return the int
	 */
	protected function byteCrc7($value, $polynomial) {
		for ($i=0; $i<8; $i++) {
		    if (($value & 1) == 1)
		    	$value ^= $polynomial;
		    $value >>= 1;
		}
				  
		return $value;
	}
	
	/**
	 * Generate table.
	 *
	 * @param polynomial the polynomial
	 * @return the int[]
	 */
	protected function generateTable($polynomial) {
                $value = array();
		
		for($i=0; $i < self::TABLE_LENGTH; $i++) {
			$value[$i] = $this->byteCrc7($i, $polynomial);
		}
		
		return $value;
	}
}

?>