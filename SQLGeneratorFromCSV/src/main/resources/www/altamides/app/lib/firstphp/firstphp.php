<?php

/*
 * Copyright (c) 2015 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 * 
 * Git: $Id$
 */

namespace Firstwap\Firstphp;

if (defined(__NAMESPACE__ . '\DEFAULT_LOADER')) {
    return;
}

require_once __DIR__ . '/core/ClassLoader/Autoloader.php';

use Firstwap\Firstphp\ClassLoader\Autoloader;

Autoloader::autoload(__DIR__ . '/core', 'Firstwap\Firstphp', true);
Autoloader::autoload(__DIR__ . '/ext/deprecated-v1.2/Firstphp', 'Firstwap\Firstphp');
Autoloader::autoload(__DIR__ . '/ext/deprecated-v1.2/PhpFramework', 'Firstwap\PhpFramework');

const DEFAULT_LOADER = __FILE__;

