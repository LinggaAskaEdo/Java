<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

/**
 * Description of CodedException
 *
 * @author setiabudi
 */
class MobiletraxException extends \Exception
{
    public function __construct($message, $code = null)
    {
        if (!$code) {
            $code = ErrorCode::GENERAL;
        }
        
        parent::__construct("$code: $message");
        $this->code = (string) $code;
    }
}