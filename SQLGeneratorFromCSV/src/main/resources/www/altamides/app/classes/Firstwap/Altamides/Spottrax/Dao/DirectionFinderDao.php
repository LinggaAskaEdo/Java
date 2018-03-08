<?php

/*
 * @copyright (c) 2014 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 *
 * System           : Altamides
 * Module           : Spottrax
 * Version          : 2.0 SP20
 * Filename         : DirectionFinderDao.php
 * Fileversion      : 2.000.000
 * Initial Creation : 2014-06-26
 * Initial Author   : Sejati
 * Purpose          : Direction Finder DAO
 * ================================================
 * Initial Request  : #657
 * ================================================
 * Change Log
 * Date         Author      Version   Request     Comment         
 * 2014-06-26   Sejati      1.0       #657        Initial Creation
 * 2014-08-12   Dwikky      1.0       #657        Add Parameter for Altasort
 * 
 * Copyright 2013 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Spottrax\Dao;

use Firstwap\Firstphp\Db\MutableRecord;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;

/**
 * Description of Direction Finder DAO
 *
 * @author W.Sejati <waluyo.sejati@1rstwap.com>
 */
class DirectionFinderDao extends MutableRecord
{

    public function __construct()
    {
        $this->fields = array(
            'TRACKING_DEVICE_SID' => null,
            'OWNER_GROUP'         => null,
            'DEVICE_ID'           => null,
            'DEVICE_TYPE'         => null,
            'PASSWORD'            => null,
            'ACTIVE'              => null,
            'DEVICE_NOTES'        => null,
            'CREATED_BY'          => null,
            'CREATED_TIMESTAMP'   => null,
            'UPDATED_BY'          => null,
            'UPDATED_TIMESTAMP'   => null,
            'DELETED'             => false
        );


        $this->keyName = 'TRACKING_DEVICE_SID';

        $this->emptyDF   = true;
        $this->dbManager = DbManager::getInstance();
        $this->pdo       = $this->dbManager->getPdo(DbNameRef::SUBCELL_TRACKING_20);
        $this->bulk      = NULL;
    }

    protected function getParams()
    {
        $inputParams = array();
        foreach ($this->fields as $key => $input) {
            $inputParams[] = $input;
        }
        return $inputParams;
    }

    /**
     * Function doDelete
     * Function to delete Data on TRACKING_DEVICE table
     * 
     * @param 
     * 
     */
    public function doDelete($sId = null)
    {
        try {
            $pdo   = $this->pdo;
            $query = "UPDATE " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE " .
                "SET deleted=true WHERE FIND_IN_SET(TRACKING_DEVICE_SID,?)";

            $stmt = $pdo->prepare($query);
            $stmt->execute(array($sId));
            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('ERROR doDelete() : ' . $ex->getMessage());
        }
    }

    /**
     * function doInsert
     * Function to Insert data on TRACKING_DEVICE table by User
     * 
     * @param 
     * 
     */
    protected function doInsert()
    {
        try {
            $pdo   = $this->pdo;
            $query = "INSERT INTO " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE (";
            $query .= implode(",", array_keys($this->fields));
            $query .= ",DEVICE_COLOR) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            $stmt  = $pdo->prepare($query);

            $params = $this->getParams();
            
            $color = $this->randomColor();
            array_push($params, $color);
            $stmt->execute($params);

            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('Can not Insert record for : ' . $ex->getMessage());
        }
    }
    function randomColorPart() {
        return str_pad( dechex( mt_rand( 0, 255 ) ), 2, '0', STR_PAD_LEFT);
    }

    function randomColor() {
        return $this->randomColorPart() . $this->randomColorPart() . $this->randomColorPart();
    }
    /**
     * function doRead
     * Function to select data from database
     * 
     * @param string $sId
     * 
     */
    protected function doRead($sId)
    {
        try {
            $pdo   = $this->pdo;
            $query = "select " . \implode(',', array_keys($this->fields)) . " from " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE where TRACKING_DEVICE_SID = ?";
            $stmt  = $pdo->prepare($query);
            $stmt->execute(array($sId));
            if ($stmt->rowCount()) {
                $this->emptyDF = false;
            }
            $this->fields = $stmt->fetch(\PDO::FETCH_ASSOC);
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF record for : ' . $e->getMessage());
        }
    }

    /**
     * 
     * Function to check is DF already exist in table
     * @param type $dfId
     * @return int
     * 
     */
    public function isExist($dfId)
    {
        try {
            $pdo   = $this->pdo;
            $query = "select * from " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE where DEVICE_ID = ? AND deleted=false";
            $stmt  = $pdo->prepare($query);
            $stmt->execute(array($dfId));
            if ($stmt->rowCount()) {
                return 1;
            } else {
                return 0;
            }
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF record for : ' . $e->getMessage());
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
        try {
            $pdo   = $this->pdo;
            $query = "UPDATE " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE SET " .
                " DEVICE_ID = ?, DEVICE_TYPE=?, PASSWORD=?, ACTIVE=?, " .
                " DEVICE_NOTES = ?, UPDATED_BY=?, UPDATED_TIMESTAMP=?" .
                " WHERE TRACKING_DEVICE_SID = ?";
            $stmt  = $pdo->prepare($query);

            $inputParams[] = $this->fields['DEVICE_ID'];
            $inputParams[] = $this->fields['DEVICE_TYPE'];
            $inputParams[] = $this->fields['PASSWORD'];
            $inputParams[] = ($this->fields['ACTIVE'] == 1) ? TRUE : FALSE;
            $inputParams[] = $this->fields['DEVICE_NOTES'];
            $inputParams[] = $this->fields['UPDATED_BY'];
            $inputParams[] = $this->fields['UPDATED_TIMESTAMP'];
            $inputParams[] = $this->fields['TRACKING_DEVICE_SID'];

            $stmt->execute($inputParams);
            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('ERROR UPDATE DF : ' . $this->fields['TRACKING_DEVICE_SID']);
        }
    }

    protected function validateFields()
    {
        
    }

    public function isDataSuccess()
    {
        return $this->doAct;
    }

    public function getListDirectionFinder($param, $sortSQLQuery = "", $isGroupAdmin = false, $isSuperAdmin = false,  $isUserAdmin = false)
    {
        $page    = ($param['sPage'] > 0) ? $param['sPage'] : 1;
        $perpage = 10;
        try {
            $pdo     = $this->pdo;
            $query   = "select TRACKING_DEVICE_SID,DEVICE_ID,DEVICE_TYPE,DEVICE_NOTES,ACTIVE" .
                " FROM " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE ";
            $sParam  = array();
            $isWhere = 0;
            if ($param['sId'] != '') {
                $sParam[] = '%' . $param['sId'] . '%';
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $isWhere  = 1;
                $query.=" $where DEVICE_ID LIKE ?";
            }
            if ($param['sStatus'] != '') {
                $sParam[] = $param['sStatus'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where ACTIVE = ?";
                $isWhere  = 1;
            }
            if ($param['sType'] != '') {
                $sParam[] = $param['sType'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where DEVICE_TYPE = ?";
                $isWhere  = 1;
            }
            
            $privilegeQuery = "";
            if($isSuperAdmin){
                $privilegeQuery = " AND 1 ";
            }
            else if($isGroupAdmin){
                $privilegeQuery = " AND OWNER_GROUP = ".$_SESSION['CLIENT_ID']." ";
            }
            else if($isUserAdmin){
                $privilegeQuery = " AND CREATED_BY = ".$_SESSION['USER_ID']." ";
            }
            
            $where = ($isWhere == 1) ? " AND " : " WHERE ";
            $query.=" $where DELETED = false ".$privilegeQuery;

            $query .=" ".$sortSQLQuery." ";
            $start = ($page - 1) * $perpage;

            $query .=" LIMIT $start,$perpage";

            $stmt = $pdo->prepare($query);
            $stmt->execute($sParam);
            if ($stmt->rowCount()) {
                $this->emptyDF = false;
            }
            $this->bulk = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return $this->bulk;
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF List Error : ' . $e->getMessage());
        }
    }

    public function getCountDF($param, $isGroupAdmin = false, $isSuperAdmin = false)
    {
        try {
            $pdo     = $this->pdo;
            $query   = "select TRACKING_DEVICE_SID FROM " . \REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE ";
            $sParam  = array();
            $isWhere = 0;
            if ($param['sId'] != '') {
                $sParam[] = '%' . $param['sId'] . '%';
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $isWhere  = 1;
                $query.=" $where DEVICE_ID LIKE ?";
            }
            if ($param['sStatus'] != '') {
                $sParam[] = $param['sStatus'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where ACTIVE = ?";
                $isWhere  = 1;
            }
            if ($param['sType'] != '') {
                $sParam[] = $param['sType'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where DEVICE_TYPE = ?";
                $isWhere  = 1;
            }
            
            $privilegeQuery = "";
            if($isSuperAdmin){
                $privilegeQuery = " AND 1 ";
            }
            else if($isGroupAdmin){
                $privilegeQuery = " AND OWNER_GROUP = ".$_SESSION['CLIENT_ID']." ";
            }
            
            $where = ($isWhere == 1) ? " AND " : " WHERE ";
            $query.=" $where DELETED = false ".$privilegeQuery;

            $stmt = $pdo->prepare($query);
            $stmt->execute($sParam);
            
            if($isGroupAdmin || $isSuperAdmin){
                return $stmt->rowCount();
            }
            
            return 0;
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF List Error : ' . $e->getMessage());
        }
    }
    
    public function checkGroup ($sId, $superAdmin, $groupAdmin) {
        if($superAdmin){
            $privquery  = '';
        } else if($groupAdmin){
            $privquery  = " AND OWNER_GROUP = '". $_SESSION['CLIENT_ID'] ."' ";
        }
        
        $query      = "SELECT COUNT(*) as count FROM ". REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE WHERE TRACKING_DEVICE_SID = '". $sId. "'" . $privquery;
        $pdo        = $this->pdo;
        $stmt       = $pdo->prepare($query);
        $stmt->execute();
        $result     = $stmt->fetchAll(\PDO::FETCH_ASSOC);

        return $result[0]['count'];
    }

}
