<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

class BinaryReplaceGsm0338EncodeBitArrayContainerAdapter extends AbstractBinaryGsm0338EncodeBitArrayContainerAdapter {

    public function __construct($bitStream) {
        parent::__construct($bitStream);
    }

    protected function getGsm0338Replacement($value) {
        if ($value >= 0x30 && $value <= 0x39) {
            return \chr($value - 0x30 + \ord('0'));
        } else if ($value >= 0x41 && $value <= 0x5A) {
            return \chr($value - 0x41 + \ord('A'));
        } else if ($value >= 0x61 && $value <= 0x7A) {
            return \chr($value - 0x61 + \ord('a'));
        }

        switch ($value) {
            case 0x00:
                return '@';
            case 0x01:
                return '£'; // Pound
            case 0x02:
                return '$';
            case 0x03:
                return '¥'; // Yen
            case 0x04:
                return 'è'; // `e 
            case 0x05:
                return 'é'; // 'e
            case 0x06:
                return 'ù'; // `u
            case 0x07:
                return 'ì'; // `i
            case 0x08:
                return 'ò'; // `o
            case 0x09:
                return 'Ç'; // ,C
            case 0x0A:
                return '['; // LF
            case 0x0B:
                return 'Ø'; // /O
            case 0x0C:
                return 'ø'; // /o
            case 0x0D:
                return ']'; // CR
            case 0x0E:
                return 'Å'; // 0A
            case 0x0F:
                return 'å'; // 0a
            case 0x10:
                return 'Δ'; // Delta
            case 0x11:
                return '_';
            case 0x12:
                return 'Φ'; // Phi
            case 0x13:
                return 'Γ'; // Gamma
            case 0x14:
                return 'Λ'; // Lambda
            case 0x15:
                return 'Ω'; // Omega
            case 0x16:
                return 'Π'; // Pi
            case 0x17:
                return 'Ψ'; // Psi
            case 0x18:
                return 'Σ'; // Sigma
            case 0x19:
                return 'Θ'; // Theta
            case 0x1A:
                return 'Ξ'; // Xi
            case 0x1B:
                return '^';
            case 0x1C:
                return 'Æ'; // AE
            case 0x1D:
                return 'æ'; // ae
            case 0x1E:
                return 'ß'; // Beta
            case 0x1F:
                return 'É'; // 'E
            case 0x20:
                return '|';
            case 0x21:
                return '!';
            case 0x22:
                return '"';
            case 0x23:
                return '#';
            case 0x24:
                return '¤'; // Currency
            case 0x25:
                return '%';
            case 0x26:
                return '&';
            case 0x27:
                return '\'';
            case 0x28:
                return '(';
            case 0x29:
                return ')';
            case 0x2A:
                return '*';
            case 0x2B:
                return '+';
            case 0x2C:
                return ',';
            case 0x2D:
                return '-';
            case 0x2E:
                return '.';
            case 0x2F:
                return '/';

            case 0x3A:
                return ':';
            case 0x3B:
                return ';';
            case 0x3C:
                return '<';
            case 0x3D:
                return '=';
            case 0x3E:
                return '>';
            case 0x3F:
                return '?';
            case 0x40:
                return '¡'; // Inverted Exlamation

            case 0x5B:
                return 'Ä'; // "A
            case 0x5C:
                return 'Ö'; // "O
            case 0x5D:
                return 'Ñ'; // ~N
            case 0x5E:
                return 'Ü'; // "U
            case 0x5F:
                return '§'; // Paragraph
            case 0x60:
                return '¿'; // Inverted Question Mark

            case 0x7B:
                return 'ä'; // "a
            case 0x7C:
                return 'ö'; // "o
            case 0x7D:
                return 'ñ'; // ~n
            case 0x7E:
                return 'ü'; // "u
            case 0x7F:
                return 'à'; // `a
            default:
                return null;
        }
    }

}