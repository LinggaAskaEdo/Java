<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Maps\Vendors\Google;
use Firstwap\Altamides\Maps\ExtendedMapViewer;


/**
 * Description of MapViewer
 *
 * @author setia.budi
 */
class MapViewer extends ExtendedMapViewer
{
    public function load()
    {
        parent::load();
        echo '<script type="text/javascript" src="'.$this->map->getOption('script_url').'"></script>';
//        echo '<script type="text/javascript" src="'.\PHP_OMNITRAX_URL.'/geofence.drawing.googleproxy.min.js"></script>';
//        echo '<script type="text/javascript" src="'.\PHP_OMNITRAX_URL.'/geofence.drawing.openlayerproxy.js"></script>';
    }
}
