<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace Firstwap\Firstphp\Filter;

/**
 * Description of DateTimeFilter
 *
 * @author Setia Budi Halim
 */
class DateTimeFilter extends AbstractFilter
{

    /**
     *
     * @var \DateTimeZone
     */
    protected $timezone;

    /**
     *
     * @var \DateTimeInterface
     */
    protected $min = null;

    /**
     *
     * @var \DateTimeInterface
     */
    protected $max = null;

    public function __construct(\DateTimeZone $timezone = null)
    {
        $this->setTimeZone($timezone ? : new \DateTimeZone(\date_default_timezone_get()));
    }

    /**
     * 
     * @return \DateTimeInterface|null
     */
    public function getMin()
    {
        return $this->min;
    }

    /**
     * 
     * @return \DateTimeInterface|null
     */
    public function getMax()
    {
        return $this->max;
    }

    /**
     * 
     * @param \DateTimeInterface|null $min
     */
    public function setMin(\DateTimeInterface $min)
    {
        $this->min = $this->ensureDateTimeType($min, true);
    }

    /**
     * 
     * @param \DateTimeInterface|null $max
     */
    public function setMax(\DateTimeInterface $max)
    {
        $this->max = $this->ensureDateTimeType($max, true);
    }

    /**
     * 
     * @param \DateTimeZone $tz
     */
    public function setTimezone(\DateTimeZone $tz)
    {
        $this->timezone = $tz;
    }

    /**
     * 
     * @return \DateTimeZone
     */
    public function getTimezone()
    {
        return $this->timezone;
    }

    /**
     * 
     * @param \DateTimeInterface|string $value
     * @param boolean $valid
     * @return \DateTimeInterface|null
     */
    protected function filter($value, &$valid)
    {
        try {
            if (!$value instanceof \DateTimeInterface) {
                $value = new \DateTime((string) $value, $this->timezone);
            }

            $valid = $this->checkRange($value);
            return $valid ? $value : null;
        } catch (\Exception $e) {
            \trigger_error("$e", \E_USER_NOTICE);
            $valid = false;
            return null;
        }
    }

    /**
     * 
     * @param \DateTimeInterface $value
     * @return boolean
     */
    protected function checkRange(\DateTimeInterface $value)
    {
        if ($this->min && ($this->min > new \DateTime($value,
                $this->min->getTimezone()))) {
            return false;
        }
        if ($this->max && ($this->max > new \DateTime($value,
                $this->max->getTimezone()))) {
            return false;
        }
        return true;
    }

}
