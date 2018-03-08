<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocateFastResultEncoder.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class LocateFastResultEncoder
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-06   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\Mobiletrax\Message;

use Firstwap\Altamides\Module\Mobiletrax\Message\EncodedMessageBase;
use Firstwap\Altamides\Module\Mobiletrax\QuickLogger;
use Firstwap\Altamides\Module\Mobiletrax\Codec\AgeOfLocationMapper;
use Firstwap\Altamides\Module\Mobiletrax\Codec\LocationApiErrorCodeMapper;
use Firstwap\Altamides\Tracking\LocationApi\Report\LocationQuality;
use Firstwap\Altamides\Module\Mobiletrax\Codec\LocationQualityMapper;
use Firstwap\Altamides\Module\Mobiletrax\Codec\PhoneStatusMapper;
use Firstwap\Altamides\Module\Mobiletrax\Codec\Gsm7EscOverflowException;

/**
 * Description of LocateFastResultEncoder
 *
 * @author Setia Budi Halim
 */
class LocateFastMessage extends EncodedMessageBase
{

    const SIZE_OF_CELL_LAC         = 16;
    const SIZE_OF_CELL_ID          = 16;
    const SIZE_OF_IMSI             = 54;
    const SIZE_OF_IMEI             = 54;
    const SIZE_OF_AZIMUTH          = 9;
    const SIZE_OF_RADIUS           = 17;
    const SIZE_OF_END_ANGLE        = 9;
    const SIZE_OF_LOCATION_QUALITY = 5;
    const SIZE_OF_PHONE_STATUS     = 2;
    const SIZE_OF_ROAMING_NUMBER   = 60;
    const SIZE_OF_VLR_GT           = 60;
    const SIZE_OF_AOL              = 7;
    const SIZE_OF_API_ERROR_CODE   = 7;
    
    const CHAR_OF_VLR_NAME         = 50;
    const CHAR_OF_REGION           = 32;
    const CHAR_OF_ZIP              = 8;

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
            $this->dataEncoder->appendMncPair($this->data['homeMcc'], $this->data['homeMnc']);

            // cell ref is MCC.MNC.LAC.CELL_ID
            $targetCellPart = explode('.', $this->data['cellRef']);
            $this->dataEncoder->appendMncPair($targetCellPart[0], $targetCellPart[1]);

            $lac = $targetCellPart[2];
            $this->dataEncoder->appendInt($lac, self::SIZE_OF_CELL_LAC);

            $cellId = $targetCellPart[3];
            $this->dataEncoder->appendInt($cellId, self::SIZE_OF_CELL_ID);

            $this->dataEncoder->appendInt($this->data['imsi'], self::SIZE_OF_IMSI);
            $this->dataEncoder->appendInt($this->data['imei'], self::SIZE_OF_IMEI);
            
            $locationQualityDbId = $this->data['locationQualityDbId'];
            
            // There's a case when the location is RBL the azimuth is missing
            // It is LWS fault but it is also good to take a preventive steps here
            if ($locationQualityDbId === LocationQuality::RBL) {
                $cellAzimuth = -1;
            } else {
                $cellAzimuth = $this->data['cellAzimuth'];
            }
            
            $this->dataEncoder->appendDegree($cellAzimuth, self::SIZE_OF_AZIMUTH, -1);
            
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
            
            $phoneStatusMapper = new PhoneStatusMapper();
            $phoneStatus = $phoneStatusMapper->encode((int) $this->data['phoneStatus']);
            $this->dataEncoder->appendInt($phoneStatus, self::SIZE_OF_PHONE_STATUS);

            $roamingNumber = ltrim($this->data['roamingNumber'], '+');
            $this->dataEncoder->appendInt($roamingNumber, self::SIZE_OF_ROAMING_NUMBER);

            $vlrGlobalTitle = ltrim($this->data['vlrGlobalTitle'], '+');
            $this->dataEncoder->appendInt($vlrGlobalTitle, self::SIZE_OF_VLR_GT);

            $aolMapper = new AgeOfLocationMapper();
            $this->dataEncoder->appendInt($aolMapper->encode($this->data['ageOfLocation']), self::SIZE_OF_AOL);

            $errorCodeMapper = new LocationApiErrorCodeMapper();
            $mappedErrorCode = $errorCodeMapper->encode($this->data['errorCode']);
            $this->dataEncoder->appendInt($mappedErrorCode, self::SIZE_OF_API_ERROR_CODE);

            QuickLogger::debug(__CLASS__ . ': Extra septet: ' . $this->dataEncoder->countExtraSeptet());

            // At this point there should not be any reminder more
            if ($this->dataEncoder->hasReminder()) {
                $reminder = dechex($this->dataEncoder->getReminderValue());
                throw new \RuntimeException('Unexpected reminder bits: ' . $reminder);
            }

            $this->dataEncoder->appendEscTradeOffString($this->data['vlrName'], self::CHAR_OF_VLR_NAME);
            $this->dataEncoder->appendEscTradeOffString($this->data['region'], self::CHAR_OF_REGION);
            $this->dataEncoder->appendBasicString($this->data['zipCode'], self::CHAR_OF_ZIP);

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
