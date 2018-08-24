
package net.homecredit.homerselect.ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Card limit data transfer object
 * 
 * <p>Java class for LimitDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LimitDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="limitTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period" type="{http://homecredit.net/homerselect/ws/card/common/v2}PeriodDto"/>
 *         &lt;choice>
 *           &lt;element name="currentValueAmount" type="{http://homecredit.net/homerselect/ws/card/common/v2}PositiveAmount"/>
 *           &lt;element name="currentValueCount" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "limitTypeId",
    "period",
    "currentValueAmount",
    "currentValueCount"
})
public class LimitDto {

    @XmlElement(required = true)
    protected String limitTypeId;
    @XmlElement(required = true)
    protected PeriodDto period;
    protected BigDecimal currentValueAmount;
    protected BigInteger currentValueCount;

    /**
     * Gets the value of the limitTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitTypeId() {
        return limitTypeId;
    }

    /**
     * Sets the value of the limitTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitTypeId(String value) {
        this.limitTypeId = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodDto }
     *     
     */
    public PeriodDto getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodDto }
     *     
     */
    public void setPeriod(PeriodDto value) {
        this.period = value;
    }

    /**
     * Gets the value of the currentValueAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentValueAmount() {
        return currentValueAmount;
    }

    /**
     * Sets the value of the currentValueAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentValueAmount(BigDecimal value) {
        this.currentValueAmount = value;
    }

    /**
     * Gets the value of the currentValueCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCurrentValueCount() {
        return currentValueCount;
    }

    /**
     * Sets the value of the currentValueCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCurrentValueCount(BigInteger value) {
        this.currentValueCount = value;
    }

}
