<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : ValueCodeMapperBase.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class ValueCodeMapperBase
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Codec;

/**
 * Description of ValueCodeMapperBase
 *
 * @author Setia Budi Halim
 */
class SimpleValueMapper implements ValueMapperInterface
{

    /**
     *
     * @var array
     */
    protected $map = array();
    
    /**
     *
     * @var mixed
     */
    protected $errorCode  = '';
    
    /**
     *
     * @var mixed
     */
    protected $errorValue = null;

    /**
     * 
     * @param int|string $value
     * @return mixed
     */
    public function encode($value)
    {
        return isset($this->map[$value]) ? $this->map[$value] : $this->errorCode;
    }

    /**
     * 
     * @param mixed $code
     * @return int|string
     */
    public function decode($code)
    {
        $value = array_search($code, $this->map);
        return $value !== false ? $value : $this->errorValue;
    }

    /**
     * 
     * @param array $map
     */
    public function setMap(array $map)
    {
        $this->map = $map;
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
     * @param int|string $value
     * @param mixed $code
     */
    public function addMap($value, $code)
    {
        $value = $this->sanitiseValue($value);
        
        $this->map[$value] = $code;
    }

    /**
     * 
     * @param int|string $value
     * @throws \OutOfBoundsException
     */
    public function removeMap($value)
    {
        if (!isset($this->map[$value])) {
            throw new \OutOfBoundsException("No map for value '$value'");
        }

        unset($this->map[$value]);
    }

}