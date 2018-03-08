<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Actions;

use Firstwap\Altamides\Module\Mobiletrax,
    Firstwap\Altamides\Module\Mobiletrax\QuickLogger,
    Firstwap\Altamides\Module\Mobiletrax\Mcrypt;

/**
 * Description of Login
 *
 * @author setia.budi
 */
class LoginAction extends Action
{

    /**
     *
     * @return \Firstwap\Altamides\Module\Mobiletrax\Response
     * @throws RuntimeException 
     */
    public function perform()
    {
        $arguments = $this->data;
        try {
            $this->validateUser($arguments['pass'], $arguments['user'],$arguments['imei'], true);
            $status = LoginActionResponse::STATUS_OK;
        } catch (\Exception $e) {
            $status = LoginActionResponse::STATUS_DENIED;
            QuickLogger::warn($e);
        }
        
        $senderId = $this->request->getSender();
        QuickLogger::info("User login from {$senderId} is $status for {$arguments['user']}  using password '{$arguments['pass']}'");
        
        $response = new LoginActionResponse($this->request);
        $response->setLoginStatus($status);
        return $response;
    }

    /**
     *
     * @return array
     * @throws InvalidRequestException
     * @throws \Exception 
     */
    protected function parseData()
    {
        /*
         * SMS Format:
         * MT10XXX user:mobile1==pass:MyPaz$
         */
        $pattern = '/(?P<user>[^ ]+?) (?P<pass>[^ ]*?) (?P<imei>.+?) (?P<iv>.+)/';
        $reqData = $this->request->getData();
        $data = array();
        $matched = preg_match($pattern, $reqData, $data);
        
        if ($matched === false) {
            QuickLogger::info('Login regex: ' . $pattern);
            QuickLogger::info('Login request data: ' . $reqData);
            QuickLogger::info('Login parsed data: ' . print_r($data, true));
            throw new \RuntimeException('Failed parsing request data: '.  preg_last_error());
        }
        
                
        QuickLogger::info('Encryption: ' . (MOBILETRAX_ENCRYPTION_ENABLED ? 'Yes' : 'No'));
        if (MOBILETRAX_ENCRYPTION_ENABLED) {
            $mcrypt = new Mcrypt(MOBILETRAX_ENCRYPTION_KEY, $data['iv']);
            $ar = array('/','+');
            $ar2 = array("_",'-');
            $encPass = $mcrypt->decrypt(str_replace($ar2,$ar,$data['pass']));            
            $encUser = $mcrypt->decrypt(str_replace($ar2,$ar,$data['user']));
        } else {
            $encPass = $data['pass'];
            $encUser = $data['user'];
        }
        
       
        if (empty($encUser)) {
            QuickLogger::info('Login regex: ' . $pattern);
            QuickLogger::info('Login request data: ' . $reqData);
            QuickLogger::info('Login parsed data: ' . print_r($data, true));
            throw new Mobiletrax\InvalidRequestException("Empty username");
        }
        
        $parsed = array (
            'user' => $encUser,
            'pass' => $encPass,
            'imei' => $data['imei']
        );
        
        return $parsed;
    }

}
