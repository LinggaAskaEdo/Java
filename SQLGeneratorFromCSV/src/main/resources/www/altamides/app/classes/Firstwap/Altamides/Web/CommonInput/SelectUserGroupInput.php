<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Web\CommonInput;

use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Sso\UserGroupList;
use Firstwap\Firstphp\Web\Form\SelectOptionsInput;

/**
 * Description of SelectUserInput
 *
 * @author Setia Budi Halim
 */
class SelectUserGroupInput extends SelectOptionsInput
{

    public function __construct($name, DataScope $scope)
    {
        try {
            $groupList = UserGroupList::getInScopeGroups($scope);
        } catch (\Exception $e) {
            \error_log($e);
            $groupList = [];
        }

        if ($scope->isGroupOnly() || $scope->isOwnOnly()) {
            $default = $scope->getGroupFilter();
        } else {
            $default = null;
        }
        parent::__construct($name, $groupList, $default);
    }

}
