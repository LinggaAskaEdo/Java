<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
namespace Firstwap\Altamides\Experimental;

/**
 * Use to test control server mechanism. Do not rely on the API as it may changes from release 
 * to release.
 * 
 * All methods in this class should state the reason it is created and expectation of removal
 * 
 * == Maintenance Limitation ==
 * Introduced: 2.0 SP18
 * Expiration: 2.0 SP20
 * 
 * @author setia.budi
 */
class LoadThrottle
{
    
    private static $timelimit = null;
    
    /**
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP19
     * 
     * @author setia.budi
     * 
     * @global array $ALTAMIDES_X_TIMELIMIT_CONFIG
     * @param string $configName
     */
    public static function setTimeLimitConfig($configName)
    {
       global $ALTAMIDES_X_TIMELIMIT_CONFIG;
       
       
       if (empty($ALTAMIDES_X_TIMELIMIT_CONFIG)) {
           trigger_error("No timelimit configuration for this server. You can set timelimit config by adding \$ALTAMIDES_X_TIMELIMIT_CONFIG in the config.global.php", E_USER_NOTICE);
           return;
       }
       
       if (empty($ALTAMIDES_X_TIMELIMIT_CONFIG[$configName])) {
           trigger_error("Can not find time limit configuration for '$configName'", E_USER_WARNING);
           return;
       }
       
       $config = $ALTAMIDES_X_TIMELIMIT_CONFIG[$configName];
       
       if (!is_array($config)) {
           trigger_error("Invalid value for '$configName'", E_USER_WARNING);
           return;
       }
       
       if (isset($config['exec']) && (($config['exec'] > 0) || ($config['exec'] === 0))) {
           set_time_limit((int) $config['exec']);
       } else {
           $config['exec'] = -1;
       }
       
       if (isset($config['stream']) && (($config['stream'] > 0) || ($config['stream'] === 0))) {
           $streamTimeout = (int) $config['stream'];
           ini_set('default_socket_timeout', $config['stream']);
           ini_set('mysql.connect_timeout', $streamTimeout);
           ini_set('mysqlnd.net_read_timeout', $streamTimeout);
       } else {
           $config['stream'] = -1;
       }
       
       self::$timelimit = $config;
    }
    
    /**
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP19
     * 
     * @param resource $resource
     */
    public static function setStreamTimeout($resource)
    {
        if (empty($resource) || !is_resource($resource)) {
           trigger_error("Invalid resource: $resource", E_USER_WARNING);
           return false;
        }
        
        $timeout = self::getStreamTimeout();
        if ($timeout === false) {
           return false;
        }
        
        return stream_set_timeout($resource, $timeout);        
    }
    
    /**
     * Get currently configured value for stream time limit
     * 
     * == Maintenance Limitation ==
     * Introduced: 2.0 SP18
     * Expiration: 2.0 SP19
     * 
     * @return int|bool CAUTION: if timeout is not configured the return value is FALSE, 
     * otherwise the timeout in seconds (integer) including 0
     */
    public static function getStreamTimeout()
    {
        if (isset(self::$timelimit['stream']) && ((self::$timelimit['stream'] > 0) || (self::$timelimit['stream'] === 0))) {
            return (int) self::$timelimit['stream'];
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @param array $contextOptions Stream context options parameters
     * @return array
     */
    public static function configureStreamSocket(array $contextOptions)
    {
        $timeout = self::getStreamTimeout();
        if ($timeout !== false) {
            $contextOptions['http']['timeout'] = (int) $timeout;
        }
        return $contextOptions;
    }
    
    public static function unlockSession()
    {
        if (session_id()) {
            session_write_close();
        }
    }
}
