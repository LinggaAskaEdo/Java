<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Sms;

/**
 * Interface specification for Altamides SMS sender. Message will be 
 * delivered using SMS Distributor
 * @author setia.budi
 */
interface IMessageSender
{

    /**
     * Send the message
     */
    public function send();

    /**
     * Define the message 
     */
    public function setMessage(IMessage $message);

    /**
     * 
     */
    public function setUser($userName);

    /**
     * 
     */
    public function setDepartment($deptName);

    /**
     * Define the sender ID 
     */
    public function setSenderId($senderId);

    /**
     * Set the destination 
     */
    public function setDestination($msisdn);
}
