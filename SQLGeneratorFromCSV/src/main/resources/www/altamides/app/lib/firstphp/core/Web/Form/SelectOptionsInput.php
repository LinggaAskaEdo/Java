<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

use Firstwap\Firstphp\Filter\KeyMapFilter;

/**
 * Description of SelectOptionsInput
 *
 * @author Setia Budi Halim
 */
class SelectOptionsInput extends AbstractInput
{

    /**
     *
     * @var KeyMapFilter
     */
    protected $filter;

    /**
     *
     * @var null
     */
    protected $welcomeValue;

    /**
     *
     * @var string 
     */
    protected $welcomeText;
    protected $welcome = false;

    /**
     * 
     * @param string $name
     */
    public function __construct($name, array $options = [], $default = null)
    {
        parent::__construct($name);
        $this->filter = new KeyMapFilter($options);
        $this->filter->setKeyBased(true);
        $this->setDefault($default);
    }

    /**
     * 
     * @return KeyMapFilter
     */
    public function getFilter()
    {
        return $this->filter;
    }

    /**
     * 
     * @param array $param
     */
    public function getOptions()
    {
        $options = $this->getFilter()->getMap();
        if ($this->welcome) {
            return [$this->welcomeValue => $this->welcomeText] + $options;
        } else {
            return $options;
        }
    }

    public function enableWelcomeOption($enabled)
    {
        $this->welcome = (bool) $enabled;
    }

    public function setWelcomeOption($text, $value)
    {
        $this->welcomeText = (string) $text;
        $this->welcomeValue = (string) $value;
    }

    /**
     * 
     * @param mixed $value The value
     */
    public function setValue($value)
    {
        if ($this->welcome && ($this->welcomeValue === (string) $value)) {
            return $this->default;
        }
        parent::setValue($this->filter->safeFilter($value, $this->default));
    }

}
