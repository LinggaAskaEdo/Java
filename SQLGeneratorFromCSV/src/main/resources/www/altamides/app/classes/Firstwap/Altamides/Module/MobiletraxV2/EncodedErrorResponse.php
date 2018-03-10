<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : GenericErrorResponse.php
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

namespace Firstwap\Altamides\Module\MobiletraxV2;

use Firstwap\Altamides\Module\MobiletraxV2\Message\SimpleTextMessage;

/**
 * Description of GenericErrorResponse
 *
 * @author Setia Budi Halim
 */
class EncodedErrorResponse extends EncodedResponse
{   

    public function __construct(Request $request)
    {
        parent::__construct($request);
        $this->setType(ResponseType::ERROR);
        $this->message = new SimpleTextMessage();
        
    }
    
    public function setErrorMessage($message)
    {
        return $this->message->setText($message);
    }
    
    public function setType($type)
    {
        return $this->type = $type;
    }    
}