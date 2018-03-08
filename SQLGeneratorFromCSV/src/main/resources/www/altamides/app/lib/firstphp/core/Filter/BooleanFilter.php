<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of BooleanFilter
 *
 * @author Setia Budi Halim
 */
class BooleanFilter extends PhpFilter
{

    public function __construct()
    {
        parent::__construct(\FILTER_VALIDATE_BOOLEAN, \FILTER_NULL_ON_FAILURE);
    }

    protected function filter($value, &$valid)
    {
        $filtered = \filter_var($value, $this->type, $this->options);
        $valid    = null !== $filtered;
        return $filtered;
    }

}
