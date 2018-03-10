<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : ObsoleteSmsResponse.php
 *  File Version     : 1.000.000
 *  Initial Creation : 13-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  13-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Actions;

use Firstwap\Altamides\Module\Mobiletrax\Response;
use Firstwap\Altamides\Sms\TerminatedMessage;

/**
 * Used only for handling obsolete login response, 
 * shold be removed once login response use new encoded message
 * 
 * @deprecated since version 2.0 SP14.3
 * @author Setia Budi Halim
 */
final class LoginActionResponse extends Response
{
    const STATUS_OK = 'OK';
    const STATUS_DENIED = 'DENIED';

    protected $loginStatus;
    
    public function setLoginStatus($status)
    {
        $this->loginStatus = $status;
    }
    
    public function send()
    {
        $message = new TerminatedMessage();
        $message->setContent($this->createMessage());
        $this->sender->setMessage($message);
        $this->sender->send();
    }
    
    protected function createMessage()
    {
        $requestId = $this->request->getRequestId();
        $status = $this->loginStatus;
        return "MOBILETRAX/LOGIN/$requestId/$status";
    }


}