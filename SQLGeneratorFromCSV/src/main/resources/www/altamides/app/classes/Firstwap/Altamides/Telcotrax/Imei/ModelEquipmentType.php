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
 * File Name        : ModelEquipmentType.php
 * File Version     : SVN: $Id: ModelEquipmentType.php 16936 2014-03-10 09:19:45Z angela.cynthia $
 * Initial Creation : 17-Feb-2014
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 * 
 * Changelog:
 * Date        Version     Comment
 * 2014-00-17  2.0 SP19    Issue #ISSUE_NUMBER: Initial creation
 * 2014-02-21  2.0 SP19    Create select data Model type from database
 * 
 * Copyright 2013 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

use Firstwap\Firstphp\Db\ReadonlyTable;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;

/**
 * Description of ModelEquipmentType
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class ModelEquipmentType extends ReadonlyTable
{
    
     /**
     * constructor
     * 
     * @param type $key
     * 
     */
    
    public function __construct($key)
    {
        $this->fields = array(
            'ME_TYPE_ID',
            'ME_TYPE_REF',
            'ME_TYPE_DESC'
        );
        $this->arrValue = array();
        
        $this->read();        
    }
    
     /**
     * Function read
     * Function to select data from database
     * 
     * @param type $key
     * 
     */
    protected function read()
    {
        $dbManager = DbManager::getInstance();
        $pdo    = $dbManager->getPdo(DbNameRef::TELCO);
        $query  = "select ".\implode(', ',$this->fields)." from ".\REF_CELLDB_DBNAME.".ME_TYPE ";
                
        $stmt = $pdo->prepare($query);
        $stmt->execute();
        $this->arrValue = $stmt->fetchAll(\PDO::FETCH_ASSOC);
    }
    
     /**
     * Function getMeTypeField
     * Function to get data by field
     * 
     * @param type $key
     * 
     */
    public function getMeTypeField($field){
        if(!isset($this->arrValue[-1]) && $field == -1){
            return "";
        }
        return $this->arrValue[$field];
    }
    
     /**
     * Function getAllMeTypeField
     * Function to select All data from database
     * 
     * @param type $key
     * 
     */
    public function getAllMeTypeField(){
        return $this->arrValue;
    }
}
