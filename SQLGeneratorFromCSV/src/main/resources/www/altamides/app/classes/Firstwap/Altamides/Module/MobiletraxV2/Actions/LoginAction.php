<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Actions;

use Firstwap\Altamides\Module\MobiletraxV2,
    Firstwap\Altamides\Module\MobiletraxV2\QuickLogger,
    Firstwap\Altamides\Module\MobiletraxV2\Mcrypt;

/**
 * Description of Login
 *
 * @author setia.budi
 */
class LoginAction extends Action
{

    /**
     *
     * @return \Firstwap\Altamides\Module\MobiletraxV2\Response
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
        //$pattern = '/user:(?P<user>.+?)==pass:(?P<pass>.*)/';
        $pattern = '/(?P<user>.+?) (?P<pass>.+?) (?P<imei>.+?) (?P<iv>.+)/';
        $reqData = $this->request->getData();
        $data = array();
        $matched = preg_match($pattern, $reqData, $data);
        
//        $data2 = array();
//        $pattern  = '/(?P<userx>.+?)\/(?P<passx>.*)\/(?P<imei>.*)/';
//        $matched2 = preg_match($pattern, $this->request->getData(), $data2);
//
//        // error_log("XXX : ".print_t($data2));
//
//        error_log("userx : " . $data2['userx'] . " user : " . $data['user']);
//        error_log("pass : " . $data2['passx'] . " Pass : " . $data['pass']);
//        error_log("imei : " . $data2['imei']);
//
        if ($matched === false) {
            throw new \RuntimeException('Failed parsing request data: '.  preg_last_error());
        }
        
        $mcrypt = new Mcrypt(MOBILETRAX_ENCRYPTION_KEY, $data['iv']);
                
        if (MOBILETRAX_ENCRYPTION_ENABLED) {
            $ar = array('/','+');
            $ar2 = array("_",'-');
            $encPass = $mcrypt->decrypt(str_replace($ar2,$ar,$data['pass']));            
            $encUser = $mcrypt->decrypt(str_replace($ar2,$ar,$data['user']));
        } else {
            $encPass = $data['pass'];
            $encUser = $data['user'];
        }
        
       
//        if (empty($data['user'])) {
//            QuickLogger::debug("Parsed data [user: $encUser] [pass: $encPass]\nMSG: $reqBody\nPARSED: ". print_r($data, true));
//        }
        
        if (empty($encUser)) {
            throw new Mobiletrax\InvalidRequestException("Missing user/pass: ". print_r($data, true));
        }
        
        $parsed = array (
            'user' => $encUser,
            'pass' => $encPass,
            'imei' => $data['imei']
        );
        
        return $parsed;
    }

}
