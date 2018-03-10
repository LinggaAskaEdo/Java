<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Maps;

/**
 * Description of ExtendedMapData
 *
 * @author setia.budi
 */
class ExtendedMap extends Map
{

    private static $registry = null;
    protected $vendor;
    protected $altConfig;
    protected $viewerClass;
    protected $enabled = true;

    public function getConfigName()
    {
        return $this->configName;
    }

    /**
     * Get maps vendor
     * @return type 
     */
    public function getVendor()
    {
        return $this->vendor;
    }

    /**
     *
     * @return \Firstwap\Altamides\Maps\viewerClass
     * @throws Exception 
     */
    public function getViewer()
    {

        if (!$this->enabled) {
            return $this->getAltViewer();
        }

        if (!empty($this->viewerClass)) {
            return new $this->viewerClass($this);
        }

        trigger_error(
            'No viewer defined for map config ' . $this->configName,
            \E_USER_WARNING);

        return $this->getAltViewer();
    }

    public function getWidth()
    {
        return $this->options['width'];
    }

    public function getHeight()
    {
        return $this->options['height'];
    }

    public static function isConfigEnabled($configName)
    {
        self::loadConfig();
        $data = self::getConfig($configName);
        return !empty($data['enabled']) &&
            (!$data['check_privilege'] || \filterPage($data['check_privilege'], false));
    }

    /**
     *
     * @throws MapUnavailableException 
     */
    protected function getAltViewer()
    {

        if (!$this->altConfig) {
            throw new ViewerUnavailableException(
                'No alternative config', $this);
        }

        $list = self::$registry;
        unset($list[$this->configName]);
        $traces = array($this->configName => true);
        $configName = $this->altConfig;

        while (true) {

            //broken link can not trace again
            if (!isset($list[$configName])) {
                break;
            }

            // possibly an infinte loop
            if (isset($traces[$configName])) {
                break;
            }

            $config = $list[$configName];

            //no privilege
            if ($config['enabled'] &&
                !empty($config['viewer']) &&
                !$config['check_privilege']
                || filterPage($config['check_privilege'], false)) {

                return new $config['viewer']($this);
            }

            if (empty($config['alt_config'])) {
                break;
            }

            $configName = $config['alt_config'];
        }

        throw new ViewerUnavailableException(
            'No available alternative', $this);
    }

    protected function loadMapDetails()
    {
        parent::loadMapDetails();
        self::loadConfig();
        $data = self::getConfig($this->configName);
        $this->altConfig = $data['alt_config'];
        $this->vendor = $data['vendor'];
        $this->viewerClass = $data['viewer'];
        $this->options = $data['options'];
        $this->enabled = !empty($data['enabled']) &&
            (!$data['check_privilege']
            || \filterPage($data['check_privilege'], false));
    }

    /**
     * Load map configurations
     */
    private static function loadConfig()
    {
        if (self::$registry !== null) {
            return;
        }
        $map = array();

        include_once \ALTAMIDES_CONFIG_PATH . 'config.extended_map.php';

        self::$registry = $map ? : array();
    }

    /**
     * Get extended map configuration
     *
     * @param type $configName
     * @return array
     * @throws ConfigNotFoundException
     * @throws ConfigNotEnabledException 
     */
    private static function getConfig($configName)
    {
        if (!isset(self::$registry[$configName])) {
            throw new ConfigNotFoundException($configName);
        }

        return self::$registry[$configName];
    }

}
