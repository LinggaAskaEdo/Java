
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetPlasticsOfCardResultCodeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GetPlasticsOfCardResultCodeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ERROR_CARD_NOT_FOUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GetPlasticsOfCardResultCodeDto")
@XmlEnum
public enum GetPlasticsOfCardResultCodeDto {

    SUCCESS,
    ERROR_CARD_NOT_FOUND;

    public String value() {
        return name();
    }

    public static GetPlasticsOfCardResultCodeDto fromValue(String v) {
        return valueOf(v);
    }

}
