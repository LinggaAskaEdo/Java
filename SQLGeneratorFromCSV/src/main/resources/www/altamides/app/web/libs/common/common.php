<?php
/**********************************************
* Class/Module Name: common.php (was firstwap.php)
*
* Current Version: 1.0
*
* Date Created: 22 February 2007
*
* Created By: Lintaka
*
* Change History:
* Version Date Change  Person  Description of Change
*
* Description of class/Module
* Wrapper for firstwap
*
* @PHP4
***********************************************/
class common
{
	/**
	 * public  : get object module
	 * @param  : module name (firstwap.xml.parser)
	 * @return : object of module
	 */
	function getModule ($moduleName)
	{
		$location = dirname(__FILE__);
		$dirs = explode (".", $moduleName);
		for ($i = 1; $i < count($dirs); $i++)
		{
			$location .= "/". $dirs[$i];			
		}

		$className = $dirs[(count($dirs) - 1)];
		$location .= "/$className.php";
		
		if (is_file ($location))
		{
			require_once $location;
			return new $className;
		}
		else
		{
			trigger_error ("Cannot load module : $moduleName", E_USER_ERROR);
			return false;
		}
		
	}

}


?>
