<?php

/*
 * (c) 2016 FirstWAP. All rights reserved
 * 
 * System           : Altamides
 * Module           : Router Rabbit-mq
 * Version          : 2.0 SP 23
 * Filename         : RpcClient.php
 * Fileversion      : 2.000.001
 * Initial Creation : 2016-09-22
 * Initial Author   : Yung Fei
 * Purpose          : Router from php to java using rabbit-mq
 * ================================================
 * Initial Request  : #9097
 * ================================================
 * Change Log
 * Date         Author		  Version     Request			Comment
 * 2016-09-22	Yung Fei          2.0 SP23    #9097		  Initial creation
 * 
 * (c) 2016 PT. 1rstWAP confidential
 * This document belongs to PT 1rstWAP. Propagation to others
 * then members of PT. 1rstWAP is strictly forbidden.
 */

namespace Rpc;

require_once ALTAMIDES_CONFIG_PATH . 'config.global.php';

use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

class RpcClient
{

    private static $connection;
    private $channel;
    private $callback_queue;
    private $response;
    private $corr_id;
    private $config;

    public function __construct()
    {
        self::$connection = new AMQPStreamConnection(
                ROUTER_QUEUE_LOGIN, ROUTER_QUEUE_PORT, ROUTER_QUEUE_USERNAME, ROUTER_QUEUE_PASSWORD
        );
        $this->channel = static::$connection->channel();
    }

    /**
     * Setter
     * @param type $newCallbackQueue
     */
    public function setCallbackQueue($newCallbackQueue)
    {
        $this->callback_queue = $newCallbackQueue;
    }

    /**
     * Getter
     * @return type
     */
    public function getCallbackQueue()
    {
        return $this->callback_queue;
    }

    /**
     * Init function to create RPC Client
     */
    public function connect()
    {
        $this->getFirstCallbackQueue();
    }

    /**
     * Get callback queue
     * @return type
     */
    public function getFirstCallbackQueue()
    {
        list($callbackQueue,, ) = $this->channel->queue_declare("", false, false, true, false);
        $this->setCallbackQueue($callbackQueue);
    }

    /**
     * 
     * @param type $rep
     */
    public function on_response($rep)
    {
        $this->response = $rep->body;
    }

    /**
     * Send message to queue
     * @param type $message
     * @param type $wait
     * @return type
     */
    public function send($message, $wait = true)
    {
        $this->response = null;
        $this->corr_id = uniqid();
        $this->channel->basic_consume($this->getCallbackQueue(), '', false, false, false, !$wait, array($this, 'on_response'));
        $msg = new AMQPMessage((string) $message, array(
            'reply_to' => $this->getCallbackQueue())
        );
        $this->channel->basic_publish($msg, RABBITMQ_TOPIC, RABBITMQ_ROUTER);

        // no need to wait response
        if (!$wait) {
            return true;
        }
        
        while (!$this->response) {
            $this->channel->wait();
        }
        
        return $this->response;
    }

    /**
     * getInstance() is used to access an instance of RpcClient. if the object
     * is already instanced, that instance will be returned. If the instance does
     * not yet exist, it will be created
     * @return type
     */
    public static function getInstance()
    {
        if (!self::$connection instanceof RpcClient) {
            self::$connection = new RpcClient();
        }

        return self::$connection;
    }

}
