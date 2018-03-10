<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : SmsResponseUtil.php
 *  File Version     : 1.000.000
 *  Initial Creation : 13-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  13-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

/**
 * Description of SmsResponseUtil
 *
 * @author Setia Budi Halim
 */
final class EncodedResponseUtil
{
    
    /**
     * Helper methods if an response has variants. e.g, LocateAction's response has Fast, Eco,
     * and complete variants
     * 
     * @param array $selection
     * @param array $availables
     * @return array
     */
    public static function selectLayoutFields(array $selection, array $availables)
    {
        if (empty($selection)) {
            \trigger_error('Empty layout fields selection', \E_USER_NOTICE);
            return array();
        }

        if (empty($availables)) {
            \trigger_error('Empty layout fields definition', \E_USER_NOTICE);
            return array();
        }

        $layout = array();
        foreach ($selection as $fieldName) {
            if (!isset($availables[$fieldName])) {
                continue;
            }
            $layout[$fieldName] = $availables[$fieldName];
        }

        return $layout;
    }
}