
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DistributionStatusDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DistributionStatusDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ISSUED"/>
 *     &lt;enumeration value="PREPARED"/>
 *     &lt;enumeration value="SENT_TO_POS"/>
 *     &lt;enumeration value="AT_POS"/>
 *     &lt;enumeration value="DISTRIBUTED"/>
 *     &lt;enumeration value="SENT_TO_HQ"/>
 *     &lt;enumeration value="LIQUIDATION"/>
 *     &lt;enumeration value="LIQUIDATED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DistributionStatusDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum DistributionStatusDto {

    ISSUED,
    PREPARED,
    SENT_TO_POS,
    AT_POS,
    DISTRIBUTED,
    SENT_TO_HQ,
    LIQUIDATION,
    LIQUIDATED;

    public String value() {
        return name();
    }

    public static DistributionStatusDto fromValue(String v) {
        return valueOf(v);
    }

}
