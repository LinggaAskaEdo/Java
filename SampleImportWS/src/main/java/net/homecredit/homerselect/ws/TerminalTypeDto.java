
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TerminalTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TerminalTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CARD_PRESENT"/>
 *     &lt;enumeration value="ONLINE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TerminalTypeDto", namespace = "http://homecredit.net/homerselect/ws/common/v2")
@XmlEnum
public enum TerminalTypeDto {

    CARD_PRESENT,

    /**
     * card not present, MOTO
     * 
     */
    ONLINE;

    public String value() {
        return name();
    }

    public static TerminalTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
