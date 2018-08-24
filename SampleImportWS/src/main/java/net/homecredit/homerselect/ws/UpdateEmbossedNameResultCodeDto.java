
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateEmbossedNameResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdateEmbossedNameResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="CARD_NOT_FOUND"/>
 *     &lt;enumeration value="INVALID_CHARACTERS"/>
 *     &lt;enumeration value="NOT_ALLOWED_FOR_CARD_TYPE"/>
 *     &lt;enumeration value="MISSING_SURNAME"/>
 *     &lt;enumeration value="LENGTH_VIOLATION"/>
 *     &lt;enumeration value="OTHER_ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdateEmbossedNameResultCodeDto")
@XmlEnum
public enum UpdateEmbossedNameResultCodeDto {

    SUCCESS,
    CARD_NOT_FOUND,
    INVALID_CHARACTERS,
    NOT_ALLOWED_FOR_CARD_TYPE,
    MISSING_SURNAME,
    LENGTH_VIOLATION,
    OTHER_ERROR;

    public String value() {
        return name();
    }

    public static UpdateEmbossedNameResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
