<?php
/**********************************************
* Copyright:    2012 1rstWAP
* System Name:  Altamides
* Version:      2.0 SP15
* File Name:    database.php
* File Version: 1.0
* Date Created: 23 March 2009
* Author:       Keyne D.A.
* 
* Change Log:
* Date Change  Person     Description of Change
* 18 July 2012, Ivana, Add variable checking for $result
*
* Description of class/Module
* Main class for 1rstwap packages
*
* @PHP5
***********************************************/

// getting all the configuration information
require_once dirname(__FILE__) . "/../../configs/config.database.php";

// getting all the needed library
require_once dirname(__FILE__) . '/../error_handler/error_handler.php';

class database {
	
	var $_db_engine;
	var $_error_handler;
	
	function __construct($db_host, $db_user, $db_pass)
	{
		$this->_error_handler = NEW error_handler();

		$db_engine = DB_ENGINE;
		switch ($db_engine)
		{
			case 'mysql':
				{
					require_once dirname(__FILE__) . '/database.mysql.php';

					$this->_db_engine = NEW database_mysql($db_host, $db_user, $db_pass);
				}; break;
			default:
				{
					$this->_db_engine = FALSE;
					
					$this->_error_handler->_triggerError(
						'Undefine database engine ['.$db_engine.']',
						'Undefine database engine',
						'DB Config Error'
					);
				}; break;
		}
		
		if ($this->_db_engine->test_connection() === FALSE)
		{
			$this->_error_handler->_triggerError(
				'Database Host['.$db_host.'], Username['.$db_user.'] or Password is invalid',
				'Connection Failed',
				'DB Config Error'
			);
			$this->_db_engine = NULL;
		}
	}
	
	function set_connection($db_host, $db_user, $db_pass)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$this->_db_engine->set_connection($db_host, $db_user, $db_pass);
			if ($this->_db_engine->test_connection() != FALSE)
			{
				$result = TRUE;
			}
			else
			{
				$this->_error_handler->_triggerError(
					'Database Host['.$db_host.'], Username['.$db_user.'] or Password is invalid',
					'Connection Failed',
					'DB Config Error'
				);
				$this->_db_engine = NULL;
			}
		}
		
		return $result;
	}

	function set_error_log_file($file)
	{
		$this->_error_handler->_setErrorLogFile($file);
	}
	
	function connect()
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->connect();
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Host['.$db_host.'], Username['.$db_user.'] or Password is invalid',
					'Connection Failed',
					'DB Config Error'
				);
			}
		}
		
		return $result;
	}

	function close($db_conn)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->close($db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database resource['.$db_conn.'] raise an error on closing',
					'Database Resource Error',
					'DB Config Error'
				);
			}
		}
		
		return $result;
	}
	
	function select_db($db_name, $db_conn = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->select_db($db_name, $db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database['.$db_name.'] cannot be access',
					'Database not exist',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}
	
	function query($query, $query_id = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->query($query);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					"Error Query String: $query\nError Query Details:".$this->_db_engine->error(),
					'Error Query ID: ['.(($query_id != NULL) ? $query_id : 'undefined').']\n',
					'DB Query Error'
				);
			}
		}
		
		return $result;
	}

	function fetch_row($db_res)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->fetch_row($db_res);
		}
		
		return $result;
	}
	
	function free_result($db_res)
	{
		return ($this->_db_engine != NULL)?
				$this->_db_engine->free_result($db_res) : false;
	}

	function fetch_array($db_res, $res_type = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->fetch_array($db_res, $res_type);
		}
		
		return $result;
	}

	function fetch_assoc($db_res)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->fetch_assoc($db_res);
		}
		
		return $result;
	}

	function fetch_object($db_res, $db_classname = NULL, $db_params = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->fetch_object($db_res, $db_classname, $db_params);
		}
		
		return $result;
	}

	function fetch_field($db_res, $offset = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->fetch_field($db_res, $offset);
		}
		
		return $result;
	}

	function num_fields($db_res)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->num_fields($db_res);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}

	function num_rows($db_res)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->num_rows($db_res);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}

	function field_name($db_res, $field_offset)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->field_name($db_res, $field_offset);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}

	function field_len($db_res, $field_offset)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->field_len($db_res, $field_offset);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}

	function escape_string($string)
	{
		$result = $string;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->escape_string($string);
		}
		
		return $result;
	}

	function real_escape_string($string, $db_conn = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->real_escape_string($string, $db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}

	function error($db_conn = NULL)
	{
		$result = isset($string) ? $string : '';
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->error($db_conn);
		}
		
		return $result;
	}

	function insert_id($db_conn = NULL)
	{
		$result = FALSE;
		
		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->insert_id($db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					'Database Resource['.$db_res.'] cannot be access',
					'Database Resource Error',
					'DB Connection Error'
				);
			}
		}
		
		return $result;
	}
	/**
	 * Start a transaction
	 * @param resource $db_conn Optional database connection
	 * @return bool
	 */
	function start_transaction($db_conn = NULL)
	{
		$result = FALSE;

		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->start_transaction($db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					"Failed starting transaction. Error message was :".$this->_db_engine->error($db_conn),
					'Failed starting transaction.',
					'DB Transaction Error'
				);
			}
		}

		return $result;
	}
	/**
	 * Commit a transaction
	 * @param resource $db_conn Optional database connection
	 * @return bool
	 */
	function commit_transaction($db_conn = NULL)
	{
		$result = FALSE;

		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->commit_transaction($db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					"Failed starting transaction. Error message was :".$this->_db_engine->error($db_conn),
					'Failed starting transaction.',
					'DB Transaction Error'
				);
			}
		}

		return $result;
	}
	/**
	 * Rollback a transaction
	 * @param resource $db_conn Optional database connection
	 * @return bool
	 */
	function rollback_transaction($db_conn = NULL)
	{
		$result = FALSE;

		if ($this->_db_engine != NULL)
		{
			$result = $this->_db_engine->rollback_transaction($db_conn);
			if ($result === FALSE)
			{
				$this->_error_handler->_triggerError(
					"Failed starting transaction. Error message was :".$this->_db_engine->error($db_conn),
					'Failed starting transaction.',
					'DB Transaction Error'
				);
			}
		}

		return $result;
	}
}
?>