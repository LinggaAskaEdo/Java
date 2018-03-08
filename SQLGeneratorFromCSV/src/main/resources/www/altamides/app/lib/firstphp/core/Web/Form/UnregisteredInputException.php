<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

/**
 * Description of UnregisteredInputException
 *
 * @author Setia Budi Halim
 */
class UnregisteredInputException extends \Exception
{
    public function __construct($name)
    {
        $message = "Unregeistered input: $name";
        parent::__construct($message, 0, null);
    }
}
