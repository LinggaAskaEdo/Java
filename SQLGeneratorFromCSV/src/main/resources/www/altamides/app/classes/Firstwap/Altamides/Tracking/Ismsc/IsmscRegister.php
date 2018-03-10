<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Tracking\Ismsc;

use Firstwap\Altamides\Db\DbManager;
use Firstwap\Altamides\Db\AltamidesDb;
use Firstwap\Firstphp\Db\Sql\SelectQueryBuilder;

/**
 * Description of IsmscList
 *
 * @author Setia Budi Halim
 */
final class IsmscRegister
{

    /**
     *
     * @var IsmscRecord[]
     */
    private static $cache = null;

    /**
     * 
     * @return string[]
     */
    public static function getNames()
    {
        self::load();
        return \array_keys(self::$cache);
    }

    /**
     * 
     * @return IsmscRecord[]
     */
    public static function getList()
    {
        self::load();
        return self::$cache;
    }

    /**
     * Load and cache SMSC list
     * @return void
     */
    private static function load()
    {
        if (null !== self::$cache) {
            return;
        }

        $pdo = DbManager::getInstance()->getPdo(AltamidesDb::$locationApi);
        $sql = new SelectQueryBuilder($pdo);
        $sql->useTable(AltamidesDb::$locationApi . '.ISMSC_REGISTER')
                ->select('ISMSC_NAME')
                ->select('SERVICE_URL');
        
        $stmt = $pdo->query($sql->getSql());
        if (!$stmt->rowCount()) {
            self::$cache = [];
        }
        
        $stmt->setFetchMode(\PDO::FETCH_ASSOC);
        $list = [];
        foreach ($stmt as $data) {
            $name = $data['ISMSC_NAME'];
            if (isset($list[$name])) {
                \trigger_error('Duplicate iSMSC name, the earlier is kept',
                        \E_USER_NOTICE);
                continue;
            }
            $record = self::createRecordObject($data);
            $list[$record->getName()] = $record;
        }
        self::$cache = $list;
    }

    /**
     * 
     * @param IsmscRecord $data
     */
    private static function createRecordObject($data)
    {
        $name = $data['ISMSC_NAME'];
        if (empty($name)) {
            throw new \UnexpectedValueException('iSMSC name can not be empty');
        }
        $record = new IsmscRecord($name);
        $record->setServiceUrl($data['SERVICE_URL']);
        return $record;
    }

}
