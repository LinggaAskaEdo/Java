<?php

/**
 *  (c) 1rstWAP. All rights reserved.
 * 
 *  System           : Altamides
 *  Module           : 
 *  Version          : 
 *  File Name        : ActionDispatcher.php
 *  File Version     : 1.000.000
 *  Initial Creation : 10-Aug-2012
 *  Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 *  Purpose          : 
 *  
 *  =====================================================================
 *  Initial Request  : 
 *  =====================================================================
 *  Change Log
 *  Date         Author          Version     Request     Comment
 *  10-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2;

/**
 * An action dispatcher for mobiletrax
 *
 * @author Setia Budi Halim
 */
class ActionDispatcher
{

    /**
     *
     * @var array
     */
    protected $requestTypeToActionMap = array(
        RequestType::LOGIN       => 'LoginAction',
        RequestType::LOCATE_FAST => 'LocateAction'
    );
    
    const ACTION_NAMESPACE = 'Firstwap\Altamides\Module\MobiletraxV2\Actions';

    /**
     * Create an action based on incoming request
     * 
     * @return \Firstwap\Altamides\Module\MobiletraxV2\Actions\Action
     * @throws \Exception
     * @throws InvalidRequestException
     */
    public function dispatch(Request $request)
    {
        try {
            $command = $request->getType();
            if (!isset($this->requestTypeToActionMap[$command])) {
                throw new InvalidRequestException('Unknown command: ' . $command);
            }

            $actionClass = self::ACTION_NAMESPACE . '\\' . $this->requestTypeToActionMap[$command];

            if (!class_exists($actionClass, true)) {
                throw new \Exception("Command '$command' has non existent action class: $actionClass");
            }

            // please note that instantiating an Action class
            // will automatically trigger the action execution
            /* @var $action Actions\Action */
            $action = new $actionClass($request);
            return $action;
        } catch (Exception $e) {
            throw new \Exception('Failed dispatching request.', 0, $e);
        }
    }

}