<?php
/**
 * Class for handling multilingual text
 * @author Huripto Sugandi
 * @since SP8.1 (MOI Nov 2010)
 */
class language {
	/**
	 * The properties file path containing text for current languages
	 * @var string
	 */
    private $file;
    private $transitionalPage=null;

	/**
	 *
	 * @param string $lang Language code: en, ar, etc
	 * @param string $module Module name, e.g., fieldtrax, disc, sam, etc.
	 * @param string $type The file/dictionary name (without .ini), e.g., admin, geofence.setup
	 * @param string $appPath Application root path, currently ignored
	 *               in favor of ALTAMIDES_LANG_PATH constant
	 */
	public function __construct($lang, $module, $type, $appPath=null) {
		$language = empty($lang) ? 'en' : $lang;

		$this->file = rtrim(ALTAMIDES_LANG_PATH, '/') . '/'. $language . '/' . $module . '/' . $type . '.ini';
	}

	/**
	 * Get the mapping from string label to the corresponding text for specified language and module
	 * @return array string to text map, or FALSE when fail
	 */
	public function setText() {
		if(file_exists($this->file)) {
			return parse_ini_file($this->file, 0);
		} else {
			return false;
		}
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