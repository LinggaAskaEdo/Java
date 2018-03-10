<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Sso;

use Firstwap\Altamides\Data\DataScope;
use Firstwap\Altamides\Controller\ClassicApp;
use Firstwap\Firstphp\Db\Sql\SelectQueryBuilder;
use Firstwap\Altamides\Db\AltamidesDb;

/**
 * Description of UserList
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
class UserList
{

    /**
     * 
     * @param DataScope $scope
     * @param type $groupId
     * @param type $showDeleted
     * @return type
     */
    public static function getInScopeUsers(DataScope $scope, $groupId = null, $showDeleted = 0)
    {
        try {
            if ($scope->isNone()) {
                return [];
            }

            $cleanGroupId = (int) $groupId;
            if ($scope->isUnrestricted()) {
                $filterByGroup = null !== $groupId;
                return $filterByGroup ? self::getGroupUsers($cleanGroupId, $showDeleted) : self::getUsers($showDeleted);
            }

            if ($scope->isGroupOnly()) {
                return self::getGroupUsers($scope->getGroupFilter(), $showDeleted);
            }

            if ($scope->isOwnOnly()) {
                $ownUser = self::getOwnUserRecord();
                return [$ownUser->getId() => $ownUser->getName()];
            }

            // failed to satisfy parameters validity requirements
            return [];
        } catch (Exception $e) {
            \error_log("$e");
            return [];
        }
    }

    /**
     * 
     * @return array 1D array In format [UserID => UserName]
     */
    public static function getUsers($showDeleted = 0)
    {
        $pdo = ClassicApp::getPdo(AltamidesDb::$sso);
        $builder = new SelectQueryBuilder($pdo);
        $wClause = ($showDeleted == 1) ? "1" : "DELETED = $showDeleted";
        $builder->useTable(AltamidesDb::$sso . '.USER')
                ->select('USER_ID')
                ->select('USER_NAME')
                ->select('HEX(DELETED) AS DELETED')
                ->where($wClause)
                ->order('USER_NAME');
        $stmt = $pdo->query($builder->getSql());
        if (!$stmt->rowCount()) {
            return [];
        } else {
            $userData = $stmt->fetchAll(\PDO::FETCH_ASSOC);
        }

        foreach ($userData as $key => $value) {
            $userOptions[$value['USER_ID']] = $value['USER_NAME'];
            $userOptions[$value['USER_ID']] .= ($value['DELETED']) ? " (deleted)" : "";
        }
        return $userOptions;
    }

    /**
     * 
     * @param int $groupId The group ID
     * @return array 1D array In format [UserID => UserName]
     */
    public static function getGroupUsers($groupId, $showDeleted = 0)
    {
        $pdo = ClassicApp::getPdo(AltamidesDb::$sso);
        $builder = new SelectQueryBuilder($pdo);
        $wClause = ($showDeleted == 1) ? "1" : "DELETED = $showDeleted";
        $builder->useTable(AltamidesDb::$sso . '.USER')
                ->select('USER_ID')
                ->select('USER_NAME')
                ->select('HEX(DELETED) AS DELETED')
                ->where($wClause)
                ->where('CLIENT_ID = ' . $builder->quoteValue($groupId))
                ->order('USER_NAME');
        $stmt = $pdo->query($builder->getSql());
        if (!$stmt->rowCount()) {
            return [];
        } else {
            $userData = $stmt->fetchAll(\PDO::FETCH_ASSOC);
        }

        foreach ($userData as $key => $value) {
            $userOptions[$value['USER_ID']] = $value['USER_NAME'];
            $userOptions[$value['USER_ID']] .= ($value['DELETED']) ? " (deleted)" : "";
        }
        return $userOptions;
    }

    /**
     * 
     * @return UserRecord
     */
    public static function getOwnUserRecord()
    {

        $ownUser = new UserRecord();
        $ownUser->setId($_SESSION['USER_ID']);
        $ownUser->setName($_SESSION['USER_NAME']);
        return $ownUser;
    }

}
