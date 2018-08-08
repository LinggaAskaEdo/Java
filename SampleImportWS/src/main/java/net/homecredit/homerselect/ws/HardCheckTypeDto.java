
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HardCheckTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HardCheckTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CUSTOMER_AGE"/>
 *     &lt;enumeration value="CARD_BLOCKED"/>
 *     &lt;enumeration value="CARD_STATUS"/>
 *     &lt;enumeration value="CONTRACT_STATUS"/>
 *     &lt;enumeration value="DPD_LIMIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HardCheckTypeDto")
@XmlEnum
public enum HardCheckTypeDto {

    CUSTOMER_AGE,
    CARD_BLOCKED,
    CARD_STATUS,
    CONTRACT_STATUS,
    DPD_LIMIT;

    public String value() {
        return name();
    }

    public static HardCheckTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
