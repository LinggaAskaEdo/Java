<?php
/*
 * (c) 2016 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : audittrax
 * Version          : 2.0 SP 24
 * Filename         : AuditService.php
 * Fileversion      : 2.000.001
 * Initial Creation : 2017-08-21
 * Initial Author   : Hadi
 * Purpose          : Audittrax service
 * ================================================
 * Initial Request  : #21301
 * ================================================
 * 
 * (c) 2016 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

require_once dirname(__DIR__) . "/../vendor/autoload.php";
require_once dirname(__DIR__) . "/../libs/RpcClient.php";

use Rpc\RpcClient;

class AuditService {

    private $rpcClient;

    public function __construct() {
        $this->rpcClient = new RpcClient();
        $this->rpcClient->connect();
    }

    private function getHeaderRpc($source, $destination = "AuditService") 
    {
        $header = [
            'destination' => $destination,
            'source' => $source,
            'callback' => ',' . $this->rpcClient->getCallbackQueue(),
            'resolvedDestination' => false,
            'resolvedCallback' => true,            
            'callbackMsg' => false
        ];
        return $header;
    }

    public function addAuditList($param) 
    {
        $serviceArgument = [
            'header' => $this->getHeaderRpc('AuditTrax', 'AuditService'),
            'body' => [
                "action" => "addAuditList",
                "body" => $param,
            ]
        ];
        $result = json_decode($this->rpcClient->send(json_encode($serviceArgument), false));
        
        return $result;
    }

}



