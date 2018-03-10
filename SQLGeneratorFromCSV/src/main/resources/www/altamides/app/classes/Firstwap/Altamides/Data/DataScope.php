<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

use InvalidArgumentException;

/**
 * This is a readonly object
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class DataScope
{

    const SCOPE_GLOBAL = 'GLOBAL';
    const SCOPE_GROUP = 'GROUP';
    const SCOPE_OWN = 'OWN';
    const SCOPE_NONE = 'NONE';

    protected $scope = self::SCOPE_NONE;
    protected $userFilter = null;
    protected $groupFilter = null;
    protected $showDeletedFilter = 'true';

    public function __construct($scope, $userGroupId = null, $userId = null, $showDeleted = 'true')
    {
        $this->setScope($scope, $userGroupId, $userId, $showDeleted);
    }

    public function __toString()
    {
        return "{$this->scope}<{$this->userFilter}:{$this->groupFilter}>";
    }

    public function isNone()
    {
        return $this->scope === self::SCOPE_NONE;
    }

    public function isUnrestricted()
    {
        return $this->scope === self::SCOPE_GLOBAL;
    }

    public function isGroupOnly()
    {
        return $this->scope === self::SCOPE_GROUP;
    }

    public function isOwnOnly()
    {
        return $this->scope === self::SCOPE_OWN;
    }

    public function getScope()
    {
        return $this->scope;
    }

    public function getUserFilter()
    {
        return $this->userFilter;
    }

    public function getGroupFilter()
    {
        return $this->groupFilter;
    }
    
    public function getShowDeletedFilter()
    {
        return $this->showDeletedFilter;
    }

    /**
     * 
     * @param DataScope $scope
     */
    public function isEqual(DataScope $scope)
    {
        return ($scope->getScope() == $this->getScope()) &&
                ($this->getUserFilter() === $this->getUserFilter()) &&
                ($this->getGroupFilter() === $this->getGroupFilter());
    }

    /**
     * 
     * @param string $scope Use constant SCOPE_* from this class
     * @param int|null $userGroupId
     * @param int|null $userId
     * @throws InvalidArgumentException
     */
    private function setScope($scope, $userGroupId = null, $userId = null, $showDeleted = 'true')
    {
        switch ($scope) {
            case self::SCOPE_GLOBAL:
                $userId = null;
                $userGroupId = null;
                break;

            case self::SCOPE_GROUP:
                $userId = null;
                $userGroupId = (int) $userGroupId;
                if (empty($userGroupId)) {
                    throw new InvalidArgumentException('User group ID can not be empty for GROUP scope');
                }
                break;

            case self::SCOPE_OWN:
                $userId = (int) $userId;
                $userGroupId = (int) $userGroupId;
                if (empty($userId)) {
                    throw new InvalidArgumentException('User ID can not be empty for OWN scope');
                }
                if (empty($userGroupId)) {
                    throw new InvalidArgumentException('User group ID can not be empty for OWN scope');
                }
                break;

            default:
                $userId = 0;
                $userGroupId = 0;
                break;
        }

        $this->scope = $scope;
        $this->userFilter = $userId;
        $this->groupFilter = $userGroupId;
        $this->showDeletedFilter = $showDeleted;
    }
    
    /**
     * @param string $showDeleted
     */
    public function setShowDeletedFilter($showDeleted)
    {
        $this->showDeletedFilter = $showDeleted;
    }

}
