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
 * Initial Author   : Nababan Maryo Sandoz <maryo.sandoz@1rstwap.com>
 * Purpose          : 
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                 Version    Request  Comment
 * 2015-04-29   Nababan Maryo          1.000.000           Initial Creation
 * 
 * Copyright 2015 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Profiletrax\Auth;

/**
 * Description of ProfiletraxPrivilege
 * 
 * @author Nababan Maryo Sandoz <maryo.sandoz@1rstwap.com>
 */
class ProfiletraxPrivilege
{

    const ACCESS_OWN                            = 'PFT_ACC_OWN';
    const ACCESS_GROUP                          = 'PFT_ACC_GROUP';
    const ACCESS_GLOBAL                         = 'PFT_ACC_GLOBAL';

    const PFT_TAB_BASIC_TARGET_ADMIN            = 'PFT_TAB_BASIC_TARGET_ADMIN'; // Grants view only access to the ProfileTrax basic target admin, where the targets can be managed
    const PFT_TAB_EXTENDED_TARGET_ADMIN         = 'PFT_TAB_EXTENDED_TARGET_ADMIN'; //Grants view only access to the ProfileTrax extended target admin, where the targets can be managed with more options
    const PFT_TAB_GROUP_TARGET_ADMIN            = 'PFT_TAB_GROUP_TARGET_ADMIN'; //Grants view only access to the ProfileTrax group target admin, where the groups can be managed with more options
    const PFT_TAB_WHITELIST                     = 'PFT_TAB_WHITELIST'; //Grants view only access to the ProfileTrax whitelist, where the targets that a user can interrogate can be managed
    const PFT_TAB_BLACKLIST                     = 'PFT_TAB_BLACKLIST'; //Grants view only access to the ProfileTrax blacklist, where the targets that are not allowed to be interrogated by the system can be managed
    const PFT_TAB_REDLIST                       = 'PFT_TAB_REDLIST'; //Grants view only access to the ProfileTrax redlist, where exceptions to the ProfileTrax blacklist can be made for specific users

    const PFT_TAB_BASIC_TARGET_ADMIN_WRITE      = 'PFT_TAB_BASIC_TARGET_ADMIN_WRITE'; //Grants view, create and update rights to users for ProfileTrax basic target admin
    const PFT_TAB_EXTENDED_TARGET_ADMIN_WRITE   = 'PFT_TAB_EXTENDED_TARGET_ADMIN_WRITE'; //Grants view, create and update rights to users for ProfileTrax extended target admin
    const PFT_TAB_GROUP_TARGET_ADMIN_WRITE      = 'PFT_TAB_GROUP_TARGET_ADMIN_WRITE'; //Grants view, create and update rights to users for ProfileTrax group target admin
    const PFT_TAB_WHITELIST_WRITE               = 'PFT_TAB_WHITELIST_WRITE'; //Grants view, create and update rights to users for ProfileTrax whitelist
    const PFT_TAB_BLACKLIST_WRITE               = 'PFT_TAB_BLACKLIST_WRITE'; //Grants view, create and update rights to users for ProfileTrax blacklist
    const PFT_TAB_REDLIST_WRITE                 = 'PFT_TAB_REDLIST_WRITE'; //Grants view, create and update rights to users for ProfileTrax redlist

    const PFT_TAB_BASIC_TARGET_ADMIN_DELETE     = 'PFT_TAB_BASIC_TARGET_ADMIN_DELETE'; //Grants view and delete rights to users for ProfileTrax basic target admin
    const PFT_TAB_EXTENDED_TARGET_ADMIN_DELETE  = 'PFT_TAB_EXTENDED_TARGET_ADMIN_DELETE'; //Grants view and delete rights to users for ProfileTrax extended target admin
    const PFT_TAB_GROUP_TARGET_ADMIN_DELETE     = 'PFT_TAB_GROUP_TARGET_ADMIN_DELETE'; //Grants view and delete rights to users for ProfileTrax group target admin
    const PFT_TAB_WHITELIST_DELETE              = 'PFT_TAB_WHITELIST_DELETE'; //Grants create and view rights to users for ProfileTrax whitelist
    const PFT_TAB_BLACKLIST_DELETE              = 'PFT_TAB_BLACKLIST_DELETE'; //Grants create and view rights to users for ProfileTrax blacklist
    const PFT_TAB_REDLIST_DELETE                = 'PFT_TAB_REDLIST_DELETE'; //Grants create and view rights to users for ProfileTrax redlist
    const PFT_OPT_BLACKLIST_CSV_UPLOAD_SHOW     = 'PFT_OPT_BLACKLIST_CSV_UPLOAD_SHOW';
    const PFT_OPT_TARGET_ADMIN_SCHEDULE_CREATE  = 'PFT_OPT_TARGET_ADMIN_SCHEDULE_CREATE'; //Option to enable creation of schedules using basic or extended target admin in ProfileTrax
    const PFT_OPT_TARGET_ADMIN_SCHEDULE_UPDATE  = 'PFT_OPT_TARGET_ADMIN_SCHEDULE_UPDATE'; //Option to enable updating of schedules shown basic or extended target admin in ProfileTrax
    const PFT_OPT_TARGET_ADMIN_SCHEDULE_DELETE  = 'PFT_OPT_TARGET_ADMIN_SCHEDULE_DELETE'; //Option to enable deleting of schedules shown basic or extended target admin in ProfileTrax
    const PFT_OPT_WHITELIST_CSV_UPLOAD_SHOW     = 'PFT_OPT_WHITELIST_CSV_UPLOAD_SHOW';
    const PFT_OPT_EXTENDED_TARGET_ADMIN_CSV_UPLOAD_SHOW     = 'PFT_OPT_EXTENDED_TARGET_ADMIN_CSV_UPLOAD_SHOW';
    const PFT_OPT_BASIC_TARGET_ADMIN_HA_SHOW    = 'PFT_OPT_BASIC_TARGET_ADMIN_HA_SHOW'; //Allow user to use high accuracy in basic target admin
    const PFT_OPT_EXTENDED_TARGET_ADMIN_HA_SHOW = 'PFT_OPT_EXTENDED_TARGET_ADMIN_HA_SHOW'; //Allow user to use high accuracy in extended target admin
    const PFT_OPT_PHONE_ALARM_SHOW              = 'PFT_OPT_PHONE_ALARM_SHOW'; //Show phone alarm checkbox
    const PFT_OPT_VIP_ALARM_SHOW                = 'PFT_OPT_VIP_ALARM_SHOW'; //Show VIP alarm checkbox
    const PFT_OPT_CALL_FORWARDING_ALARM_SHOW    = 'PFT_OPT_CALL_FORWARDING_ALARM_SHOW'; //Show call forwarding alarm checkbox
    
    const PFT_TAB_USER_TARGET_ADMIN             = 'PFT_TAB_USER_TARGET_ADMIN';
    const PFT_TAB_USER_TARGET_ADMIN_WRITE 	= 'PFT_TAB_USER_TARGET_ADMIN_WRITE';
    const PFT_TAB_USER_TARGET_ADMIN_DELETE 	= 'PFT_TAB_USER_TARGET_ADMIN_DELETE';
        
    const PFT_OPT_HA_SYNC_MS 	                = 'PFT_OPT_HA_SYNC_MS'; // Allows the user to synchronize High Accuracy location results that were collected in the past from MassTrax into OmniTrax. Works well for newly created targets.
}
