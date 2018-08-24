
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetCardsResponseResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GetCardsResponseResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR_NO_FILTERING_CRITERIA_PROVIDED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GetCardsResponseResultCodeDto")
@XmlEnum
public enum GetCardsResponseResultCodeDto {

    SUCCESS,
    ERROR_NO_FILTERING_CRITERIA_PROVIDED;

    public String value() {
        return name();
    }

    public static GetCardsResponseResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
