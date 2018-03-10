<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 */
/**
 *  System           : Altamides
 *  Module           : Mobiletrax
 *  Version          : 2.0 SP14.3
 *  File Name        : LocateResultMode.php
 *  File Version     : 1.000.000
 *  Initial Creation : 09-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : LocateResultMode class definition
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  09-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Actions;


/**
 * Locate action has different type of result.
 * This is the way it 'trick' the latency in SMS
 * communication.
 *
 * @author Setia Budi Halim
 */
final class LocateResponseMode {
    /**
     * Reply contents must fit in one message
     */
    const FAST     = 0;
    
    /**
     * Compressed result
     */
    const ECONOMY  = 1;
    
    /**
     * All possible fields are provided
     */
    const COMPLETE = 2;
}