<?php
/**
 * (c)2014 FirstWAP. All right reserved
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 2.0 SP19
 * Filename         : interceptor.php
 * Fileversion      : 
 * Initial Creation : 2009-02-27
 * Initial Author   : mimi
 * Purpose          : user privilage checker
 * ==================================================================
 * Initial Request  :
 * ==================================================================
 * Change Log:
 * Date         Author      Version     Request     Comments
 * 03-Apr-2009  keyne 	  	1.0.1                   extracting redirect to access denied page
 * 01-May-2009  mimi        1.1                     add parameters for referer
 * 13-May-2009  setia                               add new parameter at
 *                                                  redirectToAccessDeniedPage
 *                                                  function
 * 22 June 2009 setia                               update the filterpage function
 *                                                  and add redirectToLoginService
 *                                                  function.
 * 2 July 2009  setia                               update check_privileges function
 * 25 Jan 2010  setia                               implement AuditTrax
 * 10 Feb 2010  hurip                               Add functionality to insert
 *                                                  auditrax log.
 * 23 Mar 2010  setia.budi                          - Improve validip() and getIp() code paths
 *                                                  - Remove redundant code
 *                                                  - Security: Disable query exposition in saveAuditInformation()
 * 2014-04-08   Sidratul                #4051       change mysql syntax to PDO 
 * 2014-08-27   Yung Fei                #1714       [MOI] AuditTrail: Display strange character on Description colum if using Arabic language
 * 
 *
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * than members of PT. 1rstWAP is strictly forbidden.
 */

@session_start();
require_once dirname(__DIR__) . "/boot/init-basic.php";
require_once dirname(__DIR__) . "/service/audittrax/AuditService.php";
require_once ALTAMIDES_ADODB_DIR . '/adodb.php';
require_once __DIR__ . "/class.sso_client.php";

function filterPage($privilege, $autoRedirect = true, $okForLimitedSuperAdmin = false) {
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->filterPage($privilege, $autoRedirect, $okForLimitedSuperAdmin);
}

function redirectPage(){
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->redirectPage();
}

function checkExpired() {
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->checkExpiration();
}

function check_privileges($tokenId, $privilege, $autoRedirect) {
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }

    $result = array();
    if($ssoClient->checkPrivileges($privilege) == true) {
        $result[0] = "OK";
        $result[1] = "";
    } else {
        $result[0] = "Failed";
        $result[1] = "";
    }

    return $result;
}

function getIp() {
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->getIp();
}

function ssoCheckServerStatus(){
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->ssoCheckServerStatus();
}

function curPageURL() {
    if(!isset($ssoClient)) {
        $ssoClient = new sso_client();
    }
    return $ssoClient->curPageURL();
}

/**
 * Save information to audit_log table through audittrax service
 * @param string $targetNumber
 * @param string $auditMessage
 * @param string $module
 * @param string $auditLevel
 */
function saveAuditInformation($targetNumber, $auditMessage, $module, $auditLevel = '1') 
{
    $auditService = new AuditService();

    $tokenId = $_COOKIE['tokenId'];
    $timestamp = date(DATE_ATOM);    
    
    $param =  compact('targetNumber', 'auditMessage', 'module', 'auditLevel', 'tokenId', 'timestamp');
    
    $result = $auditService->addAuditList($param);
    
    return $result;
}
