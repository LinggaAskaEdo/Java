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
 * Initial Creation : 2014-02-13
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 * 
 */

namespace Firstwap\Firstphp\Db;

/**
 * An abstract class to deal with read only data table
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
abstract class ReadonlyTable extends DbTable
{

    /**
     * 
     * @param type $offset
     * @param type $value
     */
    public function offsetSet($offset, $value)
    {
        throw new \Exception('Immutable object');
    }

    /**
     * 
     * @param type $offset
     * @throws \Exception
     */
    public function offsetUnset($offset)
    {
        throw new \Exception('Immutable object');
    }

}
