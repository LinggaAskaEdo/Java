<?php
/**
 * @internal 
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : PHP Framework
 * Module           : Loader
 * Version          : 1.0
 * File Name        : ClassLoader.php
 * File Version     : 1.000.000
 * Initial Creation : 23-Aug-2012
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : 
 *  
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author          Version     Request     Comment
 * 23-Aug-2012      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\PhpFramework\Loader;

/**
 * Auto loader for classes. The classes files must be structured the same way as the class namespace
 * @author Setia Budi Halim
 */
class ClassLoader
{

    protected $directory     = '';
    protected $fileExtension = '.php';
    protected $namespace     = null;
    protected $nameSeparator = '\\';

    /**
     * Constructor
     * @param string $ns Name space without root prefix (i.e., no '\' on \Ns1\Ns2)
     * @param string $dir Source directory of the classes with the same structure as namespace
     * @param string $separator Name space separator. When empty it is \
     * @param string $ext The file extension, When empty, it is .php
     */
    public function __construct($ns = null, $dir = null, $separator = null, $ext = null)
    {
        if ('' !== (string) $dir) {
            $this->directory = realpath($dir) . DIRECTORY_SEPARATOR;
        }

        if ($separator !== null) {
            $this->nameSeparator = (string) $separator;
        }

        if ('' !== (string) $ns) {
            $this->namespace = $ns . $this->nameSeparator;
        }

        if ('' !== (string) $ext) {
            $this->fileExtension = '.' . $ext;
        }
    }

    /**
     * Try load the class.
     * This is an public method to be used by {@see spl_autoload_register }
     * @param string $fullName Fully qualified class name
     */
    public function tryLoad($fullName)
    {
        // namespace is set but does not match
        if (($this->namespace !== null) && (0 !== strpos($fullName, $this->namespace))) {
            return;
        }
        
        $filePath = $this->directory .
                str_replace($this->nameSeparator, \DIRECTORY_SEPARATOR, $fullName) .
                $this->fileExtension;
        
        include_once $filePath;
    }

    /**
     * Register this loader
     * @param bool $prepend
     */
    public function register($prepend = false)
    {
        spl_autoload_register(array($this, 'tryLoad'), true, $prepend);
    }

    /**
     * Unregister this loader
     */
    public function unregister()
    {
        spl_autoload_unregister(array($this, 'tryLoad'));
    }

    /**
     * A convenient metthod to create and register a standard autoloader in one line of code.
     * The classes must have '.php' extension and use \ as the name space separator
     * 
     * @param string $ns Name space
     * @param string $dir Source directory
     */
    public static function autoload($ns = null, $dir = null)
    {
        $loader = new ClassLoader($ns, $dir);
        $loader->register();
    }

}