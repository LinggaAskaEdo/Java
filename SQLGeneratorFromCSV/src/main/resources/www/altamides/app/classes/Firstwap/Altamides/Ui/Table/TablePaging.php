<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Ui\Table;

use Firstwap\Altamides\Ui\VisualComponent;
use Firstwap\Altamides\Data\TablePagination;
use Firstwap\Altamides\Controller\ClassicApp;

/**
 * Description of TablePaging
 *
 * @author Setia Budi Halim
 */
class TablePaging extends VisualComponent
{

    /**
     *
     * @var TablePagination
     */
    protected $pagination;
    protected $pageUrl = '?page={{page}}';

    public function display()
    {
        echo '<div class="paging"><div class="paging-nav">';
        if ($this->pagination && $this->pagination->getTotalPage()) {
            $text = ClassicApp::getSharedDictionary('basic');
            echo $this->renderBackwardNav($text);
            echo $this->renderIndex();
            echo $this->renderForwardNav($text);
            echo $this->renderSummary($text);
        }
        echo '</div>';
    }

    /**
     * 
     * @return TablePagination
     */
    public function getPagination()
    {
        return $this->pagination;
    }

    /**
     * 
     * @param TablePagination $pagination
     */
    public function setPagination(TablePagination $pagination)
    {
        $this->pagination = $pagination;
    }

    public function setUrl($template)
    {
        $this->pageUrl = (string) $template;
    }

    protected function renderBackwardNav(\ArrayAccess $text)
    {
        $prev = $this->pagination->getPage() - 1;
        if ($prev < 1) {
            $html = '<a class="paging-g paging-g-p paging-g-x" href="javascript:void(0);">';
        } else {
            $html = '<a class="paging-p" href="' . $this->createUrl($prev) . '">'
                    . $text['paging.first_page'] . '</a><span class="paging-s">|</span>'
                    . '<a class="paging-g paging-g-p" href="' . $this->createUrl($prev) . '">';
        }

        $html .= $text['paging.prev_page'] . '</a>';
        return $html;
    }

    protected function renderForwardNav(\ArrayAccess $text)
    {
        $next = $this->pagination->getPage() + 1;
        $lastPage = $this->getPagination()->getTotalPage();
        if ($next > $lastPage) {
            $html = '<a class="paging-g paging-g-n paging-g-x" href="javascript:void(0);">';
        } else {
            $html = '<a class="paging-n" href="' . $this->createUrl($lastPage) . '">'
                    . $text['paging.first_page'] . '</a><span class="paging-s">|</span>'
                    . '<a class="paging-g paging-g-n" href="' . $this->createUrl($next) . '">';
        }

        $html .= $text['paging.next_page'] . '</a>';
        return $html;
    }

    protected function renderIndex()
    {
        $pagination = $this->getPagination();
        $from = $pagination->getStartPage();
        $to = $pagination->getEndPage();
        $current = $pagination->getPage();

        $html = '<span class="paging-s">|</span>';
        for ($i = $from; $i < $to; $i++) {
            if ($i === $current) {
                $html .= '<a class="paging-p paging-p-x" href="javascript:void(0);">';
            } else {
                $html .= '<a class="paging-p" href="' . $this->createUrl($i) . '">';
            }

            $html .= $i . '</a><span class="paging-s">|</span>';
        }
        return $html;
    }

    public function renderSummary(\ArrayAccess $text)
    {
        $placeholders = ['{{page}}', '{{total_pages}}'];
        $data = [$this->getPagination()->getPage(), $this->getPagination()->getTotalPage()];
        $summary = str_replace($placeholders, $data, $text['paging.next_page']);
        return '<div class="paging-summary">' . $summary . '</div>';
    }

    protected function createUrl($page)
    {
        return \str_replace('{{page}}', $page, $this->pageUrl);
    }

}
