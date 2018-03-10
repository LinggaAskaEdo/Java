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
 * This exception is thrown when the given value has invalid pattern
 * E.g., given alphanumeric when it should be all numeric
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class InvalidPatternException extends ValidationException
{
    protected function createMessage()
    {
        return "Value of <{$this->field}> has invalid pattern: {$this->value}";
    }
}
