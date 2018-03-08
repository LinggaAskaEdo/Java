<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sam\Interrogations;

use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Data\DataScopeFactory;
use Firstwap\Altamides\Time\AltamidesTimeZone;
use Firstwap\Altamides\Web\CommonInput\SelectUserGroupInput;
use Firstwap\Altamides\Web\CommonInput\SelectUserInput;
use Firstwap\Firstphp\Web\Form\DateTimeInput;
use Firstwap\Firstphp\Web\Form\GenericForm;
use Firstwap\Firstphp\Web\Form\IntegerNumberInput;
use Firstwap\Firstphp\Web\Form\KeyInput;
use Firstwap\Firstphp\Web\Form\SelectOptionsInput;
use Firstwap\Firstphp\Web\Form\TextInput;
use PageState;
use Firstwap\Altamides\Db\AltamidesDb;
use Firstwap\Firstphp\Db\Sql\SelectQueryBuilder;

require_once \ALTAMIDES_LIB_PATH . 'class.PageState.php';

/**
 * Description of QueryReportForm
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class QueryReportForm extends GenericForm
{

    const INPUT_TIME_INTERVAL = 'timeInterval';
    const INPUT_START_TIME = 'startTime';
    const INPUT_END_TIME = 'endTime';
    const INPUT_DATA_GROUPING = 'dataGrouping';
    const INPUT_USER = 'user';
    const INPUT_USER_GROUP = 'userGroup';
    const INPUT_CLEAR_PAGE_STATE = 'clearPageState';
    const INPUT_SHOW_DELETED_USER = 'showDeleted';
    const INPUT_PAGE = 'page';
    const INPUT_ORDER = 'order';
    const INPUT_VIEW_MODE = 'viewMode';

    /**
     *
     * @var PageState
     */
    protected $pageState;

    /**
     *
     * @var DateTimeInput
     */
    protected $startTimeInput;

    /**
     *
     * @var DateTimeInput
     */
    protected $endTimeInput;

    /**
     *
     * @var IntegerNumberInput
     */
    protected $pageInput;

    /**
     *
     * @var TextInput
     */
    protected $orderInput;

    /**
     *
     * @var SelectOptionsInput
     */
    protected $timeIntervalInput;

    /**
     *
     * @var SelectUserInput
     */
    protected $userFilterInput;
    
    /**
     *
     * @var ShowDeletedCheckbox
     */
    protected $showDeletedCheckbox;

    /**
     *
     * @var SelectUserGroupInput
     */
    protected $userGroupFilterInput;

    /**
     *
     * @var SelectOptionsInput
     */
    protected $viewModeInput;

    /**
     *
     * @var SelectOptionsInput
     */
    protected $dataGroupingInput;

    /**
     *
     * @var KeyInput
     */
    protected $clearPageStateButton;

    /**
     *
     * @var DataScope
     */
    protected $dataScope;

    public function __construct()
    {
        parent::__construct('javascript:void(0)');
        $this->dataScope = DataScopeFactory::getModuleDataScope('SAM');
        $this->createInputFields();
        $this->pageState = new PageState('sam/interrogations');
    }

    /**
     * @return QueryReport
     */
    public function getReport()
    {
        $this->input();
        $options = $this->createReportOptions();
        $report = new QueryReport($options);
        return $report;
    }

    /**
     * @return QueryReportOptions 
     */
    public function createReportOptions()
    {
        $this->dataScope->setShowDeletedFilter($this->showDeletedCheckbox->getValue());
        
        $options = new QueryReportOptions();
        $options->setTimeZone(AltamidesTimeZone::getUserTimezone());
        $options->setTimeInterval($this->timeIntervalInput->getValue());
        $options->setStartTime($this->startTimeInput->getValue());
        $options->setEndTime($this->endTimeInput->getValue());
        $options->setShowDeleted($this->showDeletedCheckbox->getValue());
        $options->setDataScope($this->dataScope);
        $options->setSelectedPage($this->getPageInput()->getValue());
        $options->setRequestUserFilter($this->userFilterInput->getValue());
        $options->setRequestUserGroupFilter($this->userGroupFilterInput->getValue()); 
        $options->setDataGrouping($this->dataGroupingInput->getValue());
        $options->setViewMode($this->viewModeInput->getValue());
        return $options;
    }

    /**
     * 
     * @return PageState
     */
    public function getPageState()
    {
        return $this->pageState;
    }

    public function updatePageState()
    {
        if ($this->clearPageStateButton->isSubmitted()) {
            $this->pageState->clear();
        } else {
            $this->pageState->setParamsArray($this->getValues());
        }
    }

    public function readPageState()
    {
        $values = $this->pageState->getAllParams();
        if (empty($values)) {
            return;
        }
        
        $this->timeIntervalInput->setValue($values['timeInterval']);
        $this->dataGroupingInput->setValue($values['dataGrouping']);
        $this->viewModeInput->setValue($values['viewMode']);
        $this->startTimeInput->setValue($values['startTime']);
        $this->endTimeInput->setValue($values['endTime']);
        $this->userFilterInput->setValue($values['user']);
        $this->userGroupFilterInput->setValue($values['userGroup']);
        $this->showDeletedCheckbox->setValue($values['showDeleted']);
        $this->orderInput->setValue($values['order']);
        $this->pageInput->setValue($values['page']);
    }

    public function getStartTimeInput()
    {
        return $this->startTimeInput;
    }

    public function getEndTimeInput()
    {
        return $this->endTimeInput;
    }

    public function getPageInput()
    {
        return $this->pageInput;
    }

    public function getOrderInput()
    {
        return $this->orderInput;
    }

    public function getTimeIntervalInput()
    {
        return $this->timeIntervalInput;
    }

    public function getViewModeInput()
    {
        return $this->viewModeInput;
    }

    public function getUserFilterInput()
    {
        return $this->userFilterInput;
    }

    public function getUserGroupFilterInput()
    {
        return $this->userGroupFilterInput;
    }

    public function getClearPageStateButton()
    {
        return $this->clearPageStateButton;
    }

    public function getDataScope()
    {
        return $this->dataScope;
    }
    
    public function getShowDeletedCheckbox()
    {
        return $this->showDeletedCheckbox;
    }

    public function getDataGroupingInput()
    {
        return $this->dataGroupingInput;
    }

    protected function createInputFields()
    {
        $this->clearPageStateButton = new KeyInput('clearPageState', $this);
        $this->orderInput = new TextInput('order');
        $this->showDeletedCheckbox = new TextInput('showDeleted');
        $this->pageInput = new IntegerNumberInput('page', 1);
        $this->pageInput->setDefault(1);

        $this->startTimeInput = new DateTimeInput('startTime');
        $this->startTimeInput->getFilter()->setNullable(true);
        $this->endTimeInput = new DateTimeInput('endTime');
        $this->endTimeInput->getFilter()->setNullable(true);

        $this->timeIntervalInput = new SelectOptionsInput('timeInterval',
                $this->getTimeIntervalOptions(),
                QueryReportOptions::INTERVAL_HOUR);

        $this->createDataGroupingInput();
        $this->createUserAndGroupInput();

        $this->viewModeInput = new SelectOptionsInput('viewMode',
                $this->getViewModeOptions(),
                QueryReportOptions::VIEW_MODE_INTERROGATION);

        $this->registerInput($this->orderInput);
        $this->registerInput($this->pageInput);
        $this->registerInput($this->startTimeInput);
        $this->registerInput($this->endTimeInput);
        $this->registerInput($this->timeIntervalInput);
        $this->registerInput($this->dataGroupingInput);
        $this->registerInput($this->userGroupFilterInput);
        $this->registerInput($this->showDeletedCheckbox);
        $this->registerInput($this->userFilterInput);
        $this->registerInput($this->viewModeInput);
    }

    protected function createUserAndGroupInput()
    {
        $this->userGroupFilterInput = new SelectUserGroupInput('userGroup',
                $this->dataScope);
        $this->userGroupFilterInput->enableWelcomeOption(true);
        $this->userGroupFilterInput->setWelcomeOption('-- All --', null);

        $this->userFilterInput = new SelectUserInput('user',
                $this->dataScope);
        $this->userFilterInput->enableWelcomeOption(true);
        $this->userFilterInput->setWelcomeOption('-- All --', null);

        $grouping = $this->getDataGroupingInput()->getValue();
        switch ($grouping) {
            case QueryReportOptions::GROUP_BY_SYSTEM:
                $this->userFilterInput->setDisabled(true);
                $this->userGroupFilterInput->setDisabled(true);
                break;
            case QueryReportOptions::GROUP_BY_USER_GROUP:
                $this->userFilterInput->setDisabled(true);
                break;
        }

        $userInput = $this->userFilterInput;
        $userFilterUpdater = function($newGroup) use ($userInput) {
            $userInput->setGroupFilter($newGroup);
        };
        $this->userGroupFilterInput->attachValueObserver($userFilterUpdater);
    }

    protected function getViewModeOptions()
    {
        $dictionary = ClassicApp::getDictionary('system_usage', 'sam');
        return [
            QueryReportOptions::VIEW_MODE_INTERROGATION => 'Interrogation',
            QueryReportOptions::VIEW_MODE_SCHEDULE => 'Schedule',
        ];
    }

    /**
     * 
     */
    protected function createDataGroupingInput()
    {
        $scope = $this->dataScope;
        $dictionary = ClassicApp::getDictionary('system_usage', 'sam');

        if ($scope->isUnrestricted()) {
            $options = [
                QueryReportOptions::GROUP_BY_SYSTEM => $dictionary['form_group_by_opt_system'],
                QueryReportOptions::GROUP_BY_USER_GROUP => $dictionary['form_group_by_opt_group'],
                QueryReportOptions::GROUP_BY_USER => $dictionary['form_group_by_opt_user'],
            ];
            $default = QueryReportOptions::GROUP_BY_SYSTEM;
        } elseif ($scope->isGroupOnly()) {
            $options = [
                QueryReportOptions::GROUP_BY_USER_GROUP => $dictionary['form_group_by_opt_group'],
                QueryReportOptions::GROUP_BY_USER => $dictionary['form_group_by_opt_user'],
            ];
            $default = QueryReportOptions::GROUP_BY_USER_GROUP;
        } elseif ($scope->isOwnOnly()) {
            $options = [
                QueryReportOptions::GROUP_BY_USER => $dictionary['form_group_by_opt_user'],
            ];
            $default = QueryReportOptions::GROUP_BY_USER;
        } else {
            $options = [];
            $default = null;
        }
        $input = new SelectOptionsInput('dataGrouping', $options, $default);
        $this->dataGroupingInput = $input;
    }

    protected function getTimeIntervalOptions()
    {
        $dictionary = ClassicApp::getDictionary('system_usage', 'sam');
        return [
            QueryReportOptions::INTERVAL_HOUR => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_HOUR],
            QueryReportOptions::INTERVAL_HOUR_8 => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_HOUR_8],
            QueryReportOptions::INTERVAL_HOUR_12 => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_HOUR_12],
            QueryReportOptions::INTERVAL_DAY => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_DAY],
            QueryReportOptions::INTERVAL_WEEK => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_WEEK],
            QueryReportOptions::INTERVAL_MONTH => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_MONTH],
            QueryReportOptions::INTERVAL_QUARTER => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_QUARTER],
            QueryReportOptions::INTERVAL_YEAR => $dictionary['form_interval_' . QueryReportOptions::INTERVAL_YEAR],
        ];
    }
    
    /**
     * Get all users including deleted user for select options purpose.
     * 
     * @return array
     */
    public function getUserOptions()
    {
        // get all users include deleted users
        $userOptions = [];
        $userOptions[""] = "-- All --";
        
        $pdo = ClassicApp::getPdo(AltamidesDb::$sso);
        $builder = new SelectQueryBuilder($pdo);
        $builder->useTable(AltamidesDb::$sso . '.USER')
                ->select('USER_ID')
                ->select('USER_NAME')
                ->select('HEX(DELETED) AS DELETED')
                ->where('DELETED = 0')
                ->order('USER_NAME');
        $stmt = $pdo->query($builder->getSql());
        if ($stmt->rowCount() > 0) {
            $userData = $stmt->fetchAll(\PDO::FETCH_ASSOC);
        }
        
        foreach ($userData as $key => $value){
            $userOptions[$value['USER_ID']] = $value['USER_NAME'];
            $userOptions[$value['USER_ID']] .= ($value['DELETED']) ? " (deleted)" : "";
        }
        
        return $userOptions;
    }
}
