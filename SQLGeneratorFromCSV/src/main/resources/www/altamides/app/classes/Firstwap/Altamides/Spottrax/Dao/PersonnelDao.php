<?php
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of PersonnelDao
 *
 * @author sidratul
 */
/*
 * (c) 2014 FirstWAP. All rights reserved
 *
 * System           : Altamides
 * Module           : Spottrax
 * Version          : 2.0 SP20
 * Filename         : PersonnelDao.php
 * Fileversion      : 2.000.000
 * Initial Creation : 2014
 * Initial Author   : sidratul
 * Purpose          : Field Activity List Page
 * ================================================
 * Initial Request  : 
 * ================================================
 * Change Log
 * Date         Author      Version   Request     Comment         
 * 2014-11-12   beni        2.0       #657        remove unuse log
 * (c) 2014 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Spottrax\Dao;

use Firstwap\Firstphp\Db\MutableRecord;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;
use Firstwap\Altamides\Spottrax\Model\Personnel;

class PersonnelDao extends MutableRecord
{

    private $fieldNames;
    private $doAct;
    private $dbName;
    private $tableName;
    private $pdo;

    public function __construct()
    {
        $this->fieldNames = array(
            0 => 'PERSONNEL_SID',
            1 => 'PERSONNEL_NAME',
            2 => 'ACTIVE',
            3 => 'PERSONNEL_NOTES',
            4 => 'CREATED_BY',
            5 => 'CREATED_TIMESTAMP',
            6 => 'UPDATED_BY',
            7 => 'UPDATED_TIMESTAMP',
            8 => 'DELETED',
            9 => 'OWNER_GROUP',
        );

        $this->dbName    = DbNameRef::getName(DbNameRef::SUBCELL_TRACKING_20);
        $this->tableName = "PERSONNEL";

        $this->doAct     = false;
        $this->dbManager = DbManager::getInstance();
        $this->pdo       = $this->dbManager->getPdo(DbNameRef::SUBCELL_TRACKING_20);
    }

    private function generateResult($statement)
    {
        $count = $statement->rowCount();

        if ($count == 0) {
            return null;
        } if ($count == 1) {
            $rowResult         = $statement->fetch();
            $personnelResult[] = $this->personnelMapper($rowResult);
            return $personnelResult;
        } else {

            $result          = $statement->fetchAll();
            $personnelResult = array();

            foreach ($result as $rowResult) {
                $personnelResult[] = $this->personnelMapper($rowResult);
            }

            return $personnelResult;
        }
    }

    private function personnelMapper($rowResult)
    {

        $personnel = new Personnel();
        $personnel->setPersonnelSid($rowResult[$this->fieldNames[0]]);
        $personnel->setPersonnelName($rowResult[$this->fieldNames[1]]);

        $personnel->setActive($rowResult[$this->fieldNames[2]]);
        $personnel->setPersonnelNotes($rowResult[$this->fieldNames[3]]);
        $personnel->setCreatedBy($rowResult[$this->fieldNames[4]]);
        $personnel->setCreatedTimestamp($rowResult[$this->fieldNames[5]]);
        $personnel->setUpdateBy($rowResult[$this->fieldNames[6]]);
        $personnel->setUpdateTimestamp($rowResult[$this->fieldNames[7]]);
        $personnel->setDeleted($rowResult[$this->fieldNames[8]]);

        return $personnel;
    }

    public function insertPersonnel(Personnel $personnel)
    {   
        $query = ' SELECT PERSONNEL_SID FROM ' . $this->dbName . '.' . $this->tableName . 
            ' WHERE PERSONNEL_NAME= ' . $this->pdo->quote($personnel->getPersonnelName()) . 'AND DELETED=0';
       
        $runQuery   = $this->pdo->query($query);
        $rowCount  = $runQuery->rowCount();

        if($rowCount>0)
        {
            return 'error_duplicate';
        }
        
        $query = 'INSERT INTO ' . $this->dbName . '.' . $this->tableName . '(
                ' . $this->fieldNames[1] . ' ,
                ' . $this->fieldNames[2] . ' ,
                ' . $this->fieldNames[3] . ' ,
                ' . $this->fieldNames[4] . ' ,
                ' . $this->fieldNames[5] . ' ,
                ' . $this->fieldNames[9] . ' 
            )VALUES (?,?,?,?,?,?)';

        $statement = $this->pdo->prepare($query);

        $paramValues = array(
            $personnel->getPersonnelName(),
            ($personnel->getActive() == 1) ? TRUE : FALSE,
            $personnel->getPersonnelNotes(),
            $personnel->getCreatedBy(),
            $personnel->getCreatedTimestamp(),
            $personnel->getOwnerGroup(),
        );
        $statement->execute($paramValues);
        return 'info_saving_success';  
    }

    public function getList($param , $sortSQLQuery, $isSuperAdmin, $isGroupAdmin, $isUserAdmin, $lang1 = '', $lang2 = '')
    {
        $page    = ($param['sPage'] > 0) ? $param['sPage'] : 1;
        $perpage = 10;
        try {
            if ($lang1 !== '' && $lang2 !== '') {
                $column  = "PERSONNEL_SID, OWNER_GROUP, PERSONNEL_NAME, IF (ACTIVE = 1,'" . $lang1 . "','" . $lang2 . "') AS ACTIVE, "
                            . "PERSONNEL_NOTES, CREATED_BY, CREATED_TIMESTAMP, UPDATED_BY, UPDATED_TIMESTAMP, DELETED";
                $query   = 'SELECT ' . $column . ' FROM ' . $this->dbName . '.' . $this->tableName;
            } else {   
                $query   = 'SELECT * FROM ' . $this->dbName . '.' . $this->tableName;
            }
            $sParam  = array();
            $isWhere = 0;
            if ($param['sName'] != '') {
                $sParam[] = '%' . $param['sName'] . '%';
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $isWhere  = 1;
                $query.=" $where PERSONNEL_NAME LIKE ?";
            }
            if ($param['sStatus'] != '') {
                $sParam[] = $param['sStatus'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where ACTIVE = ?";
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
            $query.=" $where DELETED = false";
            
            $query .= $privilegeQuery." ".$sortSQLQuery." ";
            $start = ($page - 1) * $perpage;

            $query .=" LIMIT $start,$perpage";
            $statement = $this->pdo->prepare($query);

            $statement->execute($sParam);
            $a = $this->generateResult($statement);
            
            if($isGroupAdmin || $isSuperAdmin || $isUserAdmin){
                return $a;
            } else {
                return 0;
            }
        } catch (\Exception $e) {
            throw new \Exception('Can not find Personnel List Error : ' . $e->getMessage());
        }
    }

    public function getCountPersonnel($param,$isSuperAdmin, $isGroupAdmin)
    {
        try {
            $query   = 'SELECT PERSONNEL_SID FROM ' . $this->dbName . '.' . $this->tableName;
            $sParam  = array();
            $isWhere = 0;
            if ($param['sName'] != '') {
                $sParam[] = '%' . $param['sName'] . '%';
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $isWhere  = 1;
                $query.=" $where PERSONNEL_NAME LIKE ?";
            }
            if ($param['sStatus'] != '') {
                $sParam[] = $param['sStatus'];
                $where    = ($isWhere == 1) ? " AND " : " WHERE ";
                $query.=" $where ACTIVE = ?";
                $isWhere  = 1;
            }
            
            if($isSuperAdmin){
                $privilegeQuery = " And 1 ";
            }
            else if($isGroupAdmin){
                $privilegeQuery = " And OWNER_GROUP = ".$_SESSION['CLIENT_ID'];
            }

            $where = ($isWhere == 1) ? " AND " : " WHERE ";
            $query.=" $where DELETED = false ".$privilegeQuery;

            $statement = $this->pdo->prepare($query);

            $statement->execute($sParam);
            
            if($statement->rowCount() > 0  && ($isGroupAdmin || $isSuperAdmin) ){
                return $statement->rowCount();
            } else{
                return 0;
            }
        } catch (\Exception $e) {
            throw new \Exception('Can not find Personnel List Error : ' . $e->getMessage());
        }
    }

    public function getPersonnelBySid($personnelSid)
    {
        $query     = 'SELECT * FROM ' . $this->dbName . '.' . $this->tableName . ' WHERE PERSONNEL_SID= :value';
        $statement = $this->pdo->prepare($query);
        $statement->bindParam(':value', $personnelSid, \PDO::PARAM_INT);

        $statement->execute();
        $a = $this->generateResult($statement);
        return $a;
    }

    public function doDelete($sId = null)
    {
        try {
            $pdo   = $this->pdo;
            $query = "UPDATE " . $this->dbName . '.' . $this->tableName .
                " SET deleted=true WHERE FIND_IN_SET(PERSONNEL_SID,?)";

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

    protected function doInsert()
    {
        
    }

    protected function doRead($id)
    {
        
    }

    public function doUpdate($personnel = null)
    {
        try {
            $pdo   = $this->pdo;
            
            $query = ' SELECT PERSONNEL_SID FROM ' . $this->dbName . '.' . $this->tableName . 
            ' WHERE PERSONNEL_NAME= ' . $pdo->quote($personnel->getPersonnelName()) . 
            ' AND PERSONNEL_SID != ' . $pdo->quote($personnel->getPersonnelSid()). ' AND DELETED=0';
            $runQuery   = $pdo->query($query);
            $rowCount  = $runQuery->rowCount();

            if($rowCount>0)
            {
                return 'error_duplicate';
            }
        
            $query = "UPDATE " . $this->dbName . '.' . $this->tableName .
                " SET PERSONNEL_NAME = ?, PERSONNEL_NOTES=?, ACTIVE=?, " .
                " UPDATED_BY=?, UPDATED_TIMESTAMP=?" .
                " WHERE PERSONNEL_SID = ?";
            $stmt  = $pdo->prepare($query);

            $inputParams[] = $personnel->getPersonnelName();
            $inputParams[] = $personnel->getPersonnelNotes();
            $inputParams[] = ($personnel->getActive() == 1) ? TRUE : FALSE;
            $inputParams[] = $personnel->getUpdateBy();
            $inputParams[] = date("Y-m-d H:s:i P");
            $inputParams[] = $personnel->getPersonnelSid();

            $stmt->execute($inputParams);
            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
            
            return 'info_update_success';
            
        } catch (\Exception $ex) {
            throw new \Exception('ERROR UPDATE PERSONNEL : ' . $personnel->getPersonnelSid() . '=>' . $ex->getMessage());
        }
    }

    protected function validateFields()
    {
        
    }
    
    public function checkGroup ($sId, $superAdmin, $groupAdmin, $userAdmin) {
        if($superAdmin){
            $privilegeQuery = "";
        }
        else if($groupAdmin){
            $privilegeQuery = " AND OWNER_GROUP = " . $_SESSION['CLIENT_ID'] . " ";
        }
        else if($userAdmin){
            $privilegeQuery = " AND CREATED_BY = " . $_SESSION['USER_ID']. " ";
        }
        
        $query      = "SELECT COUNT(*) as count FROM " . $this->dbName . "." . $this->tableName . " WHERE PERSONNEL_SID='" . $sId . "'" . $privilegeQuery;
        $pdo        = $this->pdo;
        $stmt       = $pdo->prepare($query);
        $stmt->execute();
        $result     = $stmt->fetch(\PDO::FETCH_ASSOC);

        return $result['count'];
    }

}
