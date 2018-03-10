<?php
require_once dirname(dirname(dirname(__FILE__))).'/sso_client/interceptor.php';
require_once ALTAMIDES_LIB_PATH . 'config/AltamidesConfig.php';
require_once ALTAMIDES_CONFIG_PATH.'config.menu.php';

/**
 * Controller for menu
 */
class AltamidesPageMenu{
	/**
	 * Current menu list
	 * @var array
	 */
	private static $menuGroup=array();
	/**
	 * Selected menu
	 * @var string
	 */
	private static $activeMenuID=null;
	/**
	 * Cache for privilege checking
	 * @var array
	 */
	private static $privilegeCache=array();
	private static $loginStatusCache=null;

	/**
	 * Load menu definition
	 * @param string $groupName Menu group name,as in the $menu['groups'] settings
	 */
	public static function load($groupName){
		if(empty($groupName)){
			trigger_error('Empty menu set name.', E_USER_NOTICE);
			return;
		}

		if(!isset(AltamidesConfig::$menu['groups'][$groupName])){
			trigger_error('Menu set was not configured.', E_USER_NOTICE);
			return;
		}

		$group = AltamidesConfig::$menu['groups'][$groupName];
		$tree = AltamidesConfig::$menu['tree'];
		$extraUrl = '?logo='.urlencode(AltamidesPageBanner::guessCurrentModule());

		$menu = array();
        foreach($group as $itemID=>$itemPath){
			if(count($itemPath) != 2){
				trigger_error("Invalid menu group configuration for item '$itemID'", E_USER_WARNING);
				continue;
			}

			list ($moduleID, $pageID) = $itemPath;

			if(!isset($tree[$moduleID]))
				continue;//invalid

			$module = $tree[$moduleID];

			if(!empty($module['disabled']) || empty($module['pages'][$pageID]))
				continue;

			$page = $module['pages'][$pageID];
            $pageAddr = explode("?",$page['address']);
            $page['address'] = $pageAddr[0];
			if(empty($page['privileges']) || self::cacheCheckPrivilege($page)){
//                // see parseBracket()
//                $parsedBraced = self::parseBracket($tree, $page['address']);
//                if(!$parsedBraced) {
//                    $address = $module['baseUrl'].$page['address'];
//                } else {
//                    $address = $parsedBraced['baseUrl'].$parsedBraced['address'];
//                }
                if(isset($page['isAbsolute']) && $page['isAbsolute']) {
                    $address = $page['address'];
                } else {
                    $address = $module['baseUrl'].$page['address'];
                }
                
				$menu[$itemID] = array(
					'caption' => $page['caption'],
					'address' => $address.$extraUrl
				);
			}
		}

		self::$menuGroup = $menu;
	}

    /**
     * parseBracket()
     * to parse bracket style config and return the value string.
     *
     * @param <type> $menuTree      The array in which the key-value is stored
     * @param <type> $bracketKey    The key/path to the value in the array
     * @return <type> $menuOptions  Associate array
     */
    public static function parseBracket($menuTree, $bracketKey) {
        //$square_bracket_regex_pattern = "/\[(.+?)\]/";
        //$square_bracket_regex_pattern = "/[^\[]*(\[.*\])[^\]]*/";
        $square_bracket_regex_pattern = "/((?<=(?:[(]))[^(].*?(?=[)]))/";
        $menuOptions = array();

        $result = preg_match_all($square_bracket_regex_pattern, $bracketKey, $brackets);
        $address = "";
        if($result) {
            foreach($brackets[1] as $bracket) {
                list($key, $value) = explode("=", $bracket);
                $menuOptions[$key] = self::getArrayValue($menuTree, $value );
            }
            return $menuOptions;
        } else {
            return false;
        }
    }

    /**
     * Get array value with a string contains square brackets
     * ex. accessing $someArray[dim.1][dim.1.2] with
     *      getArrayValue($someArray, "[dim.1][dim.1.2]");
     *
     * @param <type> $array
     * @param <type> $string
     * @return <type>
     */
    public static function getArrayValue($menuTree, $string) {
        $indices = preg_split('/[][]+/', $string, -1, PREG_SPLIT_NO_EMPTY);

        foreach ($indices as $index)
            $menuTree = $menuTree[$index];

        return $menuTree;
    }

	/**
	 * activate selection on a menu item
	 * @param string $menuID The menu id
	 */
	public static function activate($menuID){
		self::$activeMenuID = $menuID;
	}
	/**
	 * Get current menu list
	 * @return array
	 */
	public static function getMenuSet(){
		return self::$menuGroup;
	}
	/**
	 * Get selected menu item
	 */
	public static function getActiveItem(){
		if(!isset(self::$menuGroup[self::$activeMenuID])){
			trigger_error('Menu item with ID "'.self::$activeMenuSetID.'" is not found', E_USER_NOTICE);
			return null;
		}
		return self::$menuGroup[self::$activeMenuSetID][self::$activeMenuID];
	}
	/**
	 * Get selected menu item id
	 * @return string
	 */
	public static function getActiveItemID(){
		return self::$activeMenuID;
	}
        
        public static function getModule($moduleName) {
            
            if(!isset(AltamidesConfig::$menu['tree'][$moduleName]))
                    return false;

            return AltamidesConfig::$menu['tree'][$moduleName]; 

        }

        /**
	 * Return the entry page for a module by searching based on the priority
	 * in the menu configuration.
	 * If user has not yet logged in then the first page of the module will be used
	 *
	 * @param string $moduleName Module id as in the $menu['tree'] configuration
	 * @return array Menu page url or FALSE when there's no accessible page
	 */
	public static function getModuleEntryPage($moduleName){
		if(!isset(AltamidesConfig::$menu['tree'][$moduleName]))
			return false;
                
		$module = AltamidesConfig::$menu['tree'][$moduleName]; //dereference

		if(!empty($module['disabled']) || empty($module['pages']))
			return false;

		/*
		 * Since we don't know the rights of not logged in user
		 * For not logged in user use first page as entry page
		 */
		if(!self::cacheCheckIsLogin())
			return reset($module['pages']);

		/*
		 * First accessible page is the entry page
		 */
//		foreach($module['pages'] as $pageName => $page){
		foreach($module['pages'] as $page){
			if(empty($page['privileges']) || self::cacheCheckPrivilege($page)){
//				error_log('['.__METHOD__."] {$page['caption']} | OK : PRIV: ".print_r($page['privileges'], true));
				return $page;
			}else{
//				error_log('['.__METHOD__."] $pageName | {$page['privileges']} > DENIED");
			}
		}

		return false;
	}

	/**
	 *
	 * @param string $moduleName
	 * @return array
	 */
	public static function getModulePages($moduleName){
//		error_log('['.__METHOD__."] $moduleName");
		/**
		 * Module not found
		 */
		if(empty(AltamidesConfig::$menu['tree'][$moduleName]))
			return array();

		$module = AltamidesConfig::$menu['tree'][$moduleName]; //deref
		/*
		 * No pages found
		 */
		if(!empty($module['disabled']) || empty($module['pages']))
			return array();

		$pages = array();
		/*
		 * Show only unprrivileged or access-granted pages
		 */
		foreach($module['pages'] as $pageName => $pageSettings){
			if(empty($pageSettings['privileges']) || self::cacheCheckPrivilege($pageSettings)){
//				error_log('['.__METHOD__."] $pageName | {$pageSettings['privileges']} > OK");
				$pages[$pageName] = $pageSettings;
//			}else{
//				error_log('['.__METHOD__."] $pageName | {$pageSettings['privileges']} > DENIED");
			}
		}

		return $pages;
	}

	/**
	 *
	 * @return bool
	 */
	private static function cacheCheckIsLogin(){
		/*
		 * Cache miss
		 */
		if(self::$loginStatusCache === null)
			self::$loginStatusCache = (checkExpired() == 1);
		return self::$loginStatusCache;
	}

	/**
	 *
	 * @param mixed $privilege
	 * @return bool
	 */
	private static function cacheCheckPrivilege($page){
		/**
		 * Check login
		 */
		if(!self::cacheCheckIsLogin()){
			return false;
		}
		
		$privilege = $page['privileges'];
		$superadminExclusive = !empty($page['superadminExclusive']);

		if(!is_array($privilege)){
			$privilege = (string) $privilege;
			if(isset(self::$privilegeCache[$privilege])){
				return self::$privilegeCache[$privilege];
			}
			self::$privilegeCache[$privilege] =	(bool) filterPage($privilege, false, $superadminExclusive);
			return self::$privilegeCache[$privilege];
		}

		/*
		 * If given array of privileges
		 */
		foreach($privilege as $privilegeName){
			if(isset(self::$privilegeCache[$privilegeName])){
				if(self::$privilegeCache[$privilegeName])
					return true;
			}
			self::$privilegeCache[$privilegeName] = (bool) filterPage($privilegeName, false, $superadminExclusive);
			if(self::$privilegeCache[$privilegeName])
				return true;
		}

		return false;
	}

	/**
	 *
	 */
	private static function resetPrivilegeCache(){
		self::$privilegeCache = array();
	}

	private static function resetLoginStatusCache(){
		self::$loginStatusCache = null;
	}

}

/**
 * View class for altamides page menu
 */
class AltamidesPageMenuDisplay{
	private $template=null;
	private $quickMenuTemplate=null;
	/**
	 * constructor
	 * @param string $template If this param is not empty set the menu template
	 */
	public function  __construct($template=null) {
		if($template)
			$this->setTemplate($template);
	}
	/**
	 * Set the menu display template, the template should be an php file.
	 * @param string $template template file
	 */
	public function setTemplate($template){
		if(!is_readable($template)){
			trigger_error('Menu template "'.$template.'" is not readable!',E_USER_WARNING);
			return;
		}
		$this->template = $template;
	}

	/**
	 * Set template for quick menu
	 */
	public function setQuickMenuTemplate($template){
		if(!is_readable($template)){
			trigger_error('Menu template "'.$template.'" is not readable!',E_USER_WARNING);
			return;
		}
		$this->quickMenuTemplate = $template;
	}

	/**
	 * Display the menu.
	 * Following variables will be passed to the template.
	 * - $menuSet Array of the menu set
	 * - $activeMenuID Active menu item ID
	 */
	public function  display() {
		$menuSet = AltamidesPageMenu::getMenuSet();
		$activeMenuID = AltamidesPageMenu::getActiveItemID();
		if($this->template !== null)
			include $this->template;
	}

	/**
	 * Generate quick jump menu html
	 * @param string $_elementID ID attribute for the container element id
	 */
	public function  generateQuickMenu($_elementID='') {
		$_elementID = trim($_elementID);
		if($this->quickMenuTemplate !== null)
			include $this->quickMenuTemplate;
	}
}
/**
 * Altamides banner control
 */
class AltamidesPageBanner{
	private static $defaultModuleEnforced=false;
	private static $defaultModule='';
	private static $moduleImages=array();
	/**
	 * Set default module id
	 * @param string $banner default banner name
	 * @param bool $enforce Enforce the banner regardless of GET/POST/SESSION variables
	 */
	public static function setDefaultModule($banner,$enforce=true){
		self::$defaultModule = $banner;
		if($enforce) $_SESSION['logo']=$banner;
		self::$defaultModuleEnforced=$enforce;
	}

	public static function getDefaultModule(){
		return self::$defaultModule;
	}

	public static function getModuleImage(){
		$module = self::guessCurrentModule();
		$moduleImage = isset(self::$moduleImages[$module])?
				self::$moduleImages[$module] : '';
		if(strlen($moduleImage)<1)
			$moduleImage = @self::$moduleImages[self::getDefaultModule()];
		return $moduleImage;
	}
	/**
	 * Guess current module based on priorities :
	 * 1. Enforced module
	 * 2. GET
	 * 3. POST
	 * 4. SESSION
	 * 5. Default module
	 * Any of those alternatives failed, the defaule will be used
	 * @return string
	 */
	public static function guessCurrentModule(){
		if(self::$defaultModuleEnforced){
			$module = self::getDefaultModule();
		}elseif(!empty($_GET['logo'])){
			$module = $_SESSION['logo'] = urldecode($_GET['logo']);
		}elseif(!empty($_POST['logo'])){
			$module = $_SESSION['logo'] = $_POST['logo'];
		}elseif(!empty($_SESSION['logo'])){
			$module = $_SESSION['logo'];
		}else{
			$module = self::getDefaultModule();
		}
		return empty($module)? self::getDefaultModule() : $module;
	}
	/**
	 * Register images for banner
	 * @param array $images
	 */
	public static function registerModuleImage(array $images){
		self::$moduleImages = $images;
	}
}