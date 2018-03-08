<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : WidgetBase.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-30
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class WidgetBase
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-08-30   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Ui\Widget;

/**
 * Description of WidgetBase
 *
 * @author Setia Budi Halim
 */
abstract class WidgetBase implements WidgetInterface
{

    /**
     * We preserve this constructor for doing widget initialisation that may be added in future
     */
    public function __construct()
    {
        // Currently do nothing
    }

    protected function loadDependencies()
    {
        return;
    }

    protected static function getScriptStart()
    {

        return '<script type="text/javascript">//<![CDATA[' . "\n";
    }

    protected static function getScriptEnd()
    {
        return "\n//]]></script>";
    }

    protected static function getScriptImport($url)
    {
        return '<script type="text/javascript" src="' . $url . '"></script>' . "\n";
    }

}
