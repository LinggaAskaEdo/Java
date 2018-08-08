
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SourceSystemEnumDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SourceSystemEnumDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BSL"/>
 *     &lt;enumeration value="RISK"/>
 *     &lt;enumeration value="COLLECTION"/>
 *     &lt;enumeration value="CARD"/>
 *     &lt;enumeration value="ACCOUNT"/>
 *     &lt;enumeration value="UFO"/>
 *     &lt;enumeration value="IB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SourceSystemEnumDto", namespace = "http://homecredit.net/homerselect/ws/common/v2")
@XmlEnum
public enum SourceSystemEnumDto {

    BSL,
    RISK,
    COLLECTION,
    CARD,
    ACCOUNT,
    UFO,

    /**
     * Internet Banking
     * 
     */
    IB;

    public String value() {
        return name();
    }

    public static SourceSystemEnumDto fromValue(String v) {
        return valueOf(v);
    }

}
