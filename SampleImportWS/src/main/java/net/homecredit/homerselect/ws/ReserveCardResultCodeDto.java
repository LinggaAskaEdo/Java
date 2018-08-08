
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReserveCardResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReserveCardResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR_CARD_ALREADY_RESERVED"/>
 *     &lt;enumeration value="ERROR_CARD_NOT_FOUND"/>
 *     &lt;enumeration value="ERROR_OPERATION_NOT_ALLOWED_FOR_CURRENT_STATE"/>
 *     &lt;enumeration value="ERROR_INVALID_CARD_TYPE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReserveCardResultCodeDto")
@XmlEnum
public enum ReserveCardResultCodeDto {

    SUCCESS,
    ERROR_CARD_ALREADY_RESERVED,
    ERROR_CARD_NOT_FOUND,
    ERROR_OPERATION_NOT_ALLOWED_FOR_CURRENT_STATE,
    ERROR_INVALID_CARD_TYPE;

    public String value() {
        return name();
    }

    public static ReserveCardResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
