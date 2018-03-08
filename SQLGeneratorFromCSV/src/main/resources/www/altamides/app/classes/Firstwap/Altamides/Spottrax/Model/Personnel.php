<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Personnel
 *
 * @author sidratul
 */

namespace Firstwap\Altamides\Spottrax\Model;

class Personnel
{

    protected $personnelSid;
    protected $personnelName;
    protected $active;
    protected $personnelNotes;
    protected $createdBy;
    protected $createdTimestamp;
    protected $updateBy;
    protected $updateTimestamp;
    protected $deleted;
    protected $ownergroup;

    public function getPersonnelSid()
    {
        return $this->personnelSid;
    }

    public function getOwnergroup()
    {
        return $this->ownergroup;
    }

    public function getPersonnelName()
    {
        return $this->personnelName;
    }

    public function getActive()
    {
        return $this->active;
    }

    public function getPersonnelNotes()
    {
        return $this->personnelNotes;
    }

    public function getCreatedBy()
    {
        return $this->createdBy;
    }

    public function getCreatedTimestamp()
    {
        return $this->createdTimestamp;
    }

    public function getUpdateBy()
    {
        return $this->updateBy;
    }

    public function getUpdateTimestamp()
    {
        return $this->updateTimestamp;
    }

    public function getDeleted()
    {
        return $this->deleted;
    }

    public function setPersonnelSid($personnelSid)
    {
        $this->personnelSid = $personnelSid;
    }

    public function setOwnergroup($ownergroup)
    {
        $this->ownergroup = $ownergroup;
    }

    public function setPersonnelName($personnelName)
    {
        $this->personnelName = $personnelName;
    }

    public function setActive($active)
    {
        $this->active = $active;
    }

    public function setPersonnelNotes($personnelNotes)
    {
        $this->personnelNotes = $personnelNotes;
    }

    public function setCreatedBy($createdBy)
    {
        $this->createdBy = $createdBy;
    }

    public function setCreatedTimestamp($createdTimestamp)
    {
        $this->createdTimestamp = $createdTimestamp;
    }

    public function setUpdateBy($updateBy)
    {
        $this->updateBy = $updateBy;
    }

    public function setUpdateTimestamp($updateTimestamp)
    {
        $this->updateTimestamp = $updateTimestamp;
    }

    public function setDeleted($deleted)
    {
        $this->deleted = $deleted;
    }

}
