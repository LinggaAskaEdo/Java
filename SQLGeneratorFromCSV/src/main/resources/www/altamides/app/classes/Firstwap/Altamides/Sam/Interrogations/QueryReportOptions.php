<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sam\Interrogations;

use DateTime;
use DateTimeZone;
use Firstwap\Altamides\Time\AltamidesTimeZone;
use Firstwap\Altamides\Data\DataOrder;
use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Data\DataScopeFactory;
use Firstwap\Altamides\Data\TablePagination;
use Firstwap\Altamides\Tracking\Ismsc\IsmscRegister;

/**
 * Description of InterrogationStatisticsOptions
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class QueryReportOptions
{

    const VIEW_MODE_SCHEDULE      = 'schedule';
    const VIEW_MODE_INTERROGATION = 'interrogation';
    const GROUP_BY_USER           = 'user';
    const GROUP_BY_USER_GROUP     = 'user_group';
    const GROUP_BY_SYSTEM         = 'system';
    const INTERVAL_HOUR           = 'PT1H';
    const INTERVAL_HOUR_8         = 'PT8H';
    const INTERVAL_HOUR_12        = 'PT12H';
    const INTERVAL_DAY            = 'P1D';
    const INTERVAL_WEEK           = 'P1W';
    const INTERVAL_MONTH          = 'P1M';
    const INTERVAL_QUARTER        = 'P3M';
    const INTERVAL_YEAR           = 'P1Y';

    /**
     *
     * @var string
     */
    protected $timeInterval = self::INTERVAL_HOUR;

    /**
     *
     * @var string
     */
    protected $dataGrouping = self::GROUP_BY_SYSTEM;

    /**
     *
     * @var DateTime
     */
    protected $startTime = null;

    /**
     *
     * @var DateTime
     */
    protected $endTime = null;
    
    /**
     *
     * @var String
     */
    protected $showDeleted = 'true';

    /**
     * 
     * @var DateTimeZone
     */
    protected $timeZone = null;

    /**
     *
     * @var string
     */
    protected $viewMode = self::VIEW_MODE_INTERROGATION;

    /**
     *
     * @var int
     */
    protected $requestUserFilter = null;

    /**
     *
     * @var int
     */
    protected $requestUserGroupFilter = null;

    /**
     *
     * @var DataScope
     */
    protected $dataScope;

    /**
     *
     * @var int
     */
    protected $limitOffset = 0;

    /**
     *
     * @var TablePagination
     */
    protected $pagination;

    /**
     *
     * @var boolean
     */
    protected $paginationEnabled = true;

    /**
     *
     * @var int
     */
    protected $selectedPage = 1;

    /**
     *
     * @var array
     */
    protected $ismsc = [];

    /**
     *
     * @var array
     */
    protected $order = [];

    public function __construct()
    {
        $this->setTimeZone(AltamidesTimeZone::getUserTimezone());
        $this->setDataScope(DataScopeFactory::getNoScope());
        $this->setIsmsc(IsmscRegister::getNames());
        $this->setOrder($this->getDefaultOrder());
        $pagination       = new TablePagination();
        $pagination->setItemPerPage(\SAM_INTERROGATIONS_ITEMS_PER_PAGE);
        $pagination->setPagePerGroup(\SAM_INTERROGATIONS_PAGES_PER_GROUP);
        $this->pagination = $pagination;
    }

    /**
     * 
     * @return string
     */
    public function getTimeInterval()
    {
        return $this->timeInterval;
    }

    /**
     * 
     * @return string
     */
    public function getDataGrouping()
    {
        return $this->dataGrouping;
    }

    /**
     * 
     * @return DateTime
     */
    public function getStartTime()
    {
        return $this->startTime;
    }

    /**
     * @return DateTime 
     */
    public function getEndTime()
    {
        return $this->endTime;
    }

    /**
     * 
     * @return DateTimeZone
     */
    public function getTimeZone()
    {
        return $this->timeZone;
    }

    /**
     * 
     * @return string
     */
    public function getViewMode()
    {
        return $this->viewMode;
    }

    /**
     * 
     * @return int
     */
    public function getRequestUserFilter()
    {
        return $this->requestUserFilter;
    }

    /**
     * 
     * @return int
     */
    public function getRequestUserGroupFilter()
    {
        return $this->requestUserGroupFilter;
    }

    /**
     * 
     * @return DataScope
     */
    public function getDataScope()
    {
        return $this->dataScope;
    }

    /**
     * 
     * @return array
     */
    public function getOrder()
    {
        return $this->order;
    }

    public function isPaginated()
    {
        return $this->paginationEnabled;
    }

    public function isFinestDataGrouping()
    {
        $scope = $this->dataScope;
        if (($this->dataGrouping === self::GROUP_BY_USER) || $scope->isNone() || $scope->isOwnOnly()) {
            return true;
        } else {
            return false;
        }
    }

    public function getFinerDataGrouping()
    {
        if ($this->dataGrouping == self::GROUP_BY_SYSTEM) {
            return self::GROUP_BY_USER_GROUP;
        } else {
            return self::GROUP_BY_USER;
        }
    }

    /**
     * 
     * @param boolean $enabled
     */
    public function setPaginationEnabled($enabled)
    {
        $this->paginationEnabled = (bool) $enabled;
        if ($this->paginationEnabled) {
            $this->pagination->setPage($this->selectedPage);
        }
    }

    /**
     * 
     * @param int $page
     */
    public function setSelectedPage($page)
    {
        $this->selectedPage = (int) $page;
        $this->pagination->setPage($page);
    }

    /**
     * 
     * @param int|null $total If not null update the pagination
     * @return TablePagination
     */
    public function getPagination($total = null)
    {
        if (null !== $total) {
            $this->pagination->setTotalItem($total);
            $this->pagination->setPage($this->selectedPage);
        }
        return $this->pagination;
    }

    /**
     * @return int
     */
    public function getSelectedPage()
    {
        return $this->selectedPage;
    }

    /**
     * 
     * @return array
     */
    public function getIsmsc()
    {
        return $this->ismsc;
    }

    /**
     * 
     * @param array $ismsc
     */
    public function setIsmsc(array $ismsc)
    {
        $this->ismsc = $ismsc;
    }

    /**
     * 
     * @param string $mode
     * @return static Return itself. It's a fluent setter
     */
    public function setTimeInterval($mode)
    {
        $this->timeInterval = empty($mode) ? self::INTERVAL_HOUR : $mode;
        return $this;
    }

    /**
     * 
     * @param string $mode
     * @return static Return itself. It's a fluent setter
     */
    public function setDataGrouping($mode)
    {
        $this->dataGrouping = empty($mode) ? self::GROUP_BY_SYSTEM : $mode;
        return $this;
    }

    /**
     * 
     * @param DateTime $startTime
     * @return static Return itself. It's a fluent setter
     */
    public function setStartTime(DateTime $startTime = null)
    {
        if ($startTime) {
            $startTime->setTimezone(AltamidesTimeZone::getServerTimezone());
        }
        $this->startTime = $startTime;
        return $this;
    }

    /**
     * 
     * @param DateTime $endTime
     * @return static Return itself. It's a fluent setter
     */
    public function setEndTime(DateTime $endTime = null)
    {
        if ($endTime) {
            $endTime->setTimezone(AltamidesTimeZone::getServerTimezone());
        }
        $this->endTime = $endTime;
        return $this;
    }

    /**
     * 
     * @param DateTimeZone $timeZone
     * @return static Return itself. It's a fluent setter
     */
    public function setTimeZone(DateTimeZone $timeZone)
    {
        $this->timeZone = $timeZone;
        return $this;
    }

    /**
     * 
     * @param string $viewMode
     * @return static Return itself. It's a fluent setter
     */
    public function setViewMode($viewMode)
    {
        if ($viewMode === self::VIEW_MODE_SCHEDULE) {
            $this->viewMode = self::VIEW_MODE_SCHEDULE;
        } else {
            $this->viewMode = self::VIEW_MODE_INTERROGATION;
        }
        return $this;
    }
    
    /**
     * @param string $showDeleted
     * @return static Return itself. It's a fluent setter
     */
    public function setShowDeleted($showDeleted)
    {
        $this->showDeleted = $showDeleted;
        return $this;
    }
    
    /**    
     * @return string
     */
    public function getShowDeleted()
    {        
        return $this->showDeleted;
    }

    /**
     * 
     * @param int $userId
     * @return static Return itself. It's a fluent setter
     */
    public function setRequestUserFilter($userId)
    {
        $this->requestUserFilter = (null === $userId) ? null : (int) $userId;
        return $this;
    }

    /**
     * 
     * @param int $groupId
     * @return static Return itself. It's a fluent setter
     */
    public function setRequestUserGroupFilter($groupId)
    {
        $this->requestUserGroupFilter = (null === $groupId) ? null : (int) $groupId;
        return $this;
    }

    /**
     * 
     * @param DataScope $dataScope
     * @return static Return itself. It's a fluent setter
     */
    public function setDataScope(DataScope $dataScope)
    {
        $this->dataScope = $dataScope;
        return $this;
    }

    /**
     * 
     * @param array $order
     * @return static Return itself. It's a fluent setter
     */
    public function setOrder($order = [])
    {
        $this->order = empty($order) ? $this->getDefaultOrder() : $order;
        return $this;
    }

    public function getDefaultOrder()
    {
        return [
            QueryReport::FIELD_REQUESTER_NAME => DataOrder::ASCENDING,
            QueryReport::FIELD_START_TIME     => DataOrder::DESCENDING,
        ];
    }

}
