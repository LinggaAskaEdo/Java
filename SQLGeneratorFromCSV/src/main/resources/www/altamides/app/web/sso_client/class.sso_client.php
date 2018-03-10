<?php
/**********************************************
* Copyright:    2012 1rstWAP
* System Name:  Altamides
* Version:      2.0 SP15
* Module Name:  SSO
* File Name:    class.sso_client.php
* File Version: 2.000.010
* Date Created: 10 March 2011
* Author:       Huripto Sugandi
*
* Change Log:
* Date Change  Person  Description of Change
* 2 August 2012, Ivana, Add getCurrentModule function and clean script on comment
*
* Inputs
*
* Outputs
*
* Globals changed
*
* Description
* This module used for checking about user log, user previlege, page referer.
**********************************************/

if(!session_id()) {
	session_start();
}

require_once dirname(__DIR__).'/boot/init-web.php';
require_once ALTAMIDES_ADODB_DIR . '/adodb.php';

use Firstwap\Altamides\Admin\Auth\AdminPrivilege;

class sso_client {
    private $dbConnProfile;

    public function __construct() {

        $this->dbConnProfile = array(
            "dbHost" => REF_DB_HOST,
            "dbUser" => REF_DB_USER,
            "dbPswd" => REF_DB_PASS,
            "dbDriver" => REF_DB_TYPE,
            "dbName" => ""
        );
    }

    /**
     * Check whether a privilege is granted for current session. If user does not
     * have the privilege and autoRedirect option is set, the client/user 
     * will be redirected to access-denied page
     * 
     * See ALTAMIDES_SSO_LIMITED_SUPERADMIN
     * @param type $privilege Privilege name
     * @param type $autoRedirect When true and user do not have the privilege user will be redirected to access-denied page
     * @param type $okForLimitedSuperAdmin Whether the checking is done for SSO-superadmin-exclusive page
     * 
     * @return boolean true/false depends whether user has privilege
     */
    public function filterPage($privilege, $autoRedirect = true, $okForLimitedSuperAdmin = false) {

        $this->checkSessionExist();

        $isLimitedSuperadmin = ALTAMIDES_SSO_LIMITED_SUPERADMIN;
        
        $isSuperadmin = $this->checkPrivileges(AdminPrivilege::ADM_ACC_GLOBAL) && 
                ($this->checkPrivileges(AdminPrivilege::ADM_GROUP_ADMIN_WRITE) || 
                 $this->checkPrivileges(AdminPrivilege::ADM_ROLES_WRITE) || 
                 $this->checkPrivileges(AdminPrivilege::ADM_USER_ADMIN_WRITE)
                );
        
        // if global config for limited superadmin: TRUE
        //    user has SSO_SuperAdmin privilege: TRUE
        //    accessed page: FALSE
        // then DENIED
        if(strtolower($isLimitedSuperadmin) == 'true' && $isSuperadmin && !$okForLimitedSuperAdmin){

            if (!$autoRedirect) {
                return false;
            }
             $referer = $this->curPageURL();
             // redirect to access denied page
            $targetURL = SSO_ACCESS_DENIED_URL;
            $referer .= '&module='.$this->getCurrentModule($referer).'';
            $this->openPage($targetURL, $referer);
            return;
        }
        
        // if global config for limited superadmin: TRUE
        //    user has SSO_SuperAdmin privilege: TRUE
        //    accessed page: TRUE
        // then keep login or login again. We don't care about the page privilege
        if($isLimitedSuperadmin && $isSuperadmin && $okForLimitedSuperAdmin){
            $checkExpired = $this->checkExpirationPage();
            if ($checkExpired==0){
                $this->keepLoginAlive();
                return true;
            }
            if (!$autoRedirect) {
                return false;
            }
                $referer = $this->curPageURL();
                $targetURL = SSO_LOGIN_SERVICE_URL;
                $referer .= '&module='.$this->getCurrentModule($referer).'';
                $this->openPage($targetURL, $referer);
                return;
        }
        
        if($this->checkPrivileges($privilege)) {
            $checkExpired = $this->checkExpirationPage();
            if ($checkExpired==0){
                $this->keepLoginAlive();
                return true;
            }
            if (!$autoRedirect) {
                return false;
            }
                $referer = $this->curPageURL();
                $targetURL = SSO_LOGIN_SERVICE_URL;
                $referer .= '&module='.$this->getCurrentModule($referer).'';
                $this->openPage($targetURL, $referer);
                return;
        } else {
            if($autoRedirect) {
                $referer = $this->curPageURL();
                if($this->isLoggedIn() == false) {
                    //redirect to login page
                    $targetURL = SSO_LOGIN_SERVICE_URL;
                } else {
                    // redirect to access denied page
                    $targetURL = SSO_ACCESS_DENIED_URL;
                }
                $referer .= '&module='.$this->getCurrentModule($referer).'';
                $this->openPage($targetURL, $referer);
                return;
            } else {
                return false;
            }
        }
    }
    
    // redirect to login page or access denied page
    public function redirectPage(){
        $referer = $this->curPageURL();
        if($this->isLoggedIn() == false) {
            //redirect to login page
            $targetURL = SSO_LOGIN_SERVICE_URL;
        } else {
            // redirect to access denied page
            $targetURL = SSO_ACCESS_DENIED_URL;
        }
        $referer .= '&module='.$this->getCurrentModule($referer).'';
        $this->openPage($targetURL, $referer);
    }

    //check referer for getting module name.
    public function getCurrentModule($referer){
        if (false !== strpos($referer, PHP_ZONETRAX_DIR)) {
            return "ZoneTrax";
        } elseif (false !== strpos($referer, PHP_FIELDTRAX_DIR)) {
            if (strpos($referer, '/whitelist.')) {
                return PROFILETRAX_ENABLED ? "ProfileTrax" : "FieldTrax";
            }elseif (strpos($referer, '/redlist.')) {
                return PROFILETRAX_ENABLED ? "ProfileTrax" : "FieldTrax";
            }elseif (strpos($referer, '/blacklist.')) {
                return PROFILETRAX_ENABLED ? "ProfileTrax" : "FieldTrax";
            }else{
                return "FieldTrax";
            }
        } elseif (false !== strpos($referer, PHP_MAPTRAX_DIR)) {
            return "MapTrax";
        } elseif (false !== strpos($referer, PHP_PROXIMITRAX_DIR)) {
            return "ProximiTrax";
        } elseif (false !== strpos($referer, PHP_OMNITRAX_DIR)) {
            if (strpos($referer, 'sysadm')) {
                return "MapTrax";
            } elseif (strpos($referer, 'ptrax')) {
                return "ProximiTrax";
            } elseif (strpos($referer, 'geofence')) {
                return "ZoneTrax";
            } elseif (strpos($referer, 'fence')) {
                return "ZipTrax";
            } elseif (strpos($referer, '/vehicle.')) {
                return PROFILETRAX_ENABLED ? "ProfileTrax" : "OmniTrax";
            } elseif (strpos($referer, '/user.')) {
                return PROFILETRAX_ENABLED ? "ProfileTrax" : "OmniTrax";
            } else {
                return "OmniTrax";
            }
        } elseif (false !== strpos($referer, PHP_DISC_DIR)) {
            return "DisC";
        } elseif (false !== strpos($referer, PHP_TELCOTRAX_DIR)) {
            return "TelcoTrax";
        } elseif (false !== strpos($referer, PHP_SAM_DIR)) {
            return "SAM";
        } elseif (false !== strpos($referer, JAVA_RAPIDTRAX_URL) || false !== strpos($referer, PHP_RAPIDTRAX_URL)) {
            return "RapidTrax";
        } elseif (false !== strpos($referer, SSO_HOME_URL)) {
            return "ADM";
        } elseif (false !== strpos($referer, PHP_AUDITTRAX_URL)) {
            return "AuditTrax";
        } elseif (false !== strpos($referer, PHP_BOMBING_URL)) {
            return "JamTrax";
        } elseif (false !== strpos($referer, PHP_PROFILETRAX_URL)) {
            return "ProfileTrax";
        } elseif (false !== strpos($referer, PHP_SPOTTRAX_URL)) {
            return "SpotTrax";
        } elseif (false !== strpos($referer, PHP_DOC_URL)) {
            return "Doc";
        } else {
            return "Unknown";
        }
    }
    
    public function openPage($targetURL,$referer = "") {
        if($referer) {
            $referer = '?referer='.$referer;
        }
        @header('Location: '.$targetURL.$referer);
        exit();
    }

    public function checkPrivileges($privilege) {
        if(isset($_SESSION['privileges']) && $_SESSION['privileges']) {
            if(in_array($privilege,$_SESSION['privileges'])) {
                return true;
            }
        }
        return false;
    }
    
    public function checkExpirationPage () {
        $this->dbConnProfile["dbName"] = REF_DB_NAME;
        $dbConn = new adodb($this->dbConnProfile,true);
        
        if($this->isLoggedIn()) {
            $currentIp = $this->getIp();
            $userId = $_SESSION['userId'];
            $userTokenId = $_SESSION['tokenId'];
            
            $query = "select EXPIRED
                      from ".REF_DB_NAME.".USER_LOG
                      where IP = ".$dbConn->qstr($currentIp)." and USER_ID = ".$dbConn->qstr($userId)." and TOKEN = ".$dbConn->qstr($userTokenId);
            try {
                $checkExpired = $dbConn->GetRow($query);
                return isset($checkExpired['EXPIRED'])?$checkExpired['EXPIRED']:1;
            } catch (Exception $e) {
                trigger_error($e, E_USER_WARNING);
                return false;
            }
        }
    }
    
    public function checkExpiration () {
        $this->dbConnProfile["dbName"] = REF_DB_NAME;
        $dbConn = new adodb($this->dbConnProfile,true);
        
        if($this->isLoggedIn()) {
            $currentIp = $this->getIp();
            $userId = $_SESSION['userId'];
            $userTokenId = $_SESSION['tokenId'];
            
            $expiredTime="";
            if (defined('SSO_CLIENT_EXPIRED_TIME') && SSO_CLIENT_EXPIRED_TIME !=""){
                $expiredTime=SSO_CLIENT_EXPIRED_TIME;
            }
            else{
                trigger_error("SSO_CLIENT_EXPIRED_TIME in config.global.php is unset",E_USER_WARNING);
                header("location:". PHP_LOGOUT_URL . "?referer=" . PHP_BASE_URL);
                return false;
            }
            
            $query = 'select USER_ID, EXPIRED
                        from '.REF_DB_NAME.'.USER_LOG
                        where IP = ? and USER_ID = ? and TOKEN = ? and (NOW() < (DATE_ADD(LAST_ATTEMPT, INTERVAL '.$expiredTime.' SECOND)))';
            try {
                $userLogCheckResult = $dbConn->GetRow($query,array($currentIp, $userId, $userTokenId));
				
            } catch (Exception $e) {
                trigger_error($e, E_USER_WARNING);
                return false;
            }
            
            if($userLogCheckResult){
				//print_r($userLogCheckResult);
				if(ord($userLogCheckResult['EXPIRED']) != 1 || $userLogCheckResult['EXPIRED'] == "") {
					if($userId != "") {
						return true;
					} else {
						//echo "user id empty";
					}
				} else {
					//echo "filled expired";
				}
            }
                
        } else {
            //echo "session user id empty";
        }
        
        $query = "update USER_LOG set
            EXPIRED = 1
            where IP = ? and USER_ID = ? and TOKEN = ?";
        try {
            $dbConn->Execute($query,array(isset($currentIp) ? $currentIp : '', isset($userId) ? $userId : '', isset($userTokenId) ? $userTokenId : ''));
            $dbConn->close();

        } catch(Exception $e) {
            trigger_error($e, E_USER_WARNING);
            return false;
        }
        return false;
    }

    /**
     * if session destroyed but token not expired
     * redirect to tokenReceiver to create new session
     */
    public function checkSessionExist(){

        // restore language and direction session if not exist
        if (empty($_SESSION["ftrack_lang"])) {
            $_SESSION["ftrack_lang"] = 'en';
        }

        if (empty($_SESSION["htmlDirection"])) {
            $_SESSION["htmlDirection"] = 'ltr';
        }
        
        //  check the session still available or not
        if (isset($_COOKIE['ams_cookies']) && !isset($_SESSION['userid'])){
            unset($_COOKIE['ams_cookies']);
        }

        if (isset($_SESSION) && isset($_COOKIE['ams_cookies']) && isset($_COOKIE['userId']) && !isset($_SESSION['tokenId'])){

            $this->dbConnProfile["dbName"] = REF_DB_NAME;
            $dbConn = new adodb($this->dbConnProfile, true);

            $currentIp = $this->getIp();
            $userId = $_COOKIE['userId'];
            $userTokenId = $_COOKIE['tokenId'];
            $client_id = $_COOKIE['clientId'];
            $timezone = $_COOKIE['clientTimezone'];
            $referer = $this->curPageURL();

            $expiredTime = "";
            if (defined('SSO_CLIENT_EXPIRED_TIME') && SSO_CLIENT_EXPIRED_TIME != ""){
                $expiredTime = SSO_CLIENT_EXPIRED_TIME;
            }
            else{
                error_log("SSO_CLIENT_EXPIRED_TIME in config.global.php is unset", E_USER_WARNING);
                $this->openPage(PHP_LOGOUT_URL, PHP_BASE_URL);
                return;
            }

            $query = 'SELECT USER_ID, EXPIRED, LAST_ATTEMPT, NOW() as NOW
                        FROM '.REF_DB_NAME.'.USER_LOG
                        WHERE IP = ? AND USER_ID = ? AND TOKEN = ?';

            try {
                $userLog = $dbConn->GetRow($query, array($currentIp, $userId, $userTokenId));
                if ($userLog){
                    if ($userLog['EXPIRED'] != 1 || $userLog['EXPIRED'] == ""){

                        $now = $userLog['NOW'];
                        $expiredTime = date('Y-m-d H:i:s', strtotime('+'.$expiredTime.' seconds', strtotime($userLog['LAST_ATTEMPT'])));

                        // logout if token has expired
                        if ($now > $expiredTime){
                            $this->openPage(PHP_LOGOUT_URL, PHP_BASE_URL);
                            return;
                        }

                        $locale = isset($_COOKIE['locale']) ? $_COOKIE['locale'] : 'en';
                        $direction = isset($_COOKIE['direction']) ? $_COOKIE['direction'] : 'ltr';

                        $redirectUrl = PHP_SSO_CLIENT_URL. '/tokenReceiver.php' . "?";
                        $redirectUrl.= "userId=" . $userId . "&";
                        $redirectUrl.= "tokenId=" . $userTokenId . "&";
                        $redirectUrl.= "clientId=" . $client_id . "&";
                        $redirectUrl.= "browserStatus=0&";
                        $redirectUrl.= "isMobileBrowser=0&";
                        $redirectUrl.= "locale=" . $locale . "&";
                        $redirectUrl.= "direction=" . $direction . "&";
                        $redirectUrl.= "clientTimezone=" . $timezone . "&";
                        $redirectUrl.= "referer=". $referer;

                        // redirect to tokenreceiver
                        $this->openPage($redirectUrl);
                    } else {
                        return;
                    }
                }

            } catch (Exception $e) {
                error_log($e, E_USER_WARNING);
                return;
            }
        }

        return;
    }
                
    public function keepLoginAlive() {
        $this->dbConnProfile["dbName"] = REF_DB_NAME;

        $dbConn = new adodb($this->dbConnProfile,true);

        $currentIp = $this->getIp();
        $userId = $_SESSION['userId'];
        $userTokenId = $_SESSION['tokenId'];

        $query = "update USER_LOG set
            LAST_ATTEMPT = NOW()
            where IP = ? and USER_ID = ? and TOKEN = ?";
        try {
            $dbConn->Execute($query,array($currentIp, $userId, $userTokenId));
            $dbConn->close();
        } catch(Exception $e) {
            //echo "Failed updating last attempt field";
            return false;
        }
    }

    public static function isLoggedIn() {
        if(isset($_SESSION['userId']) && $_SESSION['userId']) {
            //echo "masuk userId";
            return true;
        } else {
            //echo "ga masuk userId";
            return false;
        }
    }

    public function ssoCheckServerStatus() {
        $parameters = "";
        $reply = $this->do_post_request(SSO_EXPIRED_SERVICE_URL, $parameters);
        if (trim($reply) == "")
            return false;
        return true;// depends on validiy of xml response
    }

    public function savePrivileges($tokenId) {
        $parameters = "ip=" . $this->getIp() . "&tokenId=" . $tokenId;
        $xmlResponse = $this->do_post_request(SSO_PRIVILEGE_SERVICE_URL, $parameters);        

        if($xmlResponse == "" || $xmlResponse == "Failed") {
            $this->openPage(SSO_INTERNAL_ERROR_URL, "");
            return false;
        }
        
        if(!$xmlResponse) return false;
        $xmlString = @simplexml_load_string($xmlResponse);
        if(!$xmlString) return false;
        $code = $xmlString->responses->status[0]->code;
        $message = $xmlString->responses->status[0]->message;
        $privileges = $xmlString->responses->status[0]->privileges;

        if($privileges) {
            $_SESSION["privileges"] = explode(SSO_PRIVILEGES_DELIMITER,$privileges);
        }
    }
    
    /*
     * Get Admin configuration from backend then save to session
     */
    public function getAdminConfigs()
    {
        $keylist = array("sso.superadmin.limited", "user.and.target.msisdn.duplication.checking", "absolute.group.separation");
        $parameters = array(
            'module' => 'ADMIN',
            'keys' => $keylist,
        );

        $response = $this->do_post_request(ALTAMIDES_CONFIG_SERVICE_URL_MULTIVALUE,
                $parameters, "Content-type: application/json");

        if (!$response || $response == 'FAILED') {
            return false;
        }

        $result = json_decode($response);
        $configs = array();
        if (isset($result->data) && count($result->data) > 0) {
            foreach ($result->data->keys as $value) {
                $configs[$value->key] = $value->value;
            }
        }

        if ($configs) {
            $_SESSION['configs'] = $configs;
        }
    }

    /* Get global configuration */
    public function getGlobalConfigs()
    {
        $keylist = array("altamides.map.api", "dashboard.time.format", "dashboard.timezone","zoom.ha.on.lac");
        $parameters = array(
            'module' => 'GLOBAL',
            'keys' => $keylist,
        );

        $response = $this->do_post_request(ALTAMIDES_CONFIG_SERVICE_URL_MULTIVALUE,
                $parameters, "Content-type: application/json");

        if (!$response || $response == 'FAILED') {
            return false;
        }

        $result = json_decode($response);

        foreach ($result->data->keys as $value) {
            $configs[$value->key] = $value->value;
        }

        if ($configs) {
            $_SESSION['configs']['ALTAMIDES_MAP'] = $configs['altamides.map.api'];
            $_SESSION['configs']['DASHBOARD_TIME_FORMAT'] = $configs['dashboard.time.format'];
            $_SESSION['configs']['DASHBOARD_TIMEZONE'] = $configs['dashboard.timezone'];
            $_SESSION['configs']['ZOOM_HA_ON_LAC'] = $configs['zoom.ha.on.lac'];
        }
    }

    /* Get omnitrax configuration */
    public function getOmnitraxConfigs()
    {
        $keylist = array("query.per.target");
        $parameters = array(
            'module' => 'OMNITRAX',
            'keys' => $keylist,
        );

        $response = $this->do_post_request(ALTAMIDES_CONFIG_SERVICE_URL_MULTIVALUE,
                $parameters, "Content-type: application/json");

        if (!$response || $response == 'FAILED') {
            return false;
        }

        $result = json_decode($response);

        foreach ($result->data->keys as $value) {
            $configs[$value->key] = $value->value;
        }

        if ($configs) {
            $_SESSION['configs']['QUERY_PER_TARGET'] = $configs[$value->key];
        }
    }
    
    /* 
     * Get fasttrax configuration
     */   
    public function getFasttraxConfigs()
    {
        $keylist = array("enable.ha.oss.tracing.request","rapidtrax.recentqueries.limit");
        $parameters = array(
            'module' => 'FASTTRAX',
            'keys' => $keylist,
        );

        $response = $this->do_post_request(ALTAMIDES_CONFIG_SERVICE_URL_MULTIVALUE,
                $parameters, "Content-type: application/json");
        
        if (!$response || $response == 'FAILED') {
            return false;
        }

        $result = json_decode($response);

        foreach ($result->data->keys as $value) {
            $configs[$value->key] = $value->value;
        }

        if ($configs) {
            $_SESSION['configs']['OSS_HA_ENABLE'] = $configs['enable.ha.oss.tracing.request'];
            $_SESSION['configs']['RT_RECENTCQUERY_LIMIT'] = $configs['rapidtrax.recentqueries.limit'];
        }
    }

    public function setCookie($cookieName,$cookieValue,$cookieExpire,$cookiePath) {
        setcookie($cookieName, $cookieValue, $cookieExpire, $cookiePath);
    }

    public function setSession($sessionName,$sessionValue) {
        $_SESSION[$sessionName] = $sessionValue;
    }

    /**
     * Get User IP
     * @return string 
     */
    public function getIp() {
        if (defined('OVERWRITE_REFERER_IP')){
            $overwrite = trim(OVERWRITE_REFERER_IP);
            if(isset($overwrite[0]))//fast version of strlen > 0
                return $overwrite;
        }
        if (isset($_SERVER["HTTP_CLIENT_IP"]) && $this->validip($_SERVER["HTTP_CLIENT_IP"])) {
            return $_SERVER["HTTP_CLIENT_IP"];
        }
        if(isset($_SERVER["HTTP_X_FORWARDED_FOR"])){
            foreach (explode(",", $_SERVER["HTTP_X_FORWARDED_FOR"]) as $ip) {
                if ($this->validip(trim($ip))) return $ip;
            }
        }
        if (isset($_SERVER["HTTP_X_FORWARDED"]) && $this->validip($_SERVER["HTTP_X_FORWARDED"]))
            return $_SERVER["HTTP_X_FORWARDED"];
        if (isset($_SERVER["HTTP_FORWARDED_FOR"]) && $this->validip($_SERVER["HTTP_FORWARDED_FOR"]))
            return $_SERVER["HTTP_FORWARDED_FOR"];
        if (isset($_SERVER["HTTP_FORWARDED"]) && $this->validip($_SERVER["HTTP_FORWARDED"]))
            return $_SERVER["HTTP_FORWARDED"];
        return $_SERVER["REMOTE_ADDR"];
    }
    
    /**
     *
     * @staticvar array $blockedIPList Reserved ip addresses in long int format
     * @todo Review the purpose!
     * 
     * @param string $ip
     * @return bool
     */
    public function validip($ip) {
        
        /*
         * This blacklisting private IPs is ridiculuous, nothing reasonable explanation 
         * I can find. And this is blocking Altamides from working in clustered / load balanced
         * system
         */        
        return true;
        
        
        if (empty($ip)){
            return false;
        }

        $ipLong = ip2long($ip);

        if ($ipLong == -1) {
            return false;
        }

        static $blockedIPList = array (
            array (0, 50331647), //0.0.0.0 - 2.255.255.255
            array (167772160, 184549375), //10.0.0.0 - 10.255.255.255
            array (2130706432, 2147483647), //127.0.0.0 - 127.255.255.255
            array (-1442971648, -1442906113), // 169.254.0.0 - 169.254.255.255
            array (-1408237568, -1407188993), // 172.16.0.0 - 172.31.255.255
            array (-1073741312, -1073741057), // 192.0.2.0 - 192.0.2.255
            array (-1062731776, -1062666241), // 192.168.0.0 - 192.168.255.255
            array (-256, -1) //255.255.255.0 - 255.255.255.255
        );

        foreach ($blockedIPList as $range) {
            if (($ipLong >= $range[0]) && ($ipLong <= $range[1])){
                return false;
            }
        }
        return true;
    }

    public function curPageURL() {
        if (isset($_SERVER['HTTPS']) && ('on' === strtolower($_SERVER['HTTPS']))) {
            $protocolStr = 'https://';
            // check for non standard port
            $portStr = '443' == $_SERVER['SERVER_PORT'] ?  '' :  (':' . $_SERVER['SERVER_PORT']);
        } else {
            $protocolStr = 'http://';
            // check for non standard port
            $portStr = '80' == $_SERVER['SERVER_PORT'] ?  '' :  (':' . $_SERVER['SERVER_PORT']);
        }

        return $protocolStr . $_SERVER['SERVER_NAME'] . $portStr . $_SERVER['REQUEST_URI'];
    }

    function do_post_request($url, $data, $optional_headers = null) {
        
        if(empty($optional_headers)){
            $params = array (
                'http' => array (
                    'method' => 'POST',
                    'header' => "Content-type: application/x-www-form-urlencoded\r\n" . "Content-Length: " . strlen($data
                ) . "\r\n",
                'content' => $data
            ));    
        }
        else{
            $params = array (
                'http' => array (
                    'method' => 'POST',
                    'header' => $optional_headers,
                'content' => json_encode($data)
            ));
        }

        $response = 'Failed';

        $ctx = @stream_context_create($params);
        $fp = @fopen($url, 'rb', false, $ctx);
        if ($fp) {
            $response = stream_get_contents($fp);
            if ($response === false) {
                $response = 'Failed';
            }
        }

        return $response;
    }
    
    /**
     *
     * @param string $log 
     */
    public function writeTraceLog($log)
    {
        ob_start();
        debug_print_backtrace();
        $log .= "\nStack Trace:\n" . ob_get_contents();
        ob_end_clean();
        
        $this->writeLog($log);
    }
    
    /**
     *
     * @param type $log 
     */
    public function writeLog($log)
    {
        $timestamp = date(DATE_RFC3339);
        $ip = $this->getIp();
        $user = isset($_SESSION['userId']) ? $_SESSION['userId'] : '';
        $token = isset($_SESSION['tokenId']) ? $_SESSION['tokenId'] : '';
        $contents = "[$timestamp] [{$_SERVER['SERVER_NAME']}] [$ip] [$token] [$user] $log\n";
        file_put_contents(SSO_LOG_FILE, $contents, FILE_APPEND);
    }
}
?>
