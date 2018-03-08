<?php

require_once dirname(dirname(__DIR__)) . '/lib/firstphp/firstphp.php';
require_once dirname(__DIR__) . '/configs/config.global.php';
require_once dirname(__DIR__) . '/configs/config.domain.php';

use Firstwap\Firstphp\ClassLoader\Autoloader;
use Firstwap\Firstphp\File\DirectoryUtil;
use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Altamides\Db\AltamidesDb;
use Firstwap\Altamides\Db\BlacklistDb;

\date_default_timezone_set('UTC');

try {
    if ((PHP_SAPI === 'cli' || PHP_SAPI === 'cli-server') && (session_status() !== PHP_SESSION_ACTIVE)) {
        DirectoryUtil::requireExistence(ALTAMIDES_TMP_DIR . '/session');
        session_save_path(ALTAMIDES_TMP_DIR . '/session');
    }
    if (!defined('Firstwap\Altamides\INIT')) {
        Autoloader::autoload(ALTAMIDES_DIR . '/app/classes/Firstwap/Altamides',
                'Firstwap\Altamides');
        define('Firstwap\Altamides\INIT', true);
        ClassicApp::init();
    }
} catch (\Exception $e) {
    \trigger_error("$e", \E_USER_ERROR);
}

require_once ALTAMIDES_CONFIG_PATH . '/config.reference.php';

if (defined('REF_DB_NAME')) {
    AltamidesDb::$sso = \REF_DB_NAME;
    BlacklistDb::$sso = \REF_DB_NAME;
}
if (defined('REF_SMS_DISPATCHER_DBNAME')) {
    AltamidesDb::$smsDispatcher = \REF_SMS_DISPATCHER_DBNAME;
}
if (defined('REF_CELLDB_DBNAME')) {
    AltamidesDb::$telco = \REF_CELLDB_DBNAME;
}
if (defined('REF_FTRAX_LOCATIONMO_DBNAME')) {
    AltamidesDb::$locationApi = \REF_FTRAX_LOCATIONMO_DBNAME;
}
if (defined('REF_FTRAX_LOCATIONTRACKING_DBNAME')) {
    AltamidesDb::$profiletrax = \REF_FTRAX_LOCATIONTRACKING_DBNAME;
}
if (defined('REF_SPOTTRAX_DBNAME')) {
    AltamidesDb::$spottrax = \REF_SPOTTRAX_DBNAME;
}
if (defined('REF_FTRAX_1INTERMEDIA_DBNAME')) {
    AltamidesDb::$firstIntermedia = \REF_FTRAX_1INTERMEDIA_DBNAME;
}
if (defined('REF_SMS_API_DBNAME')) {
    AltamidesDb::$smsApi = \REF_SMS_API_DBNAME;
}
if (defined('REF_FTRAX_ISATLBS_DBNAME')) {
    AltamidesDb::$isatlbs = \REF_FTRAX_ISATLBS_DBNAME;
}
if (defined('REF_PUBLICBLACKLIST_DB_NAME')) {
    BlacklistDb::$publicBlacklist = \REF_PUBLICBLACKLIST_DB_NAME;
}
if (defined('REF_SECUREBLACKLIST_DB_NAME')) {
    BlacklistDb::$secureBlacklist = \REF_SECUREBLACKLIST_DB_NAME;
}

require_once ALTAMIDES_CONFIG_PATH . '/config.connections.php';
