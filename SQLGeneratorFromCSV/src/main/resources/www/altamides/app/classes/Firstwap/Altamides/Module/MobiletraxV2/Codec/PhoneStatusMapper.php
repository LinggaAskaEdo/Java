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

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

use Firstwap\Altamides\Tracking\LocationApi\Report\PhoneStatus;

/**
 * Description of PhoneStatusCode
 *
 * @author Setia Budi Halim
 */
class PhoneStatusMapper extends SimpleValueMapper
{

    public function __construct()
    {

        $map = array(
            PhoneStatus::IDLE       => 0,
            PhoneStatus::ON         => 1,
            PhoneStatus::OFF        => 2,
            PhoneStatus::RESTRICTED => 3,
            PhoneStatus::UNKNOWN    => 3
        );
        
        $this->setMap($map);

        $this->errorValue = PhoneStatus::UNKNOWN;
        $this->errorCode  = 3;
    }

}