<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Maps;


/**
 * Description of GeofenceMapViewer
 *
 * @author setia.budi
 */
abstract class MapViewerAbstract
{

    /**
     *
     * @var Map
     */
    protected $map;
    
    protected $options = array();
    
    public function __construct(Map $map)
    {
        $this->map = $map;
    }
    
    /**
     *
     * @return Map
     */
    public function getMap()
    {
        return $this->map;
    }
    
    public function load()
    {
        
        self::loadCss(PHP_JS_URL.'/svg/jquery.svg.css');
        
        self::loadMultipleJs(array(
            PHP_JS_URL.'/svg/jquery.svg.min.js',
            PHP_JS_URL.'/svg/jquery.svgdom.min.js',
            PHP_JS_URL.'/svg/jquery.svganim.min.js',
            PHP_OMNITRAX_URL.'/jsundoable.min.js',
            PHP_JS_URL.'/timeout/jquery.dotimeout.min.js',
//            PHP_OMNITRAX_URL.'/geofence.drawing.min.js'
//            PHP_OMNITRAX_URL.'/geofence.drawing.openlayerproxy.min.js'
            /*
             * DONT USE MINIFIED.
             * Until finish bug fixing
             */
            PHP_OMNITRAX_URL.'/geofence.drawing.js',
            PHP_OMNITRAX_URL.'/geofence.drawing.openlayerproxy.js'
//        echo '<script type="text/javascript" src="'.\PHP_OMNITRAX_URL.'/geofence.drawing.openlayerproxy.js"></script>';
        ));
    //            PHP_OMNITRAX_URL.'/geofence.map.js'
    }
    
    private static function loadMultipleJs(array $sources)
    {
        foreach ($sources as $url) {
            echo '<script type="text/javascript" src="' . $url . '"></script>'."\n";
        }
        
    }
    
    /**
     *
     * @param type $source 
     */
    private static function loadCss($source)
    {
        echo '<link rel="stylesheet" type="text/css" href="'.$source.'">'."\n";
        
    }

}
