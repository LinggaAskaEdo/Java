
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlasticStatusDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlasticStatusDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NEW"/>
 *     &lt;enumeration value="OBTAINED"/>
 *     &lt;enumeration value="USED"/>
 *     &lt;enumeration value="INVALID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PlasticStatusDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum PlasticStatusDto {

    NEW,
    OBTAINED,
    USED,
    INVALID;

    public String value() {
        return name();
    }

    public static PlasticStatusDto fromValue(String v) {
        return valueOf(v);
    }

}
