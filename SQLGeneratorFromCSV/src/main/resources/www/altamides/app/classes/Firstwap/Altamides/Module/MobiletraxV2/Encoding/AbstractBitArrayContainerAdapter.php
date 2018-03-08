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

class AbstractBitArrayContainerAdapter implements IBitArrayContainer {

    private $bitStream;

    public function __construct(IBitArrayContainer $bitStream) {
//        if (is_null($bitStream)) {
//            throw new \Exception("An bitStreamAdapter needs a valid BitStream reference");
//        }

        $this->bitStream = $bitStream;
    }

    public function capacity() {
        return $this->bitStream->capacity();
    }

    public function clear() {
        $this->bitStream->clear();
    }

    public function get($index) {
        return $this->bitStream->get($index);
    }

    public function getBytes() {
        return $this->bitStream->getBytes();
    }

    public function getRange($index, $length, $signed = false) {
        return $this->bitStream->getRange($index, $length, $signed);
    }

    public function set($index, $value) {
        $this->bitStream->set($index, $value);
    }

    public function setRange($index, $value, $length, $signed = false) {
        $this->bitStream->setRange($index, $value, $length, $signed);
    }

    public function setSize($size) {
        return $this->bitStream->setSize($size);
    }

    public function size() {
        return $this->bitStream->size();
    }

}