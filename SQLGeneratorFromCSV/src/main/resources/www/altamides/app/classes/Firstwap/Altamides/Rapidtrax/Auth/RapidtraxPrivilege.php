<?php


/**
 * (c) 2014 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0 SP20 
 * Initial Creation : 2014-10-22
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2014-10-22   Setia Budi Halim          1.000.000           Initial Creation
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */
namespace Firstwap\Altamides\Rapidtrax\Auth;
    
/**
 * Description of RapidtraxPrivilege
 *
 * @author setia.budi
 */
class RapidtraxPrivilege {
    
    const ACCESS_OWN = 'RT_ACC_OWN';
    const ACCESS_GROUP = 'RT_ACC_GROUP';
    const ACCESS_GLOBAL = 'RT_ACC_GLOBAL';
    
    /*
     * Locate 
     */
    const RT_TAB_LOCATE = 'RT_TAB_LOCATE';

    /*
     * Schedule
     */
    const RT_TAB_SCHEDULE = 'RT_TAB_SCHEDULE';
    const RT_TAB_SCHEDULE_WRITE = 'RT_TAB_SCHEDULE_WRITE';
    const RT_TAB_SCHEDULE_DELETE = 'RT_TAB_SCHEDULE_DELETE';
    
    /*
     * History
     */
    const RT_TAB_HISTORY = 'RT_TAB_HISTORY';

    /*
     * Tracking Report
     */
    const RT_TAB_TRACKING_REPORT = 'RT_TAB_TRACKING_REPORT';
    
    
    /*
     * Sent Location Result
     */
    
    const RT_TAB_SENT_LOCATION_RESULT = 'RT_TAB_SENT_LOCATION_RESULT';
    
    /*
     * Option Privilege
     */
    const RT_OPT_LOCATE_HA_SHOW = 'RT_OPT_LOCATE_HA_SHOW';
    const RT_OPT_TRACKING_REPORT_TECHNICAL_VIEW = 'RT_OPT_TRACKING_REPORT_TECHNICAL_VIEW';
    const RT_OPT_LOCATE_ENABLE = 'RT_OPT_LOCATE_ENABLE';
    const RT_OPT_SEND_RESULT_ENABLE = 'RT_OPT_SEND_RESULT_ENABLE';
    const RT_OPT_LOCATE_DISC_SENDER_ID_SHOW = 'RT_OPT_LOCATE_DISC_SENDER_ID_SHOW';
    
    
    const RT_OPT_TRACKING_REPORT_CSV_DOWNLOAD_SHOW = 'RT_OPT_TRACKING_REPORT_CSV_DOWNLOAD_SHOW';
    const RT_OPT_TRACKING_REPORT_PDF_DOWNLOAD_SHOW = 'RT_OPT_TRACKING_REPORT_PDF_DOWNLOAD_SHOW';
}
