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
 * Initial Author   : Nababan Maryo Sandoz <maryo.sandoz@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-08-03   Nababan Maryo Sandoz                1.000.000    Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */
namespace Firstwap\Altamides\Jamtrax\Auth;
    
/**
 * Description of RapidtraxPrivilege
 *
 * @author setia.budi
 */
class JamtraxPrivilege {
    
    const JT_ACC_OWN = 'JT_ACC_OWN';
    const JT_ACC_GROUP = 'JT_ACC_GROUP';
    const JT_ACC_GLOBAL = 'JT_ACC_GLOBAL';
    
    /*
     * Target Passive Jamming
     */
    const JT_TAB_TARGET_PASSIVE_JAMMING = 'JT_TAB_TARGET_PASSIVE_JAMMING';
    
    /*
     * Ad-Hoc Passive Jamming
     */
    const JT_TAB_ADHOC_PASSIVE_JAMMING = 'JT_TAB_ADHOC_PASSIVE_JAMMING';
    /*
     * Schedule
     */
    const JT_TAB_SCHEDULE_PASSIVE_JAMMING = 'JT_TAB_SCHEDULE_PASSIVE_JAMMING';
    const JT_TAB_SCHEDULE_PASSIVE_JAMMING_WRITE = 'JT_TAB_SCHEDULE_PASSIVE_JAMMING_WRITE';
    const JT_TAB_SCHEDULE_DATA_JAMMING_WRITE = 'JT_TAB_SCHEDULE_DATA_JAMMING_WRITE';
    
    /*
     * OPT PRIVILEGE
     */
    const JT_OPT_SCHEDULE_CSV_DOWNLOAD_SHOW = 'JT_OPT_SCHEDULE_CSV_DOWNLOAD_SHOW';
    const JT_OPT_SCHEDULE_PDF_DOWNLOAD_SHOW = 'JT_OPT_SCHEDULE_PDF_DOWNLOAD_SHOW';
 
    const JT_OPT_ADHOC_PASSIVE_JAMMING_LOCATE_ENABLE = 'JT_OPT_ADHOC_PASSIVE_JAMMING_LOCATE_ENABLE';
    const JT_OPT_ADHOC_PASSIVE_JAMMING_JAM_ENABLE = 'JT_OPT_ADHOC_PASSIVE_JAMMING_JAM_ENABLE';
    const JT_OPT_ADHOC_PASSIVE_JAMMING_UNJAM_ENABLE = 'JT_OPT_ADHOC_PASSIVE_JAMMING_UNJAM_ENABLE';
    const JT_OPT_TARGET_PASSIVE_JAMMING_LOCATE_ENABLE = 'JT_OPT_TARGET_PASSIVE_JAMMING_LOCATE_ENABLE';
    const JT_OPT_TARGET_PASSIVE_JAMMING_JAM_ENABLE = 'JT_OPT_TARGET_PASSIVE_JAMMING_JAM_ENABLE';
    const JT_OPT_TARGET_PASSIVE_JAMMING_UNJAM_ENABLE = 'JT_OPT_TARGET_PASSIVE_JAMMING_UNJAM_ENABLE';
    
    /*
     * Data JAMMING
     */
    const JT_OPT_TARGET_DATA_JAMMING_JAM_ENABLE = 'JT_OPT_TARGET_DATA_JAMMING_JAM_ENABLE';
    const JT_OPT_TARGET_DATA_JAMMING_UNJAM_ENABLE = 'JT_OPT_TARGET_DATA_JAMMING_UNJAM_ENABLE';
    const JT_OPT_ADHOC_DATA_JAMMING_JAM_ENABLE = 'JT_OPT_ADHOC_DATA_JAMMING_JAM_ENABLE';
    const JT_OPT_ADHOC_DATA_JAMMING_UNJAM_ENABLE = 'JT_OPT_ADHOC_DATA_JAMMING_UNJAM_ENABLE';
}
