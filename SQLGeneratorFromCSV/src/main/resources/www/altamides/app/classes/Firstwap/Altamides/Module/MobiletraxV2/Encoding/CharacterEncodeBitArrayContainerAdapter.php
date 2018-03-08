<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

class CharacterEncodeBitArrayContainerAdapter extends AbstractBitArrayContainerAdapter implements IStringBitArrayContainerAdapter {

    public function __construct($bitStream) {
        parent::__construct($bitStream);
    }

    public function getAsString() {
        // Maximal 32K can be encoded
        $size = $this->size();
        if ($size >= 0 && $size < 262144) {
            $temp = new ByteStoredBitArrayContainer();
            $temp->setRange(0, $size, 18, false);

            $result = $this->getChar($temp->getRange(0, 6, false)) .
                    $this->getChar($temp->getRange(6, 6, false)) .
                    $this->getChar($temp->getRange(12, 6, false));

            $index = 0;
            while ($index < $size) {
                $result .= $this->getChar($this->getRange($index, 6, false));
                $index+=6;
            }
            return $result;
        }
        return null;
    }

    public function getBytes() {
        $data = $this->getAsString();
        return \preg_split('//u', $data, -1, \PREG_SPLIT_NO_EMPTY);
    }

    public function setAsString($value) {
        $this->clear();
        if (\strlen($value) > 2) {
            $temp = new ByteStoredBitArrayContainer();
            $temp->setRange(0, $this->getValue(\substr($value, 0, 1)), 6, false);
            $temp->setRange(6, $this->getValue(\substr($value, 1, 1)), 6, false);
            $temp->setRange(12, $this->getValue(\substr($value, 2, 1)), 6, false);

            $size = $temp->getRange(0, 18, false);

            $index = 3;
            $offset = 0;
            while ($index < \strlen($value)) {
                //echo '[' . \substr($value, $index, 1) . ']';
                $data = $this->getValue(\substr($value, $index, 1));
                if (!is_null($data)) {
                    //echo $data;
                    $this->setRange($offset, $data, 6, false);
                    $offset += 6;
                }
                //echo '<br />';
                $index++;
            }

            $this->setSize($size);
        }
    }

    protected function getChar($value) {
        switch ($value) {
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
            case 16: return 'G';
            case 17: return 'H';
            case 18: return 'I';
            case 19: return 'J';
            case 20: return 'K';
            case 21: return 'L';
            case 22: return 'M';
            case 23: return 'N';
            case 24: return 'O';
            case 25: return 'P';
            case 26: return 'Q';
            case 27: return 'R';
            case 28: return 'S';
            case 29: return 'T';
            case 30: return 'U';
            case 31: return 'V';
            case 32: return 'W';
            case 33: return 'X';
            case 34: return 'Y';
            case 35: return 'Z';
            case 36: return '@';
            case 37: return '*';
            case 38: return 'a';
            case 39: return 'b';
            case 40: return 'c';
            case 41: return 'd';
            case 42: return 'e';
            case 43: return 'f';
            case 44: return 'g';
            case 45: return 'h';
            case 46: return 'i';
            case 47: return 'j';
            case 48: return 'k';
            case 49: return 'l';
            case 50: return 'm';
            case 51: return 'n';
            case 52: return 'o';
            case 53: return 'p';
            case 54: return 'q';
            case 55: return 'r';
            case 56: return 's';
            case 57: return 't';
            case 58: return 'u';
            case 59: return 'v';
            case 60: return 'w';
            case 61: return 'x';
            case 62: return 'y';
            case 63: return 'z';
            default: return ' ';
        }
    }

    protected function getValue($value) {
        switch ($value) {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
            case 'G': return 16;
            case 'H': return 17;
            case 'I': return 18;
            case 'J': return 19;
            case 'K': return 20;
            case 'L': return 21;
            case 'M': return 22;
            case 'N': return 23;
            case 'O': return 24;
            case 'P': return 25;
            case 'Q': return 26;
            case 'R': return 27;
            case 'S': return 28;
            case 'T': return 29;
            case 'U': return 30;
            case 'V': return 31;
            case 'W': return 32;
            case 'X': return 33;
            case 'Y': return 34;
            case 'Z': return 35;
            case '@': return 36;
            case '*': return 37;
            case 'a': return 38;
            case 'b': return 39;
            case 'c': return 40;
            case 'd': return 41;
            case 'e': return 42;
            case 'f': return 43;
            case 'g': return 44;
            case 'h': return 45;
            case 'i': return 46;
            case 'j': return 47;
            case 'k': return 48;
            case 'l': return 49;
            case 'm': return 50;
            case 'n': return 51;
            case 'o': return 52;
            case 'p': return 53;
            case 'q': return 54;
            case 'r': return 55;
            case 's': return 56;
            case 't': return 57;
            case 'u': return 58;
            case 'v': return 59;
            case 'w': return 60;
            case 'x': return 61;
            case 'y': return 62;
            case 'z': return 63;
            default: return null;
        }
    }

}