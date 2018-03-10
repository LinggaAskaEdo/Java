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
 * Date         Author                    Version    Request  Comment
 * 2012-08-30   Setia Budi Halim          1.000.000           Initial Creation
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Auth;

/**
 * Description of AltamidesPrivilege
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class AltamidesPrivilege
{

    const BLACKLIST_CREATE_EDIT = 'BLACKLIST_CREATE_EDIT';
    const BLACKLIST_DELETE      = 'BLACKLIST_DELETE';
    const BLACKLIST_VIEW        = 'BLACKLIST_VIEW';
    const BLACKLIST_GROUPADMIN  = 'BLACKLIST_GROUPADMIN';
    const BLACKLIST_SUPERADMIN  = 'BLACKLIST_SUPERADMIN';
    
    const REDLIST_CREATE_EDIT   = 'REDLIST_CREATE_EDIT';
    const REDLIST_DELETE        = 'REDLIST_DELETE';   
    
    const XMAP_BING_ONLINE      = 'BING_MAPS_INTEGRATION_ONLINE';
    const XMAP_BING_OFFLINE     = 'BING_MAPS_INTEGRATION_OFFLINE';
    const XMAP_GOOGLE_ONLINE    = 'GOOGLE_MAPS_INTEGRATION_ONLINE';
    const XMAP_GOOGLE_OFFLINE   = 'GOOGLE_MAPS_INTEGRATION_ONLINE';
    const XMAP_OSM_ONLINE       = 'OSM_MAPS_INTEGRATION_ONLINE';
    const XMAP_OSM_OFFLINE      = 'OSM_MAPS_INTEGRATION_OFFLINE';
    
    const MAPS_BING_ONLINE = "AMS_MAPS_BING_ONLINE";
    const MAPS_BING_OFFLINE = "AMS_MAPS_BING_OFFLINE";
    const MAPS_GOOGLE_ONLINE = "AMS_MAPS_GOOGLE_ONLINE";
    const MAPS_GOOGLE_OFFLINE = "AMS_MAPS_GOOGLE_OFFLINE";
    const MAPS_OSM_ONLINE = "AMS_MAPS_OSM_ONLINE";
    const MAPS_OSM_OFFLINE = "AMS_MAPS_OSM_OFFLINE";
    const MAPS_ARCGIS = "AMS_MAPS_ARCGIS";
    const MAPS_HERE_ONLINE = "AMS_MAPS_HERE_ONLINE";
    const MAPS_HERE_OFFLINE = "AMS_MAPS_HERE_OFFLINE";
    const MAPS_MAPTRAX_OFFLINE = "AMS_MAPS_MAPTRAX_OFFLINE";
    
    const AMS_OPT_LOCATE_METHOD_AUTOMATIC_ENABLED           = 'AMS_OPT_LOCATE_METHOD_AUTOMATIC_ENABLED';//Grant access to locate targets with automatic query method
    const AMS_OPT_LOCATE_METHOD_PRIMARY_ENABLED             = 'AMS_OPT_LOCATE_METHOD_PRIMARY_ENABLED'; //Grant access to locate targets with primary query method
    const AMS_OPT_LOCATE_METHOD_SECONDARY_ENABLED           = 'AMS_OPT_LOCATE_METHOD_SECONDARY_ENABLED'; //Grant access to locate targets with secondary query method
    const AMS_OPT_LOCATE_METHOD_ENHANCED_PRIMARY_ENABLED    = 'AMS_OPT_LOCATE_METHOD_ENHANCED_PRIMARY_ENABLED'; //Grant access to locate targets with enchanced query method
    const AMS_OPT_LOCATE_METHOD_FAST_SECONDARY_ENABLED      = 'AMS_OPT_LOCATE_METHOD_FAST_SECONDARY_ENABLED'; //Grant access to locate targets with fast secondary query method
    const AMS_OPT_HA_METHOD_SAMOUSSA_ENABLED                = 'AMS_OPT_HA_METHOD_SAMOUSSA_ENABLED'; //Allow user to use Samoussa provider for High Accuracy interrogation
    const AMS_OPT_HA_METHOD_ETISALAT_ENABLED                = 'AMS_OPT_HA_METHOD_ETISALAT_ENABLED'; //Allow user to use Etisalat provider for High Accuracy interrogation
    const AMS_OPT_HA_METHOD_OSS_ENABLED                     = 'AMS_OPT_HA_METHOD_OSS_ENABLED'; //Allow user to use OSS provider for High Accuracy interrogation
    const FT_OPT_LOCATE_IGNORE_OWNER_REDLIST_WHITELIST      = 'FT_OPT_LOCATE_IGNORE_OWNER_REDLIST_WHITELIST';
    
    const AMS_OPT_NATIONAL_OUTBOUND_ROAMER_INTERROGATION_ALLOW  = 'AMS_OPT_NATIONAL_OUTBOUND_ROAMER_INTERROGATION_ALLOW'; //Grants user permission to interrogate numbers of any origin (national and international) regardless of their current location and roaming status.
    const AMS_OPT_FOREIGN_INBOUND_ROAMER_INTERROGATION__ALLOW   = 'AMS_OPT_FOREIGN_INBOUND_ROAMER_INTERROGATION_ALLOW'; //Grants user permission to interrogate a foreign number that is currently roaming inside of the country of operation (inbound roaming).
    const AMS_OPT_WORLDWIDE_INTERROGATION_ALLOW                 = 'AMS_OPT_WORLDWIDE_INTERROGATION_ALLOW'; //Grants user permission to interrogate a national number that is currently roaming outside of the country of operation (outbound roaming).
    const AMS_OPT_OT_FAMILY_USER_ICON_ON_TARGET_SHOW            = 'AMS_OPT_OT_FAMILY_USER_ICON_ON_TARGET_SHOW'; // Allow user to see in the target list an additional icon with the target in the OmniTrax target list to indicate that target is also a user.    
}
