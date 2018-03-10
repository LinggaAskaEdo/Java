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

namespace Firstwap\Altamides\Spottrax\Auth;

/**
 * Description of SpottraxPrivilege
 *
 * @author Setia Budi Halim
 */
final class SpottraxPrivilege
{
    
    const ST_ACC_OWN                    = 'ST_ACC_OWN';                         //Grants access to the Spottrax module on a own level, allowing users to access data their own.
    const ST_ACC_GROUP                  = 'ST_ACC_GROUP';                       //Grants access to the Spottrax module on a group level, allowing users to access data which are related to the users from their own group
    const ST_ACC_GLOBAL                 = 'ST_ACC_GLOBAL';                      //Grants access to the Spottrax module on a global level, allowing users to access data which are related to all groups and individual users
    
    #   TRACKING
    const ST_TAB_TRACKING               = 'ST_TAB_TRACKING';                    //Grants view only access to the spottrax tracking page, where a user can create track target.
    const ST_TAB_TRACKING_WRITE         = 'ST_TAB_TRACKING_WRITE';              //Grants view only access to the spottrax tracking page, where a user can play recorder.
    
    #   FIELD ACTIVITY
    const ST_TAB_FIELD_ACTIVITY         = 'ST_TAB_FIELD_ACTIVITY';              //Grants view only access to the spottrax field activity page, where a user can create field activity.
    const ST_TAB_FIELD_ACTIVITY_WRITE   = 'ST_TAB_FIELD_ACTIVITY_WRITE';        //Grants view, create and update right to modify field activity in spottrax.
    const ST_TAB_FIELD_ACTIVITY_DELETE  = 'ST_TAB_FIELD_ACTIVITY_DELETE';       //Grants view and delete right to delete field activity in spottrax.
    
    #   PERSONEL    
    const ST_TAB_PERSONNEL              = 'ST_TAB_PERSONNEL';                   //Grants view only access to the Personnel page, where a user can create Personnel.
    const ST_TAB_PERSONNEL_WRITE        = 'ST_TAB_PERSONNEL_WRITE';             //Grants view, create and update right to modify Personnel in spottrax.
    const ST_TAB_PERSONNEL_DELETE       = 'ST_TAB_PERSONNEL_DELETE';            //Grants view and delete right to modify Personnel in spottrax.
        
    #   DIRECTION FINDER    
    const ST_TAB_DIRECTION_FINDER           = 'ST_TAB_DIRECTION_FINDER';           //Grants view only access to the Direction Finder page, where a user can create Direction Finder.
    const ST_TAB_DIRECTION_FINDER_WRITE     = 'ST_TAB_DIRECTION_FINDER_WRITE';     //Grants view, create and update right to modify Direction Finder in spottrax.
    const ST_TAB_DIRECTION_FINDER_DELETE    = 'ST_TAB_DIRECTION_FINDER_DELETE';    //Grants view and delete right to modify Direction Finder in spottrax.
}
