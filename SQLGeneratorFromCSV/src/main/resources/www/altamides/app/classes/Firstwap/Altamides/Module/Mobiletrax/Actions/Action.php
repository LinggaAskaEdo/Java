<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */
namespace Firstwap\Altamides\Module\Mobiletrax\Actions;

use 
    Firstwap\Altamides\Module\Mobiletrax\Request,
    Firstwap\Altamides\Module\Mobiletrax\QuickLogger,
    Firstwap\Altamides\Module\Mobiletrax\Mcrypt,
    Firstwap\Altamides\Module\Mobiletrax\InvalidAccessException,
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
     * @var \Firstwap\Altamides\Module\Mobiletrax\Request
     */
    protected $request;
    
    /**
     *
     * @var \Firstwap\Altamides\Module\Mobiletrax\Response
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
     * @return \Firstwap\Altamides\Module\Mobiletrax\Response|null 
     */
    public function getResponse(){
        return $this->response;
    }
    
    /**
     * Sanitazing msisdn
     * @param type $msisdn
     * @return string
     */
    protected function clean_msisdn_for_comparisson($msisdn) {
        if (empty($msisdn)) {
            QuickLogger::info('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is empty');
            throw new \Exception('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is empty');
        }
        if (is_null($msisdn)) {
            QuickLogger::info('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is null');
            throw new \Exception('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is null');            
        }

        if(is_object($msisdn) || is_array($msisdn))
        {
            QuickLogger::info('MSISDN expected to be a string, but ' . print_r($msisdn, true) . ' found');
            throw new \Exception('MSISDN expected to be a string, but ' . print_r($msisdn, true) . ' found');    
        }
        $msisdn =(string)$msisdn;
        
	$clean_msisdn = "";

	for($i = 0; $i < strlen($msisdn); $i++) {
		if(ctype_digit($msisdn[$i])) {
			$clean_msisdn .=  $msisdn[$i];
		}
	}
	
	return $clean_msisdn;
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
            $msisdn = $db->quote($this->clean_msisdn_for_comparisson($this->request->getSender()));
            
                        
            $query = "SELECT PASSWORD, PASSWORD_REQUIRED, ".
                     "IMEI, IMEI_REQUIRED, END_DATE " .
                     "FROM AUTHORIZED_MSISDN ".
                     "WHERE MSISDN = $msisdn ".
                     "and ACTIVE ".
                     "and END_DATE > now() ";
            
            if ($checkUser) {
                $query .= ' AND NAME = "'. $userName.'"';
            }
            
            $query  .= " LIMIT 1";
                        
            
            QuickLogger::info('validateUser(username: ' . print_r($userName, true) . ', msisdn: ' . print_r($msisdn, true) . ' CheckUser: ' . ($checkUser?'true':'false'));
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
