<?php

/**
 * (c) 2014 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : 
 * File Version     : 2.0SP22 
 * Initial Creation : 2015-04-30
 * Initial Author   : Hadi
 * Purpose          : 
 * 
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Disc\Auth;

/**
 * Description of DiscPrivilege
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DiscPrivilege
{

    const ACCESS_OWN                      = 'DSC_ACC_OWN';
    const ACCESS_GROUP                    = 'DSC_ACC_GROUP';
    const ACCESS_GLOBAL                   = 'DSC_ACC_GLOBAL';
    
    /*
     * SMS Sending
     */
    const DSC_TAB_SMS_SENDING             = 'DSC_TAB_SMS_SENDING'; //Grant view only access to SMS Sending Page, user can sent SMS and create schedule.
    
    /*
     * Sending Report
     */
    const DSC_TAB_SENDING_REPORT          = 'DSC_TAB_SENDING_REPORT'; //Grant view only access to Sending Report Page
    
    /*
     * Incoming Reply
     */
    const DSC_TAB_INCOMING_REPLY          = 'DSC_TAB_INCOMING_REPLY'; //Grant view only access to Incoming Reply Page
    
    /*
     * Sender ID Admin
     */
    const DSC_TAB_SENDER_ID_ADMIN         = 'DSC_TAB_SENDER_ID_ADMIN'; //Grant view only access to Sender ID Admin Page
    const DSC_TAB_SENDER_ID_ADMIN_WRITE   = 'DSC_TAB_SENDER_ID_ADMIN_WRITE'; //Grant view and write-edit access to Sender ID Admin Page, user can add new and edit existing sender ID.
    const DSC_TAB_SENDER_ID_ADMIN_DELETE  = 'DSC_TAB_SENDER_ID_ADMIN_DELETE'; //Grant view and delete access to Sender ID Admin Page, user can delete existing sender ID.
    const DSC_OPT_UPLOAD_CSV_SHOW         = 'DSC_OPT_UPLOAD_CSV_SHOW'; //Grant access to upload csv in Sender ID Admin Page. 

    /*
     * User Admin
     */
    const DSC_TAB_USER_ADMIN              = 'DSC_TAB_USER_ADMIN'; //Grant view only access to User Admin Page
    const DSC_TAB_USER_ADMIN_WRITE        = 'DSC_TAB_USER_ADMIN_WRITE'; //Grant view and write-edit access to User Admin Page, user can add/remove sender ID per user
    
}
