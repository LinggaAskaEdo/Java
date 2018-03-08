<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Web\CommonInput;

use Exception;
use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Sso\UserList;
use Firstwap\Firstphp\Web\Form\SelectOptionsInput;

/**
 * Description of SelectUserInput
 *
 * @author Setia Budi Halim
 */
class SelectUserInput extends SelectOptionsInput
{

    /**
     *
     * @var DataScope
     */
    protected $scope;
    
    /**
     *
     * @var int|null
     */
    protected $groupFilter = null;

    /**
     * 
     * @param string $name
     * @param DataScope $scope
     * @param int|null $groupFilter Group specific or all
     */
    public function __construct($name, DataScope $scope, $groupFilter = null)
    {
        parent::__construct($name);
        $this->applyGroupFilter($groupFilter, false);
        $this->setScope($scope);
    }

    public function getScope()
    {
        return $this->scope;
    }

    /**
     * 
     * @param DataScope $scope
     */
    public function setScope(DataScope $scope)
    {
        $old = $this->scope;
        $this->scope = $scope;
        if (empty($old) || !$scope->isEqual($old)) {
            $this->updateUserList();
        }
    }

    public function hasGroupFilter()
    {
        return null !== $this->groupFilter;
    }

    public function getGroupFilter()
    {
        return $this->groupFilter;
    }

    /**
     * Filter the user list to specific group
     * @param int|null $groupId The group ID, null means all possible users
     */
    public function setGroupFilter($groupId)
    {
        $this->applyGroupFilter($groupId, true);
    }

    protected function updateUserList()
    {
        $scope = $this->scope;
        try {
            $userList = UserList::getInScopeUsers($scope,
                            $this->getGroupFilter());
        } catch (Exception $e) {
            error_log($e);
            $userList = [];
        }
        $this->getFilter()->setMap($userList);
    }

    /**
     * Internal use
     * @param int $groupId
     * @param boolean $updateList Update the user list or not
     */
    private function applyGroupFilter($groupId, $updateList)
    {
        $old = $this->groupFilter;
        $this->groupFilter = (null === $groupId) ? null : $groupId;
        //already so
        if ($updateList && ($old !== $this->groupFilter)) {
            $this->updateUserList();
        }
    }

}
