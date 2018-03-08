<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

use Firstwap\Firstphp\Filter\AbstractFilter;

/**
 * Description of FilteredInput
 *
 * @author Setia Budi Halim
 */
class FilteredInput extends AbstractInput
{

    /**
     *
     * @var AbstractFilter
     */
    protected $filter;

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
     * @param mixed $value
     */
    public function setValue($value)
    {
        parent::setValue($this->applyFilter($value));
    }

    protected function setFilter(AbstractFilter $filter)
    {
        $this->filter = $filter;
    }

    /**
     * 
     * @param mixed $value
     * @return mixed
     */
    protected function applyFilter($value)
    {
        return $this->filter->safeFilter($value, $this->default);
    }

}
