<?php

/*
 * (c) 2014 FirstWAP. All rights reserved
 */

namespace Firstwap\Altamides\Sms;

use Firstwap\Altamides\Db\Ref;
use Firstwap\Altamides\Module\Mobiletrax\QuickLogger;

/**
 * Sender for single message
 *
 * @author setia.budi
 */
class MessageSender implements IMessageSender
{

    /**
     *
     * @var Message
     */
    protected $message = '';
    protected $user = '';
    protected $department = '';
    protected $senderId;
    protected $destination;
    protected $msgType = 1;
    protected $creator;
    protected $ftUserId;
    protected $destClientId;

    public function setUser($userName)
    {
        $this->user = $userName;
    }

    public function setDepartment($deptName)
    {
        $this->department = $deptName;
    }

    public function setSenderId($senderId)
    {
        $this->senderId = $senderId;
    }

    public function setDestination($msisdn)
    {
        $this->destination = $msisdn;
    }

    public function setMessage(IMessage $message)
    {
        $this->message = $message;
    }
    
    public function setMsgType($msgType)
    {
        $this->msgType = $msgType;
    }
    public function setCreator($creator)
    {
        $this->creator = $creator;
    }
    public function setFtUserId($ftUserId)
    {
        $this->ftUserId = $ftUserId;
    }
    public function setClientId($destClientId)
    {
        $this->destClientId = $destClientId;
    }

    /**
     *
     * @return \Firstwap\Altamides\Sms\SendingReport
     * @throws PDOException
     * @throws SendingFailedException 
     */
    public function send()
    {
        if (!$this->message) {
            throw new \RuntimeException('No message was set!');
        }
        
        $dispatcherDb = Ref\NameRef::SMS_DISTRIBUTOR;

        $db = Ref\Connection::getPdo($dispatcherDb);
        
        try {
            $user = $db->quote($this->user);
            $dept = $db->quote($this->department);
            $msg = $db->quote(self::escapeLatinText($this->message->getContent()));
            $sender = $this->senderId;
            $ftUserId =  $this->ftUserId;
            $creator = $this->creator;
            $destClientId = $this->destClientId; 

            if (strpos(trim($sender), '+') === 0) {
                // non alphanumeric sender, make sure the sender is defined correctly. 
                $sender = $db->quote($this->clean_msisdn_for_comparisson($sender, true));
                QuickLogger::info('MessageSender.send() sender correction numeric parsing ' . $this->senderId . ' -> ' . $sender);
            } else {
                // Handle sender as alphanumeric value.
                $sender = $db->quote($sender);
                QuickLogger::info('MessageSender.send() sender correction alphanumeric parsing ' . $this->senderId . ' -> ' . $sender);
            }

            $dest = $db->quote($this->clean_msisdn_for_comparisson($this->destination, false));
            $msgType = $db->quote($this->msgType); 

            $query = "insert into $dispatcherDb.SMS_DISPATCHER 
                    (SRC, DEST, MSG, COST_CENTER,  API_USER, MSG_TYPE, DEST_USER_ID, FIELDTRAX_USER_ID, DEST_CLIENT_ID)
					values ($sender, $dest, $msg, $dept, $user, $msgType, $creator, $ftUserId, '$destClientId')";

            $success = $db->exec($query);

            $statistics = array(
                'success'     => (bool) $success,
                'failed'      => $success ? 0 : 1,
                'error_items' => array()
            );

            if (!$success) {
                $statistics['error_items'][] = array(
                    'msisdn'        => $dest,
                    'error_message' => $e->getMessage()
                );
            }
            
            if($this->department == 'Mobiletrax'){
                return $db->lastInsertId();
            }
            
            return new SendingReport($statistics);
        } catch (\PDOException $e) {
            error_log("Query error: $query");
            throw $e;
        } catch (\Exception $e) {
            throw new SendingFailedException("Error when sending message", 0, $e);
        }
    }
	
    /**
     * Prepare text for middleware/queue as defined by bug #1629
     * @param string $text original text
     * @return string Escaped text
     */
    public static function escapeLatinText($text)
    {
        $encoding = mb_detect_encoding($text);
        $length = mb_strlen($text, $encoding);
        
        if (!$length) {
            return '';
        }
        
        $result = '';
        // we have to use custom replace as we dont have mb version of str_replace
        for ($i = 0; $i < $length; $i++) {
            $char = mb_substr($text, $i, 1, $encoding);
            if ($char === '\\') {
                $result .= '\\\\';
            } elseif ($char === "\n") {
                $result .= '\&';
            } elseif ($char === "\r") {
                $result .= '\r';
            } else {
                $result .= $char;
            }
        }
        
        return $result;
    }

    protected function clean_msisdn_for_comparisson($msisdn, $addplus) {
        if (empty($msisdn)) {
            QuickLogger::info('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is empty');
            throw new \Exception('MessageSender.clean_msisdn_for_comparisson() Unexpected input for MSISDN is empty');
        }

        if(is_object($msisdn) || is_array($msisdn)) {
            QuickLogger::info('MSISDN expected to be a string, but ' . print_r($msisdn, true) . ' found');
            throw new \Exception('MSISDN expected to be a string, but ' . print_r($msisdn, true) . ' found');    
        }

        $filteredMsisdn = (string) filter_var((string) $msisdn, FILTER_SANITIZE_NUMBER_INT);;
	$cleanMsisdn = \str_replace(array('+','-',' '), '', (string) $filteredMsisdn);

        return $addplus ? ('+' . $cleanMsisdn) : $cleanMsisdn;
    }

}
