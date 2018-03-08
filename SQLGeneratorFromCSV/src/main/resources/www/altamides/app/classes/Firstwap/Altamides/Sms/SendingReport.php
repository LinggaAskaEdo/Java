<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Sms;

/**
 * Description of SendingReport
 *
 * @author setia.budi
 */
class SendingReport
{

    protected $total = 0;
    protected $failed = 0;
    protected $success = 0;
    protected $errorItems = array();
    protected $errorMessage = null;

    public function __construct(array $report)
    {
        $this->processReportData($report);
    }

    public function isSuccess()
    {
        return $this->success > 0;
    }

    public function isAllSuccess()
    {
        return ($this->success > 0) && ($this->success === $this->total);
    }

    public function getTotal()
    {
        return $this->total;
    }

    public function getSuccess()
    {
        return $this->success;
    }

    public function getFailed()
    {
        return $this->failed;
    }

    public function getErrorMessage()
    {
        return $this->errorMessage;
    }

    protected function processReportData(array $report)
    {
        $this->success = isset($report['success']) ? (int) $report['success']
                : 0;

        $this->failed = isset($report['failed']) ? (int) $report['failed'] : 0;

        $this->failed = isset($report['failed']) ? (int) $report['failed'] : 0;

        $this->total = $this->success + $this->failed;

        $this->errorMessage = isset($report['error_message']) ? (int) $report['error_message']
                : null;
    }

}
