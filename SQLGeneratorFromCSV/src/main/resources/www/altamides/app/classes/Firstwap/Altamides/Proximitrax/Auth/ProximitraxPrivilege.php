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
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : 
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Proximitrax\Auth;

/**
 * Description of ProximitraxPrivilege
 * 
 * @author Dwikky Maradhiza Yudakusuma <dwikky.maradhiza@1rstwap.com>
 */
class ProximitraxPrivilege
{
    /**
     * access privilege
     */
    const ACCESS_OWN                            = 'PXT_ACC_OWN';
    const ACCESS_GROUP                          = 'PXT_ACC_GROUP';
    const ACCESS_GLOBAL                         = 'PXT_ACC_GLOBAL';
    
    /**
     * proximitrax admin
     */
    const PXT_TAB_PROXIMITRAX_ADMIN             = 'PXT_TAB_PROXIMITRAX_ADMIN'; // Grants view only access to the Proximitrax admin, where the Proximitrax admin can be managed
    const PXT_TAB_PROXIMITRAX_ADMIN_WRITE       = 'PXT_TAB_PROXIMITRAX_ADMIN_WRITE'; //Grants view only access to the Proximitrax admin, where the Proximitrax admin can be managed
    const PXT_TAB_PROXIMITRAX_ADMIN_DELETE      = 'PXT_TAB_PROXIMITRAX_ADMIN_DELETE'; //Grants view only access to the Proximitrax admin, where the Proximitrax admin can be managed
    
    /**
     * proximitrax tracking
     */
    const PXT_TAB_PROXIMITRAX_TRACKING                      = 'PXT_TAB_PROXIMITRAX_TRACKING'; //Grants view access to proximitrax tracking page
    const PXT_OPT_PROXIMITRAX_TRACKING_CSV_DOWNLOAD_SHOW    = 'PXT_OPT_PROXIMITRAX_TRACKING_CSV_DOWNLOAD_SHOW'; //Grants access to download csv in proximitrax tracking
    const PXT_OPT_LOCATE_ENABLE                             = 'PXT_OPT_LOCATE_ENABLE'; //Enable locate button
    
    /**
     * alarm history
     */
    const PXT_TAB_ALARM_HISTORY                     = 'PXT_TAB_ALARM_HISTORY'; //Grants view rights to users for proximitrax alarm history
    const PXT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW   = 'PXT_OPT_ALARM_HISTORY_CSV_DOWNLOAD_SHOW'; //Grants access to download csv in proximitrax alarm history
    const PXT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW   = 'PXT_OPT_ALARM_HISTORY_PDF_DOWNLOAD_SHOW'; //Grants access to download pdf in proximitrax alarm history
    const PXT_OPT_VIP_TARGET_ALARM_ADMIN            = 'PXT_OPT_VIP_TARGET_ALARM_ADMIN';
}
