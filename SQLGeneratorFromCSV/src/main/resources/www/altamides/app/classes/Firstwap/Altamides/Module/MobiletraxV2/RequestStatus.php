<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

/**
 * Request status string
 *
 * @author Dwikky Maradhiza
 */
final class RequestStatus
{   
    const ERROR = 'E_ERROR';
    const ERROR_PARSE = 'E_PARSE';
    const ERROR_ACCOUNT = 'E_ACCOUNT';
    const ERROR_PROTOCOL = 'E_PROTOCOL';
    const ERROR_CHECKSUM = 'E_CHECKSUM';
    const ERROR_CRYPTO = 'E_CRYPTO';
    const VALID_REQUEST = 'VALID_REQUEST';

}