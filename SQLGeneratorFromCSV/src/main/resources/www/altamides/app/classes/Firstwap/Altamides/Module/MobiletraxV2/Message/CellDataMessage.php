<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : CellDataMessage.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Dwikky Maradhiza <dwikky.maradhiza@1rstwap.com>
 * Purpose          : Definition of class CellDataMessage
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2014-12-05   Dwikky Maradhiza Y        1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Message;

use Firstwap\Altamides\Module\MobiletraxV2\Message\EncodedMessageBase;
use Firstwap\Altamides\Module\MobiletraxV2\QuickLogger;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\AgeOfLocationMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\LocationApiErrorCodeMapper;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\LocationQualityMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\PhoneStatusMapper;
use Firstwap\Altamides\Module\MobiletraxV2\Codec\Gsm7EscOverflowException;

/**
 * Description of CellDataMessage
 *
 * @author Dwikky Maradhiza Yudakusuma
 */
class CellDataMessage extends EncodedMessageBase
{
    const SIZE_OF_SESSION_ID        = 7;
    const SIZE_OF_REQUEST_ID        = 14;
    const SIZE_OF_AZIMUTH           = 9;
    const SIZE_OF_RADIUS            = 17;
    const SIZE_OF_END_ANGLE         = 9;
    const SIZE_OF_LATITUDE          = 21;
    const SIZE_OF_LONGITUDE         = 22;
    const SIZE_OF_LOCATION_QUALITY  = 7;
    
    /**
     * 
     * @return type
     * @throws \RuntimeException
     * @throws Gsm7EscOverflowException
     */
    public function getMessage()
    {
        
        try {
            $this->dataEncoder->reset();
            QuickLogger::debug(__CLASS__ . ': Response data: ' . print_r($this->data, true));
            
            // token
            $this->dataEncoder->appendInt($this->data['sessionId'], self::SIZE_OF_SESSION_ID);
            $this->dataEncoder->appendInt($this->data['requestId'], self::SIZE_OF_REQUEST_ID);
            
            $locationQualityDbId = $this->data['locationQualityDbId'];
            
            // There's a case when the location is RBL the azimuth is missing
            // It is LWS fault but it is also good to take a preventive steps here
            if ($locationQualityDbId === LocationQuality::RBL) {
                $cellAzimuth = -1;
            } else {
                $cellAzimuth = $this->data['cellAzimuth'];
            }
            
            $this->dataEncoder->appendDegree($cellAzimuth, self::SIZE_OF_AZIMUTH, null);
            
            // Use -1 for omnidirectional antennae (azimuth < 0)
            $this->dataEncoder->appendInt($this->data['cellRadius'], self::SIZE_OF_RADIUS);
            
            $this->dataEncoder->appendDegree($this->data['cellEndAngle'], self::SIZE_OF_END_ANGLE, -1);
            
            switch ($locationQualityDbId) {
                case LocationQuality::CCC:
                case LocationQuality::ECC:
                    $this->dataEncoder->appendLatitude($this->data['btsLatitude']);
                    $this->dataEncoder->appendLongitude($this->data['btsLongitude']);
                    break;
                
                case LocationQuality::RBL:
                case LocationQuality::BTS:
                case LocationQuality::LAC:
                case LocationQuality::MSC:
                case LocationQuality::GPS:
                case LocationQuality::FLL:
                default:
                    $this->dataEncoder->appendLatitude($this->data['latitude']);
                    $this->dataEncoder->appendLongitude($this->data['longitude']);
                    break;
            }
            
            $locationQualityMapper = new LocationQualityMapper();
            $locationQuality = $locationQualityMapper->encode($locationQualityDbId);
            $this->dataEncoder->appendInt($locationQuality, self::SIZE_OF_LOCATION_QUALITY);
            
            QuickLogger::debug(__CLASS__ . ': Extra septet: ' . $this->dataEncoder->countExtraSeptet());

            // At this point there should not be any reminder more
            if ($this->dataEncoder->hasReminder()) {
                $reminder = dechex($this->dataEncoder->getReminderValue());
                throw new \RuntimeException('Unexpected reminder bits: ' . $reminder);
            }

            $escCount = $this->dataEncoder->countExtraSeptet();

            if ($escCount) {
                throw new Gsm7EscOverflowException($escCount);
            }

            $this->dataEncoder->appendEscCounter();
            $this->dataEncoder->padReminder();

            return $this->dataEncoder->getResult();
        } catch (Gsm7EscOverflowException $e) {
            QuickLogger::error("Esc counter error:\nEncoded:" .
                    $this->dataEncoder->getResult() . "\nError:$e");
            return self::ESC_OVERFLOW_RESULT;
        } catch (\Exception $e) {
            QuickLogger::error("An error has occured while encoding result: $e");
        }
    }

}
