<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sam\Interrogations;

use DateTimeImmutable;
use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Data\TablePagination;
use Firstwap\Altamides\Db\AltamidesDb;
use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Time\TimeFormat;
use Firstwap\Altamides\Time\TimeZoneUtil;
use Firstwap\Firstphp\Db\Sql\SelectQueryBuilder;
use PDO;
use PDOException;
use PDOStatement;

require_once \ALTAMIDES_LIB_PATH . 'common/Altatime.php';

/**
 * Description of ReportQueryBuilder
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class QueryReport
{

    const FIELD_REQUESTER_ID = 'requester_id';
    const FIELD_REQUESTER_NAME = 'requester_name';
    const FIELD_TIME_SLOT_GROUP = 'time_slot_group';
    const FIELD_START_TIME = 'start_time';
    const FIELD_END_TIME = 'end_time';
    const FIELD_REQUEST_COUNT = 'request_count';
    const FIELD_SCHEDULED_REQUEST_COUNT = 'scheduled_request_count';
    const FIELD_METHOD_ATI = 'method_ati';
    const FIELD_METHOD_PSI = 'method_psi';
    const FIELD_METHOD_ENH = 'method_eng';
    const FIELD_METHOD_FST = 'method_fst';
    const FIELD_METHOD_HA = 'method_ha';
    const FIELD_NO_PHONE_STATUS = 'no_phone_status';
    const FIELD_HAS_CELL_REF = 'has_cell_ref';
    const FIELD_HAS_TARGET_COORDINATE = 'has_target_latlon';
    const FIELD_PREFIX_ISMSC = 'use_ismsc_';

    /**
     *
     * @var QueryReportOptions
     */
    protected $options;
    /**
     *
     * @var TablePagination
     */
    protected $pagination;

    public function __construct(QueryReportOptions $options = null)
    {
        $this->setOptions($options);        
    }

    /**
     * 
     * @return QueryReportOptions
     */
    public function getOptions()
    {
        return $this->options;
    }

    /**
     * 
     * @param $options
     */
    public function setOptions(QueryReportOptions $options)
    {
        $this->options = $options;
    }

    /**
     * 
     * @param PDO $pdo
     * @throws PDOException
     * @return PDOStatement
     */
    public function generate($order="")
    {
        $pdo = DbManager::getInstance()->getPdo(AltamidesDb::$locationApi);
        $sqlBuilder = $this->createQueryBuilder($pdo);
        $this->selectColumns($sqlBuilder);
        $this->applyOrder($sqlBuilder, $order);
        $this->applyLimit($sqlBuilder);
        $selectSql = $sqlBuilder->getSql();
        $stmt = $pdo->query($selectSql);
        return $stmt;
    }

    public function countRows()
    {
        $pdo = DbManager::getInstance()->getPdo(AltamidesDb::$locationApi);
        $countBuilder = $this->createQueryBuilder($pdo);
        $countBuilder->select('lqt.TIMESLOT_KEY');
        $countTable = $countBuilder->aliasQuery($countBuilder->getSql(), 'c');
        $wrapperBuilder = new SelectQueryBuilder($pdo);
        $wrapperBuilder->useTable($countTable)
                ->select('count(*)');
        $stmt = $pdo->query($wrapperBuilder->getSql());
        return (int) $stmt->fetchColumn(0);
    }

    /**
     * 
     * @param PDO $pdo
     * @return SelectQueryBuilder
     */
    protected function createQueryBuilder(PDO $pdo)
    {
        $lsuDb = AltamidesDb::$locationApi;
        $ssoDb = AltamidesDb::$sso;

        $builder = new SelectQueryBuilder($pdo);
        $timeslotQuery = $this->createTimeSlotQuery($pdo);
        $timeslotTable = $builder->aliasQuery($timeslotQuery, 'lqt');
        $userGroupFilter = $this->options->getRequestUserGroupFilter();
        
        $grouping = $this->options->getDataGrouping();
        if ($grouping === QueryReportOptions::GROUP_BY_USER_GROUP) {
            $builder->useTable($ssoDb . '.CLIENT', 'c')
                    ->crossjoin($timeslotTable)
                    ->leftJoin($lsuDb . '.LOCATION_QUERY_STATISTIC', 'lqs',
                            '(lqt.TIMESLOT_KEY = lqs.TIMESLOT_KEY) and (c.CLIENT_ID = lqs.REQUEST_USER_GROUP_ID)');
        } elseif ($grouping == QueryReportOptions::GROUP_BY_USER) {
            $userTable = $builder->aliasQuery($this->createGroupUserFromQuery($pdo), 'u');
            $builder->useTable($userTable)
                    ->crossjoin($timeslotTable);
            
            if (!empty($userGroupFilter)) {
                $lqsTable = $builder->aliasQuery($this->createNotEmptyUserGroupQuery($pdo), 'lqs');
                $builder->leftJoin($lqsTable, null,
                            '(lqt.TIMESLOT_KEY = lqs.TIMESLOT_KEY) and (u.USER_ID = lqs.REQUEST_USER_ID)');
            } else {
                $builder->leftJoin($lsuDb . '.LOCATION_QUERY_STATISTIC', 'lqs',
                            '(lqt.TIMESLOT_KEY = lqs.TIMESLOT_KEY) and (u.USER_ID = lqs.REQUEST_USER_ID)');
            }
                    
        } else {
            $builder->useTable($ssoDb . '.USER', 'u')
                    ->crossjoin($timeslotTable)
                    ->leftJoin($lsuDb . '.LOCATION_QUERY_STATISTIC', 'lqs',
                            '(lqt.TIMESLOT_KEY = lqs.TIMESLOT_KEY) and (u.USER_ID = lqs.REQUEST_USER_ID)');
        }

        $this->applyDataGrouping($builder);
        $this->setResultFilter($builder);
        return $builder;
    }

    /**
     * 
     * @param PDO $pdo
     * @return SelectQueryBuilder
     */
    protected function createTimeSlotQuery(PDO $pdo)
    {
        $builder = new SelectQueryBuilder($pdo);
        
        $timeShiftExpr = 'TIMESLOT_KEY ';

        $timeGroupColumn = $this->getTimeGroupColumn();
        $builder->select($timeShiftExpr)
                ->select('TIMESLOT_START')
                ->select('TIMESLOT_END')
                ->select($timeGroupColumn, 'GROUP_KEY')
                ->useTable(AltamidesDb::$locationApi . '.LOCATION_QUERY_TIMESLOT');
        $this->applyTimeRange($builder);
        return $builder->getSql();
    }
    
    /**
     * 
     * @param PDO $pdo
     * @return SelectQueryBuilder
     */
    protected function createGroupUserFromQuery($pdo)
    {
        $builder = new SelectQueryBuilder($pdo);
        $lsuDb = AltamidesDb::$locationApi;
        $ssoDb = AltamidesDb::$sso;
        
        $options = $this->options;
        $userGroupFilter = $options->getRequestUserGroupFilter();

        $builder->select('USER_ID')
                ->select('USER_NAME')
                ->select('CLIENT_ID')
                ->useTable($ssoDb . '.USER', 'u')
                ->leftJoin($lsuDb . '.LOCATION_QUERY_STATISTIC', 'lqs', '(u.USER_ID = lqs.REQUEST_USER_ID)')
                ->group('u.USER_ID');
        
        if (!empty($userGroupFilter)) {
            $builder->where('(u.CLIENT_ID = ' . $builder->quoteValue($userGroupFilter) . ' OR lqs.REQUEST_USER_GROUP_ID = ' . $builder->quoteValue($userGroupFilter) . ')');
        }
        
        $isShowDeleted = $options->getShowDeleted();
        if ($isShowDeleted === 'false') {
            $builder->where('u.DELETED = 0');
        }
        
        return $builder->getSql();
    }

    /**
     * 
     * @param PDO $pdo
     * @return SelectQueryBuilder
     */
    protected function createNotEmptyUserGroupQuery(PDO $pdo)
    {
        $builder = new SelectQueryBuilder($pdo);
        
        $lsuDb = AltamidesDb::$locationApi;        
        
        $options = $this->options;
        $userGroupFilter = $options->getRequestUserGroupFilter();
        
        $builder->select('*')                
                ->useTable($lsuDb. '.LOCATION_QUERY_STATISTIC')
                ->where('REQUEST_USER_GROUP_ID = ' . $builder->quoteValue($userGroupFilter));
        return $builder->getSql();
    }
    
    protected function selectColumns(SelectQueryBuilder $builder)
    {
        $reportTimeZone = $this->options->getTimeZone()->getName();
        $this->selectUserColumns($builder);
        $this->selectIsmsc($builder);
        $builder->select('lqt.GROUP_KEY', self::FIELD_TIME_SLOT_GROUP)
                // reverse the effect of timeshift in timeslot query
                ->select("MIN(lqt.TIMESLOT_START)", self::FIELD_START_TIME)
                ->select("MAX(lqt.TIMESLOT_END)", self::FIELD_END_TIME)
                ->select('sum(ifnull(lqs.REQUEST_COUNT, 0))',
                        self::FIELD_REQUEST_COUNT)
                ->select('sum(ifnull(lqs.SCHEDULED_REQUEST_COUNT, 0))',
                        'scheduled_request_count')
                ->select('sum(ifnull(lqs.USE_METHOD_ATI, 0))',
                        self::FIELD_METHOD_ATI)
                ->select('sum(ifnull(lqs.USE_METHOD_PSI, 0))',
                        self::FIELD_METHOD_PSI)
                ->select('sum(ifnull(lqs.USE_METHOD_ENH, 0))',
                        self::FIELD_METHOD_ENH)
                ->select('sum(ifnull(lqs.USE_METHOD_FST, 0))',
                        self::FIELD_METHOD_FST)
                ->select('sum(ifnull(lqs.USE_HA, 0))', self::FIELD_METHOD_HA)
                ->select('sum(ifnull(lqs.NO_PHONE_STATUS, 0))',
                        self::FIELD_NO_PHONE_STATUS)
                ->select('sum(ifnull(lqs.HAS_CELL_REF, 0))',
                        self::FIELD_HAS_CELL_REF)
                ->select('sum(ifnull(lqs.HAS_TARGET_COORDINATE, 0))',
                        self::FIELD_HAS_TARGET_COORDINATE);
    }
    
    protected function selectUserColumns(SelectQueryBuilder $builder)
    {
        $grouping = $this->options->getDataGrouping();

        if ($grouping === QueryReportOptions::GROUP_BY_USER_GROUP) {
            $builder->select('c.CLIENT_ID', self::FIELD_REQUESTER_ID)
                    ->select('c.CLIENT_NAME', self::FIELD_REQUESTER_NAME);
        } elseif ($grouping == QueryReportOptions::GROUP_BY_USER) {
            $builder->select('u.USER_ID', self::FIELD_REQUESTER_ID)
                    ->select('u.USER_NAME', self::FIELD_REQUESTER_NAME);
        } else {
            $builder->select('0', self::FIELD_REQUESTER_ID)
                    ->select("'System'", self::FIELD_REQUESTER_NAME);
        }
    }

    /**
     * 
     * @param SelectQueryBuilder $query
     */
    protected function selectIsmsc(SelectQueryBuilder $query)
    {
        foreach ($this->options->getIsmsc() as $node) {
            $escNode = $query->quoteValue($node);
            $query->select("sum(ifnull(lqs.ISMSC_NAME = $escNode, 0))",
                    self::FIELD_PREFIX_ISMSC . $node);
        }
    }

    /**
     * 
     * @param SelectQueryBuilder $builder
     */
    protected function applyOrder(SelectQueryBuilder $builder,$order)
    {   if($order!=""){
        $this->options->setOrder($order);
        $builder->reorder($this->options->getOrder());
    }
        $builder->reorder($this->options->getOrder());
    }

    protected function getTimeGroupColumn()
    {
        $timeConverter =  new \gmtTime();
        $clientTz = $timeConverter->getClientGmtOffset();
        $clientTime = ($clientTz=='' || $clientTz==='UTC' || $clientTz==='GMT')?"+00:00":ltrim($clientTz,"GMT");
        $startTime = 'convert_tz(TIMESLOT_START, "+00:00", "'.$clientTime.'")';
        $groupByHour = 'date_format('.$startTime.', "%Y%m%d%H")';
        $groupBy8Hour = 'concat(date_format('.$startTime.', "%Y%m%d"), floor(hour('.$startTime.')/8))';
        $groupBy12Hour = 'concat(date_format('.$startTime.', "%Y%m%d"), if(date_format('.$startTime.', "%p") = "am", 0,1))';
        $groupByDate = 'date_format('.$startTime.', "%Y%m%d")'; 
        $groupByWeek = 'date_format('.$startTime.', "%X%V")';
        $groupByMonth = 'date_format('.$startTime.', "%Y%m")';
        $groupByQuarter = ' concat(year('.$startTime.'),quarter('.$startTime.'))';
        $groupByYear =  'year('.$startTime.')';
        
        $interval = $this->options->getTimeInterval();
        switch ($interval) {
            case QueryReportOptions::INTERVAL_HOUR_8:
                return $groupBy8Hour;
            case QueryReportOptions::INTERVAL_HOUR_12:
                return $groupBy12Hour;
            case QueryReportOptions::INTERVAL_DAY:
                return $groupByDate;
            case QueryReportOptions::INTERVAL_WEEK:
                return $groupByWeek;
            case QueryReportOptions::INTERVAL_MONTH:
                return $groupByMonth;
            case QueryReportOptions::INTERVAL_QUARTER:
                return $groupByQuarter;
            case QueryReportOptions::INTERVAL_YEAR:
                return $groupByYear;
            default:
                \trigger_error("Unsupported report interval '$interval'. Fall back to default.",
                        \E_USER_NOTICE);
            case QueryReportOptions::INTERVAL_HOUR:
                return $groupByHour;
        }
    }

    protected function applyDataGrouping(SelectQueryBuilder $query)
    {
        $grouping = $this->options->getDataGrouping();
        if ($grouping === QueryReportOptions::GROUP_BY_USER) {
            $query->group('u.USER_ID');
        } elseif ($grouping === QueryReportOptions::GROUP_BY_USER_GROUP) {
            $query->group('c.CLIENT_ID');
        }
        $query->group('lqt.GROUP_KEY');
    }

    protected function applyTimeRange(SelectQueryBuilder $query, $tablePrefix = '')
    {
        $timeConverter =  new \gmtTime();
        $options = $this->options;
        if ('' !== (string) $tablePrefix) {
            $tablePrefix .= '.';
        }
                
        $startTime = $options->getStartTime();       
        if (!empty($startTime)) {            
             $startTimeUTC = $timeConverter->localToGmt($startTime->format(TimeFormat::TIMESTAMP));
             $startTimeSlotEsc = $query->quoteValue($startTimeUTC);
            $query->where("{$tablePrefix}TIMESLOT_END > $startTimeSlotEsc");
        }
        
        $endTime = $options->getEndTime();
        if (!empty($endTime)) {
            $endTimeUTC = $timeConverter->localToGmt($endTime->format(TimeFormat::TIMESTAMP));
            $endTimeSlotEsc = $query->quoteValue($endTimeUTC);
            $query->where("{$tablePrefix}TIMESLOT_START < $endTimeSlotEsc");
        }
    }
    
    /**
     * 
     * @param SelectQueryBuilder $builder
     */
    protected function setResultFilter(SelectQueryBuilder $builder)
    {
        $this->applyDataScope($builder);
        $this->applyTimeRange($builder, 'lqt');
        $options = $this->options;
        $userFilter = $options->getRequestUserFilter();
        $userGroupFilter = $options->getRequestUserGroupFilter();
        $dataGrouping = $options->getDataGrouping();
        if ($dataGrouping === QueryReportOptions::GROUP_BY_USER) {
            if (!empty($userFilter)) {                
                $builder->where('(u.USER_ID = ' . $builder->quoteValue($userFilter) . ' OR lqs.REQUEST_USER_ID = ' . $builder->quoteValue($userFilter) . ')');
            }
        }
        if ($dataGrouping === QueryReportOptions::GROUP_BY_USER_GROUP) {
            if (!empty($userGroupFilter)) {
                $builder->where('(c.CLIENT_ID = ' . $builder->quoteValue($userGroupFilter) . ' OR lqs.REQUEST_USER_GROUP_ID = ' . $builder->quoteValue($userGroupFilter) .')');
            }            
        }
    }

    /**
     * 
     * @param SelectQueryBuilder $builder
     */
    protected function applyDataScope(SelectQueryBuilder $builder)
    {
        $scope = $this->getOptions()->getDataScope();
        $userId = $builder->quoteValue($scope->getUserFilter(), PDO::PARAM_INT);
        $userGroupId = $builder->quoteValue($scope->getGroupFilter(),
                PDO::PARAM_INT);
        $showDeleted = $builder->quoteValue($scope->getShowDeletedFilter(),
                PDO::PARAM_STR);
        
        switch ($scope->getScope()) {
            case DataScope::SCOPE_GLOBAL:
                if($showDeleted === 'false') {
                    $builder->where('u.DELETED = false');
                }
                return;
            case DataScope::SCOPE_GROUP:
                $builder->where('c.CLIENT_ID = ' . $userGroupId);
                return;

            case DataScope::SCOPE_OWN:
                $builder->where('u.USER_ID = ' . $userId)
                        ->where('c.CLIENT_ID = ' . $userGroupId);
                return;
            case DataScope::SCOPE_NONE:
            default:
                $builder->where('false');
                return;
        }
    }

    /**
     * 
     * @param SelectQueryBuilder $query
     */
    protected function applyLimit(SelectQueryBuilder $query)
    {
        if (!$this->options->isPaginated()) {
            return;
        }
        $count = $this->countRows();
        $pagination = $this->options->getPagination($count);
        $limit = $pagination->getItemPerPage();
        $offset = $pagination->getStartItem();
        $query->limit($limit, $offset);
    }

    protected function getTimeSlotShift()
    {
        return TimeZoneUtil::calculateSecondOffset($this->options->getTimeZone());
    }

}
