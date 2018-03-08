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
class MapNotFoundException extends \Exception
{
    /**
     *
     * @param int $id Map ID
     */
    function __construct($id)
    {
        parent::__construct("Map <$id> was not found!", 0);
    }
}
