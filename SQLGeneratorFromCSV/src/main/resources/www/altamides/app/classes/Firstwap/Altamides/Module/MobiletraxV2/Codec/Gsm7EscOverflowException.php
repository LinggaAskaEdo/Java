<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : Gsm7EscOverflowException.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-10
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class Gsm7EscOverflowException
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-10   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

/**
 * Description of Gsm7EscOverflowException
 *
 * @author Setia Budi Halim
 */
class Gsm7EscOverflowException extends \Exception
{
    public function __construct($escCount)
    {
        $message = "Esc count is overflow (current=$escCount)";
        parent::__construct($message);
    }
}