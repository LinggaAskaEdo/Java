<?php

/*
 * @copyright (c) 2014 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 * 
 * 
 * System           : Altamides
 * Module           : All
 * Version          : 2.0 SP19
 * File Name        : ImeiNumberType.php
 * File Version     : SVN: $Id: ImeiNumberType.php 16534 2014-02-17 04:02:38Z setia.budi $
 * Initial Creation : 13-Feb-2014
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 * 
 * Changelog:
 * Date        Version     Comment
 * 2014-00-13  2.0 SP19    Issue #ISSUE_NUMBER: Initial creation
 * 
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

/**
 * Description of ImeiNumberType
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class ImeiNumberType
{

    const IMEI_SV   = 4;
    const IMEI_CD   = 3;
    const IMEI_ONLY = 2;
    const TAC_ONLY  = 1;
    const INVALID   = 0;

}
