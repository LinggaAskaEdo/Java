<?php

/**
 * (c) 2014 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : ClassicApp.php
 * File Version     : 2.0SP20 
 * Initial Creation : 2012-08-30
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class ClassicApp
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-08-30   Setia Budi Halim          1.000.000           Initial Creation
 * 2014-06-11   Yung Fei                  2.0SP20             getDbRes return $conn
 * 
 * Copyright 2014 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Controller;

use Firstwap\PhpFramework\I18n\DictionaryDirectory;
use Firstwap\PhpFramework\I18n\TextDirection;
use Firstwap\Altamides\Intl\SharedDictionaryRef;
use Firstwap\Altamides\Db\DbNameRef;
use PDO;
use PDOException;
use Firstwap\Altamides\Controller\WebTemplate;
/**
 * A central resource collection and controller for "classic" type of Altamides script, i.e, non OOP
 * or "sphagetti" code.
 * As we are moving toward full OOP MVC architecture and so will be dropping this class in future
 * we need to seal this class by labelling it final coz we dont want any accidental dependency
 * that gives us headache to manage.
 * 
 * This class is designed as static class means that no non-static member should be defined
 *
 * @author Setia Budi Halim
 */
final class ClassicApp
{

    const SHARED_DICTIONARY_DIR = 'shared';
    const DEFAULT_TEXT_DIRECTION = 'ltr';
    const DEFAULT_LANGUAGE = 'en';
    const DEFAULT_ENCODING = 'UTF-8';

    /**
     *
     * @var \PDO
     */
    private static $pdo;

    /**
     *
     * @var DictionaryDirectory
     */
    private static $dictionaryLib;

    /**
     *
     * @var string
     */
    private static $currentModule;

    /**
     * Prevent instantiation as it is static class
     */
    private function __construct()
    {
        // Intentionally empty
    }

    /**
     * Initialise application
     */
    public static function init()
    {

        if (!session_id()) {
            session_start();
        }

        self::$dictionaryLib = new DictionaryDirectory(\ALTAMIDES_LANG_PATH, self::getLanguage());
    }

    /**
     * 
     * @param string $name
     * @param string $module The language module
     * @return \Firstwap\PhpFramework\I18n\Dictionary
     */
    public static function getDictionary($name, $module = null)
    {
        return self::$dictionaryLib->getDictionary($name, $module);
    }

    /**
     * Get dictionary from shared module
     * @param string $name
     * @return \Firstwap\PhpFramework\I18n\Dictionary
     */
    public static function getSharedDictionary($name = SharedDictionaryRef::BASIC)
    {
        return self::$dictionaryLib->getDictionary($name, self::SHARED_DICTIONARY_DIR);
    }

    /**
     * Set current module
     * @param string $module
     */
    public static function setModule($module)
    {

        self::$currentModule = $module;
        self::$dictionaryLib->setDefaultModule($module);
    }

    /**
     * Get current module
     * @return string
     */
    public static function getModule()
    {
        return self::$currentModule;
    }

    /**
     * Set current language
     * @param string $lang
     */
    public static function setLanguage($lang)
    {
        $_SESSION['ftrack_lang'] = $lang;
        $_SESSION['locale'] = $lang;

        self::$dictionaryLib->setLanguage($lang);
    }

    /**
     * Get current language code
     * @return string
     */
    public static function getLanguage()
    {
        return empty($_SESSION['ftrack_lang']) ? 'en' : $_SESSION['ftrack_lang'];
    }
    
    public function getWebTemplate($templateName)
    {
        return new WebTemplate($templateName);
    }

    /**
     * 
     * @param string $direction
     */
    public static function setTextDirection($direction)
    {
        $_SESSION['htmlDirection'] = $direction ? : TextDirection::LTR;
    }

    /**
     * 
     * @return string
     */
    public static function getTextDirection()
    {
        return empty($_SESSION['htmlDirection']) ? TextDirection::LTR : $_SESSION['htmlDirection'];
    }

    /**
     * Connect to specific DB
     * @param string $dbRef Database ref constant from Firstwap\Altamides\Db\DbNameRef
     * 
     * @return \PDO
     * @throws \Exception
     */
    public static function getPdo($dbRef, $new = false)
    {
        try {

            if (!$new && (self::$pdo !== null)) {
                return self::$pdo;
            }

            $dbName = DbNameRef::getActualName($dbRef);
            $dsn    = 'mysql:dbname=' . $dbName . ';host=' . \REF_DB_HOST . ';charset=utf8';

            $pdo = new PDO($dsn, \REF_DB_USER, \REF_DB_PASS);
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            if (self::$pdo === null) {
                self::$pdo = $pdo;
            }

            return $pdo;
        } catch (PDOException $e) {
            \error_log($e);
            throw new \Exception("Failed connecting database: " . $e->getMessage());
        }
    }

    /**
     * Please use getPdo when available. Use this function only when you have to keep compatibillity
     * with existing code which has been using mysql_* functions.
     * 
     * @param int $dbRef Database name reference as defined in DbNameRef
     * @param bool $new Whether to create new link (true) or just reuse (false)
     * @throws \Exception
     */
    public static function getDbRes($dbRef, $new = false)
    {
        $conn = mysqli_connect(\REF_DB_HOST, \REF_DB_USER, \REF_DB_PASS, $new);

        if ($conn === false) {
            throw new \Exception('Failed connection database server: ' . mysqli_error($conn));
        }

        $dbName = DbNameRef::getActualName($dbRef);

        if (empty($dbName)) {
            throw new \RuntimeException('Invalid database ref: ' . $dbName);
        }

        $dbSelected = mysqli_select_db($conn, $dbName);

        if (!$dbSelected) {
            throw new \Exception('Can not select database ' . $dbName . ' : ' . mysqli_error($conn));
        }

        return $conn;
    }

}
