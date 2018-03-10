<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

class ByteStoredBitArrayContainer implements IBitArrayContainer {

    private $storage;
    private $storageSize;
    private $swapOrder;

    public function __construct() {
        $this->storage = array();
        $this->storageSize = 0;

        $this->swapOrder = $this->isLittleEndian();
    }

    public function capacity() {
        if (count($this->storage) > 0) {
            return \max(\array_keys($this->storage));
        }
        return 0;
    }

    public function clear() {
        $this->storage = array();
        $this->storageSize = 0;
    }

    public function get($index) {
        if ($index >= 0 && $index < $this->storageSize) {
            return $this->getEx(\floor($index / 8), $index % 8);
        } else {
            return null;
        }
    }

    public function getBytes() {
        $result = array();
        if (count($this->storage) > 0) {
            $max = \max(\array_keys($this->storage));
            for ($index = 0; $index <= $max; $index++) {
                if (isset($this->storage[$index])) {
                    $result[$index] = $this->storage[$index];
                } else {
                    $result[$index] = 0;
                }
            }
        }
        return $result;
    }

    public function getRange($index, $length, $signed = false) {
        if ($index >= 0 && $index < $this->storageSize && $length > 0) {
            $result = 0;

            $byteOffset = \floor($index / 8);
            $bitOffset = $index % 8;
            $bitCount = $length - 1;
            $correction = $bitCount;
            while ($bitCount >= 0) {
                if ($this->getEx($byteOffset, $bitOffset)) {
                    $result += \pow(2, $correction - $bitCount);
                }

                $bitCount--;

                $bitOffset++;
                if ($bitOffset > 7) {
                    $byteOffset++;
                    $bitOffset = 0;
                }
            }

            if ($signed == true) {
                $result = $this->unsignedToSigned($result, $length);
            }

            return $result;
        } else {
            return null;
        }
    }

    public function set($index, $value) {
        if ($index >= 0) {
            $this->setEx(\floor($index / 8), $index % 8, $value);
        }
    }

    public function setRange($index, $value, $length, $signed = false) {
        if ($index >= 0 && $length > 0) {

            $byteOffset = \floor($index / 8);
            $bitOffset = $index % 8;
            $bitCount = $length - 1;
            if ($signed == true) {
                if ($value < 0) {
                    $value = $this->signedToUnsigned($value, $length);
                }
            }

            $correction = $bitCount;
            while ($bitCount >= 0) {
                $bitValue = \pow(2, $this->endianCorrection($correction, $bitCount));
                $this->setEx($byteOffset, $bitOffset, ($value & $bitValue) == $bitValue);

                $bitCount--;

                $bitOffset++;
                if ($bitOffset > 7) {
                    $byteOffset++;
                    $bitOffset = 0;
                }
            }
        }
    }

    public function setSize($size) {
        if ($this->storageSize > $size) {
            $this->storageSize = $size;
        }
    }

    public function size() {
        return $this->storageSize;
    }

    protected function endianCorrection($length, $position) {
        if ($this->swapOrder) {
            return $length - $position;
        } else {
            return $position;
        }
    }

    protected function getEx($byteOffset, $bitOffset) {
        if (!isset($this->storage[$byteOffset])) {
            return false;
        }

        $bitValue = \pow(2, $bitOffset);

        if (($this->storage[$byteOffset] & $bitValue) == $bitValue) {
            return true;
        } else {
            return false;
        }
    }

    protected function setEx($byteOffset, $bitOffset, $value) {
        //echo 'Write: ' . $byteOffset . ' ' . $bitOffset . ' ' . $value . '<br />';
        if (!isset($this->storage[$byteOffset])) {
            $this->storage[$byteOffset] = 0;
        }

        if ($this->storageSize <= $byteOffset * 8 + $bitOffset) {
            $this->storageSize = $byteOffset * 8 + $bitOffset + 1;
        }

        $bitValue = \pow(2, $bitOffset);
        $current = ($this->storage[$byteOffset] & $bitValue) == $bitValue;

        if ($current && !$value) {
            $this->storage[$byteOffset] -= $bitValue;
        } else if (!$current && $value) {
            $this->storage[$byteOffset] += $bitValue;
        }
    }

    protected function signedToUnsigned($value, $length) {
        // Two complements compatible conversion
        if ($value < 0) {
            return \pow(2, $length) + $value;
        }
        return $value;
    }

    protected function unsignedToSigned($value, $length) {
        // Two complements compatible conversion
        if ($value >= \pow(2, $length - 1)) {
            return $value - \pow(2, $length);
        }
        return $value;
    }

    private function isLittleEndian() {
        $testint = 0x00FF;
        $p = \pack('S', $testint);
        return $testint === \current(\unpack('v', $p));
    }

}