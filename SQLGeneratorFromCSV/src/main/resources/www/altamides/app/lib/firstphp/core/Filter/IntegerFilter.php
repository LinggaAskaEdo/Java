<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of IntegerFilter
 *
 * @author Setia Budi Halim
 */
class IntegerFilter extends PhpFilter
{

    /**
     * 
     * @param int $min
     * @param int $max
     * @param int $flags
     */
    public function __construct($min = null, $max = null, $flags = null)
    {
        $options = [
        ];

        if ($min !== null) {
            $options['min_range'] = (int) $min;
        }
        if ($max !== null) {
            $options['max_range'] = (int) $max;
        }
        parent::__construct(\FILTER_VALIDATE_INT, $flags, $options);
    }

}
