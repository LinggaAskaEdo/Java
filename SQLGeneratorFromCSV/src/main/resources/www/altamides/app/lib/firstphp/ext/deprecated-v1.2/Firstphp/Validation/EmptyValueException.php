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
 * This exception can represent exception for a non-empty field where its value is:
 * - Empty string
 * - NULL
 * - Not set
 * -
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class EmptyValueException extends ValidationException
{

    protected function createMessage()
    {
        return "Empty value for <{$this->field}>";
    }

}
