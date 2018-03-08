<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

use Firstwap\Firstphp\Filter\IntegerFilter;

/**
 * Description of FormInput
 *
 * @author Setia Budi Halim
 */
class IntegerNumberInput extends AbstractInput
{

    /**
     *
     * @var IntegerFilter
     */
    protected $filter;

    /**
     * 
     * @param string $name
     */
    public function __construct($name, $min = null, $max = null)
    {
        parent::__construct($name);
        $this->filter = new IntegerFilter($min, $max);
    }

    /**
     * 
     * @return IntegerFilter
     */
    public function getFilter()
    {
        return $this->filter;
    }

    /**
     * 
     * @param mixed $value The value
     */
    public function setValue($value)
    {
        parent::setValue($this->filter->safeFilter($value, $this->default));
    }

}
