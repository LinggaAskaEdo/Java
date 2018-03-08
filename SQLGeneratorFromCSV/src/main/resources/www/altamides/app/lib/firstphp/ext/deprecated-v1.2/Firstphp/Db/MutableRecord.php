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
 * Description of MutableDbRecord
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
abstract class MutableRecord extends DbRecord
{

    /**
     *
     * @var bool Data is not loaded from persistent storage
     */
    protected $existing = false;

    /**
     *
     * @var bool Data is not yet saved after modification
     */
    protected $unsaved = true;

    /**
     * 
     * @throws \Exception
     */
    abstract protected function doInsert();

    /**
     * 
     * @throws \Exception
     */
    abstract protected function doUpdate();

    /**
     * 
     * @throws \Exception
     */
    abstract protected function doDelete();
    
    /**
     * 
     * @throws \Exception
     */
    abstract protected function validateFields();
    
    /**
     * @throws \Exception
     */
    public function save()
    {
        if (!$this->unsaved) {
            return;
        }

        $this->validateFields();

        if ($this->existing) {
            $this->doUpdate();
        } else {
            $this->doInsert();
            $this->existing = true;
        }

        $this->unsaved = false;
    }
    
    public function edit($id)
    {
	if ($this->existing && !$this->unsaved) {
	    return;	
	}
        $this->read($id);
        $this->fields[$this->keyName] = $id;
    }
    
    public function create($id = null)
    {
        $this->fields[$this->keyName] = $id;
        $this->unsaved  = true;
        $this->existing = false;
    }

    /**
     * @throws \Exception
     */
    public function delete()
    {
        $this->doDelete();
        $this->unsaved  = true;
        $this->existing = true;
    }

    public function read($id)
    {
        parent::read($id);
        $this->unsaved = false;
        $this->existing = true;
    }


    public function setField($field, $value)
    {
        if (!$this->hasField($field)) {
            throw new \OutOfBoundsException('Field not found: ' . $field);
        }

        $this->fields[$field] = $value;

        $this->unsaved = true;
    }

    /**
     * 
     * @param type $offset
     * @param type $value
     */
    public function offsetSet($offset, $value)
    {
        $this->setField($offset, $value);
    }
}
