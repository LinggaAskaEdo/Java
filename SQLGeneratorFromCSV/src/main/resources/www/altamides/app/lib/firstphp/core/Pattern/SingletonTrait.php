<?php

namespace Firstwap\Firstphp\Pattern;

/**
 * Description of Singleton
 *
 * @author Setia Budi Halim
 */
trait SingletonTrait
{

    /**
     *
     * @var self
     */
    protected static $instance;

    /**
     * 
     * @return self
     */
    public static function getSingleton()
    {
        if (null !== self::$instance) {
            return self::$instance;
        }
        self::$instance = self::createInstance();
        return self::$instance;
    }

    /**
     * 
     * @param self $instance
     * @return self
     */
    protected static function createInstance()
    {
        $class = __CLASS__;
        $instance = new $class;
        return $instance;
    }

}
