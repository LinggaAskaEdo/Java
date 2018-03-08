<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of FilterInterface
 *
 * @author Setia Budi Halim
 */
abstract class AbstractFilter
{

    protected $nullable = false;

    /**
     * 
     * @param mixed $value Plain value
     * @param bool $validity False when the input valus is invalid
     * @return mixed Filtered input or null when the value is invalid
     */
    abstract protected function filter($value, &$validity);

    /**
     * 
     * @param mixed $value Plain value
     * @param mixed $fallback
     * @return mixed Filtered value
     */
    public function safeFilter($value, $fallback)
    {
        try {
            if ($this->nullable && (null === $value)) {
                return null;
            }

            $validity = false;
            $filtered = $this->filter($value, $validity);
            return $validity ? $filtered : $fallback;
        } catch (\Exception $e) {
            \error_log($e);
            return $fallback;
        }
    }

    /**
     * 
     * @param mixed $value Plain value
     * @return mixed Filtered value
     * @throws \UnexpectedValueException
     */
    public function strictFilter($value)
    {
        try {
            if ($this->nullable && (null === $value)) {
                return null;
            }

            $validity = false;
            $filtered = $this->filter($value, $validity);
            return $filtered;
        } catch (\Exception $e) {
            \error_log($e);
            throw new InvalidValueException(self::class, $value, $e);
        }
        if (!$validity) {
            throw new InvalidValueException(self::class, $value);
        }
    }

    public function getNullable()
    {
        return $this->nullable;
    }

    public function setNullable($nullable)
    {
        $this->nullable = (bool) $nullable;
    }

}
