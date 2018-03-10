<?php

/**
 * (c) 1rstWAP. All rights reserved.
 * 
 * System           : Altamides
 * Module           : 
 * Version          : 
 * File Name        : LocationErrorCode.php
 * File Version     : 1.000.000
 * Initial Creation : 2012-09-06
 * Initial Author   : Setia Budi Halim <setia.budi@1rstwap.com>
 * Purpose          : Definition of class LocationErrorCode
 * 
 * =====================================================================
 * Initial Request  : 
 * =====================================================================
 * Change Log
 * Date         Author                    Version    Request  Comment
 * 2012-09-06   Setia Budi Halim          1.000.000           Initial Creation
 */

namespace Firstwap\Altamides\Module\MobiletraxV2\Codec;

/**
 * Description of LocationErrorCode
 *
 * @author Setia Budi Halim
 */
class LocationApiErrorCodeMapper extends SimpleValueMapper
{
    public function __construct()
    {

        // Value 27 is not omitted as to avoid two bytes value
        $map = array(
            // <editor-fold defaultstate="collapsed" desc="Location API response code to its mobiletrax value map">
            // Target Interrogation Successful
            '0+0+0+0'                        => 0,
            // Insufficient location data for geographical target mapping
            '0+0+0+0+NLL'                    => 1,
            // A Secondary Method interrogation may return a better result.
            '0+0+0+0+NCILL'                  => 2,
            // Destination Network does not support subscriber information retrieval.
            '0+0+0+0+NALL'                   => 3,
            // Target device is switched-off.
            '0+0+0+0+OFF'                    => 4,
            // This Destination Network is not supported.
            '0+0+0+0+NCI'                    => 5,
            // Temporary Destination Network resource congestion or interrogation blocked by Destination Operator
            '0+4+0+0'                        => 6,
            // No response from Destination Network (SS7)/ no SS7 Coverage
            '0+8+0+0'                        => 7,
            // Unknown Target Number (Please check MSISDN or update System MSISDN Number Ranges)
            '1+0+0+0'                        => 8,
            // Interrogation Failure (no further information available)
            '10'                             => 9,
            // SMSC congestion.
            '103+0+0+0'                      => 10,
            // SMS Service not activated for this MSISDN
            '11+0+0+0'                       => 11,
            // Service not activated for this MSISDN
            '11+8+0+0'                       => 12,
            // Destination Network not opened (please contact your system administrator)
            '12+0+0+0'                       => 13,
            // MSISDN barred by Handset or Destination Network
            '13+0+0+0'                       => 14,
            // No SS7 Route to Destination Network
            '14+0+0+0'                       => 15,
            // Last Status received from Destination Network: Pending
            '140+0+0+0'                      => 16,
            // Destination Network unreachable
            '15+0+0+0'                       => 17,
            // Unknown Status (Destination Network did not return Status Information)
            '16+0+0+0'                       => 18,
            // Target is currently roaming with a destination network which cannot be reached via SS7
            '17+0+0+0'                       => 19,
            // Destination Network does not support location interrogations
            '18+0+0+0'                       => 20,
            // Destination Network does not support location interrogations
            '19+0+0+0'                       => 21,
            // Unknown Status; No Status obtainable for requested Service
            '2+0+0+0'                        => 22,
            // Service not authorized by Destination Network
            '20+0+0+0'                       => 23,
            // No response from the SS7 network
            '21+0+0+0'                       => 24,
            // Request rejected by Mobile Network
            '255+0+0+0'                      => 25,
            // IP Network Resource limitation. Please check with your system administrator.
            '256+0+0+0'                      => 26,
            // MSISDN switched off or out of coverage area (direct response from HLR)
            '27+0+0+0'                       => 28,
            // MSISDN switched off or out of coverage area (after retry)
            '27+8+0+0'                       => 29,
            // MSISDN unreachable; Requested Service expired based on System Retry Rules
            '300+0+0+0'                      => 30,
            // MSISDN unreachable; Requested Service expired based on System Retry Rules
            '301+0+0+0'                      => 31,
            // MSISDN busy
            '31+0+0+0'                       => 32,
            // SIM Card memory full
            '32+0+0+0'                       => 33,
            // SIM Card busy or protocol error (e.g. CDMA)
            '32+0+1+0'                       => 34,
            // Incompatible target device
            '32+0+2+0'                       => 35,
            // Temporary Congestion of visited MSC
            '34+0+0+0'                       => 36,
            // MSISDN not authorized to use SMSC
            '34+0+0+1'                       => 37,
            // Incompatible target device
            '34+0+0+2'                       => 38,
            // Temporary Error in visited MSC
            '34+0+0+3'                       => 39,
            // Memory of visited MSC full
            '34+0+0+4'                       => 40,
            // Service rejected by Destination Network (e.g. iSMSC not white-listed; VLR GT and MSRN mismatch) or Unsupported data value (e.g. Unicode not supported by visited MSC)
            '36+0+0+0'                       => 41,
            // IP Network Resource limitation. Please check with your system administrator.
            '37+0+0+0'                       => 42,
            // Message too long (>160 characters) and visited SMSC/SME does not support concatenated text messages
            '38+0+0+0'                       => 43,
            // SMSC exceeds maximum number of connections. Please contact your system administrator.
            '39+0+0+0'                       => 44,
            // Unknown originating number
            '5+0+0+0'                        => 45,
            // Destination SMSC does not return delivery status
            '500+0+0+0'                      => 46,
            // Target MSISDN absent. The destination network cannot contact the target MSISDN
            '6+0+0+0'                        => 47,
            // The requesting MSISDN is not authorized for location interrogations
            '62+0+0+0'                       => 48,
            // SMS-MT put into the store & forward cycle (Not an error!)
            '63+0+0+0'                       => 49,
            // The end of validity period has been reached
            '64+0+0+0'                       => 50,
            // Internal error due to abnormal MAP dialog (e.g. 1rstWAP sending MAP Version 3 but destination network supports MAP Version 1 only)
            '65+0+0+0'                       => 51,
            // No response from SS7 Interface. Please contact your system administrator.
            '7+0+0+0'                        => 52,
            // No response from SS7 Interface. Please contact your system administrator.
            '7+7+0+0'                        => 53,
            // No response from local SS7 Network. Please contact your system administrator.
            '8+0+0+0'                        => 54,
            // No response from transit SS7 Network. Please contact your system administrator.
            '8+8+0+0'                        => 55,
            // Service request rejected by Destination Network (e.g. Destination network blocked iSMSC (Global Title)
            '9+0+0+0'                        => 56,
            // Query status failed
            'ERROR'                          => 57,
            // Unsupported mobile network error
            'x+x+x+x'                        => 58,
            // User is not authorized to query this blacklisted target number. - phone status is unknown, last detected Cell ID = null
            '302'                            => 59,
            // Unsupported mobile network error
            'x+x+x+x'                        => 60,
            // Invalid Target Number
            '1rstwaperr+1'                   => 61,
            // -found valid location entry
            '1rstwaperr+knowcelldb'          => 62,
            // -LAC ({0}) value not in db
            '1rstwaperr+knowcelldbnotlac'    => 63,
            // -Cell ID ({0}) value not in db
            '1rstwaperr+knowcelldbnotcellid' => 64,
            // -location entry not found
            '1rstwaperr+unknowcelldb'        => 65,
            // -no exact cell data found
            '1rstwaperr+incompletecelldb'    => 66,
            // Found more than one numbering plan
            '1rstwaperr+morenumberingplan'   => 67,
            // The MSISDN format seems to be incorrect. Please check MSISDN and/or topicality of International Numbering Plan.
            '1rstwaperr+notsuitablesnl'      => 68,
            // Missing INP data
            '1rstwaperr+nonumberingplan'     => 69,
            // Unknown Location Quality
            'unknown_calc_method'            => 70,
            // You are not allowed to locate this MSISDN
            '1rstwaperr+2'                   => 71,
            // You are not allowed to schedule this MSISDN
            '1rstwaperr+3'                   => 72,
            // Please login again
            '1rstwaperr+4'                   => 73,
            // Location interrogation is currently not possible
            '1rstwaperr+5'                   => 74,
            // Invalid Country Code or Area Prefix
            '1rstwaperr+6'                   => 75,
            // Page update request is aborted by the operator, the location interrogation is performed by using PRIMARY
            '1rstwaperr+7'                   => 76,
            // Page update request is aborted by the operator, the location interrogation is performed by using FAST SECONDARY
            '1rstwaperr+8'                   => 77,
            // Interrogation of Roaming MSISDNs is not allowed
            'Restrict+001'                   => 78,
            // Only Interrogation of Local MSISDNs are allowed
            'Restrict+002'                   => 79,
            // Locating Target Number is not allowed
            'Restrict+003'                   => 80,
            // Not Allowed, because Trial Period/License has expired
            'Restrict+004'                   => 81,
            // Target number is beyond the user's whitelist range
            '303'                            => 82,
            // user don't have privilege
            '205'                            => 83,
            // </editor-fold>
        );

        $this->setMap($map);
        $this->errorCode = 127;
        $this->errorValue = null;
    }

}