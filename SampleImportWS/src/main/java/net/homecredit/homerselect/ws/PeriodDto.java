
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PeriodDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PeriodDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SINGLE_UPPER"/>
 *     &lt;enumeration value="SINGLE_LOWER"/>
 *     &lt;enumeration value="DAY"/>
 *     &lt;enumeration value="WEEK"/>
 *     &lt;enumeration value="MONTH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PeriodDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum PeriodDto {

    SINGLE_UPPER,
    SINGLE_LOWER,
    DAY,
    WEEK,
    MONTH;

    public String value() {
        return name();
    }

    public static PeriodDto fromValue(String v) {
        return valueOf(v);
    }

}
