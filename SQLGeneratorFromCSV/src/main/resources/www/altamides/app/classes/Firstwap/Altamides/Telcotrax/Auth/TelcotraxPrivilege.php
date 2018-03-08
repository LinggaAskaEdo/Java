<?php
/*
 * (c) 2014 FirstWAP. All rights reserved
 *
 * System           : Altamides
 * Module           : Telcotrax
 * Version          : 1.0 SP21
 * Filename         : lookupdb.php
 * Fileversion      : 
 * Initial Creation : 2015-03-28
 * Initial Author   : Sejati <waluyo.sejati@1rstwap.com>
 * Purpose          : Search IMSI or MSISDN data
 * 
 * ================================================
 * Initial Request  : #
 * ================================================
 * Change Log
 * Date         Author      Version     Request     Comment
 * 2014-03-09   Sejati      1.0         #2402       Create imsi or msisdn lookup
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Auth;

final class TelcotraxPrivilege {
    
    /*Access-Level Privilege*/
    const TT_ACC_GLOBAL = 'TT_ACC_GLOBAL';
    
    /*Tab Privilege*/
    const TT_TAB_CELL_DB = 'TT_TAB_CELL_DB';
    const TT_TAB_CELL_DB_WRITE = 'TT_TAB_CELL_DB_WRITE';
    const TT_TAB_CELL_DB_DELETE = 'TT_TAB_CELL_DB_DELETE';
    
    const TT_TAB_MSC = 'TT_TAB_MSC';
    const TT_TAB_MSC_WRITE = 'TT_TAB_MSC_WRITE';
    const TT_TAB_MSC_DELETE = 'TT_TAB_MSC_DELETE';
    
    const TT_TAB_INP = 'TT_TAB_INP';
    const TT_TAB_INP_WRITE = 'TT_TAB_INP_WRITE';
    const TT_TAB_INP_DELETE = 'TT_TAB_INP_DELETE';
    
    const TT_TAB_IMEI_DB = 'TT_TAB_IMEI_DB';
    const TT_TAB_IMEI_DB_WRITE = 'TT_TAB_IMEI_DB_WRITE';
    
    const TT_TAB_IMSI_LOOKUP = 'TT_TAB_IMSI_LOOKUP';
    
    const TT_TAB_CELL_DENSITY_VIEW = 'TT_TAB_CELL_DENSITY_VIEW';
    
    const TT_TAB_CELL_COMPUTATION = 'TT_TAB_CELL_COMPUTATION';
    const TT_TAB_CELL_COMPUTATION_WRITE = 'TT_TAB_CELL_COMPUTATION_WRITE';
    /*
     * end of tab priv
     */
    
    const TT_OPT_CELL_DB_MAX_UPLOAD = 'TT_OPT_CELL_DB_MAX_UPLOAD';
    const TT_OPT_MSC_MAX_UPLOAD = 'TT_OPT_MSC_MAX_UPLOAD';
    const TT_OPT_INP_MAX_UPLOAD = 'TT_OPT_INP_MAX_UPLOAD';
    
}
