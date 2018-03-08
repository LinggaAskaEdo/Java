<?php
/**
 * @copyright (c) 2012 1rstWAP. All rights reserved
 * @author Setia Budi Halim setia.budi@1rstwap.com
 * 
 * @internal 
 * System           : 1rstWAP PHP PhpFramework
 * Module           : 
 * Version          : 
 * File Name        : FileNotFoundException.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-08-16 WIT
 * Purpose          : Declaration for class FileNotFoundException
 * Initial Request  : 
 * 
 * Changelog        :
 * =================================================================================================
 * Date         Author          Version     Request     Comment
 * =================================================================================================
 * 2012-08-16      Setia Budi Halim         1.000.000               Initial Creation
 */

namespace Firstwap\PhpFramework\File;

/**
 * An exception that indicates a required file was not found
 *
 * @author Setia Budi Halim
 * @since 1.0.0
 */
class FileNotFoundException extends \Exception
{
    /**
     * Constructor
     * @param string $file The file name
     */
    public function __construct($file) {
        parent::__construct("File not found: $file");
    }
}