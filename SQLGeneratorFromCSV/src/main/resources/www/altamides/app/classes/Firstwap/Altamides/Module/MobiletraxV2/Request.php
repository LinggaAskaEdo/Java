<?php

/*
 * (c) 2012 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

/**
 * Abstract the incoming SMS request
 *
 * @author setia.budi
 */
class Request
{

    /**
     * Sender MSISDN
     * @var string
     */
    protected $sender;

    /**
     * Destined virtual number MSISDN
     * @var string
     */
    protected $receiver;

    /**
     * 0 = Latin
     * @var int
     */
    protected $encoding;

    /**
     * SMS text
     * @var string
     */
    protected $message;

    /**
     * Forwarder IP address
     * @var string
     */
    protected $ip;

    /**
     * Time stamp of this object creation
     * @var \DateTime
     */
    protected $timestamp;
    
    /**
     * The command code for this request
     * @var string
     */
    protected $type;
        
    /**
     * The arguments for command
     * @var string
     */
    protected $data;
    
    /**
     * Request ID
     * @var string
     */
    protected $requestId;
    
    /**
     * The 'header' part of request which contain
     * MOBILETRAX string and command code
     * @var string
     */
    protected $signature;

    /**
     * The constructor
     * It reads request from $_GET and search for follwing elements:
     * It reads these fields from GET 
     * - sender
     * - destination
     * - encoding
     * - message: \r\n in the message will be converted to \n
     * @see $_GET 
     */
    public function __construct()
    {
        $this->readMessage();
    }

    /**
     * Return string representation of this request
     * @return string
     */
    public function __toString()
    {

        return "From: {$this->sender}
To: {$this->receiver}
Encoding: {$this->encoding}
Time: ".$this->timestamp->format(DATE_ISO8601)."
Message:
$this->message";
    }

    /**
     * Return the sender name
     * @return string
     */
    public function getSender()
    {
        return $this->sender;
    }

    /**
     * Return requesting IP
     * @return string
     */
    public function getIp()
    {
        return $this->ip;
    }

    /**
     * Return the virtual number
     * @return string
     */
    public function getReceiver()
    {
        return $this->receiver;
    }

    /**
     * Return the message text
     * @return string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * Return request timestamp
     * @return \DateTime
     */
    public function getTimestamp()
    {
        return clone($this->timestamp);
    }
    
    /**
     * Get command part of the request
     * @return string
     */
    public function getType() {
        return $this->type;
    }
    
    /**
     * Getter for request ID
     * @return string
     */
    public function getRequestId() {
        return $this->requestId;
    }
    
    /**
     * Get signature part of the request
     * @return string
     */
    public function getSignature() {
        return $this->signature;
    }
    
    /**
     * Get data part of the request
     * @return string
     */
    public function getData() {
        return $this->data;
    }
    
    /**
     * Read the request SMS
     */
    private function readMessage()
    {
        // To be accurate get the time stamp early
        $this->timestamp = new \DateTime();

        $this->sender = empty($_GET['sender']) ? '' : ltrim(rawurldecode($_GET['sender']));
        $this->receiver = empty($_GET['destination']) ? '' : rawurldecode($_GET['destination']);
        $this->encoding = empty($_GET['encoding']) ? 0 : $_GET['encoding'];
        // ensure consistent new line
        $this->message = str_replace("\r\n", "\n", rawurldecode($_GET["message"]));
        $this->ip = $_SERVER['REMOTE_ADDR'];
        
        $this->parseMessage();
    }

    /**
     * Parse request message
     * 
     * @throws \RuntimeException
     * @throws InvalidRequestException
     */
    protected function parseMessage() {
        
//        $messagePattern = '/^(?P<signature>MT(?P<type>.{2})(?P<requestId>.{3})) (?P<data>.*)/ums';
//        
//        $parseResult = array();
//        $parseStatus = \preg_match($messagePattern, $this->message, $parseResult);
//        
//        if ($parseStatus === false) {
//            throw new \RuntimeException('Failed parsing message. PCRE error code: ' .  \preg_last_error());
//        }
//        
//        if ($parseStatus === 0) {
//            throw new InvalidRequestException('Invalid message format. Message: ' . $this->message);
//        }
//        
//        $this->requestId = $parseResult['requestId'];
//        $this->type = $parseResult['type'];
//        $this->signature = $parseResult['signature'];
//        $this->data = $parseResult['data'];
    }
}