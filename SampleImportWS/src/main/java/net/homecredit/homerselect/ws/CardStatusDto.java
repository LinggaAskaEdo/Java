
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardStatusDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CardStatusDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNCONFIRMED"/>
 *     &lt;enumeration value="CREATED"/>
 *     &lt;enumeration value="RESERVED"/>
 *     &lt;enumeration value="INACTIVE"/>
 *     &lt;enumeration value="RENEWED"/>
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="BLOCKED"/>
 *     &lt;enumeration value="DETAIN"/>
 *     &lt;enumeration value="STOLEN"/>
 *     &lt;enumeration value="LOST"/>
 *     &lt;enumeration value="CANCELLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CardStatusDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum CardStatusDto {

    UNCONFIRMED,
    CREATED,
    RESERVED,
    INACTIVE,
    RENEWED,
    ACTIVE,
    BLOCKED,
    DETAIN,
    STOLEN,
    LOST,
    CANCELLED;

    public String value() {
        return name();
    }

    public static CardStatusDto fromValue(String v) {
        return valueOf(v);
    }

}
