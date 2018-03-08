<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

/**
 * Description of PaginationInterface
 *
 * @author Setia Budi Halim
 */
interface PaginationInterface
{

    /**
     * 
     * @param int $page
     */
    public function setPage($page);

    /**
     * 
     * @param type $total
     */
    public function setTotalItem($total);

    /**
     * @return int
     */
    public function getPage();

    /**
     * @return bool 
     */
    public function isLastPage();

    /**
     * @return int
     */
    public function getStartItem();

    /**
     * @return int
     */
    public function getTotalItem();

    /**
     * @return int
     */
    public function getTotalPage();
}
