<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

/**
 * Page is one-based index
 * Item number is zero-based
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DataPagination
{

    protected $page = 1;
    protected $totalPage = 1;
    protected $itemPerPage = 1;
    protected $currentGroup = 1;
    protected $totalGroup = 1;
    protected $pagePerGroup = 20;
    protected $totalItem = 0;
    protected $limitless;

    public function getPage()
    {
        return $this->page;
    }

    public function setPage($page)
    {
        $this->page = (int) $page;
        $this->calcPosition();
    }

    public function getTotalPage()
    {
        return $this->totalPage;
    }

    public function getItemPerPage()
    {
        return $this->itemPerPage;
    }

    public function setItemPerPage($count)
    {
        if (1 > (int) $count) {
            throw new \DomainException('Item per page must be a positive integer');
        }
        $this->itemPerPage = (int) $count;
        $this->calcDistribution();
    }

    public function getPagePerGroup()
    {
        return $this->pagePerGroup;
    }

    public function setPagePerGroup($count)
    {
        if (1 > (int) $count) {
            throw new \DomainException('Page per group must be a positive integer');
        }
        $this->pagePerGroup = (int) $count;
        $this->calcDistribution();
    }

    /**
     * 
     * @return int 
     */
    public function getStartItem()
    {
        return 1 + ($this->page * $this->itemPerPage);
    }

    /**
     * 
     * @return int 
     */
    public function getEndItem()
    {
        $endItem = $this->page * $this->itemPerPage;
        return $endItem > $this->totalItem ? $this->totalItem : $endItem;
    }

    /**
     * 
     * @return int 
     */
    public function getStartPage()
    {
        return 1 + (($this->currentGroup - 1) * $this->pagePerGroup);
    }

    /**
     * 
     * @return int 
     */
    public function getEndPage()
    {
        $endPage = $this->currentGroup * $this->pagePerGroup;
        return $endPage > $this->totalPage ? $this->totalPage : $endPage;
    }
    
    public function isLastPage()
    {
        return $this->page >= $this->totalPage;
    }

    public function getTotalItem()
    {
        return $this->totalItem;
    }

    public function setTotalItem($total)
    {
        if ($total < 0) {
            $this->totalItem = 0;
        } else {
            $this->totalItem = (int) $total;
        }
        $this->calcDistribution();
    }

    function getPageGroup()
    {
        return $this->currentGroup;
    }

    function getTotalGroup()
    {
        return $this->totalGroup;
    }

    protected function calcPosition()
    {
        if ($this->page <= 1) {
            $this->page = 1;
            $this->currentGroup = 1;
        } elseif ($this->page >= $this->totalPage) {
            $this->page = $this->totalPage;
            $this->currentGroup = $this->totalGroup;
        } else {
            $this->currentGroup = ceil($this->page / $this->pagePerGroup);
        }
    }

    /**
     * Calculate paging paramters
     */
    protected function calcDistribution()
    {
        if ($this->totalItem <= 1) {
            $this->totalPage = 1;
            $this->totalGroup = 1;
        } else {
            $this->totalPage = (int) \ceil($this->totalItem / $this->itemPerPage);
            $this->totalGroup = (int) \ceil($this->totalPage / $this->pagePerGroup);
        }

        $this->calcPosition();
    }

}
