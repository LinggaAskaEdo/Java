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
 * Initial Creation : 2014-02-17
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 */

namespace Firstwap\Firstphp\Db;

/**
 * Description of DbTable
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
abstract class DbTable implements \ArrayAccess
{

    protected $keyName;
    protected $records = array();

    abstract protected function read();

    public function hasRecord($id)
    {
        return isset($this->records[$id]);
    }

    public function listRecords()
    {
        return $this->records;
    }

    /**
     * 
     * @param mixed $id
     * @return array
     * @throws \Exception
     */
    public function getRecord($id)
    {
        if ($this->hasRecord($id)) {
            return $this->records[$id];
        }

        throw new \Exception('Row with key ' . $id . ' does not exist');
    }

    /**
     * 
     * @param mixed $offset
     * @return bool
     */
    public function offsetExists($offset)
    {
        return $this->hasRecord($offset);
    }

    /**
     * 
     * @param mixed $offset
     * @return array
     */
    public function offsetGet($offset)
    {
        return $this->getRecord($offset);
    }

}
