<?php

/*
 * Copyright (c) Error: on line 4, column 33 in file:///D:/Users/Buddy/Documents/Office/Projects/php-framework/project-data/license-header.txt
  The string doesn't match the expected date/time format. The string to parse was: "Jul 7, 2014". The expected format was: "dd-MMM-yyyy". 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 * 
 * 
 * System           : Firstwap PHP Framework
 * File Version     : SVN: $Id: SpottraxPrivilege.php 17882 2014-07-08 07:52:44Z setia.budi $
 * Initial Creation : Error: on line 12, column 38 in file:///D:/Users/Buddy/Documents/Office/Projects/php-framework/project-data/license-header.txt
  The string doesn't match the expected date/time format. The string to parse was: "Jul 7, 2014". The expected format was: "dd-MMM-yyyy".
 * Initial Author   : Setia Budi Halim
 * Purpose          :
 * 
 */

namespace Firstwap\Altamides\Admin\Auth;

/**
 * Description of SpottraxPrivilege
 *
 * @author Setia Budi Halim
 */
final class AdminPrivilege
{
    const SUPERADMIN  = 'SSO_SuperAdmin';
    
    //Access-Level Privilege:
    const ADM_ACC_OWN                   = 'ADM_ACC_OWN'; //Grants access to the Admin module on a user level, allowing users to access data which are related ONLY to their own account;
    const ADM_ACC_GROUP                 = 'ADM_ACC_GROUP'; //Grants access to the Admin module on a group level, allowing users to access data which are related to the users from their own group;
    const ADM_ACC_GLOBAL                = 'ADM_ACC_GLOBAL'; //Grants access to the Admin module on a global level, allowing users to access data which are related to all groups and individual users;
    
    // Group Admin
    const ADM_GROUP_ADMIN           = 'ADM_TAB_GROUP_ADMIN'; //Grants view only access to the Group Admin Page in Admin module;
    const ADM_GROUP_ADMIN_WRITE     = 'ADM_TAB_GROUP_ADMIN_WRITE'; //Grants view, create and update right to modify group in Group Admin Page of Admin module;
    const ADM_GROUP_ADMIN_DELETE    = 'ADM_TAB_GROUP_ADMIN_DELETE'; //Grants view and delete right to modify group in Group Admin Page of Admin module;
    
    // User Admin
    const ADM_USER_ADMIN            = 'ADM_TAB_USER_ADMIN'; // Grants view only access to the User Admin Page in Admin module.
    const ADM_USER_ADMIN_WRITE      = 'ADM_TAB_USER_ADMIN_WRITE'; // Grants view, create and update right to modify user in User Admin Page of Admin module.
    const ADM_USER_ADMIN_DELETE     = 'ADM_TAB_USER_ADMIN_DELETE'; // Grants view and delete right to modify user in User Admin Page of Admin module.
    
    // Users
    const ADM_USER_LIST             = 'ADM_TAB_USER_LIST'; //Grants view only access to the User List Page of Admin module
    
    // Roles
    const ADM_ROLES                 = 'ADM_TAB_ROLES'; //Grants view only access to the Roles Page of Admin module.
    const ADM_ROLES_WRITE           = 'ADM_TAB_ROLES_WRITE'; //Grants view, create and update right to modify role in Roles Page of Admin module.
    const ADM_ROLES_DELETE          = 'ADM_TAB_ROLES_DELETE'; //Grants view and delete right to modify role in Roles Page of Admin module.
    
    // Privileges
    const ADM_PRIVILEGES            = 'ADM_TAB_PRIVILEGES'; //Grants view only access to the Privileges Page of Admin module.
    
    // Option Privileges
    const ADM_USER_ADMIN_REACTIVE_ADMIN         = 'ADM_OPT_USER_ADMIN_REACTIVE_ADMIN'; //replacement from 'SSO_ReactiveAdmin'
    const ADM_USER_ADMIN_REDLIST_AS_WHITELIST   = 'ADM_OPT_USER_ADMIN_REDLIST_AS_WHITELIST'; //replacement from 'SSO_REDLIST_AS_WHITELIST'
    const ADM_USER_ADMIN_RESET_PASSWORD         = 'ADM_OPT_USER_ADMIN_RESET_PASSWORD'; //replacement from 'SSO_ResetPassword'
    
    //QUOTA
    const ADM_TAB_QUOTA_OVERVIEW = 'ADM_TAB_QUOTA_OVERVIEW';
    const ADM_TAB_QUOTA_HISTORY = 'ADM_TAB_QUOTA_HISTORY';
    const ADM_TAB_QUOTA_OVERVIEW_WRITE = 'ADM_TAB_QUOTA_OVERVIEW_WRITE';
    const ADM_TAB_QUOTA_OVERVIEW_DELETE = 'ADM_TAB_QUOTA_OVERVIEW_DELETE';
    const ADM_OPT_MANAGE_QUOTA_GROUP = 'ADM_OPT_MANAGE_QUOTA_GROUP';
}
