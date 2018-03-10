<?php

/*
 * @copyright (c) 2014 1rstWAP.
 * This document belongs to PT 1rstWAP.
 * Propagation to others than members 
 * of PT. 1rstWAP is strictly forbidden.
 * 
 * 
 * System           : Altamides
 * Module           : All
 * Version          : 2.0 SP19
 * File Name        : ImeiNumber.php
 * File Version     : SVN: $Id: ImeiNumber.php 16948 2014-03-11 09:26:20Z arahman $
 * Initial Creation : 13-Feb-2014
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          :
 * 
 * 
 * Changelog:
 * Date        Version     Comment
 * 2014-02-13  2.0 SP19    Issue #ISSUE_NUMBER: Initial creation
 * 2014-02-21  2.0 SP19    Create Parsing Imei Number Function
 * 2014-03-11  2.0 SP19    Bug 3776 : fixed Imei Length 
 * 
 * Copyright 2013 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Firstwap\Altamides\Telcotrax\Imei;

use Firstwap\Firstphp\Validation;

/**
 * A class that represents IMEI number or TAC
 * 
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class ImeiNumber
{

    protected $imei           = '';
    protected $imeiType       = ImeiNumberType::INVALID;
    protected $tac            = '';
    protected $rbi            = '';
    protected $svn            = '';
    protected $serial         = '';
    protected $checkDigit     = '';
    protected $validLuhnDigit = false;

    public function __construct($imei)
    {
        $this->parse($imei);
    }

    public function getImei()
    {
        return $this->imei;
    }

    public function getRbi()
    {
        return $this->rbi;
    }

    public function getTac()
    {
        return $this->tac;
    }

    public function getSerial()
    {
        return $this->serial;
    }

    public function getSvn()
    {
        return $this->svn;
    }

    public function getCheckDigit()
    {
        return $this->checkDigit;
    }

    public function hasValidCheckDigit()
    {
        return $this->validLuhnDigit;
    }

    public function getType()
    {
        
    }

    /**
     * Acceptable format:
     * - 8 Digit TAC
     * - 14 digit IMEI-only
     * - 15 digit IMEI + Check digit
     * - 16 digit IMEI-SV
     * 
     * @param string $imei IMEI number or TAC
     * @throws \Exception when format is not one of acceptable format
     */
    protected function parse($imei)
    {
        $imei = (string) $imei;

        if ($imei === '') {
            throw new Validation\EmptyValueException('IMEI');
        }

        if (!\ctype_digit($imei)) {
            throw new Validation\InvalidPatternException('IMEI', $imei);
        }

        $length = \strlen($imei);

        $this->imei = $imei;

        // Valid length is 8, 14, 15, 16
        if ($length === 8) {
            $this->imeiType = ImeiNumberType::TAC_ONLY;
            
        } elseif ($length >= 9 && $length <= 14) {
            $this->imeiType = ImeiNumberType::IMEI_ONLY;
            
        } elseif ($length === 15) {
            $this->imeiType   = ImeiNumberType::IMEI_CD;
            // check digit is 15th digit
            $this->checkDigit = \substr($imei, 14, 1);
            $this->validateCheckDigit();
            
        } elseif ($length === 16) {
            $this->imeiType = ImeiNumberType::IMEI_SV;
            // SVN is 15th and 16th 
            $this->svn      = \substr($imei, 14, 2);
            
        } else {
            throw new Validation\InvalidValueException('IMEI', $imei);
        }

        $this->rbi    = \substr($imei, 0, 2); // RBI is digit 1 to 2
        $this->tac    = \substr($imei, 0, 8); // TAC is digit 1 to 8
        $this->serial = \substr($imei, 8, 6); // TAC is digit 9 to 14
    }

    /**
     * @link http://en.wikipedia.org/wiki/Luhn_algorithm Luhn Algorithn
     * @link http://en.wikipedia.org/wiki/International_Mobile_Equipment_Identity IMEI
     */
    protected function validateCheckDigit()
    {
        if ($this->imeiType !== ImeiNumberType::IMEI_CD) {
            $this->validLuhnDigit = false;
        }
        
        $imei = $this->imei;
        $odd  = $imei[0] + $imei[2] + $imei[4] + $imei[6] + $imei[8] + $imei[10] + $imei[12];
        $even = (2*$imei[1])."".(2*$imei[3])."".(2*$imei[5])."".(2*$imei[7])."".(2*$imei[9])."".(2*$imei[11])."".(2*$imei[13]);
        $evenSum = 0;
        for($i=0;$i<strlen($even);$i++){            
            $evenSum += $even[$i];
        }
        $total = $odd + $evenSum + $this->checkDigit;
        $this->validLuhnDigit = ($total % 10) == 0;
    }

}
