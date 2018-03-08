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
 * Filename         : ActivityDao.php
 * Fileversion      : 2.000.000
 * Initial Creation : 2014-06-30
 * Initial Author   : Sejati
 * Purpose          : Activity DAO
 * ================================================
 * Initial Request  : #657
 * ================================================
 * Change Log
 * Date         Author           Version   Request     Comment         
 * 2014-06-30   Sejati          1.0       #657        Initial Creation
 * 2014-08-11   Nababan Maryo             #1540       Fixing DF number
 * 2014-08-12   Dwikky          1.1       #657        Altasort
 * 2014-09-04   Nababan Maryo             #2094       Fixing sort and date format
 * 2014-09-05   Yung Fei                  #2094       [MOI] SpotTrax -> Field Activity: On first visit to the tab 'Field Activity' after login, the FA list shows that the list is ordered by 'Created Date', but the result is not correct.  
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Spottrax\Dao;

use Firstwap\Firstphp\Db\MutableRecord;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\DbNameRef;

require_once \ALTAMIDES_LIB_PATH . 'common/Altatime.php';

/**
 * Description of Direction Finder DAO
 *
 * @author W.Sejati <waluyo.sejati@1rstwap.com>
 */
class ActivityDao extends MutableRecord
{

    public function __construct()
    {
        $this->fields = array(
            'TRACKING_ACTIVITY_SID' => null,
            'ACTIVITY_NAME'         => '',
            'TARGET_MSISDN'         => null,
            'START_TIMESTAMP'       => null,
            'END_TIMESTAMP'         => null,
            'ACTIVITY_NOTES'        => '',
        );

        $this->keyName = 'TRACKING_ACTIVITY_SID';

        $this->emptyDF   = true;
        $this->dbManager = DbManager::getInstance();
        $this->pdo       = $this->dbManager->getPdo(DbNameRef::SUBCELL_TRACKING_20);
    }

    /**
     * 
     * @return fields
     */
    public function getDataDf()
    {
        return $this->fields;
    }

    protected function getParams()
    {
        $inputParams = array();
        foreach ($this->fields as $input) {
            $inputParams[] = $input;
        }
        return $inputParams;
    }

    /**
     * Function doDelete
     * Function to delete Data on TRACKING_ACTIVITY table
     * 
     * @param 
     * 
     */
    public function doDelete($sId = null)
    {
        try {
            $pdo   = $this->pdo;
            $query = "UPDATE " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY" .
                " SET deleted=true where FIND_IN_SET(TRACKING_ACTIVITY_SID,?)";

            $stmt = $pdo->prepare($query);
            $stmt->execute(array($sId));
            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('Can not find Activity record for : ' . $ex->getMessage());
        }
    }

    /**
     * function doInsert
     * Function to Insert data on TRACKING_ACTIVITY table by User
     * 
     * @param 
     * 
     */
    public function doInsert()
    {
        try {
            $pdo   = $this->pdo;
            $query = "INSERT INTO " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY (";
            $query .= implode(",", array_keys($this->fields));
            $query .= ") values (?,?,?,?,?,?,?,?)";
            $stmt  = $pdo->prepare($query);
            $stmt->execute($this->getParams());

            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('Can not Insert record for : ' . $ex->getMessage());
        }
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
            $query = "select " . \implode(',', array_keys($this->fields)) . " from " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY where TRACKING_ACTIVITY_SID = ?";
            $stmt  = $pdo->prepare($query);
            $stmt->execute(array($sId));
            if ($stmt->rowCount()) {
                $this->emptyDF = false;
            }
            $this->fields = $stmt->fetch(\PDO::FETCH_ASSOC);
        } catch (\Exception $e) {
            throw new \Exception('Can not find Activity record for : ' . $e->getMessage());
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
            $query = "UPDATE " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY SET " .
                " ACTIVITY_NAME = ?,  ACTIVITY_NOTES=?" .
                " WHERE TRACKING_ACTIVITY_SID = ?";
            $stmt  = $pdo->prepare($query);

            $inputParams[] = $this->fields['ACTIVITY_NAME'];
            $inputParams[] = $this->fields['ACTIVITY_NOTES'];
            $inputParams[] = $this->fields['TRACKING_ACTIVITY_SID'];

            $stmt->execute($inputParams);
            if ($stmt->rowCount()) {
                $this->doAct = true;
            } else {
                $this->doAct = false;
            }
        } catch (\Exception $ex) {
            throw new \Exception('Can not find Activity record for : ' . $this->fields['TRACKING_ACTIVITY_SID']);
        }
    }

    public function getListFA($param , $sortSQLQuery = '', $queryLimit='', $queryOffset='',$isSuperAdmin, $isGroupAdmin)
    {
        $timeConverter = new \gmtTime();
        $page    = ($param['sPage'] > 0) ? $param['sPage'] : 1;
        $perpage = 10;
        try {
            $pdo    = $this->pdo;
            $query  = "SELECT ta.ACTIVITY_NAME as FA_NAME, 
                ta.TARGET_MSISDN, START_TIMESTAMP as SDATE
                ,END_TIMESTAMP as EDATE,
                ta.TRACKING_ACTIVITY_SID,
                ta.ACTIVITY_NOTES, 
                COUNT(tad.TRACKING_DEVICE_SID) AS dfNumber" .
                " FROM " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY ta".
                " LEFT JOIN ". REF_SPOTTRAX_DBNAME .".TRACKING_ACTIVITY_DEVICE tad".
                " ON ta.TRACKING_ACTIVITY_SID = tad.TRACKING_ACTIVITY_SID" .
                " WHERE 1 ";
            $sParam = array();
            if ($param['sName'] != '') {
                $sParam[] = '%' . $param['sName'] . '%';
                $query.=" AND ta.ACTIVITY_NAME LIKE ?";
            }
            if ($param['sMSISDN'] != '') {
                $sParam[] = '%' . $param['sMSISDN'] . '%';
                $query.=" AND ta.TARGET_MSISDN LIKE ?";
            }
            if ($param['sDate'] != '') {
                $sParam[] = $timeConverter->localToGmt($param['sDate']."00:00:00");
                $query.=" AND ta.START_TIMESTAMP >= ?";
            }
            if ($param['eDate'] != '') {
                $sParam[] = $timeConverter->localToGmt($param['eDate']."23:59:59");
                $query.=" AND ta.END_TIMESTAMP <= ?";
            }
            if ($param['sStatus'] != '') {
                if ($param['sStatus'] === '1') {
                    $query.=" AND ta.END_TIMESTAMP IS NULL";
                } else {
                    $query.=" AND ta.END_TIMESTAMP != ''";
                }
            }
            
            $privilegeQuery = "";
            if($isSuperAdmin){
                $privilegeQuery = " AND 1 ";
            }
            else if($isGroupAdmin){
                $privilegeQuery = " AND OWNER_GROUP = ".$_SESSION['CLIENT_ID']." ";
            }            

            $query.=" AND ta.DELETED = false ".$privilegeQuery;
            $query.=" GROUP BY tad.TRACKING_ACTIVITY_SID";
            $sNum  = $query;
            $query .=" ".$sortSQLQuery." ";
            $start = ($page - 1) * $perpage;
            
            if($sortSQLQuery != '')
            {
                $query .=" LIMIT $queryOffset,$queryLimit";
            }
            
            $stmt    = $pdo->prepare($query);
            $stmtNum = $pdo->prepare($sNum);
            
            $stmt->execute($sParam);
            $stmtNum->execute($sParam);
            
            $num = 0;
            if ($stmt->rowCount() && ($isGroupAdmin || $isSuperAdmin) ) {
                $num = $stmtNum->rowCount();
                $this->emptyDF = false;
            }
            $this->bulk = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return array('data' => $this->bulk, 'num' => $num);
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF List Error : ' . $e->getMessage());
        }
    }
    
    /**
     * Using for Record Section
     * @param type $traxID
     */
    public function getRecordedActivity($traxID)
    {
        $data = array(
            "isStopped"   => 0,
            "timeElapsed" => "0 days 0 hours 0 minutes",
        );
        try {
            $pdo   = $this->pdo;
            /**
             * TIME ELAPSED
             */
            $query = "select DATE_FORMAT(START_TIMESTAMP,'%d/%m/%Y %h:%i:%s') as SDATE," .
                "DATE_FORMAT(END_TIMESTAMP,'%d/%m/%Y %h:%i:%s') as EDATE" .
                " FROM " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY WHERE ";

            $query.=" TRACKING_ACTIVITY_SID = ?";

            $stmt          = $pdo->prepare($query);
            $stmt->execute(array($traxID));
            $this->emptyDF = false;

            $result = $stmt->fetch(\PDO::FETCH_ASSOC);
            if ($result['EDATE'] != '') {
                $data['isStopped']   = 1;
                $data['timeElapsed'] = $this->elapseTime($result);
            }

            /**
             * DF Data
             */
            $query1  = "" .
                "SELECT TDD.* FROM " . REF_SPOTTRAX_DBNAME . ".TRACKING_DEVICE_DATA TDD" .
                " LEFT JOIN " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY_DEVICE TAD ON TDD.TRACKING_ACTIVITY_DEVICE_SID=TAD.TRACKING_ACTIVITY_DEVICE_SID" .
                " WHERE TAD.TRACKING_ACTIVITY_SID=?" .
                " ORDER BY TDD.TRACKING_DEVICE_DATA_SID";
            $stmt1   = $pdo->prepare($query1);
            $stmt1->execute(array($traxID));
            $result1 = $stmt1->fetch(\PDO::FETCH_ASSOC);

            return $data;
        } catch (\Exception $e) {
            throw new \Exception('Can not find DF List Error : ' . $e->getMessage());
        }
    }

    protected function elapseTime($dates)
    {
        $first = new \DateTime($dates['SDATE']);
        $last  = new \DateTime($dates['EDATE']);
        $delta = $first->diff($last);

        $quantities = array(
            'year'   => $delta->y,
            'month'  => $delta->m,
            'day'    => $delta->d,
            'hour'   => $delta->h,
            'minute' => $delta->i,
            'second' => $delta->s);

        $str = $delta->format('%a days %H hours %i minutes');
        return $str;
    }

    protected function validateFields()
    {
        
    }

    public function isDataSuccess()
    {
        return $this->doAct;
    }

    public function getAutoComplete($key, $val = null)
    {
        try {
            $pdo   = $this->pdo;
            $query = "select distinct " . $key . " from " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY";
            if ($val != null) {
                $query .= " where " . $key . " like '%" . $val . "%'";
            }
            $stmt = $pdo->prepare($query);
            $stmt->execute();

            return $stmt->fetchAll(\PDO::FETCH_ASSOC);
        } catch (\Exception $e) {
            throw new \Exception('Can not find Activity record for : ' . $key);
        }
    }

    public function getCellInfo($trackingActivitySid, $dataOffset)
    {
        try {
            $pdo   = $this->pdo;
            $query = "SELECT LRS.IMSI AS IMSI, 
                             LCM.METHOD_SHORT_NAME,
                             LRSC.CELL_REF AS CELLREF,
                             IF(LCM.METHOD_SHORT_NAME = 'MSC', LRA.LATITUDE, LRSC.CENTER_LATITUDE) AS C_LAT,
                             LRSC.CENTER_LONGITUDE AS C_LON,
                             LRSC.BTS_LONGITUDE AS BTS_LON,
                             LRSC.BTS_LATITUDE AS BTS_LAT,
                             LRSC.CELL_RADIUS AS C_RADIUS,
                             LRA.TEXTUAL_DESCRIPTION,
                             IF(LCM.METHOD_SHORT_NAME = 'MSC', LRA.RADIUS, LRSC.FORWARD_AXIS) AS WIDTH,
                             IF(LCM.METHOD_SHORT_NAME = 'MSC', LRA.RADIUS, LRSC.ORTHOGONAL_AXIS)  AS HEIGHT,
                             LRSC.AZIMUTH AS AZIMUTH,
                             CELL.ELEVATION AS ELEVATION,
                             CELL.COMMENT AS COMMENT,
                             CELL.GSM_CID AS GSM_CID,
                             CELL.RNC_CID AS RNC_CID,
                             CELL.BCH AS BCH,
                             TCR.TARGET_CELL_REQUEST_SID AS ID
                        FROM 
                             " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY AS TA
                            inner join " . REF_SPOTTRAX_DBNAME . ".TARGET_CELL_REQUEST AS TCR
                                on(TA.TRACKING_ACTIVITY_SID = TCR.TRACKING_ACTIVITY_SID)
                            inner join " . \REF_FTRAX_LOCATIONMO_DBNAME . ".LOCATION_REQUEST AS LRQ
                                on (TCR.LOCATION_REQUEST_ID = LRQ.ID)
                            inner join " . \REF_FTRAX_LOCATIONMO_DBNAME . ".LOCATION_RESULT AS LRS
                                on (LRQ.ID = LRS.LOCATION_REQUEST_ID)
                            left join " . \REF_FTRAX_LOCATIONMO_DBNAME . ".LOCATION_RESULT_ADDITIONAL AS LRA
                                on (LRS.ID = LRA.LOCATION_RESULT_ID)
                            left join " . \REF_FTRAX_LOCATIONMO_DBNAME . ".LOCATION_RESULT_CELL AS LRSC
                                on (LRS.ID = LRSC.LOCATION_RESULT_ID)
                            inner join " . REF_NUMPLAN_DBNAME . ".CELL_DB AS CELL
                                on (CELL.CELL_REF = LRSC.CELL_REF)
                            left join " . \REF_DB_NAME . ".LOCATION_CALCULATION_METHOD AS LCM
                                on (LRS.LOCATION_CALCULATION_METHOD_ID = LCM.ID)
                        WHERE
                            TA.TRACKING_ACTIVITY_SID = ? AND
                            TCR.TARGET_CELL_REQUEST_SID >= ? order by TCR.TARGET_CELL_REQUEST_SID LIMIT 1
                             ";
            $stmt  = $pdo->prepare($query);
            $stmt->execute(array($trackingActivitySid, $dataOffset));
            return $stmt->fetchAll(\PDO::FETCH_ASSOC);
        } catch (\Exception $e) {
            throw new \Exception('Can not find data : ' . $e->getMessage());
        }
    }

    public function getTargetInfo($trackingActivitySid, $dataOffset)
    { 
         try {
            $gmt = str_split(ALTAMIDES_CLIENT_GMT_HOUR_OFFSET);
            $pdo   = $this->pdo;
            $query = "SELECT TA.TARGET_MSISDN AS MSISDN, 
                             TL.COMPUTED_LATITUDE AS LAT,     
                             TL.COMPUTED_LONGITUDE AS LON,
                             TL.CALC_TIMESTAMP LASTCALC ,
                             TL.RADIUS AS ACCURACY,
                             TL.TARGET_LOCATION_SID AS ID
                        FROM 
                             " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY AS TA
                             INNER JOIN " . REF_SPOTTRAX_DBNAME . ".TARGET_LOCATION AS TL
                             ON(TA.TRACKING_ACTIVITY_SID  = TL.TRACKING_ACTIVITY_SID)
                        WHERE
                             TL.TARGET_LOCATION_SID >=:TLS AND
                             TA.TRACKING_ACTIVITY_SID =:TAS ORDER BY TL.TARGET_LOCATION_SID";
            $stmt  = $pdo->prepare($query);
            $stmt->bindparam(":TLS",$dataOffset);
            $stmt->bindparam(":TAS",$trackingActivitySid);
            $stmt->execute();
            $return1 =  $stmt->fetchAll(\PDO::FETCH_ASSOC);
            if($dataOffset < 1){
                return $return1;
            }else{
                $lastCalc = trim(str_replace(array("PM","AM"), "", $return1[0]['LASTCALC']));
                $query2 = "SELECT 
                                rq.ID,
                                rq.DESTINATION_HP,
                                rq.DATE_TIME,
                                rs.CELL_ID,
                                rs.IMSI,
                                rs.IMEI,
                                ra.LATITUDE,
                                ra.LONGITUDE,
                                ra.RADIUS,
                                lrc.BTS_LONGITUDE,
                                lrc.BTS_LATITUDE
                            FROM
                                LOCATION_SINGLE_USE.LOCATION_REQUEST AS rq
                                    INNER JOIN
                                LOCATION_SINGLE_USE.LOCATION_RESULT AS rs ON (rq.ID = rs.LOCATION_REQUEST_ID)
                                    LEFT JOIN
                                LOCATION_SINGLE_USE.LOCATION_PSL AS t2 ON (rs.LOCATION_REQUEST_ID = t2.LOCATION_REQUEST_ID)
                                    LEFT JOIN
                                LOCATION_SINGLE_USE.LOCATION_PSL_SHAPE AS t3 ON (t2.LOCATION_PSL_ID = t3.LOCATION_PSL_ID)
                                    LEFT JOIN
                                LOCATION_SINGLE_USE.LOCATION_RESULT_ADDITIONAL AS ra ON (ra.LOCATION_RESULT_ID = rs.ID)
                                    LEFT JOIN
                                LOCATION_SINGLE_USE.LOCATION_RESULT_CELL AS lrc ON (lrc.LOCATION_RESULT_ID = rs.ID)
                            WHERE
                            rq.DESTINATION_HP =:DHP 
                            AND rq.REQUESTING_USER =:RU
                            ORDER BY rq.ID DESC
                            LIMIT 1;";
                $stmt2  = $pdo->prepare($query2);
                $stmt2->bindparam(":DHP",$return1[0]['MSISDN']);
                $stmt2->bindparam(":RU",$_SESSION['user_id']);
                $stmt2->execute();
                $return2 = $stmt2->fetchAll(\PDO::FETCH_ASSOC);
                if($return2){
                    $result = array();
                    foreach($return1 as $dtReturn){
                        $dtReturn['IMSI'] = $return2[0]['IMSI'];
                        $dtReturn['CELLREF'] = $return2[0]['CELL_ID'];
                        $result[] = $dtReturn;
                    }
                    return $result;
                }else{
                    return $return1;
                }

            }
        }  catch (\Exception $e) {
            throw new \Exception('Can not find data : ' . $e->getMessage());
        }
    }

    public function getActivityOption($selected = '', $superadmin = '' , $group = '')
    {
        $pdo     = $this->pdo;
        $options = "";
        $query   = "SELECT ACTIVITY_NAME as FA_NAME,
                          TRACKING_ACTIVITY_SID,
                          END_TIMESTAMP AS EDATE
                   FROM " . REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY WHERE DELETED=0 ";
        if ($group && !$superadmin) {
            $query .= "AND OWNER_GROUP =" . $pdo->quote($_SESSION['CLIENT_ID'], \PDO::PARAM_INT);
        }
        $query .= " ORDER BY TRACKING_ACTIVITY_SID DESC LIMIT 10";

        $stmt   = $pdo->query($query);
        $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
        if ($result && ($superadmin || $group) ) {
            foreach ($result as $val) {
                $stat = 'Inactive';
                if($val['EDATE'] == 'null' || $val['EDATE'] == ''){
                    $stat = 'Active';
                }
                if ($selected == $val['TRACKING_ACTIVITY_SID']) {
                    $options .= "<option selected='selected' value='" . $val['TRACKING_ACTIVITY_SID'] . "'>" . $val['FA_NAME'] . " - " . $stat . "</option>";
                } else {
                    $options .= "<option value='" . $val['TRACKING_ACTIVITY_SID'] . "'>" . $val['FA_NAME'] . " - " . $stat . "</option>";
                }
            }
        }

        return $options;
    }
    
    public function checkGroup ($sId, $superAdmin, $groupAdmin) {
        if($superAdmin){
            $privquery  = '';
        } else if($groupAdmin){
            $privquery  = " AND OWNER_GROUP = '". $_SESSION['CLIENT_ID'] ."' ";
        }
        
        $query      = "SELECT COUNT(*) as count FROM ". REF_SPOTTRAX_DBNAME . ".TRACKING_ACTIVITY WHERE TRACKING_ACTIVITY_SID = '". $sId. "'" . $privquery;
        $pdo        = $this->pdo;
        $stmt       = $pdo->prepare($query);
        $stmt->execute();
        $result     = $stmt->fetchAll(\PDO::FETCH_ASSOC);

        return $result[0]['count'];
    } 

}
