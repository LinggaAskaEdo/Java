
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REVOLVING_ACCOUNT"/>
 *     &lt;enumeration value="CURRENT_ACCOUNT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountTypeDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2")
@XmlEnum
public enum AccountTypeDto {

    REVOLVING_ACCOUNT,
    CURRENT_ACCOUNT;

    public String value() {
        return name();
    }

    public static AccountTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
