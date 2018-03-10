<?php 
/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : MobileTrax V2
 * Version          : 
 * File Name        : Mobiletrax.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-23
 * Initial Author   : Denis Patrice <denispatrice@yahoo.com>
 * Purpose          : Definition of class Mobiletrax
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-23   Dwikky Maradhiza          1.000.000           Rewrite code
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

use Firstwap\Altamides\Module\MobiletraxV2\MessageHandler;
class Mobiletrax
{
     public static function request ( $bridge, $isSocket )
     {
        $Gateway = new MessageHandler();
        $Gateway->setInstance($bridge);
        
        if (!$isSocket) {
            $Gateway->setClientMsisdn( \ltrim($bridge->getClientMsisdn() , "+") );
            $Gateway->setVirtualNumber( \ltrim($bridge->getServerMsisdn() , "+"));
        }
        $Gateway->setMessageContent($bridge->getRequestSms());
        $Gateway->MessageHandler = $Gateway;
        if($Gateway->processMessage()){
            error_log('MobileTrax Process Done');
        }
     }
}
