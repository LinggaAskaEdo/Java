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
 * File Name        : ReportingBodyRecord.php
 * File Version     : SVN: $Id: ReportingBodyRecord.php 16936 2014-03-10 09:19:45Z angela.cynthia $
 * Initial Creation : 13-Feb-2014
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 * 
 * Changelog:
 * Date        Version     Comment
 * 2014-00-13  2.0 SP19    Issue #ISSUE_NUMBER: Initial creation
 * 2014-02-21  2.0 SP19    Select Reporting body record from database
 * 
 * Copyright 2013 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

use Firstwap\Firstphp\Db\ReadonlyRecord;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;

/**
 * Description of ReportingBodyRecord
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class ReportingBodyRecord extends ReadonlyRecord
{   
    public function __construct($rbi)
    {
        $this->keyName = 'RBI';
        $this->fields  = array(
            'RBI',
            'REPORTING_BODY_NAME',
            'IS_ACTIVE',
            'REPORTING_BODY_NOTES',
        );
        $this->arrValue = array();
        parent::__construct($rbi);
    }

    protected function doRead($rbi)
    {
        if(empty($rbi) || $rbi == '') return;
        $dbManager = DbManager::getInstance();
        $pdo    = $dbManager->getPdo(DbNameRef::TELCO);
        $query = "select ".\implode(',',$this->fields)." from ".\REF_CELLDB_DBNAME.".TAC_REPORTING_BODY where RBI = ".$rbi;
        $stmt = $pdo->prepare($query);        
        $stmt->execute();
        $this->arrValue = $stmt->fetch(\PDO::FETCH_ASSOC); 
    }
    
    public function getRbiField($field)
    {
        return $this->arrValue[$field];
    }
}
