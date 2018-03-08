<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Form;

use DateTimeInterface;
use Firstwap\Firstphp\Filter\DateTimeFilter;

/**
 * Description of DateTimeInput
 *
 * @author Setia Budi Halim
 */
class DateTimeInput extends AbstractInput
{

    /**
     *
     * @var DateTimeFilter
     */
    protected $dateTimeFilter;

    public function __construct($name)
    {
        $this->dateTimeFilter = new DateTimeFilter();
        parent::__construct($name);
    }

    /**
     * @return DateTimeInterface|null
     */
    public function getValue()
    {
        return parent::getValue();
    }

    public function formatValue($format = 'Y-m-d H:i:s')
    {
        $value = $this->value;
        if ($value instanceof \DateTimeInterface) {
            return $value->format($format);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param mixed $value
     */
    public function setValue($value)
    {
        if ((empty($value) || !\is_object($value)) && ('' === (string) $value)) {
            $value = null;
        }
        $datetime = $this->dateTimeFilter->safeFilter($value, $this->default);
        parent::setValue($datetime);
    }

    /**
     * 
     * @return DateTimeFilter
     */
    public function getFilter()
    {
        return $this->dateTimeFilter;
    }

    /**
     * 
     * @param DateTimeFilter $filter
     */
    public function setFilter(DateTimeFilter $filter)
    {
        $this->dateTimeFilter = $filter;
    }

}
