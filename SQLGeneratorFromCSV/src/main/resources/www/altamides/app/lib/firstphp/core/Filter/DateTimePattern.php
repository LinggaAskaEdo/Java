<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of DateTimeFilterRegex
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DateTimePattern
{

    const DATE = '^[0-9]{4}-[0-2][0-9]-[0-3][0-9]$';
    const DATE_TIME = '^[0-9]{4}-[0-2][0-9]-[0-3][0-9] [0-2][0-9]:[0-5][0-9]$';
    const TIME = '^[0-2][0-9]:[0-5][0-9]$';
    const TIME_SECOND = '^[0-2][0-9]:[0-5][0-9]:[0-5][0-9]$';
    const TIMESTAMP = '^[0-9]{4}-[0-2][0-9]-[0-3][0-9] [0-2][0-9]:[0-5][0-9]:[0-5][0-9] ((GMT)?((+|-)[0-2][0-9]:[0-5][0-9])))?$';

}
