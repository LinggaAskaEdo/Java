<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

/**
 * Description of GenericForm
 *
 * @author Setia Budi Halim
 */
class GenericForm extends AbstractForm
{

    /**
     *
     * @var AbstractInput[]
     */
    protected $inputList = [];

    public function input()
    {
        $source = $this->getMethod() === FormMethod::GET ? \INPUT_GET : \INPUT_POST;
        foreach ($this->inputList as $input) {
            $input->inputValue($source);
        }
    }

    public function reset()
    {
        foreach ($this->inputList as $input) {
            $input->reset();
        }
    }

    public function getValues()
    {
        $values = [];
        foreach ($this->inputList as $input) {
            $values[$input->getName()] = $input->getValue();
        }
        return $values;
    }
    
    /**
     * 
     * @param AbstractInput $input
     */
    protected function registerInput(AbstractInput $input)
    {
        $this->inputList[$input->getName()] = $input;
    }

}
