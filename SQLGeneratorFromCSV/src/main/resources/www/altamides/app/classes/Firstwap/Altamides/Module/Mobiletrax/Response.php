<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\Mobiletrax;

use Firstwap\Altamides\Module\Mobiletrax\Request,
    Firstwap\Altamides\Module\Mobiletrax\ResponseType,
    Firstwap\Altamides\Sms\MessageSender;

/**
 * Response is result of an action performed against user request
 *
 * @author setia.budi
 */
abstract class Response implements ResponseInterface
{

    const SIGNATURE = 'MT';
    
    /**
     *
     * @var Request
     */
    protected $request;

    /**
     *
     * @var MessageSender
     */
    protected $sender;
    
    /**
     *
     * @var string
     */
    protected $type = ResponseType::UNSPECIFIED;
    
    /**
     *
     * @var array
     */
    protected $data = array();
    
    public function __construct(Request $request)
    {
        $this->request = $request;
    }


    /**
     * Set the message format code. this is two character followin MT 
     * header in the SMS
     * @param int $type NULL for compatibility with unencoded message format
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * 
     * @param array $data
     */
    public function setData(array $data)
    {
        $this->data = $data;
    }
    
    /**
     * Set single field of data
     * @param string $name
     * @param mixed $value
     */
    public function setDataField($name, $value)
    {
        $this->data[$name] = $value;
    }

    /**
     * 
     * @param \Firstwap\Altamides\Sms\MessageSender $sender
     */
    public function setSender(MessageSender $sender)
    {
        $this->sender = $sender;
    }

}
