<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of TextFilter
 *
 * @author Setia Budi Halim
 */
class TextFilter extends PhpFilter
{
    public function __construct()
    {
        parent::__construct(\FILTER_UNSAFE_RAW);
    }
}
