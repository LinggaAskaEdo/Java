<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : TransitionalPage.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-30
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class TransitionalPage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-08-30   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Controller;

/**
 * Description of TransitionalPage
 *
 * @author Setia Budi Halim
 */
class TransitionalPage
{
    protected $view;
    protected $message;
    
    public function __construct($view)
    {
        $this->view = $view;
    }
    
    public function setMessage($message)
    {
        
    }

    public function render($param)
    {
        
    }

    public function setTransitionalPage($path=null){
		$this->transitionalPage = $path;
	}

	/**
	 * Generate a transitional page (commonly used in Altamides form post processing)
	 * The page will then redirect to a specified URL after optionally
	 * showing a message box
	 * This method will end script processing after generating the page
	 *
	 * @param string $_url destination url
	 * @param string $_message the message string
	 * @see language::generateNotification()
	 *
	 */
	public function generateTransitionalPage($_url, $_message=''){
		while(ob_get_status ()){ //clean previous buffer
			ob_end_clean();
		}
		include $this->transitionalPage===null?
			(ALTAMIDES_BASE_PATH.'transition.php') :
			$this->transitionalPage;
		exit;
	}
	/**
	 * Similiar like generateNotification() with addition that
	 * the generated script will be wrapped in <script> tag
	 * @param string $message The message to show
	 * @param string $url URL redirect to URL
	 * @see language::generateNotification()
	 * @return string Generated script
	 */
	public function generateNotificationScript($message,$url=null){
		$script = "<script type=\"text/javascript\">//<![CDATA[\n";
		$script .= $this->generateNotification($message, $url);
		$script .= "\n//]]></script>";
		return $script;
	}

	/**
	 * Generate javascript code which will show message and redirect client
	 * page to another url
	 *
	 * @param string $message The message to show
	 *                        Leaving it empty wont show any message
	 * @param string $url Destination URL, set it null to disable redirection
	 * @return string Generated javascript code
	 */
	public function generateNotification($message,$url=null){
		$emptyMessage = $message == '';
		$encodedMessage = json_encode($message);
		if($url !== null){
			$url = json_encode($url);
			return $emptyMessage?
				"location.href =$url;":
				"showAlertWithAction($encodedMessage, function(){location.href =$url;});";
		}else{
			return "showAlert($encodedMessage);";
		}
	}
}