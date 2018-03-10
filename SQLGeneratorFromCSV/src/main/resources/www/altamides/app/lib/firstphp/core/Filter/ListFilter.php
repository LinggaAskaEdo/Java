<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of FixedListFilter
 *
 * @author Setia Budi Halim
 */
class ListFilter implements AbstractFilter
{

    protected $allowNull = false;

    /**
     *
     * @var array 
     */
    protected $domain;

    /**
     * 
     * @param array $values
     */
    public function __construct(array $values)
    {
        $this->setDomain($values);
    }

    /**
     * 
     * @return array
     */
    public function getDomain()
    {
        return $this->domain;
    }

    /**
     * 
     * @param array $domain
     */
    function setDomain(array $domain)
    {
        $this->domain = $domain;
    }

    public function getAllowNull()
    {
        return $this->allowNull;
    }

    public function setAllowNull($allowNull)
    {
        $this->allowNull = (bool) $allowNull;
    }

    /**
     * Return filtered value or the default value if filter fails
     * @param mixed $value
     * @param mixed $default
     * @return mixed
     */
    public function apply($value, $default)
    {
        $valid = false;
        $filtered = $this->applyFilter($value, $valid);
        return $valid ? $filtered : $default;
    }

    /**
     * Validate whether a value is valid
     * @param mixed $value To be validated value
     * @return mixed The input value
     * @throws \UnexpectedValueException When filter fails
     */
    public function validate($value)
    {
        $valid = false;
        $filtered = $this->applyFilter($value, $valid);
        if ($valid) {
            return $filtered;
        }
        $valueType = \gettype($value);
        $classPath = \explode('\\', self::class);
        $baseName = \end($classPath);
        throw new \UnexpectedValueException("Filter '$baseName' does not allow value: <$valueType> $value");
    }

    /**
     * 
     * @param mixed $value
     * @param bool $valid Whether the value is valid or not
     * @return mixed The filtered value. Can be null or false or something else when the filter fails
     */
    protected function applyFilter($value, &$valid)
    {
        $valid = \in_array($value, $this->domain, true);
        return $valid ? $value : null;
    }

}
