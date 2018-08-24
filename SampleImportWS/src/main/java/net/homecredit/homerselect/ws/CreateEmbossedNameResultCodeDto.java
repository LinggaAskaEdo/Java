
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreateEmbossedNameResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CreateEmbossedNameResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="NOT_ALLOWED_FOR_CARD_TYPE"/>
 *     &lt;enumeration value="UNKNOWN_CARDTYPE"/>
 *     &lt;enumeration value="INSUFFICIENT_PARAMETERS_FOR_EMBOSSNAME_CREATION"/>
 *     &lt;enumeration value="OTHER_ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CreateEmbossedNameResultCodeDto")
@XmlEnum
public enum CreateEmbossedNameResultCodeDto {

    SUCCESS,
    NOT_ALLOWED_FOR_CARD_TYPE,
    UNKNOWN_CARDTYPE,
    INSUFFICIENT_PARAMETERS_FOR_EMBOSSNAME_CREATION,
    OTHER_ERROR;

    public String value() {
        return name();
    }

    public static CreateEmbossedNameResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
