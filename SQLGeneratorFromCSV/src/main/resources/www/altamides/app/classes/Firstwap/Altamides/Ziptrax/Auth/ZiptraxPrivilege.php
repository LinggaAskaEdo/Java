<?php

/**
 * (c) 2015 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0SP22 
 * Initial Creation : 2015-08-04
 * Initial Author   : Yung Fei <yung.fei@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                 Version    Request  Comment
 * 2015-08-04   Yung Fei                1.000.000           Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Ziptrax\Auth;

/**
 * Description of ProfiletraxPrivilege
 * 
 * @author Nababan Maryo Sandoz <maryo.sandoz@1rstwap.com>
 */
class ZiptraxPrivilege
{

    const ACCESS_OWN                            = 'ZPT_ACC_OWN'; //Grants access to the Ziptrax module on a user level, allowing users to access data which are related ONLY to their own account
    const ACCESS_GROUP                          = 'ZPT_ACC_GROUP'; //Grants access to the Ziptrax module on a group level, allowing users to access data which are related to the users from their own group
    const ACCESS_GLOBAL                         = 'ZPT_ACC_GLOBAL'; //Grants access to the Ziptrax module on a global level, allowing users to access data which are related to all groups and individual users

    /*
     * Ziptrax Admin 
     */
    const ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN              = 'ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN'; //Grants view only access to the City/Country Fence Admin Page, where a user can add/edit  Ziptrax City/Country Fence admin.
    const ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN_WRITE        = 'ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN_WRITE'; //Grants view, add & edit access to the City/Country Fence Admin Page, where a user can add/edit City/Country Fence admin.
    const ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN_DELETE       = 'ZPT_TAB_CITY_COUNTRY_FENCE_ADMIN_DELETE'; //Grants view and delete access to the City/Country Fence Admin Page, where a user can add/edit City/Country Fence admin.

    /*
     * Ziptrax tracking
     */
    const ZPT_TAB_CITY_COUNTRY_FENCE_TRACKING           = 'ZPT_TAB_CITY_COUNTRY_FENCE_TRACKING'; //Grants view only access to the Ziptrax Tracking Page, where a user can locate.

    /*
     * Ziptrax alarm
     */
    const ZPT_TAB_ALARM_HISTORY                         = 'ZPT_TAB_ALARM_HISTORY'; //Grants view only access to the Ziptrax Alarm History Page.
    
    /*
     *  opt
     */
    const ZPT_OPT_CITY_COUNTRY_FENCE_TRACKING_CSV_DOWNLOAD_SHOW             = 'ZPT_OPT_CITY_COUNTRY_FENCE_TRACKING_CSV_DOWNLOAD_SHOW'; //Grants download csv access in Ziptrax Tracking Page.
    const ZPT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW                           = 'ZPT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW'; //Grants download csv access in Ziptrax Alarm History Page.
    const ZPT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW                           = 'ZPT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW'; //Grants download pdf access in Ziptrax Alarm History Page.
    const ZPT_OPT_LOCATE_ENABLE                                             = 'ZPT_OPT_LOCATE_ENABLE'; //Privilege for enabled/disabled locate button.
}
