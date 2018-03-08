<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of HexStringBitStreamAdapter
 *
 * @author beni
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

class HexStringBitArrayContainerAdapter extends AbstractBitArrayContainerAdapter implements IStringBitArrayContainerAdapter {

    public function __construct($bitStream) {
        parent::__construct($bitStream);
    }

    public function getAsString() {
        $bit8List = array();
        $size = \Floor(($this->size()) / 8);
        if (($this->size()) % 8 != 0) {
            $size++;
        }
        for ($index = 0; $index < $size; $index++) {
            $bit8List[] = $this->getRange($index * 8, 8, false);
        }

        $desanitizedString = '';
        foreach ($bit8List as $bit) {
            $desanitizedString .= $this->zeroPadding(\dechex($bit), 2); //sprintf("%02x", (float) bindec($bit));
        }
        return $desanitizedString;
    }

    public function setAsString($value) {
        $index = 0;
        for ($i = 0; $i < \mb_strlen($value, 'UTF-8'); $i = $i + 2) {
            $hex = \mb_substr($value, $i, 2, 'UTF-8');
            $this->setRange($index, \hexdec($hex), 8, false);
            $index += 8;
        }
    }

    protected function zeroPadding($value, $length) {
        $value = trim($value);
        while (\strlen($value) < $length) {
            $value = '0' . $value;
        }
        return $value;
    }

}