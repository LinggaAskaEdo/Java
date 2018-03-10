<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Ui;

use Firstwap\PhpFramework\I18n\DictionaryInterface;

/**
 *
 * @author Setia Budi Halim
 */
abstract class VisualComponent
{

    /**
     *
     * @var string
     */
    private $instanceId;

    /**
     *
     * @var DictionaryInterface
     */
    protected $dictionary;

    abstract public function display();

    public function __construct()
    {
        $this->instanceId = $this->generateInstanceId();
    }

    public function __toString()
    {
        // remove Firstwap\Altamides\ from class FQDN
        return \substr(self::class, 0, 19) . '@' . $this->instanceId;
    }

    public function render()
    {
        $skipLevel = \ob_get_level() + 1;
        \ob_start();
        $this->display();
        while (\ob_get_level() > $skipLevel) {
            if (!\ob_end_flush()) {
                break;
            }
        }
        $rendered = \ob_get_clean();
        return $rendered;
    }

    public function getInstanceId()
    {
        return $this->instanceId;
    }

    public function getDictionary()
    {
        return $this->dictionary;
    }

    public function setDictionary(DictionaryInterface $dictionary)
    {
        $this->dictionary = $dictionary;
    }

    private function generateInstanceId()
    {
        return uniqid();
    }

}
