
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FindCardResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FindCardResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR_CARD_NOT_FOUND"/>
 *     &lt;enumeration value="ERROR_MULTIPLE_CARDS_FOUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FindCardResultCodeDto")
@XmlEnum
public enum FindCardResultCodeDto {

    SUCCESS,
    ERROR_CARD_NOT_FOUND,
    ERROR_MULTIPLE_CARDS_FOUND;

    public String value() {
        return name();
    }

    public static FindCardResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
