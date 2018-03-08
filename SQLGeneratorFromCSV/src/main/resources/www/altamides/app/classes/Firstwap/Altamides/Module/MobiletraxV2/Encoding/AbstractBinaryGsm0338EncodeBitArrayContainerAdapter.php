<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

abstract class AbstractBinaryGsm0338EncodeBitArrayContainerAdapter extends AbstractBitArrayContainerAdapter implements IStringBitArrayContainerAdapter {

    public function __construct($bitStream) {
        parent::__construct($bitStream);
    }

    public function getBytes() {
        return $this->convertStringToGSM7Bytes($this->getAsString());
    }

    public function getAsString() {
        $size = $this->size();
        if ($size >= 0) {
            $result = '';
            $index = 0;
            while ($index < $size) {
                $result .= $this->getGsm0338Replacement($this->getRange($index, 7, false));
                $index+=7;
            }
            return $result;
        }
        return null;
    }

    public function setAsString($value) {
        $this->clear();
        if (strlen($value) > 0) {
            $characters = \preg_split('//u', $value, -1, \PREG_SPLIT_NO_EMPTY);
            $offset = 0;
            foreach ($characters as $character) {
                $data = $this->getGsm0338Value($character);
                if (!is_null($data)) {
                    $this->setRange($offset, $data, 7, false);
                    $offset += 7;
                }
            }
            $size = $this->calculateDataSize(count($characters), $this->size());
            if (!\is_null($size)) {
                $this->setSize($size);
            }
        }
    }

    protected function calculateDataSize($num_of_characters, $num_of_bits) {
        return \floor(($num_of_characters * 7) / 8) * 8;
    }

    protected function convertStringToGSM7Bytes($data) {
        $characters = \preg_split('//u', $data, -1, \PREG_SPLIT_NO_EMPTY);

        $result = array();
        foreach ($characters as $character) {
            $temp = $this->GSM7CharacterConvert($character);
            if (!is_null($temp)) {
                foreach ($temp as $value) {
                    $result[] = $value;
                }
            }
        }

        return $result;
    }

    protected function getDefaultGsm0338Value() {
        return null;
    }

    abstract protected function getGsm0338Replacement($value);

    protected function getGsm0338Value($value) {
        $index = 0;
        while ($index < 128) {
            $character = $this->getGsm0338Replacement($index);
            if (strcmp($character, $value) == 0) {
                return $index;
            }
            $index++;
        }
        return $this->getDefaultGsm0338Value();
    }

    protected function GSM7CharacterConvert($character) {
        switch ($character) {
            case '@':
                return $this->GSM7CharacterResult1(0x00);
            case '£':
                return $this->GSM7CharacterResult1(0x01);
            case '$':
                return $this->GSM7CharacterResult1(0x02);
            case '¥':
                return $this->GSM7CharacterResult1(0x03);
            case 'è':
                return $this->GSM7CharacterResult1(0x04);
            case 'é':
                return $this->GSM7CharacterResult1(0x05);
            case 'ù':
                return $this->GSM7CharacterResult1(0x06);
            case 'ì':
                return $this->GSM7CharacterResult1(0x07);
            case 'ò':
                return $this->GSM7CharacterResult1(0x08);
            case 'Ç':
                return $this->GSM7CharacterResult1(0x09);
            case '\n':
                return $this->GSM7CharacterResult1(0x0A);
            case 'Ø':
                return $this->GSM7CharacterResult1(0x0B);
            case 'ø':
                return $this->GSM7CharacterResult1(0x0C);
            case '\r':
                return $this->GSM7CharacterResult1(0x0D);
            case 'Å':
                return $this->GSM7CharacterResult1(0x0E);
            case 'å':
                return $this->GSM7CharacterResult1(0x0F);
            case 'Δ':
                return $this->GSM7CharacterResult1(0x10);
            case '_':
                return $this->GSM7CharacterResult1(0x11);
            case 'Φ':
                return $this->GSM7CharacterResult1(0x12);
            case 'Γ':
                return $this->GSM7CharacterResult1(0x13);
            case 'Λ':
                return $this->GSM7CharacterResult1(0x14);
            case 'Ω':
                return $this->GSM7CharacterResult1(0x15);
            case 'Π':
                return $this->GSM7CharacterResult1(0x16);
            case 'Ψ':
                return $this->GSM7CharacterResult1(0x17);
            case 'Σ':
                return $this->GSM7CharacterResult1(0x18);
            case 'Θ':
                return $this->GSM7CharacterResult1(0x19);
            case 'Ξ':
                return $this->GSM7CharacterResult1(0x1A);
            case 'Æ':
                return $this->GSM7CharacterResult1(0x1C);
            case 'æ':
                return $this->GSM7CharacterResult1(0x1D);
            case 'ß':
                return $this->GSM7CharacterResult1(0x1E);
            case 'É':
                return $this->GSM7CharacterResult1(0x1F);
            case ' ':
                return $this->GSM7CharacterResult1(0x20);
            case '!':
                return $this->GSM7CharacterResult1(0x21);
            case '"':
                return $this->GSM7CharacterResult1(0x22);
            case '#':
                return $this->GSM7CharacterResult1(0x23);
            case '¤':
                return $this->GSM7CharacterResult1(0x24);
            case '%':
                return $this->GSM7CharacterResult1(0x25);
            case '&':
                return $this->GSM7CharacterResult1(0x26);
            case '\'':
                return $this->GSM7CharacterResult1(0x27);
            case '(':
                return $this->GSM7CharacterResult1(0x28);
            case ')':
                return $this->GSM7CharacterResult1(0x29);
            case '*':
                return $this->GSM7CharacterResult1(0x2A);
            case '+':
                return $this->GSM7CharacterResult1(0x2B);
            case ',':
                return $this->GSM7CharacterResult1(0x2C);
            case '-':
                return $this->GSM7CharacterResult1(0x2D);
            case '.':
                return $this->GSM7CharacterResult1(0x2E);
            case '/':
                return $this->GSM7CharacterResult1(0x2F);
            case '0':
                return $this->GSM7CharacterResult1(0x30);
            case '1':
                return $this->GSM7CharacterResult1(0x31);
            case '2':
                return $this->GSM7CharacterResult1(0x32);
            case '3':
                return $this->GSM7CharacterResult1(0x33);
            case '4':
                return $this->GSM7CharacterResult1(0x34);
            case '5':
                return $this->GSM7CharacterResult1(0x35);
            case '6':
                return $this->GSM7CharacterResult1(0x36);
            case '7':
                return $this->GSM7CharacterResult1(0x37);
            case '8':
                return $this->GSM7CharacterResult1(0x38);
            case '9':
                return $this->GSM7CharacterResult1(0x39);
            case ':':
                return $this->GSM7CharacterResult1(0x3A);
            case ';':
                return $this->GSM7CharacterResult1(0x3B);
            case '<':
                return $this->GSM7CharacterResult1(0x3C);
            case '=':
                return $this->GSM7CharacterResult1(0x3D);
            case '>':
                return $this->GSM7CharacterResult1(0x3E);
            case '?':
                return $this->GSM7CharacterResult1(0x3F);
            case '¡':
                return $this->GSM7CharacterResult1(0x40);
            case 'A':
                return $this->GSM7CharacterResult1(0x41);
            case 'B':
                return $this->GSM7CharacterResult1(0x42);
            case 'C':
                return $this->GSM7CharacterResult1(0x43);
            case 'D':
                return $this->GSM7CharacterResult1(0x44);
            case 'E':
                return $this->GSM7CharacterResult1(0x45);
            case 'F':
                return $this->GSM7CharacterResult1(0x46);
            case 'G':
                return $this->GSM7CharacterResult1(0x47);
            case 'H':
                return $this->GSM7CharacterResult1(0x48);
            case 'I':
                return $this->GSM7CharacterResult1(0x49);
            case 'J':
                return $this->GSM7CharacterResult1(0x4A);
            case 'K':
                return $this->GSM7CharacterResult1(0x4B);
            case 'L':
                return $this->GSM7CharacterResult1(0x4C);
            case 'M':
                return $this->GSM7CharacterResult1(0x4D);
            case 'N':
                return $this->GSM7CharacterResult1(0x4E);
            case 'O':
                return $this->GSM7CharacterResult1(0x4F);
            case 'P':
                return $this->GSM7CharacterResult1(0x50);
            case 'Q':
                return $this->GSM7CharacterResult1(0x51);
            case 'R':
                return $this->GSM7CharacterResult1(0x52);
            case 'S':
                return $this->GSM7CharacterResult1(0x53);
            case 'T':
                return $this->GSM7CharacterResult1(0x54);
            case 'U':
                return $this->GSM7CharacterResult1(0x55);
            case 'V':
                return $this->GSM7CharacterResult1(0x56);
            case 'W':
                return $this->GSM7CharacterResult1(0x57);
            case 'X':
                return $this->GSM7CharacterResult1(0x58);
            case 'Y':
                return $this->GSM7CharacterResult1(0x59);
            case 'Z':
                return $this->GSM7CharacterResult1(0x5A);
            case 'Ä':
                return $this->GSM7CharacterResult1(0x5B);
            case 'Ö':
                return $this->GSM7CharacterResult1(0x5C);
            case 'Ñ':
                return $this->GSM7CharacterResult1(0x5D);
            case 'Ü':
                return $this->GSM7CharacterResult1(0x5E);
            case '§':
                return $this->GSM7CharacterResult1(0x5F);
            case '¿':
                return $this->GSM7CharacterResult1(0x60);
            case 'a':
                return $this->GSM7CharacterResult1(0x61);
            case 'b':
                return $this->GSM7CharacterResult1(0x62);
            case 'c':
                return $this->GSM7CharacterResult1(0x63);
            case 'd':
                return $this->GSM7CharacterResult1(0x64);
            case 'e':
                return $this->GSM7CharacterResult1(0x65);
            case 'f':
                return $this->GSM7CharacterResult1(0x66);
            case 'g':
                return $this->GSM7CharacterResult1(0x67);
            case 'h':
                return $this->GSM7CharacterResult1(0x68);
            case 'i':
                return $this->GSM7CharacterResult1(0x69);
            case 'j':
                return $this->GSM7CharacterResult1(0x6A);
            case 'k':
                return $this->GSM7CharacterResult1(0x6B);
            case 'l':
                return $this->GSM7CharacterResult1(0x6C);
            case 'm':
                return $this->GSM7CharacterResult1(0x6D);
            case 'n':
                return $this->GSM7CharacterResult1(0x6E);
            case 'o':
                return $this->GSM7CharacterResult1(0x6F);
            case 'p':
                return $this->GSM7CharacterResult1(0x70);
            case 'q':
                return $this->GSM7CharacterResult1(0x71);
            case 'r':
                return $this->GSM7CharacterResult1(0x72);
            case 's':
                return $this->GSM7CharacterResult1(0x73);
            case 't':
                return $this->GSM7CharacterResult1(0x74);
            case 'u':
                return $this->GSM7CharacterResult1(0x75);
            case 'v':
                return $this->GSM7CharacterResult1(0x76);
            case 'w':
                return $this->GSM7CharacterResult1(0x77);
            case 'x':
                return $this->GSM7CharacterResult1(0x78);
            case 'y':
                return $this->GSM7CharacterResult1(0x79);
            case 'z':
                return $this->GSM7CharacterResult1(0x7A);
            case 'ä':
                return $this->GSM7CharacterResult1(0x7B);
            case 'ö':
                return $this->GSM7CharacterResult1(0x7C);
            case 'ñ':
                return $this->GSM7CharacterResult1(0x7D);
            case 'ü':
                return $this->GSM7CharacterResult1(0x7E);
            case 'à':
                return $this->GSM7CharacterResult1(0x7F);
            case '\f':
                return $this->GSM7CharacterResult2(0x1B, 0x0A);
            case '^':
                return $this->GSM7CharacterResult2(0x1B, 0x14);
            case '{':
                return $this->GSM7CharacterResult2(0x1B, 0x28);
            case '}':
                return $this->GSM7CharacterResult2(0x1B, 0x29);
            case '\\':
                return $this->GSM7CharacterResult2(0x1B, 0x2F);
            case '[':
                return $this->GSM7CharacterResult2(0x1B, 0x3C);
            case '~':
                return $this->GSM7CharacterResult2(0x1B, 0x3D);
            case ']':
                return $this->GSM7CharacterResult2(0x1B, 0x3E);
            case '|':
                return $this->GSM7CharacterResult2(0x1B, 0x40);
            case '€':
                return $this->GSM7CharacterResult2(0x1B, 0x65);
            default:
                return null;
        }
    }

    protected function GSM7CharacterResult1($value1) {
        $result = array();
        $result[] = $value1;
        return $result;
    }

    protected function GSM7CharacterResult2($value1, $value2) {
        $result = array();
        $result[] = $value1;
        $result[] = $value2;
        return $result;
    }

}
