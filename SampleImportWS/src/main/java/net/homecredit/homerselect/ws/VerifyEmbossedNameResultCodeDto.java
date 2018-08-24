
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerifyEmbossedNameResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VerifyEmbossedNameResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="NOT_ALLOWED_FOR_CARD_TYPE"/>
 *     &lt;enumeration value="UNKNOWN_CARDTYPE"/>
 *     &lt;enumeration value="INSUFFICIENT_PARAMETERS_FOR_EMBOSSNAME_VALIDATION"/>
 *     &lt;enumeration value="INVALID_CHARACTERS"/>
 *     &lt;enumeration value="MISSING_SURNAME"/>
 *     &lt;enumeration value="LENGTH_VIOLATION"/>
 *     &lt;enumeration value="OTHER_ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VerifyEmbossedNameResultCodeDto")
@XmlEnum
public enum VerifyEmbossedNameResultCodeDto {

    SUCCESS,
    NOT_ALLOWED_FOR_CARD_TYPE,
    UNKNOWN_CARDTYPE,
    INSUFFICIENT_PARAMETERS_FOR_EMBOSSNAME_VALIDATION,
    INVALID_CHARACTERS,
    MISSING_SURNAME,
    LENGTH_VIOLATION,
    OTHER_ERROR;

    public String value() {
        return name();
    }

    public static VerifyEmbossedNameResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
