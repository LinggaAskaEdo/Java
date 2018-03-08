<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Web\Html;

/**
 * Description of FormRenderer
 *
 * @author Setia Budi Halim
 */
class FormRenderer
{

    const ENC_URL       = 'application/x-www-form-urlencoded';
    const ENC_MULTIPART = 'multipart/form-data';
    const METHOD_POST   = 'post';
    const METHOD_GET    = 'get';
    const METHOD_PUT    = 'put';
    const METHOD_DELETE = 'delete';

    /**
     *
     * @var HtmlRenderer
     */
    protected $htmlRenderer;

    public function __construct()
    {
        $this->htmlRenderer = new HtmlRenderer();
    }

    /**
     * 
     * @return HtmlRenderer
     */
    public function getHtmlRenderer()
    {
        return $this->htmlRenderer;
    }

    public function openForm($name, $submitUrl = null, $method = 'post',
                             $multipart = false, array $attrs = [])
    {
        $attrs['name'] = $name;
        if ($multipart) {
            $attrs['enctype'] = self::ENC_MULTIPART;
        } else {
            $attrs['enctype'] = self::ENC_URL;
        }
        $attrs['method'] = $method ? : self::METHOD_POST;
        if (!empty($submitUrl)) {
            $attrs['action'] = $submitUrl;
        }
        return $this->htmlRenderer->renderBlockStart('form', $attrs);
    }

    public function closeForm()
    {
        return $this->htmlRenderer->renderBlockEnd('form');
    }

    public function renderLabel($text, $for = null, array $attrs = [])
    {
        if (null !== $for) {
            $attrs['for'] = $for;
        }
        return $this->htmlRenderer->renderBlock('label', $attrs, $text);
    }

    public function renderContentLabel($content, $for = null, array $attrs = [])
    {
        if (null !== $for) {
            $attrs['for'] = $for;
        }
        return $this->htmlRenderer->renderBlock('label', $attrs, $content, false);
    }

    public function renderInput($type, $name, $value = null, array $attrs = [])
    {
        $attrs['name']  = $name;
        $attrs['value'] = $value;
        $attrs['type']  = empty($type) ? 'text' : $type;
        return $this->htmlRenderer->renderInline('input', $attrs);
    }

    public function renderHidden($name, $value, array $attrs = [])
    {
        return $this->renderInput('hidden', $name, $value, $attrs);
    }

    public function renderList($name, array $options, $selected = null,
                               array $attrs = [])
    {
        $attrs['name'] = $name;
        $optionsList   = $this->renderOptionsList($options, $selected);
        return $this->htmlRenderer->renderBlock('select', $attrs, $optionsList, false);
    }

    public function renderCheckInput($name, $value = null, $selected = false,
                                     $type = 'checkbox', array $attrs = [])
    {
        if ($selected) {
            $attrs['checked'] = 'checked';
        } else {
            unset($attrs['checked']);
        }
        return $this->renderInput($type, $name, $value, $attrs);
    }

    /**
     * 
     * @param array $options
     * @param mixed $choosen
     */
    protected function renderOptionsList(array $options, $choosen = null)
    {
        if (empty($options)) {
            return '';
        }
        $rendered = '';
        foreach ($options as $value => $text) {
            $attrs = ['value' => $value];
            if ($value === $choosen) {
                $attrs['selected'] = 'selected';
            }
            $rendered .= $this->htmlRenderer->renderBlock('option', $attrs,
                                                          $text);
        }
        return $rendered;
    }

}
