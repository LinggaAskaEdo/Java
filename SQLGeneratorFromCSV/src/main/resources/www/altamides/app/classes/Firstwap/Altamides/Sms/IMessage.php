<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Sms;

/**
 * Interface of an SMS message
 *
 * @author setia.budi
 */
interface IMessage
{

    /**
     * Get the message content
     * @return string 
     */
    public function getContent();

    /**
     * Get the bytes length of the content 
     * @return int
     */
    public function getByteLength();
    
    /**
     * Get the characters count from
     */
    public function getCharLength();
}
