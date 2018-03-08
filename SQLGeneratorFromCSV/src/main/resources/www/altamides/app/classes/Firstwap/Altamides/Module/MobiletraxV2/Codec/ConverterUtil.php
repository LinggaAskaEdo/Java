<?php

/**
 * @author Dwikky Maradhiza
 */
namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

class ConverterUtil {
    const ESC_REPLACEMENT = '{';
        const BYTE_SIZE = 8;
	public function byteArray2BinaryString($byteArray = array()) {
		$sb = '';
		for ($i = 0; $i < self::BYTE_SIZE * count($byteArray); $i++){
                    if($byteArray[$i / self::BYTE_SIZE] << $i % self::BYTE_SIZE & 0x80){
                        $sb .= '0';
                    }
                    else{
                        $sb .= '1';
                    }
                    $sb .= ($byteArray[$i / self::BYTE_SIZE] << $i % self::BYTE_SIZE & 0x80) == 0 ? '0' : '1';
                }
		return $sb;
	}

	public function int2Binary7BitString($value) {
		return $sb = str_pad(decbin($value), 7, "0", STR_PAD_LEFT);
	}

	public function binaryList2ByteArray($list = array()) {
		$size = count($list);
		for ($i = 0; $i < $size; $i++) {
			$signedVal = $list[$i];
			$intVal = bindec($signedVal);
			$byteArray[$i] = $intVal;
		}
		return $byteArray;
	}

	public function binaryStringTo8bitList($binary) {
		return str_split($binary , self::BYTE_SIZE);
	}

	public function binaryStringTo7bitList($string) {
                $length = mb_strlen($string, 'UTF-8');
                $mod = $length % 7;
                if($mod > 0){
                    $string = str_pad($string, ($length + (7 - $mod)) , '0', STR_PAD_RIGHT);
                }
                $string = str_split($string , 7);
		return $string;
	}

	public function gsm7Char2int($gsm7Char) {
		if ($gsm7Char == '@') {
			return 0x00;
		} else if ($gsm7Char == '£') {
			return 0x01;
		} else if ($gsm7Char == '$') {
			return 0x02;
		} else if ($gsm7Char == '¥') {
			return 0x03;
		} else if ($gsm7Char == 'è') {
			return 0x04;
		} else if ($gsm7Char == 'é') {
			return 0x05;
		} else if ($gsm7Char == 'ù') {
			return 0x06;
		} else if ($gsm7Char == 'ì') {
			return 0x07;
		} else if ($gsm7Char == 'ò') {
			return 0x08;
		} else if ($gsm7Char == 'Ç') {
			return 0x09;
		} else if ($gsm7Char == "\n") {
			return 0x0A;
		} else if ($gsm7Char == 'Ø') {
			return 0x0B;
		} else if ($gsm7Char == 'ø') {
			return 0x0C;
		} else if ($gsm7Char == "\r") {
			return 0x0D;
		} else if ($gsm7Char == 'Å') {
			return 0x0E;
		} else if ($gsm7Char == 'å') {
			return 0x0F;
		} else if ($gsm7Char == 'Δ') {
			return 0x10;
		} else if ($gsm7Char == '_') {
			return 0x11;
		} else if ($gsm7Char == 'Φ') {
			return 0x12;
		} else if ($gsm7Char == 'Γ') {
			return 0x13;
		} else if ($gsm7Char == 'Λ') {
			return 0x14;
		} else if ($gsm7Char == 'Ω') {
			return 0x15;
		} else if ($gsm7Char == 'Π') {
			return 0x16;
		} else if ($gsm7Char == 'Ψ') {
			return 0x17;
		} else if ($gsm7Char == 'Σ') {
			return 0x18;
		} else if ($gsm7Char == 'Θ') {
			return 0x19;
		} else if ($gsm7Char == 'Ξ') {
			return 0x1A;
		} else if ($gsm7Char == 'Æ') {
			return 0x1C;
		} else if ($gsm7Char == 'æ') {
			return 0x1D;
		} else if ($gsm7Char == 'ß') {
			return 0x1E;
		} else if ($gsm7Char == 'É') {
			return 0x1F;
		} else if ($gsm7Char == ' ') {
			return 0x20;
		} else if ($gsm7Char == '!') {
			return 0x21;
		} else if ($gsm7Char == '"') {
			return 0x22;
		} else if ($gsm7Char == '#') {
			return 0x23;
		} else if ($gsm7Char == '¤') {
			return 0x24;
		} else if ($gsm7Char == '%') {
			return 0x25;
		} else if ($gsm7Char == '&') {
			return 0x26;
		} else if ($gsm7Char == '\'') {
			return 0x27;
		} else if ($gsm7Char == '(') {
			return 0x28;
		} else if ($gsm7Char == ')') {
			return 0x29;
		} else if ($gsm7Char == '*') {
			return 0x2A;
		} else if ($gsm7Char == '+') {
			return 0x2B;
		} else if ($gsm7Char == ',') {
			return 0x2C;
		} else if ($gsm7Char == '-') {
			return 0x2D;
		} else if ($gsm7Char == '.') {
			return 0x2E;
		} else if ($gsm7Char == '/') {
			return 0x2F;
		} else if ($gsm7Char == '0') {
			return 0x30;
		} else if ($gsm7Char == '1') {
			return 0x31;
		} else if ($gsm7Char == '2') {
			return 0x32;
		} else if ($gsm7Char == '3') {
			return 0x33;
		} else if ($gsm7Char == '4') {
			return 0x34;
		} else if ($gsm7Char == '5') {
			return 0x35;
		} else if ($gsm7Char == '6') {
			return 0x36;
		} else if ($gsm7Char == '7') {
			return 0x37;
		} else if ($gsm7Char == '8') {
			return 0x38;
		} else if ($gsm7Char == '9') {
			return 0x39;
		} else if ($gsm7Char == ':') {
			return 0x3A;
		} else if ($gsm7Char == ';') {
			return 0x3B;
		} else if ($gsm7Char == '<') {
			return 0x3C;
		} else if ($gsm7Char == '=') {
			return 0x3D;
		} else if ($gsm7Char == '>') {
			return 0x3E;
		} else if ($gsm7Char == '?') {
			return 0x3F;
		} else if ($gsm7Char == '¡') {
			return 0x40;
		} else if ($gsm7Char == 'A') {
			return 0x41;
		} else if ($gsm7Char == 'B') {
			return 0x42;
		} else if ($gsm7Char == 'C') {
			return 0x43;
		} else if ($gsm7Char == 'D') {
			return 0x44;
		} else if ($gsm7Char == 'E') {
			return 0x45;
		} else if ($gsm7Char == 'F') {
			return 0x46;
		} else if ($gsm7Char == 'G') {
			return 0x47;
		} else if ($gsm7Char == 'H') {
			return 0x48;
		} else if ($gsm7Char == 'I') {
			return 0x49;
		} else if ($gsm7Char == 'J') {
			return 0x4A;
		} else if ($gsm7Char == 'K') {
			return 0x4B;
		} else if ($gsm7Char == 'L') {
			return 0x4C;
		} else if ($gsm7Char == 'M') {
			return 0x4D;
		} else if ($gsm7Char == 'N') {
			return 0x4E;
		} else if ($gsm7Char == 'O') {
			return 0x4F;
		} else if ($gsm7Char == 'P') {
			return 0x50;
		} else if ($gsm7Char == 'Q') {
			return 0x51;
		} else if ($gsm7Char == 'R') {
			return 0x52;
		} else if ($gsm7Char == 'S') {
			return 0x53;
		} else if ($gsm7Char == 'T') {
			return 0x54;
		} else if ($gsm7Char == 'U') {
			return 0x55;
		} else if ($gsm7Char == 'V') {
			return 0x56;
		} else if ($gsm7Char == 'W') {
			return 0x57;
		} else if ($gsm7Char == 'X') {
			return 0x58;
		} else if ($gsm7Char == 'Y') {
			return 0x59;
		} else if ($gsm7Char == 'Z') {
			return 0x5A;
		} else if ($gsm7Char == 'Ä') {
			return 0x5B;
		} else if ($gsm7Char == 'Ö') {
			return 0x5C;
		} else if ($gsm7Char == 'Ñ') {
			return 0x5D;
		} else if ($gsm7Char == 'Ü') {
			return 0x5E;
		} else if ($gsm7Char == '§') {
			return 0x5F;
		} else if ($gsm7Char == '¿') {
			return 0x60;
		} else if ($gsm7Char == 'a') {
			return 0x61;
		} else if ($gsm7Char == 'b') {
			return 0x62;
		} else if ($gsm7Char == 'c') {
			return 0x63;
		} else if ($gsm7Char == 'd') {
			return 0x64;
		} else if ($gsm7Char == 'e') {
			return 0x65;
		} else if ($gsm7Char == 'f') {
			return 0x66;
		} else if ($gsm7Char == 'g') {
			return 0x67;
		} else if ($gsm7Char == 'h') {
			return 0x68;
		} else if ($gsm7Char == 'i') {
			return 0x69;
		} else if ($gsm7Char == 'j') {
			return 0x6A;
		} else if ($gsm7Char == 'k') {
			return 0x6B;
		} else if ($gsm7Char == 'l') {
			return 0x6C;
		} else if ($gsm7Char == 'm') {
			return 0x6D;
		} else if ($gsm7Char == 'n') {
			return 0x6E;
		} else if ($gsm7Char == 'o') {
			return 0x6F;
		} else if ($gsm7Char == 'p') {
			return 0x70;
		} else if ($gsm7Char == 'q') {
			return 0x71;
		} else if ($gsm7Char == 'r') {
			return 0x72;
		} else if ($gsm7Char == 's') {
			return 0x73;
		} else if ($gsm7Char == 't') {
			return 0x74;
		} else if ($gsm7Char == 'u') {
			return 0x75;
		} else if ($gsm7Char == 'v') {
			return 0x76;
		} else if ($gsm7Char == 'w') {
			return 0x77;
		} else if ($gsm7Char == 'x') {
			return 0x78;
		} else if ($gsm7Char == 'y') {
			return 0x79;
		} else if ($gsm7Char == 'z') {
			return 0x7A;
		} else if ($gsm7Char == 'ä') {
			return 0x7B;
		} else if ($gsm7Char == 'ö') {
			return 0x7C;
		} else if ($gsm7Char == 'ñ') {
			return 0x7D;
		} else if ($gsm7Char == 'ü') {
			return 0x7E;
		} else if ($gsm7Char == 'à') {
			return 0x7F;
		} else if ($gsm7Char == self::ESC_REPLACEMENT) {
			return 0x1B;
		} else {
			return -1;
		}

	}

	public function int2Gsm7Char($value) {
		if ($value == 0) {
			return '@';
		} else if ($value == 1) {
			return '£';
		} else if ($value == 2) {
			return '$';
		} else if ($value == 3) {
			return '¥';
		} else if ($value == 4) {
			return 'è';
		} else if ($value == 5) {
			return 'é';
		} else if ($value == 6) {
			return 'ù';
		} else if ($value == 7) {
			return 'ì';
		} else if ($value == 8) {
			return 'ò';
		} else if ($value == 9) {
			return 'Ç';
		} else if ($value == 10) {
			return "\n";
		} else if ($value == 11) {
			return 'Ø';
		} else if ($value == 12) {
			return 'ø';
		} else if ($value == 13) {
			return "\r";
		} else if ($value == 14) {
			return 'Å';
		} else if ($value == 15) {
			return 'å';
		} else if ($value == 16) {
			return 'Δ';
		} else if ($value == 17) {
			return '_';
		} else if ($value == 18) {
			return 'Φ';
		} else if ($value == 19) {
			return 'Γ';
		} else if ($value == 20) {
			return 'Λ';
		} else if ($value == 21) {
			return 'Ω';
		} else if ($value == 22) {
			return 'Π';
		} else if ($value == 23) {
			return 'Ψ';
		} else if ($value == 24) {
			return 'Σ';
		} else if ($value == 25) {
			return 'Θ';
		} else if ($value == 26) {
			return 'Ξ';
		} else if ($value == 27) {
			return '{';
		} else if ($value == 28) {
			return 'Æ';
		} else if ($value == 29) {
			return 'æ';
		} else if ($value == 30) {
			return 'ß';
		} else if ($value == 31) {
			return 'É';
		} else if ($value == 32) {
			return ' ';
		} else if ($value == 33) {
			return '!';
		} else if ($value == 34) {
			return '"';
		} else if ($value == 35) {
			return '#';
		} else if ($value == 36) {
			return '¤';
		} else if ($value == 37) {
			return '%';
		} else if ($value == 38) {
			return '&';
		} else if ($value == 39) {
			return '\'';
		} else if ($value == 40) {
			return '(';
		} else if ($value == 41) {
			return ')';
		} else if ($value == 42) {
			return '*';
		} else if ($value == 43) {
			return '+';
		} else if ($value == 44) {
			return ',';
		} else if ($value == 45) {
			return '-';
		} else if ($value == 46) {
			return '.';
		} else if ($value == 47) {
			return '/';
		} else if ($value == 48) {
			return '0';
		} else if ($value == 49) {
			return '1';
		} else if ($value == 50) {
			return '2';
		} else if ($value == 51) {
			return '3';
		} else if ($value == 52) {
			return '4';
		} else if ($value == 53) {
			return '5';
		} else if ($value == 54) {
			return '6';
		} else if ($value == 55) {
			return '7';
		} else if ($value == 56) {
			return '8';
		} else if ($value == 57) {
			return '9';
		} else if ($value == 58) {
			return ':';
		} else if ($value == 59) {
			return ';';
		} else if ($value == 60) {
			return '<';
		} else if ($value == 61) {
			return '=';
		} else if ($value == 62) {
			return '>';
		} else if ($value == 63) {
			return '?';
		} else if ($value == 64) {
			return '¡';
		} else if ($value == 65) {
			return 'A';
		} else if ($value == 66) {
			return 'B';
		} else if ($value == 67) {
			return 'C';
		} else if ($value == 68) {
			return 'D';
		} else if ($value == 69) {
			return 'E';
		} else if ($value == 70) {
			return 'F';
		} else if ($value == 71) {
			return 'G';
		} else if ($value == 72) {
			return 'H';
		} else if ($value == 73) {
			return 'I';
		} else if ($value == 74) {
			return 'J';
		} else if ($value == 75) {
			return 'K';
		} else if ($value == 76) {
			return 'L';
		} else if ($value == 77) {
			return 'M';
		} else if ($value == 78) {
			return 'N';
		} else if ($value == 79) {
			return 'O';
		} else if ($value == 80) {
			return 'P';
		} else if ($value == 81) {
			return 'Q';
		} else if ($value == 82) {
			return 'R';
		} else if ($value == 83) {
			return 'S';
		} else if ($value == 84) {
			return 'T';
		} else if ($value == 85) {
			return 'U';
		} else if ($value == 86) {
			return 'V';
		} else if ($value == 87) {
			return 'W';
		} else if ($value == 88) {
			return 'X';
		} else if ($value == 89) {
			return 'Y';
		} else if ($value == 90) {
			return 'Z';
		} else if ($value == 91) {
			return 'Ä';
		} else if ($value == 92) {
			return 'Ö';
		} else if ($value == 93) {
			return 'Ñ';
		} else if ($value == 94) {
			return 'Ü';
		} else if ($value == 95) {
			return '§';
		} else if ($value == 96) {
			return '¿';
		} else if ($value == 97) {
			return 'a';
		} else if ($value == 98) {
			return 'b';
		} else if ($value == 99) {
			return 'c';
		} else if ($value == 100) {
			return 'd';
		} else if ($value == 101) {
			return 'e';
		} else if ($value == 102) {
			return 'f';
		} else if ($value == 103) {
			return 'g';
		} else if ($value == 104) {
			return 'h';
		} else if ($value == 105) {
			return 'i';
		} else if ($value == 106) {
			return 'j';
		} else if ($value == 107) {
			return 'k';
		} else if ($value == 108) {
			return 'l';
		} else if ($value == 109) {
			return 'm';
		} else if ($value == 110) {
			return 'n';
		} else if ($value == 111) {
			return 'o';
		} else if ($value == 112) {
			return 'p';
		} else if ($value == 113) {
			return 'q';
		} else if ($value == 114) {
			return 'r';
		} else if ($value == 115) {
			return 's';
		} else if ($value == 116) {
			return 't';
		} else if ($value == 117) {
			return 'u';
		} else if ($value == 118) {
			return 'v';
		} else if ($value == 119) {
			return 'w';
		} else if ($value == 120) {
			return 'x';
		} else if ($value == 121) {
			return 'y';
		} else if ($value == 122) {
			return 'z';
		} else if ($value == 123) {
			return 'ä';
		} else if ($value == 124) {
			return 'ö';
		} else if ($value == 125) {
			return 'ñ';
		} else if ($value == 126) {
			return 'ü';
		} else if ($value == 127) {
			return 'à';
		} else {
			return '}';
		}

	}

}

?>