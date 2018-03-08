<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : PHP Framework
 *  Module           : 
 *  Version          : 
 *  File Name        : DictionaryInterface.php
 *  File Version     : 1.000.000
 *  Initial Creation : 16-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  16-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\PhpFramework\I18n;

/**
 *
 * @author Setia Budi Halim
 */
interface DictionaryInterface
{
    public function getTerm($term);
    public function setTerm($term, $definition);
    public function hasTerm($term);
    public function removeTerm($term);
    public function formatData($term, array $data);
    public function toArray();
}