<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Sms;

/**
 * Description of TerminatedMessage
 *
 * @author setia.budi
 */
class TerminatedMessage implements IMessage
{

    const MAX_LENGTH = 160;

    protected $smsContent;

    public function setContent($content)
    {
        return $this->smsContent = $content;
    }

    public function getContent()
    {
        return $this->smsContent;
    }
    
    public function getCharLength()
    {
        return mb_strlen($this->smsContent);
    }
    
    public function getByteLength()
    {
        return strlen($this->smsContent);
    }

}
