
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="truncatedPan" type="{http://homecredit.net/homerselect/ws/card/common/v2}TruncatedPan" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}ReserveCardResultCodeDto"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "truncatedPan",
    "resultCode"
})
@XmlRootElement(name = "ReserveCardResponse")
public class ReserveCardResponse {

    protected String truncatedPan;
    @XmlElement(required = true)
    protected ReserveCardResultCodeDto resultCode;

    /**
     * Gets the value of the truncatedPan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruncatedPan() {
        return truncatedPan;
    }

    /**
     * Sets the value of the truncatedPan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruncatedPan(String value) {
        this.truncatedPan = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReserveCardResultCodeDto }
     *     
     */
    public ReserveCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReserveCardResultCodeDto }
     *     
     */
    public void setResultCode(ReserveCardResultCodeDto value) {
        this.resultCode = value;
    }

}
