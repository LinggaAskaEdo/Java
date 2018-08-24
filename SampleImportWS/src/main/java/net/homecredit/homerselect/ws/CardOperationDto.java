
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardOperationDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CardOperationDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RESERVE"/>
 *     &lt;enumeration value="CONFIRM"/>
 *     &lt;enumeration value="ACTIVATE"/>
 *     &lt;enumeration value="BLOCK"/>
 *     &lt;enumeration value="UNBLOCK"/>
 *     &lt;enumeration value="LOST"/>
 *     &lt;enumeration value="STOLEN"/>
 *     &lt;enumeration value="DETAIN"/>
 *     &lt;enumeration value="RENEW"/>
 *     &lt;enumeration value="CANCEL"/>
 *     &lt;enumeration value="REPLACE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CardOperationDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum CardOperationDto {


    /**
     * Reserve card (ReserveCardRequest)
     * 
     */
    RESERVE,

    /**
     * Initialize card (InitializeCardRequest)
     * 
     */
    CONFIRM,

    /**
     * Activate card (ActivateCardRequest)
     * 
     */
    ACTIVATE,

    /**
     * Block card (BlockCardRequest)
     * 
     */
    BLOCK,

    /**
     * Unblock card (UnblockCardRequest)
     * 
     */
    UNBLOCK,

    /**
     * Report card as lost (LostCardRequest)
     * 
     */
    LOST,

    /**
     * Report card as stolen (StolenCardRequest)
     * 
     */
    STOLEN,

    /**
     * Report that card should be held back when used next time (HoldBackCardRequest)
     * 
     */
    DETAIN,

    /**
     * Renew card
     * 
     */
    RENEW,

    /**
     * Cancel card (CancelCardRequest)
     * 
     */
    CANCEL,

    /**
     * Replace the card with a new card (ReplaceCardRequest)
     * 
     */
    REPLACE;

    public String value() {
        return name();
    }

    public static CardOperationDto fromValue(String v) {
        return valueOf(v);
    }

}
