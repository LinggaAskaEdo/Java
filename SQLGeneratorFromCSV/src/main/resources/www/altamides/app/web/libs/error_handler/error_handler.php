<?php
/**********************************************
* Class/Module Name: error_handler.php (was firstwapAbstract.php)
*
* Current Version: 1.0
*
* Date Created: 6 February 2007
*
* Created By: Lintaka
*
* Change History:
* Version Date Change  Person     Description of Change
*   1.0   23 Mar 2009  Keyne D.A. Making this library as the global library
*
* Description of class/Module
* Main class for 1rstwap packages
*
* @PHP4
***********************************************/
//ini_set('display_errors', 1); // this is to hide the warning and error message on the page
require_once dirname(__FILE__) . "/../common/common.php";
require_once dirname(__FILE__) . "/../../configs/config.error_handler.php"; //change this to default error configuration

class error_handler
{
	var $_error_log_file;
	
	/**
	 * protected : trigger error
	 * @param    : error message
	 * @result   : show error and write to log
	 */
	function _triggerError ($errorDetail, $errorSummarize = "", $errorTitle = "")
	 {  
	 	switch (POPUP_ERROR_LEVEL)
	 	{
	 		case NO_ERROR         : $popupMessage = ''; break;
	 		case SUMMARIZED_ERROR : $popupMessage = $errorSummarize; break;
	 		case DETAIL_ERROR     : $popupMessage = $errorSummarize . "\n" . $errorDetail; break;
	 		case EXTENSIVE_ERROR  : $popupMessage = $errorSummarize . "\n"
	 		                                      . $errorDetail . "\n"
	 		                                      . $this->_getDebugBackTrace (); break;
	 		default               : $popupMessage = ''; break;
	 	}
		if (POPUP_ERROR_LEVEL > NO_ERROR)
		{
	 		$this->_popupError ($popupMessage, $errorTitle);
		}
	 	
	 	switch (SHOW_ERROR_LEVEL)
	 	{
	 		case NO_ERROR         : $showMessage = ''; break;
	 		case SUMMARIZED_ERROR : $showMessage = $errorSummarize; break;
	 		case DETAIL_ERROR     : $showMessage = $errorSummarize . "\n" . $errorDetail; break;
	 		case EXTENSIVE_ERROR  : $showMessage = $errorSummarize . "\n"
	 		                                     . $errorDetail . "\n"
	 		                                     . $this->_getDebugBackTrace (); break;
	 		default               : $showMessage = ''; break;
	 	}
		if (SHOW_ERROR_LEVEL > NO_ERROR)
		{
	 		$this->_showError ($showMessage, $errorTitle);
		}
	 	
	 	switch (LOG_ERROR_LEVEL)
	 	{
	 		case NO_ERROR         : $logMessage = ''; break;
	 		case SUMMARIZED_ERROR : $logMessage = $errorSummarize; break;
	 		case DETAIL_ERROR     : $logMessage = $errorSummarize . "\n" . $errorDetail; break;
	 		case EXTENSIVE_ERROR  : $logMessage = $errorSummarize . "\n"
	 		                                    . $errorDetail . "\n"
	 		                                    . $this->_getDebugBackTrace (); break;
	 		default               : $logMessage = ''; break;
	 	}
		if (LOG_ERROR_LEVEL > NO_ERROR)
		{
	 		$this->_writeError ($logMessage, $errorTitle);
		}
	 }
	 
	/**
	 * private : popup error
	 * @param  : error message
	 * @result : popup error
	 */
	 function _popupError ($errorMessage)
	 {
		$popupMessage = $errorMessage;
	 	echo '
			<script language="JavaScript" type="text/javascript">
				<!--
	 				alert("'.$popupMessage.'");
				//-->
			</script>
		';
	 }
	 
	 /**
	 * private : show error
	 * @param  : error message
	 * @result : show error
	 */
	 function _showError ($errorMessage, $errorTitle = "")
	 {
 		$errorTitle = (!empty($errorTitle) ? $errorTitle : "User Error");
	 		
	 	$errorMessage = nl2br($errorMessage);
	 	
	 	echo "
	 		<table width=\"100%\" style=\"border-collapsed:collapsed; border:1px solid #CCCCCC;\">
	 			<tr>
	 				<td style=\"color:red; font-size:12px; background-color:#EEEEEE; font-family:verdana, arial;\">$errorTitle</td>
	 			</tr>
	 			<tr>
	 				<td style=\"color:black; font-size:11px; font-family:verdana, arial;\">$errorMessage</td>
	 			</tr>
	 		</table>
	 	";
	 }
	 
	/**
	 * private : setting error log file
	 * @param  : error log file
	 */
	 function _setErrorLogFile ($file)
	 {
	 	if (!empty($file))
	 	{
	 		$this->_error_log_file = $file;
	 	}
		else
		{
			$this->_showError ("Undefined error log file");
		}
	 }
	 
	 /**
	 * private : write error to log
	 * @param  : error message
	 * @result : write error to log
	 */
	 function _writeError ($errorMessage, $errorTitle)
	 {
	 	$errorTitle   = (!empty($errorTitle) ? $errorTitle : "User Error");
	 	$errorLogFile = (!empty($this->_error_log_file) ? $this->_error_log_file : DEFAULT_ERROR_LOG_FILE);
	 	
	 	$errorMessages = "\n=================================================================\n";
        $errorMessages .=  "Date : ". date ("Y-m-d h:i:s") ." - ($errorTitle)\n";
        $errorMessages .= "User ID : ";
        if( isset($_SESSION['userId']) ? $_SESSION['userId'] : '' )
            $errorMessages .= $_SESSION['userId']."\n";
        else
            $errorMessages .= "[logged out]\n";
        $errorMessages .=  $errorMessage;
	 	
	 	$fp = @fopen ($errorLogFile, "a");
		if ($fp)
		{
			fwrite ($fp, $errorMessages);
			fclose ($fp);
		}
		else
			$this->_showError ("Cannot write log file: ". $errorLogFile);
	 }
	
	/**
	 * protected : write to log
	 * @param    : string
	 * @param    : log file
	 */	
	function _writeToLog ($string, $logFile)
	{
        if($_SESSION['userId'])
            $userId = $_SESSION['userId']."\n";
        else
            $userId = "[logged out]\n";

		$string = date ("Y-m-d h:i:s") . " User Id : ". $userId ." ". $string ."\n";
		$fp = @fopen ($logFile, "a");
		if ($fp)
		{
			fwrite ($fp, $string);
			fclose ($fp);	
		}
		else
			$this->_triggerError ("Cannot write log file: $logFile");
	}
	
	/**
	 * private : get debug caller
	 */
	function _getDebugBackTrace ()
	{
		$backtrace = debug_backtrace ();
		
		$error = "";
		for ($i = 1; $i < count($backtrace); $i++)
		{
			$error .= "[$i] ";
			
			if (!empty($backtrace[($i+1)]["class"]))
				$error .= $backtrace[($i+1)]["class"] ."::";
			
			if (!empty($backtrace[($i+1)]["function"]))
				$error .= $backtrace[($i+1)]["function"] ."() ";
			
			$error .= "called from ". $backtrace[$i]["file"] ."(". $backtrace[$i]["line"] .")\n";
			
		}
		
		return $error;
	
	}
	
	/**
	 * private : get module of object
	 * @param  : modulename
	 * @return : object of module
	 */
	 function _getModule ($moduleName)
	 {
	 	return common::getModule ($moduleName);
	 }
	
	/**
	 * public : manage error handling
	 * @param : ($errno, $errstr, $errfile, $errline)
	 */
	 function setErrorHandler ($errno, $errstr, $errfile, $errline, $errorLevel)
	 {
		switch ($errno) 
		{
			case E_ERROR:
				$errorMessage = "$errstr\n";
				$errorMessage .= "FATAL ERROR: on line $errline in file $errfile\n";
				$titleError = "Fatal Error [$errno]";
				exit(1);
			break;

			case E_WARNING:
				if (!preg_match ("/\^E_WARNING/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "WARNING ERROR: on line $errline in file $errfile\n";
					$titleError = "Warning Error [$errno]";
				}
			break;

			case E_NOTICE:
				if (!preg_match ("/\^E_NOTICE/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "NOTICE ERROR: on line $errline in file $errfile\n";
					$titleError = "Notice Error [$errno]";
				}
			break;
			
			case E_PARSE:
				if (!preg_match ("/\^E_PARSE/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "PARSE ERROR: on line $errline in file $errfile\n";
					$titleError = "Parse Error [$errno]";
				}
			break;
			
			case E_STRICT:
				if (!preg_match ("/\^E_STRICT/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "Strict ERROR: on line $errline in file $errfile\n";
					$titleError = "Strict Error [$errno]";
				}
			break;
			
			case E_STRICT:
				if (!preg_match ("/\^E_STRICT/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "Strict ERROR: on line $errline in file $errfile\n";
					$titleError = "Strict Error [$errno]";
				}
			break;
			
			default:
				if (!preg_match ("/\^E_UNKNOWN/", $errorLevel))
				{
					$errorMessage = "$errstr\n";
					$errorMessage .= "UNKNOWN ERROR: on line $errline in file $errfile\n";
					$titleError = "Unknown Error [$errno]";
				}
			break;
		}
	 	
	 	if ($errorLevel == "0")
	 		$errorMessage = "";
	 	
	 	if (!empty($errorMessage))
	 		$this->_triggerError ($errorMessage, $titleError);
	 }	
	 
}

?>