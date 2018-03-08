<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Encoding;

interface IStringBitArrayContainerAdapter extends IBitArrayContainer {

    public function getAsString();

    public function setAsString($value);
}