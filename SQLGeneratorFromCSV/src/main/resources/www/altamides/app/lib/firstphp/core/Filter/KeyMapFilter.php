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
class KeyMapFilter extends AbstractFilter
{

    /**
     *
     * @var array 
     */
    protected $map;

    /**
     *
     * @var boolean Use key or use value
     */
    protected $keyBased = false;

    /**
     * 
     * @param array $map
     */
    public function __construct(array $map)
    {
        $this->setMap($map);
    }

    /**
     * Use key or use value
     * @return boolean
     */
    public function isKeyBased()
    {
        return $this->keyBased;
    }

    /**
     * Use key or use value
     * @param boolean $useKey True for key and false for value
     */
    public function setKeyBased($useKey)
    {
        $this->keyBased = (bool) $useKey;
    }

    /**
     * 
     * @return array
     */
    public function getMap()
    {
        return $this->map;
    }

    /**
     * 
     * @param array $domain
     */
    function setMap(array $domain)
    {
        $this->map = $domain;
    }

    /**
     * 
     * @param mixed $value
     * @param bool $valid Whether the value is valid or not
     * @return mixed The filtered value. Can be null or false or something else when the filter fails
     */
    protected function filter($value, &$valid)
    {
        if ($this->keyBased) {
            return $this->filterKey($value, $valid);
        } else {
            return $this->filterValue($value, $valid);
        }
    }

    protected function filterKey($key, &$valid)
    {
        $valid = \array_key_exists($key, $this->map);
        return $valid ? $key : null;
    }

    protected function filterValue($value, &$valid)
    {
        $valid = \in_array($value, $this->map);
        return $valid ? $value : null;
    }

}
