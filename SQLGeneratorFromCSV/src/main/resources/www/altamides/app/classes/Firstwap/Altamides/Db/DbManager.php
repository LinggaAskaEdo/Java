<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Db;

/**
 *
 * @author Setia Budi Halim
 */
class DbManager
{

    /**
     *
     * @var DbManager 
     */
    protected static $instance = null;

    /**
     * 
     * @return DbManager
     */
    public static function getInstance()
    {
        if (self::$instance !== null) {
            return self::$instance;
        }

        self::$instance = new DbManager();
        return self::$instance;
    }

    /**
     * 
     * @param string $dbRef
     * @return \PDO
     */
    public function getPdo($dbRef)
    {
        $dbName = DbNameRef::getName($dbRef);
        $dsn    = "mysql:dbname=$dbName;host=" . \REF_DB_HOST . ';charset=utf8';
        return self::createPdo($dsn, \REF_DB_USER, \REF_DB_PASS);
    }

    /**
     * 
     * @param string $dsn
     * @param string $user
     * @param string $pass
     * @return \PDO
     */
    protected static function createPdo($dsn, $user, $pass)
    {
        $pdo = new \PDO($dsn, $user, $pass);
        $pdo->setAttribute(\PDO::ATTR_ERRMODE, \PDO::ERRMODE_EXCEPTION);
        return $pdo;
    }

}
