<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Html;

/**
 * Description of HtmlPresenter
 *
 * @author Setia Budi Halim
 */
class HtmlRenderer
{

    public function renderBlock($type, $attrs = null, $content = null, $encode = true)
    {
        $block = $this->renderBlockStart($type, $attrs);
        if (($content !== null) && $encode) {
            $block .= $this->encode($content);
        } else {
            $block .= $content;
        }
        $block .= $this->renderBlockEnd($type);
        return $block;
    }

    /**
     * Render an inline element 
     * @param string $type
     * @param array $attrs
     * @return string
     */
    public function renderInline($type, $attrs = null)
    {
        return '<' . $type . $this->renderAttrs($attrs) . ' />';
    }

    public function encode($string)
    {
        return \htmlspecialchars($string, \ENT_HTML5 | \ENT_QUOTES);
    }

    public function renderBlockStart($type, $attrs = null)
    {

        return '<' . $type . $this->renderAttrs($attrs) . '>';
    }

    public function renderBlockEnd($type)
    {

        return '</' . $type . '>';
    }

    /**
     * 
     * @param array $attrs
     * @return string
     */
    public function renderAttrs(array $attrs = null)
    {
        if (empty($attrs)) {
            return '';
        }
        $rendered = '';
        foreach ($attrs as $name => $value) {
            $rendered .= ' ' . $name . '="' . $this->encode($value) . '"';
        }
        return $rendered;
    }

    public function renderBooleanAttr($name, $value)
    {
        return $value ? $this->renderAttrs([$name => $name]) : '';
    }

}
