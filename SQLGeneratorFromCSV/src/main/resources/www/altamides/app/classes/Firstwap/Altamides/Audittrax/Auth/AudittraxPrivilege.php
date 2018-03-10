<?php

/**
 * (c) 2015 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : AudittraxPrivilege.php
 * File Version     : 2.0SP22 
 * Initial Creation : 2015-05-04
 * Initial Author   : Sejati <waluyo.sejati@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version       Request     Comment
 * 2015-05-04   Sejati                    1.000.000                 Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Audittrax\Auth;

/**
 * Description of AudittraxPrivilege
 *
 * @author Sejati <waluyo.sejati@1rstwap.com>
 */
class AudittraxPrivilege {
    
    const BTN_AUDIT_TRAIL_DOWNLOAD_SHOW     = 'AT_BTN_AUDIT_TRAIL_DOWNLOAD_SHOW';
    const OPT_AUDIT_TRAIL_ADD_NOTES_SHOW    = 'AT_OPT_AUDIT_TRAIL_ADD_NOTES_SHOW';
    const ACC_GLOBAL                        = 'AT_ACC_GLOBAL';
    const ACC_GROUP                         = 'AT_ACC_GROUP';
    
    /*Audit trail Tab*/
    const AT_TAB_AUDITTRAIL                 = 'AT_TAB_AUDITTRAIL';
}
