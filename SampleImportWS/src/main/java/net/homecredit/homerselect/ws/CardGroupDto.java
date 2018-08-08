
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardGroupDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CardGroupDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PERSONALIZED"/>
 *     &lt;enumeration value="INSTANT"/>
 *     &lt;enumeration value="NONAME"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CardGroupDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum CardGroupDto {

    PERSONALIZED,
    INSTANT,
    NONAME;

    public String value() {
        return name();
    }

    public static CardGroupDto fromValue(String v) {
        return valueOf(v);
    }

}
