<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : AlertWidget.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-30
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class AlertWidget
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
 * Description of AlertWidget
 * Require altamides js lib
 *
 * @author Setia Budi Halim
 */
class AlertWidget extends WidgetBase
{

    protected $message;
    protected $type;

    public function __construct($message = '', $type = AlertWidgetType::INFO)
    {
        parent::__construct();
        $this->setMessage($message);
        $this->setType($type);
    }

    /**
     * Set the alert message
     * @param string $message the message
     */
    public function setMessage($message)
    {
        $this->message = (string) $message;
    }

    /**
     * 
     * @param type $type
     */
    public function setType($type)
    {
        $this->type = (string) $type;
    }

    public function render()
    {
        echo self::getScriptStart(), 'showAlert(', json_encode($this->message), ');',
                self::getScriptEnd();
    }

}