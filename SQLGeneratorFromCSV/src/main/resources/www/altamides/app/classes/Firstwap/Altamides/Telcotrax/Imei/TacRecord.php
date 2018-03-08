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
 * File Name        : TacRecord.php
 * File Version     : SVN: $Id: TacRecord.php 16936 2014-03-10 09:19:45Z angela.cynthia $
 * Initial Creation : 13-Feb-2014
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : 
 * 
 * 
 * Changelog:
 * Date        Version     Comment
 * 2014-00-13  2.0 SP19    Issue #ISSUE_NUMBER: Initial creation
 * 2014-02-21  2.0 SP19    select Tac Data from database
 * 
 * Copyright 2013 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

use Firstwap\Firstphp\Db\MutableRecord;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;

/**
 * Description of TacRecord
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class TacRecord extends MutableRecord
{

    public function __construct()
    {
        $this->fields = array(
            'TAC'               => null,
            'MANUFACTURER_NAME' => null,
            'MANUFACTURER_NAME_HASH'=> null,
            'MODEL_NAME'        => null,
            'ME_TYPE_ID'        => null,
            'TAC_NOTES'         => null,
            'MODIFIED_BY'         => null,
            'MODIFIED_TIMESTAMP'         => null,
            'IS_MODIFIED'         => null,
        );
        
        $this->keyName = 'TAC';
        
        $this->emptyTac = true;
        
        $this->authFields = $this->fields;
        
        $this->doAct = false;
        $this->dbManager = DbManager::getInstance();  
        $this->pdo    = $this->dbManager->getPdo(DbNameRef::TELCO);
    }

    /**
     * Function revertUserChange()
     * Function to revert user changed based on TAC Authenticate Table
     * 
     * @param 
     * 
     */
    public function revertUserChange($tac)
    {
     try{   
            $this->hasAuthenticSource($tac);
            $pdo    = $this->pdo;
            $query  = "update ". \REF_CELLDB_DBNAME . ".TAC_DATA set ".
                        "`ME_TYPE_ID` = ".$this->authFields['ME_TYPE_ID'].", ".
                        "`TAC_NOTES` = '".$this->authFields['TAC_NOTES']."', ".
                        "`IS_MODIFIED` = '0', ".
                        "`MODIFIED_BY` = ".$_SESSION['USER_ID'].", ".
                        "`MODIFIED_TIMESTAMP` = '".date('Y-m-d H:i:s')."' ".
                      "where TAC = '".$this->authFields['TAC']."'";
            $stmt         = $pdo->prepare($query);        
            
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount()) {           
                $this->doAct = true;
            }else{
                $this->doAct = false;
            }
        }catch (\Exception $ex){
            throw new \Exception('Can not find TAC record for : ' . $tac);
        }   
    }

    /**
     * Function isChangedByUser
     * Function to checked is data modified by user or original
     * 
     * @param 
     * 
     */
    public function isChangedByUser()
    {
        return $this->fields['IS_MODIFIED'];
    }

    /**
     * Function hasAuthenticSource
     * Function to checked dependency between TAC_DATA table and TAC_AUTHENTIC_DATA
     * 
     * @param 
     * 
     */
    public function hasAuthenticSource($tac = null)
    {
        try{             
            if(empty($tac) || $tac == null) $tac = $this->fields['TAC'];
            $pdo          = $this->pdo;
            $query        = "select `TAC_DATA_ID`, `TAC`, `MANUFACTURER_NAME`, `MODEL_NAME`, ".
                            "`ME_TYPE_ID`, `TAC_NOTES`, `MODIFIED_TIMESTAMP` from " . \REF_CELLDB_DBNAME . ".TAC_AUTHENTIC_DATA ".
                            "where TAC = '" . $tac . "'";
            
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            
            $this->authFields = $stmt->fetch(\PDO::FETCH_ASSOC);
            
            if ($stmt->rowCount()) {           
                 $this->doAct = true;
                 return true;
            }else{
                $this->doAct = false;
                 return false;
            }
        }catch (\Exception $e){
            throw new \Exception('Can not find TAC record for : ' . $this->fields['TAC']);
        }
    }

    /**
     * Function doDelete
     * Function to delete Data on TAC_DATA table
     * 
     * @param 
     * 
     */
    public function doDelete($tac = null)
    {
        try{
            $pdo    = $this->pdo;
            $query  = "delete from ". \REF_CELLDB_DBNAME . ".TAC_DATA ".
                      "where TAC = '".$tac."'";       
            
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount()) {           
                $this->doAct = true;
            }else{
                $this->doAct = false;
            }
        }catch (\Exception $ex){
            throw new \Exception('Can not find TAC record for : ' . $tac);
        }
    }

    /**
     * function doInsert
     * Function to Insert data on TAC_DATA table by User
     * 
     * @param 
     * 
     */
    protected function doInsert()
    {
        try{
            $pdo    = $this->pdo;
            $query  = "insert into ". \REF_CELLDB_DBNAME . ".TAC_DATA (".
                        "`TAC_DATA_ID`, `TAC`, `MANUFACTURER_NAME`,`MANUFACTURER_NAME_HASH`, `MODEL_NAME`, ".
                        "`ME_TYPE_ID`, `TAC_NOTES`, `IS_MODIFIED`, `MODIFIED_BY`, `MODIFIED_TIMESTAMP`".
                      ") values ('','".$this->fields['TAC']."','".$this->fields['MANUFACTURER_NAME']."','".$this->fields['MANUFACTURER_NAME_HASH']."',".
                        "'".$this->fields['MODEL_NAME']."',".$this->fields['ME_TYPE_ID'].",".
                        "'".$this->fields['TAC_NOTES']."',1,".
                        "".$this->fields['MODIFIED_BY'].",'".$this->fields['MODIFIED_TIMESTAMP']."')";          
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount()) {           
                $this->doAct = true;
            }else{
                $this->doAct = false;
            }
        }catch (\Exception $ex){
            throw new \Exception('Can not find TAC record for : ' . $tac);
        }
    }

    /**
     * function doRead
     * Function to select data from database
     * 
     * @param string $tac
     * 
     */
    protected function doRead($tac)
    {
        try{
            $pdo          = $this->pdo;
            $query        = "select " . \implode(',', array_keys($this->fields)) . " from " . \REF_CELLDB_DBNAME . ".TAC_DATA where TAC = '" . $tac . "'";
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount()) {           
                $this->emptyTac = false;
            }
            $this->fields = $stmt->fetch(\PDO::FETCH_ASSOC);
        }catch (\Exception $e){
            throw new \Exception('Can not find TAC record for : ' . $tac);
        }
    }

    /**
     * 
     * Function 
     * 
     * @param 
     * 
     */
    protected function doUpdate()
    {
        try{
            $pdo    = $this->pdo;
            $query  = "update ". \REF_CELLDB_DBNAME . ".TAC_DATA set ".
                        "`ME_TYPE_ID` = ".$this->fields['ME_TYPE_ID'].", ".
                        "`TAC_NOTES` = '".$this->fields['TAC_NOTES']."', ".
                        "`IS_MODIFIED` = '1', ".
                        "`MODIFIED_BY` = ".$this->fields['MODIFIED_BY'].", ".
                        "`MODIFIED_TIMESTAMP` = '".$this->fields['MODIFIED_TIMESTAMP']."' ".
                      "where TAC = '".$this->fields['TAC']."'";
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount()) {           
                $this->doAct = true;
            }else{
                $this->doAct = false;
            }
        }catch (\Exception $ex){
            throw new \Exception('Can not find TAC record for : ' . $tac);
        }
    }
    
    protected function validateFields()
    {
        
    }
    
    public function isDataEmpty(){
        return $this->emptyTac;
    }
    
    public function isDataSuccess(){
        return $this->doAct;
    }
    
    public function getAutoComplete($key, $val=null){
        try{
            $pdo          = $this->pdo;
            $query        = "select distinct " . $key . " from " . \REF_CELLDB_DBNAME . ".TAC_DATA";
            if($val != null){
                $query .= " where ".$key." like '%".$val."%'";
            }
            $stmt         = $pdo->prepare($query);
            $stmt->execute();
            
            return $stmt->fetchAll(\PDO::FETCH_ASSOC);
        }catch (\Exception $e){
            throw new \Exception('Can not find TAC record for : ' . $key);
        }
    }
}
