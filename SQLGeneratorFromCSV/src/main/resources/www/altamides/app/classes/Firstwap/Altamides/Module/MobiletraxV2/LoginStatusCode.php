<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LoginStatusCode.php
 * File Version     : 1.000.000
 * Initial Creation : 2015-02-06
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class LoginStatusCode
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2015-02-06   Dwikky Maradhiza          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

/**
 * Description of LoginStatusCode
 *
 * @author Dwikky Maradhiza Yudakusuma
 */
final class LoginStatusCode
{
    
    const LOGIN_TECHNICAL_ERROR  = '0';
    const LOGIN_AUTHENTICATED    = '1';
    const LOGIN_ACCOUNT_DISABLED = '2';
    
    /*
     * In case if password is invalid, return the message Id code with different format response
     */
    const LOGIN_UNAUTHENTICATED  = '1';
    const INVALID_IMEI = '2';
    const INVALID_IP = '3';
    const MT_NOT_ACTIVE = '4';
    const USER_NOT_ACTIVE = '5';
    const GROUP_NOT_ACTIVE = '6';
    
}