<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocateV2ReplyFields.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class LocateV2ReplyFields
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Tracking\LocationApi\Report;

/**
 * Description of LocateV2ReplyFields
 *
 * @author Setia Budi Halim
 */
class LocateResultField
{
    // Field 0 always empty

    const ADDITIONAL_ERROR_CODE = 1;
    const ADDITIONAL_ERROR_DESC = 2;
    const TARGET_NUMBER         = 3;
    const VISITED_COUNTRY_NAME  = 4;
    const VISITED_OPERATOR_NAME = 5;
    const VLR_NAME              = 6;
    const CELL_REF              = 7;
    const POSITION_LONGITUDE    = 8;
    const POSITION_LATITUDE     = 9;
    const CELL_RADIUS_DB        = 10;
    const CELL_AZIMUTH          = 11;
    const REPLY_DATE            = 12;
    const REPLY_TIME            = 13;
    const PHONE_STATUS          = 14;
    const AGE_OF_LOCATION       = 15;
    const ROAMING_NUMBER        = 16;
    const CELL_ADDRESS          = 17;
    const LOCATION_QUALITY_ID   = 18;
    const LOCATION_QUALITY_DESC = 19;
    const HOME_COUNTRY_NAME     = 20;
    const HOME_OPERATOR_NAME    = 21;
    const VLR_GLOBAL_TITLE      = 22;
    const SMS_NODE              = 23;
    const TARGET_IMSI           = 24;
    const BTS_LAT               = 25;
    const BTS_LONG              = 26;
    const AGE_OF_LOCATION_DESC  = 27;
    const PHONE_STATUS_DESC     = 28;

    /**
     * Can be MSC radius, LAC radius, or cell radius
     * depends on location quality
     */
    const LOCATION_RADIUS = 29;

    /**
     * Location API response code:
     */
    const ERROR_CODE = 30;

    // Field 31: Always 0
    const CELL_END_ANGLE         = 32;
    const TARGET_NUMBER_TYPE     = 33;
    const TARGET_IMEI            = 34;
    const TARGET_INP_ID          = 35;
    const CELL_COMMENT           = 36;
    const ROAMING_NET_START_DATE = 37;
    const ROAMING_NET_TYPE       = 38;
    const HOME_MCC               = 39;
    const HOME_COUNTRY_ISO       = 40;
    const ROAMING_MCC            = 41;
    const ROAMING_COUNTRY_ISO    = 42;

    // Field 43: ???
    // Field 44: ???
    const ROAMING_INP_ID = 45;
    const REGION         = 46;
    const ZIP_CODE       = 47;
    const HOME_MNC       = 48;

}