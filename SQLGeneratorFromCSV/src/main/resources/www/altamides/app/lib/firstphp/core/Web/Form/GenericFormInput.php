<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

use Firstwap\Firstphp\Filter\AbstractFilter;
use Firstwap\Firstphp\Filter\AbstractFilter;

/**
 * Description of FormInput
 *
 * @author Setia Budi Halim
 */
class GenericFormInput extends AbstractInput
{

    /**
     *
     * @var AbstractFilter
     */
    protected $filter;
    protected $failSafe = true;

    /**
     * 
     * @param string $name
     * @param AbstractFilter $filter
     */
    public function __construct($name, AbstractFilter $filter)
    {
        parent::__construct($name);
        $this->setFilter($filter);
    }

    /**
     * 
     * @param AbstractFilter $filter
     */
    public function setFilter(AbstractFilter $filter)
    {
        $this->filter = $filter;
    }

    /**
     * Set the value. The value will be filtered by the input filter.
     * 
     * @param mixed $value The value
     */
    public function setValue($value)
    {
        $this->value = $this->filter->safeFilter($value, $this->default);
        parent::setValue($this->filter->safeFilter($value, $this->default));
    }

    /**
     * 
     * @param callback $listener Callback must have this signature function(mixed $value, mixed $oldValue);
     */
    public function attachChangeObserver($listener)
    {
        if (!\is_callable($listener)) {
            throw new \InvalidArgumentException('Change watcher must be a callable type');
        }
        $this->changeListeners[] = $listener;
    }

    public function resetChangeObserver()
    {
        $this->changeListeners = [];
    }

}
