<?php

namespace Firstwap\Firstphp\File;

/**
 * Description of DirectoryUtil
 *
 * @author Setia Budi Halim
 */
final class DirectoryUtil
{

    public static function requireExistence($path, $auto = true, $mode = 0700,
                                            $deep = true)
    {
        if (\is_dir($path)) {
            return;
        }
        if (!$auto) {
            throw new \Exception('Required directory does not exist: ' . $path);
        }
        if (!\mkdir($path, $mode, $deep)) {
            throw new \RuntimeException('Failed creating directory ' . $path);
        }
    }

}
