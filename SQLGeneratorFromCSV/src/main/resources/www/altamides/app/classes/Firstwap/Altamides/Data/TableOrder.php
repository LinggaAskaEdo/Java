<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

/**
 * Description of DataSortOptions
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class TableOrder
{

    protected $order = [];

    /**
     * 
     * @return array
     */
    public function getOrder()
    {
        return $this->order . \SORT_ASC;
    }

    /**
     * 
     * @param array $orders
     */
    public function setOrder(array $orders = [])
    {
        $this->order = $orders;
        foreach ($orders as $key => $direction) {
        }
    }

}
