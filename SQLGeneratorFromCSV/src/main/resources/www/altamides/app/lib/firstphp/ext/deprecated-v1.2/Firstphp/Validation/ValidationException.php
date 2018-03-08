<?php

/*
 * Copyright (c) 2014 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 * 
 * 
 * System           : Firstwap PHP Framework
 * File Version     : SVN: $Id$
 * Initial Creation : 2014-02-24
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 */

namespace Firstwap\Firstphp\Validation;

/**
 * This represent exceptions thrown in value validation
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
abstract class ValidationException extends \Exception
{

    protected $field;
    protected $value;

    abstract protected function createMessage();

    /**
     * Constructor 
     * 
     * @param string $name Field or variable name
     * @param string $value The actual value
     * @param \Exception $previous
     */
    public function __construct($name, $value = null, \Exception $previous = null)
    {
        $this->field = $name;
        $this->value = $value;

        parent::__construct($this->createMessage(), 0, $previous);
    }

    public function getField()
    {
        return $this->field;
    }

    public function getValue()
    {
        return $this->value;
    }

}
