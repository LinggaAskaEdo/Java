<?php

/**
 * @copyright (c) 2012 1rstWAP. All rights reserved
 * @author Setia Budi Halim setia.budi@1rstwap.com
 * 
 * @internal 
 * System           : PHP Framework
 * Module           : I18n
 * Version          : 
 * File Name        : DictionaryDirectory.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-16 WIT
 * Purpose          : Declaration for class Language
 * Initial Request  : 
 * 
 * Changelog        :
 * =================================================================================================
 * Date         Author          Version     Request     Comment
 * =================================================================================================
 * 2012-08-16      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\PhpFramework\I18n;

/**
 * Class for accessing language files in directories structured using following patterns:
 * 
 * ROOT\
 * +-LANG1
 *  +-MODULE1
 *   +-FILE1
 *   +-FILE2
 *   -MODULE2
 *   +-FILE3
 *   +-FILE4
 * +-LANG2
 *  +-MODULE1
 *   +-FILE1
 *   +-FILE2
 *   -MODULE2
 *   +-FILE3
 *   +-FILE4
 * 
 *
 * @author Setia Budi Halim
 * @since 1.0.0
 */
class DictionaryDirectory
{

    const FILE_EXT = 'ini';

    protected $root          = './';
    protected $language      = '';
    protected $defaultModule = null;

    /**
     * Cache of loaded dictionary so we dont need to read and parse the same file again and again
     * @var Dictionary[]
     */
    protected $cache = array();

    /**
     * Constructor
     * @param string $dir Root directory
     * @param string $language Language code
     */
    public function __construct($dir, $language)
    {
        $this->setRoot($dir);
        $this->setLanguage($language);
    }

    /**
     * Set the base directory for dictionary files, this will reset cached dictionary
     * @param type $dir
     */
    public function setRoot($dir)
    {
        $root = realpath($dir);
        if ($root === false) {
            $root = '.';
            \trigger_error("Dictionary directory was not found: $dir", E_USER_WARNING);
        }
        $this->root = $root;
        $this->resetCache();
    }

    /**
     * Set active language, this will reset cached dictionary
     * @param string $lang
     */
    public function setLanguage($lang)
    {
        $this->language = $lang;
        $this->resetCache();
    }

    /**
     * Set the default module
     * @param string $module
     */
    public function setDefaultModule($module)
    {
        $defaultModule       = (string) $module;
        $this->defaultModule = ($defaultModule == '') ? null : $defaultModule;
    }

    /**
     * 
     * @param string $name
     * @param string $module
     * @return \Firstwap\PhpFramework\I18n\Dictionary
     * @throws \InvalidArgumentException
     */
    public function getDictionary($name, $module = null)
    {
        if ($name == '') {
            throw new \InvalidArgumentException('Empty dictionary name');
        }

        $file = $this->resolveFile($name, $module);

        if (isset($this->cache[$file])) {
            return $this->cache[$file];
        }

        return $this->load($file);
    }

    protected function resolveFile($name, $module)
    {
        if ($module === null) {
            $module = $this->defaultModule;
        }

        return ($module == '')
                ? ($name . self::FILE_EXT)
                : ($module . DIRECTORY_SEPARATOR . $name . '.' . self::FILE_EXT);
    }

    /**
     * Get current active directory based on language and root setting
     * @return string Current language directory (with trailing slash)
     */
    protected function resolveDirectory()
    {
        $path = $this->root;

        if ($this->language != '') {
            $path .= DIRECTORY_SEPARATOR . $this->language;
        }
        
        return $path;
    }

    /**
     * Load a dictionary from file
     * @param string $file file may contain DIRECTORY_SEPARATOR in the middle but not in the
     * beginning and the end
     * @return \Firstwap\PhpFramework\I18n\Dictionary
     */
    protected function load($file)
    {
        $fileFullPath = $this->resolveDirectory() . DIRECTORY_SEPARATOR . $file;
        $dictionary   = new Dictionary();
        $dictionary->fromFile($fileFullPath);

        $this->cache[$file] = $dictionary;

        return $dictionary;
    }

    /**
     * Reset internal cache
     */
    protected function resetCache()
    {
        $this->cache = array();
    }

}