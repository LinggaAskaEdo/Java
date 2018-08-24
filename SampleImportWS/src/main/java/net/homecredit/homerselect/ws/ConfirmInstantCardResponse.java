
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Instant card confirmation response.
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}ConfirmInstantCardResultCodeDto"/>
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
    "resultCode"
})
@XmlRootElement(name = "ConfirmInstantCardResponse")
public class ConfirmInstantCardResponse {

    @XmlElement(required = true)
    protected ConfirmInstantCardResultCodeDto resultCode;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmInstantCardResultCodeDto }
     *     
     */
    public ConfirmInstantCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmInstantCardResultCodeDto }
     *     
     */
    public void setResultCode(ConfirmInstantCardResultCodeDto value) {
        this.resultCode = value;
    }

}
