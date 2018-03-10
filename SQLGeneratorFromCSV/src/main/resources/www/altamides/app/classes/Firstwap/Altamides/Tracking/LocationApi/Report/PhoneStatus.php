<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : PhoneStatusCode.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class PhoneStatusCode
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Tracking\LocationApi\Report;

/**
 * Description of PhoneStatusCode
 *
 * @author Setia Budi Halim
 */
class PhoneStatus
{
    /**
     * Phone is on but not in use
     */
    const IDLE = 0;

    /**
     * Phone is busy (on call)
     */
    const ON = 1;

    /**
     * Not allowed
     */
    const RESTRICTED = -1;

    /**
     * The phone is switched off
     */
    const OFF = 2;

    /**
     * Can not determine phone status
     */
    const UNKNOWN = 255;

}