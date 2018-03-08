<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Db\Ref;

/**
 * Act as a reference for database name
 *
 * @author setia.budi
 */
class NameRef
{
    /**
     * Omnitrax family database 
     */
    const MULTI_TRACKING = \REF_FTRAX_LOCATIONTRACKING_DBNAME;
    
    /**
     * RapidTrax database 
     */
    const SINGLE_TRACKING = \REF_FTRAX_LOCATIONMO_DBNAME;
    
    /**
     * SMS Distributor database 
     */
    const SMS_DISTRIBUTOR = \REF_SMS_DISPATCHER_DBNAME;

}
