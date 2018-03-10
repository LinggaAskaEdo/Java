<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Maps;

use \PDO;

/**
 * Description of Map
 *
 * @author setia.budi
 */
abstract class Map
{
    protected $name;
    protected $ref;
    protected $id;
    protected $width = null;
    protected $height = null;
    protected $isExtended;
    protected $configName;
    protected $options = array();
    
    public function __construct($mapId)
    {
        $this->id = $mapId;
        $this->loadMapDetails();
    }
    
    abstract public function getVendor();

    public function getName()
    {
        return $this->name;
    }
    
    public function getRef()
    {
        return $this->ref;
    }
    
    public function getId()
    {
        return $this->id;
    }
    
    public function getWidth()
    {
        return $this->width;
    }
    
    public function getHeight()
    {
        return $this->height;
    }
    
    /**
     * Is this native or extended map
     * @return type 
     */
    public function isExtended()
    {
        return $this->isExtended;
    }
    
    
//    
//    public function getConfigName()
//    {
//        return $this->configName;
//    }
    
    abstract public function getViewer();
    
    public function getOption($name)
    {
        return $this->options[$name];
    }
    
    public function allOptions()
    {
        return $this->options;
    }
    
    public function setOption($name, $value)
    {
        return $this->options[$name] = $value;
    }
        
    /**
     * @var array
     */
    protected function loadMapDetails()
    {
        $dsn = 'mysql:dbname='.REF_FTRAX_LOCATIONTRACKING_DBNAME.';host='.REF_DB_HOST;
        $pdo = new PDO($dsn, REF_DB_USER, REF_DB_PASS);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $query = 'select * from MAP where ID = :id';
        $stmt = $pdo->prepare($query);
        $stmt->bindValue(':id', $this->id);
        $stmt->execute();
        $data = $stmt->fetch(PDO::FETCH_ASSOC);
        $this->name = $data['MAP_DISPLAY_NAME'];
        $this->ref = $data['MAP_REF'];
        $this->isExtended = !empty($data['IS_EXTENDED']);
        $this->configName = $data['CONFIG_NAME'] ?: null;
    }

}
