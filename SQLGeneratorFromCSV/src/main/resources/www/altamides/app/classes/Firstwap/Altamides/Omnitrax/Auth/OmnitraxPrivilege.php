<?php


/**
 * (c) 2015 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0 SP22 
 * Initial Creation : 2015-06-30
 * Initial Author   : Nababan Maryo Sandoz <maryo.sandoz@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-06-30   Nababan Maryo Sandoz          1.000.000           Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */
namespace Firstwap\Altamides\Omnitrax\Auth;
    
/**
 * Description of OmnitraxPrivilege
 *
 * @author maryo.sandoz
 */
class OmnitraxPrivilege {
    
    const ACCESS_OWN = 'OT_ACC_OWN';
    const ACCESS_GROUP = 'OT_ACC_GROUP';
    const ACCESS_GLOBAL = 'OT_ACC_GLOBAL';
        
    /*
     * Location Tracking 
     */
    const OT_TAB_LOCATION_TRACKING = 'OT_TAB_LOCATION_TRACKING';

    /*
     * Alarm History
     */
    const OT_TAB_ALARM_HISTORY = 'OT_TAB_ALARM_HISTORY';
    
    /*
     * GPS Command History
     */
    const OT_TAB_GPS_COMMAND_HISTORY = 'OT_TAB_GPS_COMMAND_HISTORY';

    /*
     * Tracking Report
     */
    const OT_TAB_TRACKING_REPORT = 'OT_TAB_TRACKING_REPORT';
  
    /*
     * Option Privilege
     */
    const OT_OPT_ALARM_HISTORY_CALL_FORWARDING_ALARM = 'OT_OPT_ALARM_HISTORY_CALL_FORWARDING_ALARM';
    const OT_OPT_ALARM_HISTORY_PHONE_ALARM = 'OT_OPT_ALARM_HISTORY_PHONE_ALARM';
    const OT_OPT_LOCATE_ENABLE = 'OT_OPT_LOCATE_ENABLE';
    const OT_OPT_LOCATION_TRACKING_HA_SHOW = 'OT_OPT_BASIC_TARGET_ADMIN_HA_SHOW';    
    const OT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW = 'OT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW';
    const OT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW = 'OT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW';
    const OT_OPT_TRACKING_REPORT_CSV_DOWNLOAD_SHOW = 'OT_OPT_TRACKING_REPORT_CSV_DOWNLOAD_SHOW';
    const OT_OPT_TRACKING_REPORT_PDF_DOWNLOAD_SHOW = 'OT_OPT_TRACKING_REPORT_PDF_DOWNLOAD_SHOW';
    const OT_OPT_TRACKING_REPORT_TECHNICAL_VIEW = 'OT_OPT_TRACKING_REPORT_TECHNICAL_VIEW';
    const OT_OPT_DETAIL_TECHNICAL_VIEW = 'OT_OPT_DETAIL_TECHNICAL_VIEW';
}
