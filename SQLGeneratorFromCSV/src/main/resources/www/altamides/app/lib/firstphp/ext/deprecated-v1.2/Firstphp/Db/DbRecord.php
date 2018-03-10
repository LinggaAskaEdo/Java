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
 * 
 */

namespace Firstwap\Firstphp\Db;

/**
 * Description of DataRecord
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
abstract class DbRecord implements \ArrayAccess
{

    protected $keyName;
    protected $fields = array();

    /**
     * 
     * @throws \Exception
     */
    abstract protected function doRead($id);

    public function getField($field)
    {
        if (!$this->hasField($field)) {
            throw new \OutOfBoundsException('Field not found: ' . $field);
        }

        return $this->fields[$field];
    }

    public function listFields()
    {
        return $this->fields;
    }

    public function getId()
    {
        return isset($this->fields[$this->keyName]) ? $this->fields[$this->keyName] : null;
    }

    public function hasField($field)
    {
        return array_key_exists($field, $this->fields);
    }

    /**
     * 
     * @param type $offset
     * @return type
     */
    public function offsetExists($offset)
    {
        return $this->hasField($offset);
    }

    /**
     * 
     * @param type $offset
     * @return type
     */
    public function offsetGet($offset)
    {
        return $this->getField($offset);
    }

    /**
     * 
     * @param type $offset
     * @throws \Exception
     */
    public function offsetUnset($offset)
    {
        throw new \Exception('Can not remove key ' . $offset);
    }

    /**
     * 
     * @param mixed $id the record id
     * @throws \Exception
     */
    public function read($id)
    {
        $this->doRead($id);
    }

}
