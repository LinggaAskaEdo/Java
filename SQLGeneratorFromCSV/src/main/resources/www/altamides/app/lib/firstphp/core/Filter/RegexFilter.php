<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of RegexFilter
 *
 * @author Setia Budi Halim
 */
class RegexFilter extends PhpFilter
{

    public function __construct($pattern)
    {
        $options = [
            'regexp' => $this->normalizeRegex($pattern),
        ];
        parent::__construct(\FILTER_VALIDATE_REGEXP, null, $options);
    }

    public function getRegex()
    {
        return empty($this->options['options']['regexp']) ? '' : $this->options['options']['regexp'];
    }

    protected function normalizeRegex($pattern)
    {
        if (0 === \strpos($pattern, '/')) {
            return $pattern;
        } else {
            return '/' . $pattern . '/';
        }
    }

}
