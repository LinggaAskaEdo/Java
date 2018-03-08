<?php

/* 
 * (c) 2012 FirstWAP. All rights reserved
 */

require_once __DIR__ . '/init-basic.php';

require_once ALTAMIDES_CONFIG_PATH.'/config.sso_client.php';
require_once ALTAMIDES_BASE_PATH.'/sso_client/interceptor.php';
require_once ALTAMIDES_LIB_PATH.'/common/menu.lib.php';

use Firstwap\Firstphp\ClassLoader\Autoloader;
Autoloader::autoload(ALTAMIDES_DIR . '/app/lib/latte/src', 'Latte');