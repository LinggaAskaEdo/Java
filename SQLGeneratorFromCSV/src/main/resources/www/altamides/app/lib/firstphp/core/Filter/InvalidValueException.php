<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of InvalidValueException
 *
 * @author Setia Budi Halim
 */
class InvalidValueException extends \Exception
{

    public function __construct($filterName, $value, \Exception $prev = null)
    {
        $stringValue = \gettype($value) . ' ';
        if (!\is_object($value) || \method_exists($value, '__toString')) {
            $stringValue .= $value;
        } else {
            $stringValue .= \get_class($value);
        }
        $message = "Filter $filterName reject value $stringValue";
        parent::__construct($message, 0, $prev);
    }

}
