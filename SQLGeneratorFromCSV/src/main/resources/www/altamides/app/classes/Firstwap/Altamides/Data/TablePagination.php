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
class TablePagination
{

    protected $page = 1;
    protected $totalPage = 1;
    protected $itemPerPage = 1;
    protected $group = 1;
    protected $totalGroup = 1;
    protected $pagePerGroup = 20;
    protected $totalItem = 0;

    public function __toString()
    {
        $string = "Page {$this->page} of {$this->totalPage} with {$this->itemPerPage} item(s) per page";
        $string = 'Page: ' . $this->getPage() . '/' . $this->getTotalPage() .
                ', Item: ' . $this->getStartItem() . '/' . $this->getTotalItem() .
                ', Item/Page: ' . $this->getItemPerPage() .
                ', Page: ' . $this->getPage() . '/' . $this->getTotalPage() .
                ', Page/Group: ' . $this->getPagePerGroup() .
                ', Group: ' . $this->getGroup() . '/' . $this->getTotalGroup();
        return $string;
    }

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
        $this->calculatePage();
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
        $this->calculatePage();
    }

    /**
     * 
     * @return int
     */
    public function getStartItem()
    {
        return ($this->page - 1) * $this->itemPerPage;
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
        return 1 + (($this->group - 1) * $this->pagePerGroup);
    }

    /**
     * 
     * @return int 
     */
    public function getEndPage()
    {
        $endPage = $this->group * $this->pagePerGroup;
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
        $this->calculatePage();
    }

    function getGroup()
    {
        return $this->group;
    }

    function getTotalGroup()
    {
        return $this->totalGroup;
    }

    protected function calcPosition()
    {
        if ($this->page <= 1) {
            $this->page = 1;
            $this->group = 1;
        } elseif ($this->page >= $this->totalPage) {
            $this->page = $this->totalPage;
            $this->group = $this->totalGroup;
        } else {
            $this->group = ceil($this->page / $this->pagePerGroup);
        }
    }

    /**
     * Calculate paging paramters
     */
    protected function calculatePage()
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
