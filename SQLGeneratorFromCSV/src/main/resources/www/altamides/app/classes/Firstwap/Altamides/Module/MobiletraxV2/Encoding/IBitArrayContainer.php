<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

interface IBitArrayContainer {

    public function capacity();

    public function clear();

    public function get($index);  // $index = long, return Boolean

    public function getBytes(); // return Array

    public function getRange($index, $length, $signed = false); // $index = long, $length = int, $signed = Boolean, returns Long

    public function set($index, $value);

    public function setRange($index, $value, $length, $signed = false);

    public function setSize($size);

    public function size();
}

