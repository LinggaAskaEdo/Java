<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : All
 * Version          : 2.0 SP19
 * Filename         : MapList.php
 * Fileversion      : 
 * Initial Creation : 
 * Initial Author   : setia.budi
 * Purpose          : Show Map List at dropdown listbox
 * 
 * ================================================
 * Change Log
 * Date         Author                                  Version     Request     Comment         
 * 2014-04-23   Arahman                                 2.0 SP19     4148       Default World Map for Native Map
 * 
 * Copyright 2012 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Maps;
use Firstwap\Altamides\Auth\AltamidesPrivilege;
/**
 * Description of GeofenceMapsList
 *
 * @author setia.budi
 */
class MapList
{

    const FIELD_REF = 'map_ref';
    const FIELD_ID = 'map_id';
    const ID_WORLD_WIDE_MAP= 0;
    const ID_BING_OFFLINE_ROAD = -2;
    const ID_BING_OFFLINE_AERIAL = -6;
    const ID_BING_OFFLINE_HYBRID = -8;
    
    const ID_BING_ONLINE_ROAD = -1;
    const ID_BING_ONLINE_AERIAL = -5;
    const ID_BING_ONLINE_HYBRID = -7;
    
    const ID_GOOGLE_OFFLINE_ROAD = -4;
    const ID_GOOGLE_OFFLINE_AERIAL = -10;
    const ID_GOOGLE_OFFLINE_HYBRID = -12;
    
    const ID_GOOGLE_ONLINE_ROAD = -3;
    const ID_GOOGLE_ONLINE_AERIAL = -9;
    const ID_GOOGLE_ONLINE_HYBRID = -11;
    
    const ID_OSM_ONLINE = -13;
    const ID_OSM_OFFLINE = -14;
    
    const ID_ARCGIS_MIN = -29;
    const ID_ARCGIS_MAX = -20;
    /**
     * elements:
     * - map_id
     * - map_ref
     * - map_name
     * - is_native (Default: true)
     * @var array
     */
    protected $maps = array();
    protected $valueField = self::FIELD_ID;
    protected $selected = null;
    protected $default = null;

    /**
     * @var array
     */
    protected static $extendedMaps = null;

    public function load(\database $db, $groupFilter)
    {
        
        $dbName = \REF_FTRAX_LOCATIONTRACKING_DBNAME;
        $this->loadExtendedMaps($db);
        
        $query = "
            select 
                m.ID as map_id, is_extended,
                m.MAP_REF as map_ref, m.MAP_DISPLAY_NAME as map_name
            from $dbName.MAP as m
            inner join $dbName.MAP_CLIENT_MAP as mc
                on(mc.MAP_ID=m.ID)
            where 
                mc.CLIENT_ID = '" . $db->real_escape_string($groupFilter) . "'
            order by
                map_name asc";
        
        if(NATIVE_MAP_LIMIT >= 0){
            $query .= " limit ". NATIVE_MAP_LIMIT;
        }
        
        $result = $db->query($query, 'MapList::load');
            
               
                $query = "
                    select
                        MAP.ID as map_id, MAP.MAP_DISPLAY_NAME as map_name,
                        (TL_LAT/3600000) as TL_LATITUDE,(TL_LONG/3600000) as TL_LONGITUDE,
                        (BR_LAT/3600000) as BR_LATITUDE,(BR_LONG/3600000) as BR_LONGITUDE, 
                        SCALE                    
                    from
                        ".REF_FTRAX_LOCATIONTRACKING_DBNAME.".MAP 
                    where
                        MAP.ID = 0
                    order by
                        MAP.ID ASC
                    limit 1";
                $result2 = $db->query($query, 'DefaultNative::load');

        
        
        if (!$result2) { // error
            \trigger_error('Error reading maps list: ' . $db->error(),
                \E_USER_WARNING);
            return;
        }
        
        if (!$db->num_rows($result2)) { //no result
            \trigger_error('Found no world map: ', \E_USER_NOTICE);
            return;
        }
        
        $data = $db->fetch_assoc($result2);
        if ($this->default === null) {
            $this->default = $data['map_id'];
        }
        
        do { //group 
            $this->maps[$data['map_id']] = $data;
        } while ($data = $db->fetch_assoc($result2));
            
        if ($db->num_rows($result)) {
            $data = $db->fetch_assoc($result);
            if ($this->default === null) {
                $this->default = $data['map_id'];
            }

            do{ //group 
                $this->maps[$data['map_id']] = $data;
            }while ($data = $db->fetch_assoc($result));
        $db->free_result($result);
    }
        $db->free_result($result2);
    }
    
    public function has($mapId)
    {
        return isset($this->maps[$mapId]);
    }
    
    public function getDetails($mapId)
    {
        return $this->has($mapId)
            ? $this->maps[$mapId]
            : null;
    }
    
    public function getAsArray()
    {
        return $this->maps;
    }

    /**
     *
     * @param string $fieldName Possible values are: map_id, map_ref
     */
    public function setValueField($fieldName)
    {
        $this->valueField = (string) $fieldName;
    }
    public function generateHtmlSelect($name, array $attrs = array())
    {

        echo '<select';

        if ($name !== null) {
            echo ' name="' . $name . '"';
        }

        if (!empty($attrs)) {
            foreach ($attrs as $name => $value) {
                echo ' ' . $name . '="'
                . htmlentities($value, ENT_QUOTES, 'UTF-8')
                . '"';
            }
        }

        echo '> ';
        $this->generateHtmlOptions();
        echo '</select>';
    }

    public function generateHtmlOptions()
    {
        $OptionRelevantArea = array();        
        $bingMapOfflineEnabled = filterPage(AltamidesPrivilege::MAPS_BING_OFFLINE,false);
        $bingMapOnlineEnabled = filterPage(AltamidesPrivilege::MAPS_BING_ONLINE,false);
        $googleMapOfflineEnabled = filterPage(AltamidesPrivilege::MAPS_GOOGLE_OFFLINE,false);
        $googleMapOnlineEnabled = filterPage(AltamidesPrivilege::MAPS_GOOGLE_ONLINE,false);
        $osmMapOnlineEnabled = filterPage(AltamidesPrivilege::MAPS_OSM_ONLINE,false);
        $osmMapOfflineEnabled = filterPage(AltamidesPrivilege::MAPS_OSM_OFFLINE,false);
        $arcgisMapEnabled = filterPage(AltamidesPrivilege::MAPS_ARCGIS,false);
        
        if (!$this->maps) {
            return;
        }

        $valueField = $this->valueField;
        $selected = $this->getSelectedValue();

        
        if ($selected !== null) {
            foreach ($this->maps as $map) {
                
                         
        if($bingMapOnlineEnabled && $map['map_id']==self::ID_BING_ONLINE_ROAD) {
   
        }elseif($bingMapOnlineEnabled && $map['map_id']==self::ID_BING_ONLINE_HYBRID) {
           
        }elseif($bingMapOnlineEnabled && $map['map_id']==self::ID_BING_ONLINE_AERIAL) {
        
        }elseif($bingMapOfflineEnabled && $map['map_id']==self::ID_BING_OFFLINE_ROAD) {
           
        }elseif($bingMapOfflineEnabled && $map['map_id']==self::ID_BING_OFFLINE_HYBRID) {
         
        }elseif($bingMapOfflineEnabled && $map['map_id']==self::ID_BING_OFFLINE_AERIAL) {
           
        }elseif($googleMapOnlineEnabled && $map['map_id']==self::ID_GOOGLE_ONLINE_ROAD) {
           
        }elseif($googleMapOnlineEnabled && $map['map_id']==self::ID_GOOGLE_ONLINE_HYBRID) {
           
        }elseif($googleMapOnlineEnabled && $map['map_id']==self::ID_GOOGLE_ONLINE_AERIAL) {
            
        }elseif($googleMapOfflineEnabled && $map['map_id']==self::ID_GOOGLE_OFFLINE_ROAD) {
           
        }elseif($googleMapOfflineEnabled && $map['map_id']==self::ID_GOOGLE_OFFLINE_HYBRID) {
         
        }elseif($googleMapOfflineEnabled && $map['map_id']==self::ID_GOOGLE_OFFLINE_AERIAL) {
           
        }elseif($osmMapOnlineEnabled && $map['map_id']==self::ID_OSM_ONLINE) {
            
        }elseif($osmMapOfflineEnabled && $map['map_id']==self::ID_OSM_OFFLINE) {
          
        }elseif($arcgisMapEnabled && ($map['map_id']>=self::ID_ARCGIS_MIN && $map['map_id']<=self::ID_ARCGIS_MAX)) {
          
        }elseif ($map['map_id']==self::ID_WORLD_WIDE_MAP) {
       
        }else{
                    continue;
        }
                    
                $value = $map[$valueField];
                echo '<option ';
                if ($value == $selected) {
                    echo 'selected="selected"';
                }
                echo ' value="' . htmlentities($value, ENT_QUOTES),
                    '">' . htmlentities($map['map_name'], ENT_QUOTES),
                    '</option>';
            }
            
        } else {
            foreach ($this->maps as $map) {
                echo '<option value="',
                    htmlentities($map[$valueField], ENT_QUOTES) . '">',
                    htmlentities($map['map_name'], ENT_QUOTES) . '</option>';
            }
        }

    }

    public function getDefaultMapId()
    {
        return $this->default;
    }
    
    public function setSelected($mapId)
    {
        if (!$this->has($mapId)) {
            throw new MapNotFoundException($mapId);
        }
        
        $this->selected = $mapId;
    }

    public function getSelectedId()
    {
        return $this->selected;
    }
    
    public function getSelectedValue()
    {
        $id = $this->getSelectedId();
        
        return ($id === null) ? null : $this->maps[$id][$this->valueField];
        
    }
    
    /**
     *
     * @return \Firstwap\Altamides\Maps\ExtendedMap|\Firstwap\Altamides\Maps\NativeMap
     * @throws \Exception
     * @throws MapNotFoundException 
     */
    public function getSelectedMap()
    {
        $selectedId = $this->getSelectedId();
        if( $selectedId === null) {
            $mapId = $this->getDefaultMapId();
        } else {
            $mapId = $selectedId;
        }
        
        if ($mapId === null) {
            throw new \Exception('No available map');
        }
        
        if (!isset($this->maps[$mapId])) {
            throw new MapNotFoundException();
        }
        
        if ($this->maps[$mapId]['is_extended']) {
            return new ExtendedMap($mapId);
        } else {
            return new NativeMap($mapId);
        }
    }
    
    private function loadExtendedMaps(\database $db) {
        $dbName = \REF_FTRAX_LOCATIONTRACKING_DBNAME;
        
        $query = "
            select 
                m.ID as map_id, 1 as is_extended,
                m.MAP_REF as map_ref, m.MAP_DISPLAY_NAME as map_name,
                m.CONFIG_NAME as config_name
            from $dbName.MAP as m
            where is_extended 
            order by map_name";
        
        $result = $db->query($query, 'MapList::loadExtendedMaps');
            
        if (!$result) { // error
            \trigger_error('Error reading maps list: ' . $db->error(),
                \E_USER_WARNING);
            return;
        }
        
        if (!$db->num_rows($result)) { //no result
            \trigger_error('Did not find any extended map: ', \E_USER_NOTICE);
            return;
        }
        
        $data = $db->fetch_assoc($result);
        
        do { //group 
            if (!ExtendedMap::isConfigEnabled($data['config_name'])) {
                continue;
            }
            $this->maps[$data['map_id']] = $data;

            if ($this->default === null) {
                $this->default = $data['map_id'];
            }
                    
        } while ($data = $db->fetch_assoc($result));

        $db->free_result($result);
    }
    

}