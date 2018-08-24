
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HardCheckDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HardCheckDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://homecredit.net/homerselect/ws/card/management/v2}HardCheckTypeDto"/>
 *         &lt;element name="result" type="{http://homecredit.net/homerselect/ws/card/management/v2}HardCheckResultDto"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HardCheckDto", propOrder = {
    "type",
    "result"
})
@XmlSeeAlso({
    CardBlockHardCheckDto.class
})
public class HardCheckDto {

    @XmlElement(required = true)
    protected HardCheckTypeDto type;
    @XmlElement(required = true)
    protected HardCheckResultDto result;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link HardCheckTypeDto }
     *     
     */
    public HardCheckTypeDto getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardCheckTypeDto }
     *     
     */
    public void setType(HardCheckTypeDto value) {
        this.type = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link HardCheckResultDto }
     *     
     */
    public HardCheckResultDto getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardCheckResultDto }
     *     
     */
    public void setResult(HardCheckResultDto value) {
        this.result = value;
    }

}
