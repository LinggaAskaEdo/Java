
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoundingType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RoundingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UP"/>
 *     &lt;enumeration value="DOWN"/>
 *     &lt;enumeration value="TRUNC"/>
 *     &lt;enumeration value="MATH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoundingType", namespace = "http://homecredit.net/homerselect/ws/common/v2")
@XmlEnum
public enum RoundingType {


    /**
     * UP - RoundingMode.UP
     * 
     */
    UP,

    /**
     * DOWN - RoundingMode.FLOOR
     * 
     */
    DOWN,

    /**
     * TRUNC - RoundingMode.DOWN
     * 
     */
    TRUNC,

    /**
     * MATH - RoundingMode.HALF_UP
     * 
     */
    MATH;

    public String value() {
        return name();
    }

    public static RoundingType fromValue(String v) {
        return valueOf(v);
    }

}
