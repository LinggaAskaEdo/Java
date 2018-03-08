<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of Filter
 *
 * @author Setia Budi Halim
 */
class PhpFilter extends AbstractFilter
{

    protected $type;
    protected $options = [];

    /**
     * 
     * @param int $type See constant \FILTER_*
     * @param int $flag See constant \FILTER_FLAG_*
     * @param array $options
     */
    public function __construct($type, $flag = null, array $options = [])
    {
        $this->type    = (int) $type;
        $this->options = ['options' => $options];
        if (null !== $flag) {
            $this->options['flags'] = (int) $flag;
        }
    }

    /**
     * 
     * @param mixed $value
     * @param bool $valid Whether the value is valid or not
     * @return mixed The filtered value. Can be null or false or something else when the filter fails
     */
    protected function filter($value, &$valid)
    {
        $filtered = \filter_var($value, $this->type, $this->options);
        $valid    = false !== $filtered;
        return $filtered;
    }

}
