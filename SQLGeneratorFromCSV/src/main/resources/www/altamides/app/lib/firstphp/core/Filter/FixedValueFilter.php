<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of FixedValueFilter
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class FixedValueFilter extends AbstractFilter
{

    protected $fixedValue = null;

    public function __construct($value)
    {
        $this->fixedValue = $value;
    }

    public function getFixedValue()
    {
        return $this->fixedValue;
    }

    protected function filter($value, &$validity)
    {
        $validity = $value === $this->fixedValue;
        return $this->fixedValue;
    }

}
