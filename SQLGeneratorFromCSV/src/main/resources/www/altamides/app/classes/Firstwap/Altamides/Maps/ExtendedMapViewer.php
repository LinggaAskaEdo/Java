<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Maps;

/**
 * Description of ExtendedMapViewer
 *
 * @author setia.budi
 */
abstract class ExtendedMapViewer extends MapViewerAbstract
{
    public function __construct(ExtendedMap $map)
    {
        parent::__construct($map);
        
    }

}
