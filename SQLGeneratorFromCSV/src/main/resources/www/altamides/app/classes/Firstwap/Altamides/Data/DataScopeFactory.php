<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Data;

/**
 * Description of DataScopeFactory
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class DataScopeFactory
{

    /**
     * 
     * @param DataScope $moduleId
     */
    public static function getModuleDataScope($moduleId)
    {
        $userId = empty($_SESSION['userId']) ? 0 : $_SESSION['userId'];
        $userGroupId = empty($_SESSION['CLIENT_ID']) ? 0 : $_SESSION['CLIENT_ID'];
        
        if (\filterPage($moduleId . '_ACC_GLOBAL', false)) {
            return new DataScope(DataScope::SCOPE_GLOBAL);
        } elseif (\filterPage($moduleId . '_ACC_GROUP', false)) {
            return new DataScope(DataScope::SCOPE_GROUP, $userGroupId);
        } elseif (\filterPage($moduleId . '_ACC_OWN', false)) {
            return new DataScope(DataScope::SCOPE_OWN, $userGroupId, $userId);
        } else {
            return new DataScope(DataScope::SCOPE_NONE);
        }
    }

    /**
     * @return DataScope
     */
    public static function getNoScope()
    {
        return new DataScope(DataScope::SCOPE_NONE);
    }

}
