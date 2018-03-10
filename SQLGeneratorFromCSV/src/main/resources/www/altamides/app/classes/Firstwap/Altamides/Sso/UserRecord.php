<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sso;

/**
 * Description of UserRecord
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class UserRecord
{

    protected $id;
    protected $name;
    protected $groupId;
    
    public function __toString()
    {
        return $this->name;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getGroupId()
    {
        return $this->groupId;
    }

    public function setId($id)
    {
        $this->id = $id;
    }

    public function setName($name)
    {
        $this->name = $name;
    }

    public function setGroupId($groupId)
    {
        $this->groupId = $groupId;
    }

}
