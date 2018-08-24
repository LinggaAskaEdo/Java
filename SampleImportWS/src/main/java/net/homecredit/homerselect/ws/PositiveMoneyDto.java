
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Positive money definition - amount >= 0 and currency.
 * 
 * <p>Java class for PositiveMoneyDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PositiveMoneyDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://homecredit.net/homerselect/ws/common/v2}MoneyDto">
 *       &lt;sequence>
 *         &lt;element name="value">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="currency" type="{http://homecredit.net/homerselect/ws/common/v2}CurrencyCodeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositiveMoneyDto", namespace = "http://homecredit.net/homerselect/ws/common/v2")
public class PositiveMoneyDto
    extends MoneyDto
{


}
