
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionSubTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionSubTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TW"/>
 *     &lt;enumeration value="CD"/>
 *     &lt;enumeration value="CL"/>
 *     &lt;enumeration value="REG_INT"/>
 *     &lt;enumeration value="UN_GP_INT"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionSubTypeDto", namespace = "http://homecredit.net/homerselect/ws/common/v2")
@XmlEnum
public enum TransactionSubTypeDto {


    /**
     * Two wheeler.
     * 
     */
    TW,

    /**
     * Consumer goods.
     * 
     */
    CD,

    /**
     * Cash loan.
     * 
     */
    CL,

    /**
     * Regular interest.
     * 
     */
    REG_INT,

    /**
     * Unused grace period interest.
     * 
     */
    UN_GP_INT,

    /**
     * Unknown transaction sub type.
     * 
     */
    UNKNOWN;

    public String value() {
        return name();
    }

    public static TransactionSubTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
