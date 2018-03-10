<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Tracking\Ismsc;

/**
 * Description of IsmscDefinition
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class IsmscRecord
{

    protected $name;
    protected $serviceUrl  = 'http://localhost/request.php';
    protected $description = '';

    public function __construct($name)
    {
        $this->setName($name);
    }

    public function getName()
    {
        return $this->name;
    }

    public function getServiceUrl()
    {
        return $this->serviceUrl;
    }

    public function getDescription()
    {
        return $this->description;
    }

    public function setName($name)
    {
        if ('' === (string) $name) {
            throw new \InvalidArgumentException('iSMSC name can not be empty');
        }
        $this->name = (string) $name;
    }

    public function setServiceUrl($serviceUrl)
    {
        $this->serviceUrl = (string) $serviceUrl;
    }

    public function setDescription($description)
    {
        $this->description = (string) $description;
    }

}
