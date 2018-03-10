<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Experimental;

/**
 * This class will act as cache layer for cell calculation. It reduce probability of calculation 
 * request to geofence server and thus can speed up ajax.getPositions.php in omnitrax.
 *
 * All public and protected methods in this class should state the expectation of removal
 * 
 * == Maintenance Limitation ==
 * Introduced: 2.0 SP18
 * Expiration: 2.0 SP20
 * 
 * @author setia.budi
 */
final class OmnitraxCalculationCache
{

    private $cacheDir;
    private $cacheFile;
    private $cacheTtl;
    private $cacheTag;
    private $cellsList = array();
    
    /**
     * 
     * @global type $ALTAMIDES_X_CELL_CALC_CACHE_TTL
     */
    public function __construct()
    {
        global $ALTAMIDES_X_CELL_CALC_CACHE_TTL;
        
        if (!empty($ALTAMIDES_X_CELL_CALC_CACHE_TTL)) {
            $this->cacheTtl = (int) $ALTAMIDES_X_CELL_CALC_CACHE_TTL;
            
        } else {
            // one week by default
            $this->cacheTtl = 604800;
            
        }
        
        $this->cacheDir = \ALTAMIDES_CACHE_DIR . '/omnitrax-cell-calc';
        if (!\file_exists($this->cacheDir)) {
            \mkdir($this->cacheDir, 0750, true);
        }
    }

    /**
     * Set calculation target
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP20
     * 
     * @param array $cellRef Array of cell ref
     */
    public function setCellRef(array $cellRef)
    {
        $uniqueList      = array_unique($cellRef);
        $this->cellsList = $uniqueList;
        $this->cacheTag  = $this->determineCacheTag($this->cellsList);
        $this->cacheFile = $this->determineCacheFile($this->cacheTag);
    }

    /**
     * Try to get cell info from cache
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP20
     * 
     * @return array|false
     */
    public function getCellsFromCache()
    {
        // cache miss
        if (!$this->testCacheHit($this->cacheTag)) {
            return false;
        }

        return $this->readCache();
    }

    /**
     * Save calculated cell data
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP20
     * 
     * @return array|false
     */
    public function saveCalculationResult(array $data)
    {
        $this->writeCache($data);
    }

    private static function intDegreeToDecimal($deg)
    {
        return $deg / 3600000;
    }

    private function determineCacheTag(array $cellList)
    {
        \sort($cellList);
        $tagValue   = \implode('-', $cellList);
        $tagHash    = \crc32($tagValue);
        return $tagHash;
    }

    private function determineCacheFile($cacheTag)
    {
        return $this->cacheDir .'/' . $cacheTag . '.dat';
    }

    private function testCacheHit($cacheTag)
    {
        $fileName = $this->determineCacheFile($cacheTag);
        if (!\is_readable($fileName)) {
            return false;
        }
        
        if ($this->isCacheExpired($fileName)) {
            return false;
        }
        
        return true;
    }
    
    private function isCacheExpired()
    {
        $modified = \filemtime($this->cacheFile);
        $now = \time();
        $age = $now - $modified;
        return $age > $this->cacheTtl;
    }

    private function readCache()
    {
        $cache = \file_get_contents($this->cacheFile);
        if (!$cache) {
            return false;
        }

        $data = $this->unserializeData($cache);

        if (!$data) {
            trigger_error('Invalid cache content. Attempt to reset cache', E_USER_WARNING);
            return false;
        }
        
        return $data;
        
    }

    private function readCellInfoXml(\SimpleXMLElement $xml)
    {
        try {
            if ($xml->attributes() !== 'OK') {
                throw new \Exception('Invalid XML reply', $code, $previous);
            }

            $data = array(
                'countries'     => array(),
                'cells'         => array(),
                'operators'     => array(),
                'operatorInfos' => array(),
                'cellInfos'     => array(),
            );

            $countries = array();
            $operators = array();
            $cells     = array();

            foreach ($xml->map->countries->country as $country) {
                $countryId             = (string) $country['countryID'];
                $countries[$countryId] = (string) $country['countryName'];
            }

            foreach ($xml->map->operators->operator as $item) {
                $countryId  = (string) $item['countryID'];
                $operatorId = (string) $item['operatorID'];

                if (isset($countries[$countryId])) {
                    $countryName = $countries[$countryId];
                } else {
                    $countryName = "";
                }

                $operators[$operatorId] = array(
                    'operatorName'  => (string) $item['operatorName'],
                    'operatorColor' => (string) $item['operatorColor'],
                    'cellCount'     => (int) $item['cellCount'],
                    'countryID'     => $countryId,
                    'countryName'   => $countryName,
                    'operatorID'    => $operatorId
                );
            }

            foreach ($xml->map->cellInfos->cellInfo as $item) {
                $cellMcc  = (string) $item['mcc'];
                $cellMnc  = (string) $item['mnc'];
                $cellLac  = (string) $item['lac'];
                $cellId   = (string) $item['cellID'];
                $cellDbId = (string) $item['cellDBID'];
                $cellRef  = $cellMcc . '.' . $cellMnc . '.' . $cellLac . '.' . $cellId;

                $cells[$cellId] = array();

                $width            = (string) $item['width'];
                $height           = (string) $item['height'];
                $azimuth          = (string) $item['azimuth'];
                $endAngle         = (string) $item['endAngle'];
                $latitude         = (string) $item['latitude'];
                $cartesianAzimuth = (string) $item['cartesianAzimuth'];
                $longitude        = (string) $item['longitude'];

                $cellEdgesList = (string) $item['coordinate'];

                if (!empty($cellEdgesList)) {
                    list($pos1, $pos2, $pos3, $pos4,
                        $pos5, $pos6, $pos7, $pos8) = explode(",", $cellEdgesList);
                } else {
                    $pos1 = $pos2 = $pos3 = $pos4 = $pos5 = $pos6 = $pos7 = $pos8 = null;
                }

                list($cellCenterLat, $cellCenterLong) = explode(",", $item["cellCenter"]);
                $cellCenterLong = trim($cellCenterLong);

                // the key should actually cellRef, but we will use cellID for now to not regress
                $cells[$cellId] = array(
                    "cellCenterLat"    => self::intDegreeToDecimal($cellCenterLat),
                    "cellCenterLong"   => self::intDegreeToDecimal($cellCenterLong),
                    "latitude"         => self::intDegreeToDecimal($latitude),
                    "longitude"        => self::intDegreeToDecimal($longitude),
                    /*
                     * Bug 3445
                     * [MOI] Location Tracking: Azimuth and Radius 0, the Cell Centre Location and BTS Location are different, Location Quality is Not Available
                     * the position of bts lat/long will be taken from XML.
                     */
                    "btslatitude"      => self::intDegreeToDecimal($latitude),
                    "btslongitude"     => self::intDegreeToDecimal($longitude),
                    "width"            => (($width / 2) / 1000),
                    "height"           => (($height / 2) / 1000),
                    "angle"            => (90 - ($endAngle - $azimuth)),
                    "cartesianAzimuth" => $cartesianAzimuth,
                    "pos1"             => self::intDegreeToDecimal($pos1),
                    "pos2"             => self::intDegreeToDecimal($pos2),
                    "pos3"             => self::intDegreeToDecimal($pos3),
                    "pos4"             => self::intDegreeToDecimal($pos4),
                    "pos5"             => self::intDegreeToDecimal($pos5),
                    "pos6"             => self::intDegreeToDecimal($pos6),
                    "pos7"             => self::intDegreeToDecimal($pos7),
                    "pos8"             => self::intDegreeToDecimal($pos8),
                    'region'           => (string) $item['region'],
                    'zip'              => (string) $item['zip'],
                    'azimuth'          => (string) $item['azimuth'],
                    'radius'           => (string) $item['radius'],
                    'elevation'        => (string) $item['elevation'],
                    'gravity'          => (string) $item['gravity'],
                    'comment'          => (string) $item['comment'],
                    'mcc'              => $cellMcc,
                    'mnc'              => $cellMnc,
                    'lac'              => $cellLac,
                    'cellID'           => $cellId,
                    'cellDBID'         => $cellDbId,
                    'cellDBID'         => $cellDbId,
                );
            }

            foreach ($xml->map->cells->cell as $item) {
                $cellMcc = (string) $item['mcc'];
                $cellMnc = (string) $item['mnc'];
                $cellLac = (string) $item['lac'];
                $cellId  = (string) $item['cellID'];
                $cellRef = $cellMcc . '.' . $cellMnc . '.' . $cellLac . '.' . $cellId;

                if (!isset($cells[$cellId])) {
                    trigger_error('No cell info for cell #' . $cellId, E_USER_NOTICE);
                    continue;
                }

                $operatorId = (string) $item['operatorID'];

                $cells[$cellId]['color']      = (string) $item['cellColor'];
                $cells[$cellId]['operatorID'] = $operatorId;
                $cells[$cellId]['cellID']     = $cellId;
                $cells[$cellId]['cellDBID']   = (string) $item['cellDBID'];
                $cells[$cellId]['cellRef']    = $cellRef;
                $cells[$cellId]['cellReff']   = $cellRef;

                $operators[$operatorId]['mnc'] = (string) $cells[$cellId]['mnc'];
            }

            $data['cells']     = $cells;
            $data['operators'] = $operators;
            $data['countries'] = $countries;
            return $data;
        } catch (\Exception $e) {
            \error_log($e);
            return false;
        }
    }

    private function clearCache()
    {
        if (!unlink($this->cacheFile)) {
            trigger_error('Failed deleting cell calculation cache: ' . $this->cacheFile,
                          E_USER_WARNING);
        }
    }

    private function writeCache($data)
    {
        $serialised = $this->serializeData($data);
        if ($serialised === false) {
            trigger_error('Failed serialising data. Can not update cache', \E_USER_WARNING);
            return false;
        }

        $written = \file_put_contents($this->cacheFile, $serialised);
        if (!$written) {
            trigger_error('Failed writing cell calculation cache data to ' . $this->cacheFile,
                          \E_USER_WARNING);
            return false;
        }

        return true;
    }

    private function serializeData($data)
    {
        return serialize($data);
    }

    private function unserializeData($data)
    {
        return unserialize($data);
    }

}
