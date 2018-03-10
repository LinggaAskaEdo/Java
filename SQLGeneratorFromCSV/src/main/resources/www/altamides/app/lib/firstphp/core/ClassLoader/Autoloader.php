<?php

namespace Firstwap\Firstphp\ClassLoader;

/**
 * Description of Autoloader
 *
 * @author Buddy
 */
final class Autoloader
{

    protected $namespace;
    protected $directory;
    private $namespaceLength;

    /**
     * Register an PSR4 compatible classes source
     * @param string $dir Source directory. 
     * Files are stored in this directory with relative structure to the namespace
     * @param string $ns Namespace Namespace of root contents of the directory
     * @param boolean $prepend If true the loader will be prepended
     * @return \Firstwap\Firstphp\ClassLoader\Autoloader
     */
    public static function autoload($dir, $ns = '', $prepend = false)
    {
        $loader = new Autoloader($dir, $ns);
        $loader->activate($prepend);
        return $loader;
    }

    /**
     * 
     * @param string $dir Source directory. 
     * Files are stored in this directory with relative structure to the namespace
     * @param type $ns Namespace Namespace of root contents of the directory
     */
    public function __construct($dir, $ns = '')
    {
        $this->namespace       = \trim($ns, '\\') . '\\';
        $this->namespaceLength = (int) \strlen($this->namespace);
        $this->directory       = \rtrim($dir, \DIRECTORY_SEPARATOR) . \DIRECTORY_SEPARATOR;
    }

    /**
     * Load the requested class, an interface required by SPL autoloader
     * @param string $class Full class name
     */
    public function loadClass($class)
    {
        if (0 !== \strpos($class, $this->namespace)) {
            return;
        }
        $path = \substr($class, (int) $this->namespaceLength);
        $file = $this->directory . \str_replace('\\', \DIRECTORY_SEPARATOR,
                                                $path) . '.php';
        if (\is_readable($file)) {
            include_once($file);
        }
    }

    /**
     * Register this autoloader with SPL auto loader register
     * @param boolean $prepend
     */
    public function activate($prepend = false)
    {
        \spl_autoload_register([$this, 'loadClass'], true, $prepend);
    }

    public function getNamespace()
    {
        return $this->namespace;
    }

    public function getDirectory()
    {
        return $this->directory;
    }

}
