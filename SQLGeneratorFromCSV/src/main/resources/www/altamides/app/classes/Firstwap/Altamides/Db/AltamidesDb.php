<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Db;

/**
 * Description of DbName
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class AltamidesDb
{

    /**
     * @var string SSO database name
     */
    public static $sso = 'LRTP_V2';

    /**
     * @var string Target profile and location database
     */
    public static $profiletrax = 'LOCATION_TRACKING';

    /**
     * @var string Location API backend, scheduler, FieldTrax
     */
    public static $locationApi = 'LOCATION_SINGLE_USE';

    /**
     *
     * @var string SMS distributor/dispatcher and DisC database
     */
    public static $smsDispatcher = 'SMSDISPATCHER';

    /**
     *
     * @var string SMS sending related (legacy, no actual benefit)
     */
    public static $smsApi = 'SMS_API';

    /**
     *
     * @var string Telco data
     */
    public static $telco = 'CELL_DB';

    /**
     * @var string First Intermedia database (telco/sms related)
     */
    public static $firstIntermedia = 'First_Intermedia';

    /**
     *
     * @var string LAC location information
     */
    public static $isatlbs = 'ISATLBS';

    /**
     *
     * @var string SpotTrax datbase
     */
    public static $spottrax = 'SPOTTRAX';

    /**
     *
     * @var string Required by Agent Status for some trivial things
     */
    public static $smsBilling = 'BILL_U_MESSAGE';

}
