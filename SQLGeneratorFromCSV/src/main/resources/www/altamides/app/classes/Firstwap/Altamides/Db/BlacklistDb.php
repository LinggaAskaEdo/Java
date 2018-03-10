<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Altamides\Db;

/**
 * Description of BlacklistDb
 *
 * @author Setia Budi Halim <setia.budi@1rstwap.com>
 */
final class BlacklistDb
{

    /**
     *
     * @var string Blacklist server SSO database. Protected from Altamides
     */
    public static $sso = 'LRTP_V2';

    /**
     *
     * @var string Plain blacklist/redlist database. Protected from Altamides
     */
    public static $secureBlacklist = 'SECUREBLACKLIST';

    /**
     *
     * @var string Hashed blacklist/redlist database. Shared with Altamides
     */
    public static $publicBlacklist = 'PUBLICBLACKLIST';

}
