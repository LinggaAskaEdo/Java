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
class UserGroupList
{

    public static function getInScopeGroups(DataScope $scope)
    {
        switch ($scope->getScope()) {
            case DataScope::SCOPE_GLOBAL:
                return self::getGroups();

            case DataScope::SCOPE_GROUP:
            case DataScope::SCOPE_OWN:
                $ownGroup = self::getOwnGroupRecord();
                return [$ownGroup->getId() => $ownGroup->getName()];

            default:
                return [];
        }
    }

    public static function getGroups()
    {
        $pdo = ClassicApp::getPdo(AltamidesDb::$sso);
        $builder = new SelectQueryBuilder($pdo);
        $builder->useTable(AltamidesDb::$sso . '.CLIENT')
                ->select('CLIENT_ID')
                ->select('CLIENT_NAME')
                ->where('DELETED = 0')
                ->order('CLIENT_NAME');
        

        $stmt = $pdo->query($builder->getSql());
        if (!$stmt->rowCount()) {
            return [];
        } else {
            return $stmt->fetchAll(\PDO::FETCH_KEY_PAIR);
        }
    }

    /**
     * 
     * @return UserGroupRecord
     */
    public static function getOwnGroupRecord()
    {

        $group = new UserGroupRecord();
        $group->setId($_SESSION['CLIENT_ID']);
        $group->setName($_SESSION['CLIENT_NAME']);
        return $group;
    }

}
