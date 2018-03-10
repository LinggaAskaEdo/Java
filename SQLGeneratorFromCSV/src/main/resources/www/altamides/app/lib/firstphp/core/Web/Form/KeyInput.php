<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

/**
 * Description of ButtonInput
 *
 * @author Setia Budi Halim
 */
class KeyInput extends AbstractInput
{

    /**
     *
     * @var AbstractForm
     */
    protected $form;

    public function __construct($name, AbstractForm $form)
    {
        parent::__construct($name);
        $this->form = $form;
    }

    public function isSubmitted()
    {
        $source = $this->form->getMethod() === FormMethod::GET ? \INPUT_GET : \INPUT_POST;
        return \filter_has_var($source, $this->name);
    }

}
