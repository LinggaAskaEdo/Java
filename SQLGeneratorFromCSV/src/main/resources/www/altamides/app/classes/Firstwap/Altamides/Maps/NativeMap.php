<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Maps;
use Firstwap\Altamides\Maps\Vendors\Native;

/**
 * Description of NativeMap
 *
 * @author setia.budi
 */
class NativeMap extends Map
{
    public function __construct($mapId)
    {
        parent::__construct($mapId);
    }
    
    public function getVendor()
    {
        return 'Native';
    }
    
    public function isExtended()
    {
        return false;
    }
    
    /**
     *
     * @return \Firstwap\Altamides\Maps\Vendors\Native\MapViewer 
     */
    public function getViewer()
    {
        return new Native\MapViewer($this);
    }
    
    
}
