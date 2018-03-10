<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : LocateResponseLayout.php
 *  File Version     : 1.000.000
 *  Initial Creation : 13-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  13-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Actions;

use Firstwap\Altamides\Module\Mobiletrax\EncodedResponseUtil;
use Firstwap\Altamides\Module\Mobiletrax\Codec\FieldType;

/**
 * Description of LocateResponseLayout
 *
 * @author Setia Budi Halim
 */
final class LocateResponseLayout
{
    /**
     *
     * @var array
     */
    protected static $fastFields = array(
        'homeMccLength', 'homeMcc', 'homeMncLength', 'homeMnc',
        'cellMccLength', 'cellMcc', 'cellMncLength', 'cellMnc', 'cellLac', 'cellId',
        'imsi', 'imei',
        'azimuth', 'radius',
        'latitude', 'longitude',
        'locationQualityId',
        'roamingNumber', 'vlrGt', 'vlrName',
        'region', 'zipCode', 'btsLat', 'btsLong',
        'errorCode',
        'ageOfLocation',
        'numberingType',
        'roamingCountry', 'roamingOperator',
        'absentTime', 'phoneStatus'
    );

    /**
     *
     * @var array
     */
    protected static $economyFields = array(
        'homeMccLength', 'homeMcc', 'homeMncLength', 'homeMnc',
        'cellMccLength', 'cellMcc', 'cellMncLength', 'cellMnc', 'cellLac', 'cellId',
        'imsi', 'imei',
        'azimuth', 'radius', 'endAngle',
        'latitude', 'longitude',
        'locationQualityId',
        'roamingNumber', 'vlrGt', 'vlrName',
        'region', 'zipCode', 'btsLat', 'btsLong',
        'cellHeight', 'cellWidth',
        'errorCode',
        'ageOfLocation',
        'numberingType',
        'roamingCountry', 'roamingOperator',
        'absentTime', 'phoneStatus'
    );

    // <editor-fold defaultstate="collapsed" desc="Result fields definition">
    /**
     *
     * @var array
     */
    protected static $fields = array(
        // Home MCC digit length
        'homeMccLength' => array(
            'dataRef'    => 'home_mcc_length',
            'dataType'   => FieldType::UINT,
            'dataLength' => 2
        ),
        // Integer encoded MCC
        'homeMcc'    => array(
            'dataRef'       => 'home_mcc',
            'dataType'      => FieldType::UINT,
            'dataLength'    => 10
        ),
        // Home MNC digit length
        'homeMncLength' => array(
            'dataRef'    => 'home_mnc_length',
            'dataType'   => FieldType::UINT,
            'dataLength' => 2
        ),
        //Home MNC
        'homeMnc'    => array(
            'dataRef'       => 'home_mnc',
            'dataType'      => FieldType::UINT,
            'dataLength'    => 10
        ),
        'cellMccLength' => array(
            'dataRef'    => 'cell_mcc_length',
            'dataType'   => FieldType::UINT,
            'dataLength' => 2
        ),
        'cellMcc'    => array(
            'dataRef'       => 'cell_mcc',
            'dataType'      => FieldType::UINT,
            'dataLength'    => 10
        ),
        'cellMncLength' => array(
            'dataRef'    => 'cell_mnc_length',
            'dataType'   => FieldType::UINT,
            'dataLength' => 2
        ),
        'cellMnc'    => array(
            'dataRef'    => 'cell_mnc',
            'dataType'   => FieldType::UINT,
            'dataLength' => 10
        ),
        'cellLac'    => array(
            'dataRef'    => 'cell_lac',
            'dataType'   => FieldType::UINT,
            'dataLength' => 16
        ),
        'cellId'     => array(
            'dataRef'    => 'cell_id',
            'dataType'   => FieldType::UINT,
            'dataLength' => 16
        ),
        'imsi'       => array(
            'dataRef'    => 'imsi',
            'dataType'   => FieldType::VAR_DIGIT,
            'baseLength' => 15,
            'metaLength' => 2
        ),
        'imei'       => array(
            'dataRef'    => 'imei',
            'dataType'   => FieldType::VAR_DIGIT,
            'baseLength' => 15,
            'metaLength' => 2
        ),
        // value 0-360
        'azimuth'    => array(
            'dataRef'  => 'azimuth',
            'dataType' => FieldType::INT_DEGREE
        ),
        'radius'   => array(
            'dataRef'    => 'sector_radius',
            'dataType'   => FieldType::UINT,
            'dataLength' => 17
        ),
        'endAngle'   => array(
            'dataRef'  => 'cell_end_angle',
            'dataType' => FieldType::INT_DEGREE
        ),
        'latitude' => array(
            'dataRef'   => 'lat',
            'dataType'  => FieldType::LATITUDE
        ),
        'longitude' => array(
            'dataRef'           => 'long',
            'dataType'          => FieldType::LONGITUDE
        ),
        'locationQualityId' => array(
            'dataRef'       => 'cell_calc_method_id',
            'dataType'      => FieldType::UINT,
            'dataLength'    => 5
        ),
        'roamingNumber' => array(
            'dataRef'  => 'roaming_number',
            'dataType' => FieldType::MSISDN
        ),
        'vlrGt'    => array(
            'dataRef'  => 'msc_gt',
            'dataType' => FieldType::MSISDN
        ),
        'vlrName'  => array(
            'dataRef'    => 'msc_name',
            'dataType'   => FieldType::VAR_STRING,
            'metaLength' => 8
        ),
        'region'     => array(
            'dataRef'    => 'region',
            'dataType'   => FieldType::STRING,
            'dataLength' => 32
        ),
        'zipCode'    => array(
            'dataRef'    => 'zip',
            'dataType'   => FieldType::STRING,
            'dataLength' => 8
        ),
        'btsLat'     => array(
            'dataRef'  => 'bts_lat',
            'dataType' => FieldType::LATITUDE
        ),
        'btsLong'  => array(
            'dataRef'  => 'bts_long',
            'dataType' => FieldType::LONGITUDE
        ),
//        'cellDirection' => array (
//            'dataRef' => 'cell_direction_deg',
//            'dataType' => FieldType::INT_DEGREE
//        ),


        'cellHeight' => array(
            'dataRef'    => 'cell_height',
            'dataType'   => FieldType::UINT,
            'dataLength' => 16
        ),
        'cellWidth'  => array(
            'dataRef'    => 'cell_width',
            'dataType'   => FieldType::UINT,
            'dataLength' => 16
        ),
//        'cellStartAngle' => array (
//            'dataRef' => 'zip',
//            'dataType' => FieldType::LONGITUDE
//        ),


        'errorCode' => array(
            'dataRef'       => 'error_code',
            'dataType'      => FieldType::VAR_STRING,
            'metaLength'    => 5
        ),
        'ageOfLocation' => array(
            'dataRef'       => 'age_of_location',
            'dataType'      => FieldType::UINT,
            'dataLength'    => 16
        ),
        'numberingType' => array(
            'dataRef'        => 'numbering_type_id',
            'dataType'       => FieldType::UINT,
            'dataLength'     => 2
        ),
        'roamingCountry' => array(
            'dataRef'         => 'roaming_country_code',
            'dataType'        => FieldType::STRING,
            'dataLength'      => 2
        ),
        'roamingOperator' => array(
            'dataRef'    => 'roaming_operator',
            'dataType'   => FieldType::VAR_STRING,
            'metaLength' => 6
        ),
        'absentTime' => array(
            'dataRef'     => 'age_of_location',
            'dataType'    => FieldType::UINT,
            'dataLength'  => 18
        ),
        'phoneStatus' => array(
            'dataRef'    => 'phone_status_code_mt',
            'dataType'   => FieldType::UINT,
            'dataLength' => 3
        )
    ); // </editor-fold>
    
    /**
     * Create data layout based on response mode
     * @param int $mode
     * @return array
     */
    public static function getDataLayout($mode)
    {
        switch ($mode) {
            case LocateResponseMode::COMPLETE:
                return self::$fields;
            
            case LocateResponseMode::ECONOMY:
                return EncodedResponseUtil::selectLayoutFields(self::$economyFields, self::$fields);
            
            case LocateResponseMode::FAST:
            default:
                return EncodedResponseUtil::selectLayoutFields(self::$fastFields, self::$fields);
        }
    }
}