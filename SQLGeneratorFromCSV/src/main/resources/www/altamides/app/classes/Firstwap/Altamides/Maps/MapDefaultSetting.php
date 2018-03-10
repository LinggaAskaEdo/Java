<?php

/*
 * (c) 2014 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : All
 * Version          : 2.0 SP19
 * Filename         : MapDefaultSetting.php
 * Fileversion      : 2.000.001
 * Initial Creation : 20 Jan 2012
 * Initial Author   : setia.budi
 * Purpose          : Altamides framework, specific functions for altamides
 * ================================================
 * Initial Request  : #
 * ================================================
 * Change Log
 * Date         Author      Version     Request     Comment        
 * 2012-01-20   setia.budi  2.0                     Initial Creation
 * 2014-04-21   angela      2.0SP19     #4166       Static info checkbox does not follow user map setting
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Maps;

use \PDO;

/**
 * Description of MapDefaultSetting
 *
 * @author angela
 */
class MapDefaultSetting
{
    /**
     * elements:
     * - MAP_DEFAULT
     * - MAP_ZOOM
     * - MAP_LATITUDE
     * - MAP_LONGITUDE
     * - MAP_VIEW
     * - RULER_IS_METRIC
     * - MAP_RULER
     * - MAP_CELL_SHAPE
     * - MAP_CELL_DIRECTION
     * - MAP_COMPASS
     * - MAP_GRID
     * @var array
     */
    protected $settings = array();

    /**
     * @var array
     */
    protected static $extendedMaps = null;

    public function load()
    {
        $dsn = 'mysql:dbname='.REF_DB_NAME.';host='.REF_DB_HOST;
        $pdo = new PDO($dsn, REF_DB_USER, REF_DB_PASS);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $query = "select u.MAP_DEFAULT, 
            u.MAP_ZOOM, u.MAP_LATITUDE, u.MAP_LONGITUDE, 
            u.MAP_DEFAULT is not null as HAS_DEFAULT_MAP,
            IF(u.MAP_VIEW is null, 2, u.MAP_VIEW) as MAP_VIEW,
            IF(u.MAP_MEASUREMENT is null, 1, u.MAP_MEASUREMENT =  1) as RULER_IS_METRIC,
            IF(u.MAP_RULER is null, 1, u.MAP_RULER = b'1') as MAP_RULER,
            IF(u.MAP_CELL_SHAPE is null, 1, u.MAP_CELL_SHAPE = b'1') as MAP_CELL_SHAPE, 
            IF(u.MAP_CELL_DIRECTION is null, 1, u.MAP_CELL_DIRECTION = b'1') as MAP_CELL_DIRECTION, 
            IF(u.MAP_COMPASS is null, 1, u.MAP_COMPASS = b'1') as MAP_COMPASS, 
            IF(u.MAP_GRID is null, 1, u.MAP_GRID = b'1') as MAP_GRID,
            IF(u.MAP_INCESSANT_REFRESH is null, 1, u.MAP_INCESSANT_REFRESH = b'1') as MAP_INCESSANT_REFRESH,
            IF(u.MAP_ADDITIONAL_INFO is null, 1, u.MAP_ADDITIONAL_INFO = b'1') as MAP_ADDITIONAL_INFO,
            IF(u.MAP_BUBBLE_HISTORY is null, 1, u.MAP_BUBBLE_HISTORY = b'1') as MAP_BUBBLE_HISTORY             
            from USER as u 
            where u.USER_ID= :id ";
        $stmt = $pdo->prepare($query);
        $stmt->bindValue(':id', $_SESSION['USER_ID']);
        $stmt->execute();
        $this->settings = $stmt->fetch(PDO::FETCH_ASSOC);
    }
    
    public function has($mapId)
    {
        return isset($this->settings[$mapId]);
    }
    
    public function getDetails($value)
    {
        return $this->has($value)
            ? $this->settings[$value]
            : null;
    }
    
    public function getAsArray()
    {
        return $this->settings;
    }
}