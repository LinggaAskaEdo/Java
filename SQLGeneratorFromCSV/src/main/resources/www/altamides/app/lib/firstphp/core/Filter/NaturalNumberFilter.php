<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Only allow integer greater than one
 *
 * @author Setia Budi Halim
 */
class NaturalNumberFilter extends IntegerFilter
{

    public function __construct()
    {
        parent::__construct(1);
    }

}
