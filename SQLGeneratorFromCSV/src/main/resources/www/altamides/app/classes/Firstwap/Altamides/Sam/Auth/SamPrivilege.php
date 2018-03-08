<?php

/**
 * (c) 2015 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0SP22 
 * Initial Creation : 2015-04-29
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                 Version    Request  Comment
 * 2015-04-29   Dwikky Maradhiza       1.000.000           Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Sam\Auth;

/**
 * Description of SamPrivilege
 * 
 * @author Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 */
class SamPrivilege
{

    const ACCESS_GLOBAL                         = 'SAM_ACC_GLOBAL';
    const ACCESS_GROUP                          = 'SAM_ACC_GROUP';
    const ACCESS_OWN                            = 'SAM_ACC_OWN';

    const SAM_TAB_SERVICE_MONITOR               = 'SAM_TAB_SERVICE_MONITOR'; // Grants view only access to the Sam Service Monitor
    const SAM_TAB_SYSTEM_STATISTIC              = 'SAM_TAB_SYSTEM_STATISTIC'; //Grants view only access to the Sam System Statistic
    const SAM_TAB_CONFIGURATION                 = 'SAM_TAB_CONFIGURATION'; //Grants view only access to the Sam Configuration
    
    const SAM_TAB_INTERROGATIONS                  = 'SAM_TAB_INTERROGATIONS_STATISTICS';
    const SAM_TAB_INTERROGATIONS_WRITE            = 'SAM_TAB_INTERROGATIONS_STATISTICS_WRITE';
    const SAM_TAB_INTERROGATIONS_DELETE           = 'SAM_TAB_INTERROGATIONS_STATISTICS_DELETE';
    
    const SAM_OPT_INTERROGATIONS_CSV_DOWNLOAD     = 'SAM_OPT_INTERROGATIONS_STATISTICS_CSV_DOWNLOAD_SHOW';
    const SAM_OPT_INTERROGATIONS_PDF_DOWNLOAD     = 'SAM_OPT_INTERROGATIONS_STATISTICS_PDF_DOWNLOAD_SHOW';
}
