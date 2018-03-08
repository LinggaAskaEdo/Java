<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

/**
 * Description of DataOrder
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DataOrder
{

    const ASCENDING = 'asc';
    const DESCENDING = 'desc';

    private static $keywords = [
        1 => true,
        'up' => true,
        'asc' => true,
        'ascending' => true,
        0 => false,
        'down' => false,
        'desc' => false,
        'descending' => false,
    ];

    public static function isAscending($keyword)
    {
        return isset(self::$keywords[$keyword]) && self::$keywords[$keyword];
    }

    public static function isDescending($keyword)
    {
        return isset(self::$keywords[$keyword]) && !self::$keywords[$keyword];
    }

}
