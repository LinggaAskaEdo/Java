<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocationQualityMapper.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class LocationQualityMapper
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Codec;

use Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality;

/**
 * Description of LocationQualityMapper
 *
 * @author Setia Budi Halim
 */
class LocationQualityMapper extends SimpleValueMapper
{

    public function __construct()
    {
        $map = array(
            LocationQuality::GPS => 1,
            LocationQuality::CCC => 2,
            LocationQuality::ECC => 3,
            LocationQuality::BTS => 4,
            LocationQuality::MSC => 5,
            LocationQuality::LAC => 6,
            LocationQuality::FLL => 7,
            LocationQuality::RBL => 8
        );

        $this->setMap($map);
        $this->errorValue = -1;
        $this->errorCode  = 0;
    }

}