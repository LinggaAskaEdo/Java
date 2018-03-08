<?php
/**
 * @copyright (c) 2012 1rstWAP. All rights reserved
 * @author Setia Budi Halim setia.budi@1rstwap.com
 * 
 * @internal 
 * System           : 1rstWAP PHP PhpFramework
 * Module           : 
 * Version          : 1.0.0
 * File Name        : Dictionary.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-16 WIT
 * Purpose          : Declaration for class SimpleDictionary
 * Initial Request  : 
 * 
 * Changelog        :
 * =================================================================================================
 * Date         Author          Version     Request     Comment
 * =================================================================================================
 * 2012-08-16      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\PhpFramework\I18n;

use Firstwap\PhpFramework\File\FileNotFoundException;

/**
 * Represent string translation from a language file
 *
 * @author Setia Budi Halim
 * @since version 1.0.0
 */
class Dictionary implements DictionaryInterface, \ArrayAccess
{
    /**
     * The translation mapping
     * @var string[]
     */
    protected $terms = array();
    
    /**
     * Load term definitins from file
     * 
     * @param string $file
     * @throws FileNotFoundException
     * @throws \RuntimeException
     */
    public function fromFile($file)
    {
        if (!file_exists($file)) {
            throw new FileNotFoundException($file);
        }
        
        $terms = parse_ini_file($file, false);
        
        if ($terms === false) {
            throw new \RuntimeException('Failed loading dictionary file');
        }
        
        $this->terms = $terms;
    }
    
    /**
     * Load definitions from array
     * @param array $terms
     */
    public function fromArray(array $terms)
    {
        $this->terms = $terms;
    }
    
    /**
     * Format data using definition of the term in dictionary
     * @param string $term
     * @param array $data
     * @return string
     */
    public function formatData($term, array $data)
    {
        return vsprintf($term, $data);
    }
    
    /**
     * Get all terms in dictionary as array
     * @return string[]
     */
    public function toArray()
    {
        return $this->terms;
    }

    /**
     * Get definition of a term from dictionary
     * @param string $term
     */
    public function getTerm($term)
    {
        return isset($this->terms[$term]) ? $this->terms[$term] : '';
    }

    /**
     * Check if this dictionary has definitions for a term
     * @param string $term
     * @return bool
     */
    public function hasTerm($term)
    {
        return isset($this->terms[$term]);
    }
    
    /**
     * Set definition for a term
     * @param string $term
     */
    public function setTerm($term, $definition)
    {
        $this->terms[$term] = $definition;
    }
    
    /**
     * Remove a term from dictionary
     * @param string $term
     */
    public function removeTerm($term)
    {
        if (isset($this->terms[$term])) {
            unset($this->terms[$term]);
        }
    }

    /**
     * Implementation of array isset
     * @see hasTerm
     * @param string $offset
     */
    public function offsetExists($offset)
    {
        return $this->hasTerm($offset);
    }

    /**
     * Implementation of array read
     * @see getTerm
     * @param string $offset
     */
    public function offsetGet($offset)
    {
        return $this->getTerm($offset);
    }

    /**
     * Implementation of array offset assignment
     * @see setTerm
     * @param string $offset
     */
    public function offsetSet($offset, $value)
    {
        $this->setTerm($offset, $value);
    }

    /**
     * Implementation of array offset unset
     * @see unsetTerm
     * @param string $offset
     */
    public function offsetUnset($offset)
    {
        $this->removeTerm($offset);
    }
}