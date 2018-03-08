<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of FixedListFilter
 *
 * @author Setia Budi Halim
 */
class OptionsFilter extends ListFilter
{

    /**
     * 
     * @param mixed $value
     * @param bool $valid Whether the value is valid or not
     * @return mixed The filtered value. Can be null or false or something else when the filter fails
     */
    protected function filter($value, &$valid)
    {
        $valid = isset($this->domain[$value]);
        return $valid ? $value : null;
    }

}
