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
 * 
 * This exception represent condition when a field contains invalid value
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class InvalidValueException extends ValidationException
{

    protected function createMessage()
    {
        return "Invalid value of <{$this->field}>: {$this->value}";
    }

}
