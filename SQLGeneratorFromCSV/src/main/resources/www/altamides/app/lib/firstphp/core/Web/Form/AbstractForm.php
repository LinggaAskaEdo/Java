<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

/**
 * Description of MetaForm
 *
 * @author Setia Budi Halim
 */
abstract class AbstractForm
{

    /**
     *
     * @var string
     */
    protected $method   = FormMethod::POST;
    protected $encoding = FormEncoding::URL;
    protected $action   = '';

    /**
     * Read form input
     */
    abstract public function input();

    /**
     * Reset the form state to default
     */
    abstract public function reset();

    public function __construct($action)
    {
        $this->action = (string) $action;
    }

    public function getMethod()
    {
        return $this->method;
    }

    public function getAction()
    {
        return $this->action;
    }

    public function getEncoding()
    {
        return $this->encoding;
    }

    protected function readInputValue($input)
    {
        $source = ($this->method === FormMethod::GET) ? \INPUT_GET : \INPUT_POST;
        return \filter_input($source, $input, \FILTER_DEFAULT,
                             \FILTER_NULL_ON_FAILURE);
    }

}
