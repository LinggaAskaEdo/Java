<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Maps;

/**
 * Description of MapUnavailableException
 *
 * @author setia.budi
 */
class ConfigNotFoundException extends \Exception
{
    /**
     *
     * @param int $config Map ID
     */
    function __construct($config)
    {
        parent::__construct("Map configuration  named <$config> was not found!");
    }
}
