<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Transform nothing validate nothing
 *
 * @author Setia Budi Halim
 */
final class NoFilter extends AbstractFilter
{

    protected function filter($value, &$validity)
    {
        $validity = true;
        return $value;
    }

}
