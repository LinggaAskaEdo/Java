<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Maps;
use \Firstwap\Altamides\Exception;

/**
 * Description of ViewerUnavailableException
 *
 * @author setia.budi
 */
class ViewerUnavailableException extends Exception
{
    /**
     *
     * @var Map
     */
    protected $map;
    protected $mapName;
    
    public function __construct($message, Map $map = null)
    {
        $this->map = $map;
        $mapName = $map ? $map->getName() : '(Unknown Map)';
        parent::__construct("Map viewer for '$mapName' is not available. $message");
    }
    
    /**
     *
     * @return Map
     */
    public function getMap()
    {
        return $this->map;
    }
}
