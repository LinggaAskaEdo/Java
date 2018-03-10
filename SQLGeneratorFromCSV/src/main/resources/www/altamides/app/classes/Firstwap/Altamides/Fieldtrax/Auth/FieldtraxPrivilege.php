<?php

/**
 * (c) 2014 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0SP20 
 * Initial Creation : 2014-08-11
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version       Request     Comment
 * 2012-08-30   Setia Budi Halim          1.000.000                 Initial Creation
 * 2015-03-10   Hadi                      1.000.000     #4303       Add USER_SET_REDLIST_AS_WHITELIST  constanta
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Fieldtrax\Auth;

/**
 * Description of FieldtraxPrivilege
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class FieldtraxPrivilege
{
    const ACC_GLOBAL                        = 'FT_ACC_GLOBAL';
    const ACC_GROUP                         = 'FT_ACC_GROUP';
    const ACC_OWN                           = 'FT_ACC_OWN';

    /**
     *  FT user Admin
     */
    const FT_TAB_USER_ADMIN                 = 'FT_TAB_USER_ADMIN';
    const FT_TAB_USER_ADMIN_WRITE           = 'FT_TAB_USER_ADMIN_WRITE';
    const FT_TAB_USER_ADMIN_DELETE          = 'FT_TAB_USER_ADMIN_DELETE';
    
    /**
     * FT Location Record
     */
    const FT_TAB_LOCATION_RECORDS            = 'FT_TAB_LOCATION_RECORDS';
    
    /**
     * FT Whitelist
     */
    const FT_TAB_WHITELIST                  = 'FT_TAB_WHITELIST';
    const FT_TAB_WHITELIST_WRITE            = 'FT_TAB_WHITELIST_WRITE';
    const FT_TAB_WHITELIST_DELETE           = 'FT_TAB_WHITELIST_DELETE';
    
    /**
     * FT Redlist
     */
    const FT_TAB_REDLIST                    = 'FT_TAB_REDLIST';
    const FT_TAB_REDLIST_WRITE              = 'FT_TAB_REDLIST_WRITE';
    const FT_TAB_REDLIST_DELETE             = 'FT_TAB_REDLIST_DELETE';
    
    /**
     * FT admin History
     */
    const FT_TAB_ADMIN_HISTORY              = 'FT_TAB_ADMIN_HISTORY';
    
    /**
     *  OPT
     */
    const FT_OPT_USER_ADMIN_WHITELIST_IGNORE        = 'FT_OPT_USER_ADMIN_WHITELIST_IGNORE';

    const FT_OPT_LOCATION_RECORDS_CSV_DOWNLOAD_SHOW  = 'FT_OPT_LOCATION_RECORDS_CSV_DOWNLOAD_SHOW';
    
    const FT_OPT_SMS_COMMAND_CONTROL_ADMIN_SHOW  = 'FT_OPT_SMS_COMMAND_CONTROL_ADMIN_SHOW';
    
    const FT_OPT_RESULT_RESTRICTION_ADMIN_SHOW  = 'FT_OPT_RESULT_RESTRICTION_ADMIN_SHOW';
    
    const FT_OPT_SCHEDULE_DELETE ='FT_OPT_SCHEDULE_DELETE';
    
    const FT_OPT_SCHEDULE_WRITE  ='FT_OPT_SCHEDULE_WRITE';


}
