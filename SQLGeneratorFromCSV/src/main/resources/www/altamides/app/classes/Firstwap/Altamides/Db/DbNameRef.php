<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : DbNameRef.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class DbNameRef
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-06   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Db;

/**
 * @deprecated since version 2.22.0
 * @author Setia Budi Halim
 */
final class DbNameRef
{

    const SSO = 0;
    const SMS_DISTRIBUTOR = 1;
    const TELCO = 2;
    const DIRECT_TRACKING = 3;
    const COMPLEX_TRACKING = 4;
    const SUBCELL_TRACKING = 5;
    const SUBCELL_TRACKING_20 = 6;

    /**
     * Use AltamidesDb instead
     * @see AltamidesDb
     * @param int $ref
     * @return string Database name
     * @deprecated since version 2.22.0
     */
    public static function getActualName($ref)
    {

        $map = [
            self::SSO => AltamidesDb::$sso,
            self::SMS_DISTRIBUTOR => AltamidesDb::$smsDispatcher,
            self::TELCO => AltamidesDb::$telco,
            self::DIRECT_TRACKING => AltamidesDb::$locationApi,
            self::COMPLEX_TRACKING => AltamidesDb::$profiletrax,
            self::SUBCELL_TRACKING_20 => AltamidesDb::$spottrax,
            self::SUBCELL_TRACKING => AltamidesDb::$spottrax
                ,
        ];
        if (isset($map[$ref])) {
            return $map[$ref];
        } else { // make it compatible for code calling this with name instead of ID
            return $ref;
        }

        \trigger_error("Unknown DB Ref: $ref", \E_USER_WARNING);
        throw new \InvalidArgumentException("Invalid database reference");
    }

    /**
     * Use AltamidesDb instead
     * @see AltamidesDb
     * @param int $ref
     * @return string
     * @deprecated since version 2.22.0s
     */
    public static function getName($ref)
    {
        return self::getActualName($ref);
    }

}
