<?php

/**
 * (c) 2014 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.1SP22 
 * Initial Creation : 2015-06-30
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version       Request     Comment
 * 2015-06-30   Dwikky Maradhiza          1.000.000                 Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Doc\Auth;

/**
 * Description of DocPrivilege
 * 
 * @author Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 */
class DocPrivilege
{
    const ACCESS_GLOBAL                     = 'DOC_ACC_GLOBAL'; //Grants access to the documentation module on a global level, allowing users to access data which are related to all groups and individual users

    const DOC_TAB_USER_ACCEPTANCE_TESTS     = 'DOC_TAB_USER_ACCEPTANCE_TESTS'; // Grants view only access to the UAT Documents page.
    const DOC_TAB_USER_MANUALS              = 'DOC_TAB_USER_MANUALS'; // Grants view only access to the user manual page.
    const DOC_TAB_ADMINISTRATOR_DOCUMENTS   = 'DOC_TAB_ADMINISTRATOR_DOCUMENTS'; // Grants view only access to the administrator document page.
    const DOC_TAB_VIDEOS                    = 'DOC_TAB_VIDEOS'; // Grants view only access to the videos page.
}
