
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Activate Card response.
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
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}ActivateCardResultCodeDto"/>
 *         &lt;element name="hardChecks" type="{http://homecredit.net/homerselect/ws/card/management/v2}HardCheckDto" maxOccurs="unbounded" minOccurs="0"/>
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
    "resultCode",
    "hardChecks"
})
@XmlRootElement(name = "ActivateCardResponse")
public class ActivateCardResponse {

    @XmlElement(required = true)
    protected ActivateCardResultCodeDto resultCode;
    protected List<HardCheckDto> hardChecks;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link ActivateCardResultCodeDto }
     *     
     */
    public ActivateCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivateCardResultCodeDto }
     *     
     */
    public void setResultCode(ActivateCardResultCodeDto value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the hardChecks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hardChecks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHardChecks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HardCheckDto }
     * 
     * 
     */
    public List<HardCheckDto> getHardChecks() {
        if (hardChecks == null) {
            hardChecks = new ArrayList<HardCheckDto>();
        }
        return this.hardChecks;
    }

}
