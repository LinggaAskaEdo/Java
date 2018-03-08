<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

/**
 * Response type enumeration
 *
 * @author setia.budi
 */
final class ResponseType
{
    const UNSPECIFIED = '00';
    const ERROR       = '01';
    
    const LOCATE_RESULT_FAST     = '20';
    const LOCATE_RESULT_ECONOMY  = '21';
    const LOCATE_RESULT_COMPLETE = '22';

}