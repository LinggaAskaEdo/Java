<?php
/**
 * Class/Module Name: adodb.php
 *
 * Current Version: 1.0
 *
 * Date Created: 10 July 2009
 *
 * Created By: Huripto Sugandi
 *
 * Description : This is wrapper class for database connections using ADOdb. This will cover common
 *               ADOdb functions that are used in the project.
 *
 * Change History:
 * Version  Date        Change  Person          Description of Change
 * 1.0      10-7-2009   none    Huripto Sugandi First Build
 */

require_once dirname(__FILE__).'/adodb.inc.php';
require_once dirname(__FILE__).'/adodb-exceptions.inc.php';
/**
 * @example
 * $newConn =  new adodb('theProfile');
 *
 * @property bool $debug
 */
class adodb {
	/**
	 *
	 * @var ADOConnection
	 */
    private $_db;
	
	/**
	 * The default db driver that will be used if it is not specified in the
	 * profile
	 *
	 * 'mysqli' is used here because the old mysql extension is no longer
	 * enabled by default nor bundled in PHP5+
	 * @link http://id.php.net/manual/en/mysql.installation.php mysql extension
	 */
	const DRIVER_DEFAULT='mysqli'; //preferred driver is mysqli since the old mysqlt has been deprecated

	/**
	 * constructor
	 * @param array $profile the connection profile, an array of arguments
	 *                       Possible keys:
	 *                       - dbHost   : The database IP/hostname
	 *                       - dbUser   : The database user name
	 *                       - dbPswd   : The database user password
	 *                       - dbName   : The database name (optional)
	 *                       - dbDriver : The database driver. can be
	 *                                     mysqli, mysql, pdo,
	 * @param bool $forceNew Force new connection, or let php/extension to decide
	 */
    public function __construct(array $profile, $forceNew=false) {
		if(empty ($profile)){
			throw new Exception('Invalid connection spesification');
		}

		$dbHost = isset($profile['dbHost'])? $profile['dbHost'] : '';
		$dbUser = isset($profile['dbUser'])? $profile['dbUser'] : '';
		$dbPswd = isset($profile['dbPswd'])? $profile['dbPswd'] : '';
		$dbName = isset($profile['dbName'])? $profile['dbName'] : '';
		$dbDriver = empty($profile['dbDriver'])? self::DRIVER_DEFAULT : $profile['dbDriver'];

        $this->_db = NewADOConnection($dbDriver);    // call the adodb class
        if($forceNew){
			$this->_db->NConnect($dbHost, $dbUser, $dbPswd, $dbName); // make and wrap the connections
		}else{
			$this->_db->Connect($dbHost, $dbUser, $dbPswd, $dbName); // make and wrap the connections
		}
    }

	/**
	 * Close database connection
	 */
	public function Close(){
		$this->_db->Close();
	}

	/**
	 *
	 * @return bool
	 */
	public function IsConnected(){
		return $this->_db->IsConnected();
	}

	/**
	 *
	 * @param int $mode
	 * One of :
	 * - ADODB_FETCH_ASSOC
	 * - ADODB_FETCH_NUM
	 * - ADODB_FETCH_BOTH
	 * - ADODB_FETCH_DEFAULT
	 */
	public function SetFetchMode($mode) {
		$this->_db->SetFetchMode($mode);
	}


		/////////////// QUERIES //////////////
	/**
	 *
	 * @param string $query query string
	 * @param mixed $param
	 * @return ADORecordSet
	 */
    public function Execute($query, $param=false) {
       return $this->_db->Execute($query,$param);
    }
	
    public function CacheExecute($secs2cache, $sql, $param=false) {
       return $this->_db->CacheExecute($secs2cache, $sql, $inputarr);
    }

	/**
	 * Get all records from query as an array with one of the fields value
	 * as the array key
	 *
	 * @param string $indexField The field which will be used as key
	 * @param string $sql the query
	 * @param mixed $inputarr input param
	 * @return array
	 */
    public function GetAllIndexed($indexField, $sql, $inputarr=false) {
		$rs = $this->_db->Execute($sql, $inputarr);
		
		if(!$rs->RecordCount()){
			return array();
		}
		
		$indexField = (string) $indexField;
		if(!$rs->fields || !array_key_exists($indexField, $rs->fields)){
			trigger_error('Index field <'.$indexField.'> is not found in the result set', E_USER_WARNING);
			return array();
		}

		$rows = array();
		while(!$rs->EOF){
			$rows[$rs->fields[$indexField]] = $rs->fields;
			$rs->MoveNext();
		}
		
		$rs->Close();
		return $rows;
    }
	
	/**
	 * Get all records from query as an array with one of the fields value
	 * as the array key
	 *
	 * @param string $indexField The field which will be used as key
	 * @param string $sql the query
	 * @param mixed $inputarr input param
	 * @return array
	 */
    public function CacheGetAllIndexed($secs2cache, $indexField, $sql, $inputarr=false) {
		$rs = $this->_db->CacheExecute($secs2cache, $sql, $inputarr);

		if(!$rs->RecordCount()){
			return array();
		}

		$indexField = (string) $indexField;
		if(!$rs->fields || !array_key_exists($indexField, $rs->fields)){
			trigger_error('Index field <'.$indexField.'> is not found in the result set', E_USER_WARNING);
			return array();
		}
		
		$rows = array();
		while(!$rs->EOF){
			$rows[$rs->fields[$indexField]] = $rs->fields;
			$rs->MoveNext();
		}
		
		$rs->Close();
		return $rows;
    }

	/**
	 * Retrieved all result as two dimensional array
	 * Example usefule for 
	 * 
	 * @param type $groupIndexField The array first dimension index
	 * @param type $sql The query SQL
	 * @param type $inputarr SQL param arguments (if any), false to skip
	 * @param type $itemIndexField The array's second dimension index.
	 *	NULL or omit to use numeric value
	 * 
	 * @return array
	 */
	public function GetAllGrouped($groupIndexField, $sql, $inputarr=false, $itemIndexField = null) {
		$rs = $this->_db->Execute($sql, $inputarr);

		if(!$rs->RecordCount()){
			return array();
		}

		$rows = array();
		$itemIndexField = (string) $itemIndexField;
		$groupIndexField = (string) $groupIndexField;

		if(!isset($rs->fields[$groupIndexField])){
			$rs->Close();
			throw new Exception("Group index field was not found in the result");
			return array();
		}

		if(($itemIndexField !== null) && !isset($rs->fields[$itemIndexField])){
			$rs->Close();
			throw new Exception("Item index field was not found in the result");
			return array();
		}

		if($itemIndexField === null){

			while(!$rs->EOF){
				$fields =& $rs->fields;
				$groupIndex = $fields[$groupIndexField];
		
				if(!isset($groupIndex)){
					$rows[$groupIndex] = array();
				}

				$rows[$groupIndex][] = $fields;

				$rs->MoveNext();
			}

		} else {

			while(!$rs->EOF){
				$fields =& $rs->fields;
				$groupIndex = $fields[$groupIndexField];
				$itemIndex = $fields[$itemIndexField];

				if(!isset($groupIndex)){
					$rows[$groupIndex] = array();
				}

				$rows[$groupIndex][$itemIndex] = $fields;

				$rs->MoveNext();
			}

		}

		$rs->Close();
		return $rows;
    }


	/**
	 *
	 * @param string $query query string
	 * @param mixed $inputarr
	 * @return array
	 */
    public function GetAll($sql, $inputarr=false) {
        return $this->_db->GetAll($sql, $inputarr);
    }

	public function CacheGetAll($secs2cache, $sql=false, $inputarr=false) {
		return $this->_db->CacheGetAll($secs2cache, $sql, $inputarr);
	}

	/**
	 * Get value of first column in first row of the query result
	 * @param string $query query string
	 * @param mixed $inputarr
	 * @return mixed
	 */
    public function GetOne($sql,$inputarr=false) {
        return $this->_db->GetOne($sql,$inputarr);
    }
	
	public function CacheGetOne($secs2cache, $sql=false, $inputarr=false) {
		return $this->_db->CacheGetOne($secs2cache, $sql, $inputarr);
	}

	/**
	 * Get first row of the query result
	 * @param string $query query string
	 * @param mixed $inputarr
	 * @return mixed result in array when success or FALSE if failed
	 */
    public function GetRow($sql,$inputarr=false) {
        return $this->_db->GetRow($sql,$inputarr);
    }

	public function CacheGetRow($secs2cache, $sql=false, $inputarr=false) {
		return $this->_db->CacheGetRow($secs2cache, $sql, $inputarr);
	}

    public function GetCol($sql, $inputarr=false, $trim=false) {
        return $this->_db->GetCol($sql, $inputarr, $trim);
    }

	public function CacheGetCol($secs2cache, $sql, $inputarr=false, $trim=false) {
		return $this->_db->GetCol($secs2cache, $sql, $inputarr, $trim);
	}
	
	////////////// RESULT HELPER
	/**
	 * Alias to Insert_ID
	 * @param <type> $table
	 * @param <type> $column
	 * @return <type>
	 */
	public function InsertId($table='',$column='') {
        return $this->_db->Insert_ID($table,$column);
    }
	/**
	 * Get last query auto increment value
	 * @param string $table
	 * @param string $column
	 * @return int
	 */
    public function Insert_ID($table='',$column='') {
        return $this->_db->Insert_ID($table,$column);
    }
	/**
	 * Number the affected rows
	 * @return int
	 */
    public function Affected_Rows() {
		return $this->_db->Affected_Rows();
    }

	/*
	 * Wrap call to AutoExecute
	 * Auto create sql statement and execute it
	 *
	 * @param string $table
	 * @param array $fields_values
	 * @param string $mode INSERT or UPDATE
	 * @param string $where WHERE clause, if any, FALSE to disable
	 * @param bool $forceUpdate
	 * @param bool $magicq
	 *
	 * @return bool success or not
	 */
	public function AutoExecute($table, $fields_values, $mode = 'INSERT', $where = FALSE, $forceUpdate=true, $magicq=false) {
		return $this->_db->AutoExecute($table, $fields_values, $mode, $where, $forceUpdate, $magicq);
	}
	
	public function qstr($s, $magic_quotes=false) {
		return $this->_db->qstr($s, $magic_quotes);
	}

	////////////// DEBUGGING
	/**
	 * alias of ErrorMsg
	 * @return string
	 */
    public function ErrorMessage() {
        return $this->_db->ErrorMsg();
    }
	/**
	 * get the latest error message
	 * @return string
	 */
    public function ErrorMsg() {
        return $this->_db->ErrorMsg();
    }

	/////////// TRANSACTION /////////////
	/**
	 * start transaction
	 * @param <type> $errfn
	 */
	public function StartTrans($errfn='ADODB_TransMonitor'){
		$this->_db->StartTrans($errfn);
	}
	/**
	 * 
	 * @param <type> $autoComplete
	 * @return <type>
	 */
	public function CompleteTrans($autoComplete=true){
		return $this->_db->CompleteTrans($autoComplete);
	}
	/**
	 * Has a failed transaction
	 * @return <type>
	 */
	public function HasFailedTrans(){
		return $this->_db->HasFailedTrans();
	}
	
	/**
	 * Commit pending  transactions
	 * @param <type> $ok
	 * @return <type>
	public function CommitTrans($ok=true){
		$this->_db->CommitTrans($ok);
	}
	 */
	/**
	 * cancel transaction
	 * @return <type>
	 */
	public function RollbackTrans(){
		$this->_db->RollbackTrans();
	}
	/**
	 * trigger transaction failure
	 * @return <type>
	 */
	public function FailTrans(){
		$this->_db->FailTrans();
	}

	/////////// PREPARED STATEMENTS ///////////////
	/**
	 * Prepare a statement
	 * @param string $sql
	 * @return mixed FALSE on failure, or the prepared statement
	 */
	public function Prepare($sql) {
		return $this->_db->Prepare($sql);
	}
	
	/**
	 * A helper function to provide portable way to create parameter place
	 *
	 * ==============
	 * From ADODB ADODB_Connection::Prepare doc :
	 *   Returns placeholder for parameter, eg. $DB->Param('a')
	 *   will return ':a' for Oracle, and '?' for most other databases...
	 *   For databases that require positioned params, eg $1, $2, $3 for postgresql,
	 *   pass in Param(false) before setting the first parameter.
	 * ==============
	 *
	 * @param string $param Parameter name
	 * @param string $type Parameter data type, see http://phplens.com/lens/adodb/docs-datadict.htm
	 *                     for complete list
	 * @return string
	 */
	public function Param($param, $type='C') {
		return $this->_db->Param($name, $type);
	}

	/////////// CLASS KIT ///////////////
	/**
	 * [NOT RECOMENDED, use this wrapper class methods where possible]
	 * get the adodb object. If user want to use advanced feature provided by adodb
	 * but not extended by this wrapper class, then use this method to
	 * retrieve ADOConnection object
	 *
	 * @return ADOConnection
	 */
	
	public function GetADODB(){
		return $this->_db;
	}
    /*

	public function  __get($name) {
		if($name=='debug')
			return $this->_db->debug;
	}

	public function  __set($name, $value) {
		if($name=='debug')
			return $this->_db->debug=$value;

	}
	*/
}
