<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

/**
 *
 * @author Setia Budi Halim
 */
abstract class AbstractInput
{

    /**
     *
     * @var string
     */
    protected $name;

    /**
     *
     * @var mixed
     */
    protected $value = null;

    /**
     *
     * @var mixed
     */
    protected $default = null;

    /**
     *
     * @var bool
     */
    protected $readOnly = false;

    /**
     *
     * @var boolean
     */
    protected $disabled = false;

    /**
     *
     * @var callback
     */
    protected $valueObservers = [];

    public function __construct($name)
    {
        $this->name = (string) $name;
    }

    /**
     * Get the input name
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Get current value
     */
    public function getValue()
    {
        return $this->value;
    }

    /**
     * Set current value
     * @param mixed $value
     */
    public function setValue($value)
    {
        if ($this->isReadOnly()) {
            return;
        }
        $old         = $this->value;
        $this->value = $value;
        if ($this->isDifferentValue($value, $old)) {
            $this->onValueChange($value, $old);
        }
    }

    /**
     * Set value from using submitted value
     * @param int $method See \INPUT_*
     */
    public function inputValue($method = \INPUT_POST)
    {
        $this->setValue(\filter_input($method, $this->getName(),
                                      \FILTER_DEFAULT, \FILTER_NULL_ON_FAILURE));
    }

    /**
     * Set the default value
     * @param mixed $default
     */
    public function setDefault($default)
    {
        $this->default = $default;
    }

    /**
     * Get the default value
     * @return mixed 
     */
    public function getDefault()
    {
        return $this->default;
    }

    /**
     * Reset the value to default
     */
    public function reset()
    {
        $this->value = $this->default;
    }

    /**
     * Is this input read-only?
     */
    public function isReadOnly()
    {
        return $this->readOnly;
    }

    /**
     * Set this input to read-only or read-write
     * @param bool $readOnly
     */
    public function setReadOnly($readOnly)
    {
        $this->readOnly = (bool) $readOnly;
    }

    /**
     * 
     * @return boolean
     */
    public function isDisabled()
    {
        return $this->disabled;
    }

    /**
     * 
     * @param boolean $disabled Disablement
     */
    public function setDisabled($disabled)
    {
        $this->disabled = (bool) $disabled;
    }

    protected function isDifferentValue($new, $old)
    {
        return $new !== $old;
    }

    public function attachValueObserver(callable $observer)
    {
        $this->valueObservers = $observer;
    }

    protected function onValueChange($new, $old)
    {
        foreach ($this->valueObservers as $index => $observer) {
            try {
                $this->notifyValueObserver($observer, $new, $old);
            } catch (\Exception $e) {
                \trigger_error("Value observer '$index' failed: $e",
                               \E_USER_WARNING);
            }
        }
    }

    protected function notifyValueObserver($observer, $new, $old)
    {
        if (!\is_callable($observer)) {
            throw new \UnexpectedValueException('Observer must be a callable');
        }
        $observer($new, $old);
    }

}
