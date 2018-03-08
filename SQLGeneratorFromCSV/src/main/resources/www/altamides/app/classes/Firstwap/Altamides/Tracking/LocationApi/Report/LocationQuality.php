<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocationQuality.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class LocationQuality
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
 * See table LOCATION_CALCULATION_METHOD in LRTP_V2 database
 * 
 * +----+-----------------------------+-------------------+
 * | ID | LOCATION_CALCULATION_METHOD | METHOD_SHORT_NAME |
 * +----+-----------------------------+-------------------+
 * |  1 | GPS Location                | GPS               | 
 * |  2 | Calculated Cell Center      | CCC               | 
 * |  3 | Estimated Cell Center       | ECC               | 
 * |  4 | BTS Location                | BTS               | 
 * |  5 | MSC Location                | MSC               | 
 * |  6 | LAC Location                | LAC               | 
 * |  7 | Fixed Line Location         | FLL               | 
 * |  8 | Retrieved BTS Location      | RBL               |
 * +----+-----------------------------+-------------------+ 
 * 
 *
 * @author Setia Budi Halim
 * 

 */
class LocationQuality
{
    /**
     * GPS Location
     */

    const GPS = 1;
    /**
     * Calculated cell centre
     */
    const CCC = 2;
    
    /**
     * Estiamted cell centre
     */
    const ECC = 3;
    
    /**
     * BTS location
     */
    const BTS = 4;
    
    /**
     * MSC location
     */
    const MSC = 5;

    /**
     * LAC location
     */
    const LAC = 6;

    /**
     * Fixed line location
     */
    const FLL = 7;

    /**
     * Retrieved BTS location
     */
    const RBL = 8;
    
    /**
    * Retrieved Estimated Cell Area
    */
    const ECA = 9;

}