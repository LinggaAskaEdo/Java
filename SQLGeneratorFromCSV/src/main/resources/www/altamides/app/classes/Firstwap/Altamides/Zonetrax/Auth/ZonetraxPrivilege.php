<?php
/**
 * (c) 2015 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0 SP22 
 * Initial Creation : 2015-08-03
 * Initial Author   : Yung Fei <yung.fei@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-08-03   Yung Fei                1.000.000    Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */
namespace Firstwap\Altamides\Zonetrax\Auth;
    
/**
 * Description of RapidtraxPrivilege
 *
 * @author setia.budi
 */
class ZonetraxPrivilege {
    
    const ACCESS_OWN = 'ZNT_ACC_OWN';
    const ACCESS_GROUP = 'ZNT_ACC_GROUP';
    const ACCESS_GLOBAL = 'ZNT_ACC_GLOBAL';
    
    /*
     * GSM Geofence
     */
    const ZNT_TAB_GSM_GEOFENCE = 'ZNT_TAB_GSM_GEOFENCE';
    const ZNT_TAB_GSM_GEOFENCE_WRITE = 'ZNT_TAB_GSM_GEOFENCE_WRITE';
    const ZNT_TAB_GSM_GEOFENCE_DELETE = 'ZNT_TAB_GSM_GEOFENCE_DELETE';
    
    /*
     * GPS Geofence
     */
    const ZNT_TAB_GPS_GEOFENCE = 'ZNT_TAB_GPS_GEOFENCE';
    const ZNT_TAB_GPS_GEOFENCE_WRITE = 'ZNT_TAB_GPS_GEOFENCE_WRITE';
    const ZNT_TAB_GPS_GEOFENCE_DELETE = 'ZNT_TAB_GPS_GEOFENCE_DELETE';

    /*
     * Location Tracking
     */
    const ZNT_TAB_LOCATION_TRACKING = 'ZNT_TAB_LOCATION_TRACKING';    
    
    /*
     * Alaram History
     */    
    const ZNT_TAB_ALARM_HISTORY = 'ZNT_TAB_ALARM_HISTORY';
    
    /*
     * Option Privilege
     */
    const ZNT_OPT_LOCATION_TRACKING_CSV_DOWNLOAD_SHOW = 'ZNT_OPT_LOCATION_TRACKING_CSV_DOWNLOAD_SHOW';
    
    const ZNT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW = 'ZNT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW';
    const ZNT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW = 'ZNT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW';
    const ZNT_OPT_LOCATE_ENABLE = 'ZNT_OPT_LOCATE_ENABLE';

}
