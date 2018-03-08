<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

use Exception;

/**
 * Description of DataScopeException
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DataScopeViolationException extends Exception 
{
    /**
     * 
     * @param DataScope $dataScope Referenced scope rule
     * @param string $message Additional message
     * @param Exception $prev Previous exception
     */
    public function __construct(DataScope $dataScope, $message = '', Exception $prev = null)
    {
        $fullMessage = "Data scope violation ($dataScope). $message";
        parent::__construct($fullMessage, 0, $prev);
    }
}
