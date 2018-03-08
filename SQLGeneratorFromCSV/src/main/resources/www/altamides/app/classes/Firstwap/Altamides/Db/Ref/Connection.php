<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Db\Ref;

use \PDO;

/**
 * Description of AccountRef
 *
 * @author setia.budi
 */
class Connection
{
    /**
     * Charset used for connection encoding
     */
    const TEXT_ENCODING = 'utf8';

    public static function getDsn($dbName)
    {
        // We need unicode coverage for Mobiletrax/full GSM7 SMS (which is not possible for 8859-1)]
        // but don't want break existing code, so UTF 8 is the most appropriate choice for 
        // the connection charset
        return "mysql:dbname=$dbName;host=" . \REF_DB_HOST .';charset=' . self::TEXT_ENCODING;
    }
    
    /**
     * Get a PDO connection
     * @param type $dbName
     * @return \PDO 
     */
    public static function getPdo($dbName)
    {
        $dsn = self::getDsn($dbName);
        $pdo = new PDO($dsn, \REF_DB_USER, \REF_DB_PASS);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $pdo;
    }

    /**
     * Get a classic mysql connection
     * Added as a workaround to older code that is still using mysql_* 
     * functions
     * 
     * @param string $dbName
     * @deprecated 
     * @return resource
     */
    public static function getClassicLink($dbName = null)
    {
        $conn = mysqli_connect(\REF_DB_HOST, \REF_DB_USER, \REF_DB_PASS);
        if ($conn === false) {
            throw new \Exception('Connection failed: ' . mysqli_error($conn), mysqli_errno($conn));
        }

        if ($dbName !== null) {
            $ok = mysqli_select_db($conn,$dbName);
            if (!$ok) {
                throw new \Exception('Database selection failed: ' . mysqli_error($conn), mysqli_errno($conn));
            }
        }

        return $conn;
    }

}
