<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Module\MobiletraxV2\Actions;

use 
    Firstwap\Altamides\Module\MobiletraxV2\Request,
    Firstwap\Altamides\Module\MobiletraxV2\QuickLogger,
    Firstwap\Altamides\Module\MobiletraxV2\Mcrypt,
    Firstwap\Altamides\Module\MobiletraxV2\InvalidAccessException,
    Firstwap\Altamides\Db\Ref;

/**
 * Description of ResponsiveAction
 *
 * @author setia.budi
 */
abstract class Action
{
    
    const FIELDS_DELIMITER = '\#';
    const FIELD_VALUE_DELIMITER = ':';
    
    /**
     * The request that trigger this action
     * 
     * @var \Firstwap\Altamides\Module\MobiletraxV2\Request
     */
    protected $request;
    
    /**
     *
     * @var \Firstwap\Altamides\Module\MobiletraxV2\Response
     */
    protected $response;
    
    /**
     *
     * @var array
     */
    protected $data;
    
    /**
     * @param array $arguments
     * @return Mobiletrax\Response
     */
    abstract protected function perform();
    
    /**
     * Parse message and extract arguments
     * @return array
     */
    abstract protected function parseData();

    /**
     *
     * @param Request $request 
     */
    public function __construct(Request $request)
    {
        $this->request = $request;
        $this->data = $this->parseData();
        $this->response = $this->perform();
    }
    
    /**
     *
     * @return \Firstwap\Altamides\Module\MobiletraxV2\Response|null 
     */
    public function getResponse(){
        return $this->response;
    }
    
    /**
     *
     * @param type $password
     * @throws \RuntimeException
     * @throws Mobiletrax\InvalidAccessException 
     */
    protected function validateUser($password, $userName = null, $imei = null,$checkUser = false)
    {
        
        $fieldtraxDb = Ref\NameRef::SINGLE_TRACKING;
        
        try {
            $db = Ref\Connection::getPdo($fieldtraxDb);
            $msisdn = $db->quote($this->request->getSender());
            
                        
            $query = "SELECT PASSWORD, PASSWORD_REQUIRED, ".
                     "IMEI, IMEI_REQUIRED " .
                     "FROM AUTHORIZED_MSISDN ".
                     "WHERE MSISDN = $msisdn ".
                     "and ACTIVE ".
                     "and END_DATE > now() ";
            
            if ($checkUser) {
                $query .= ' AND NAME = "'. $userName.'"';
            }
            
            $query  .= " LIMIT 1";
                        
            
            $result = $db->query($query);            
            $user = $result->fetch(\PDO::FETCH_NAMED);
                                    
        } catch (\Exception $e) {
            QuickLogger::error($e);
            throw new \RuntimeException("Failed querying database", 0, $e);
        }
        
        $valid = false;
        $failure = '';
        
        if (!$user) {
            $failure = 'User is not found';
            
        } elseif ($user['IMEI_REQUIRED'] && ($imei !== $user['IMEI'])) {
            $failure = 'IMEI does not match, required=' . $user['IMEI'];
            
        } elseif ($user['PASSWORD_REQUIRED'] && ($password !== $user['PASSWORD'])) {
            $failure = '[Security] Incorrect password, provided='.$password;
            
        } else {
            $valid = true;
            $failure = '';
        }
        
        
        if (!$valid) {
	    QuickLogger::error("[Security] Blocked login attempt from user $userName($msisdn) with IMEI $imei. Reason is: $failure ");
            throw new InvalidAccessException("Invalid access: $failure");
        }
    }
        
}
