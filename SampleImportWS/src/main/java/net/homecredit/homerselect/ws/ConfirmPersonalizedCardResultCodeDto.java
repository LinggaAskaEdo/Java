
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfirmPersonalizedCardResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConfirmPersonalizedCardResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR_CARD_NOT_FOUND"/>
 *     &lt;enumeration value="ERROR_INVALID_CARD_TYPE"/>
 *     &lt;enumeration value="ERROR_OPERATION_NOT_ALLOWED_FOR_CURRENT_STATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConfirmPersonalizedCardResultCodeDto")
@XmlEnum
public enum ConfirmPersonalizedCardResultCodeDto {

    SUCCESS,
    ERROR_CARD_NOT_FOUND,
    ERROR_INVALID_CARD_TYPE,
    ERROR_OPERATION_NOT_ALLOWED_FOR_CURRENT_STATE;

    public String value() {
        return name();
    }

    public static ConfirmPersonalizedCardResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
