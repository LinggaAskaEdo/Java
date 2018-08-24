
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}ReplaceCardResultCodeDto"/>
 *         &lt;element name="hardChecks" type="{http://homecredit.net/homerselect/ws/card/management/v2}HardCheckDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="newPcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId"/>
 *           &lt;element name="truncatedPan" type="{http://homecredit.net/homerselect/ws/card/common/v2}TruncatedPan"/>
 *         &lt;/sequence>
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
    "hardChecks",
    "newPcid",
    "truncatedPan"
})
@XmlRootElement(name = "ReplaceCardResponse")
public class ReplaceCardResponse {

    @XmlElement(required = true)
    protected ReplaceCardResultCodeDto resultCode;
    protected List<HardCheckDto> hardChecks;
    protected Long newPcid;
    protected String truncatedPan;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReplaceCardResultCodeDto }
     *     
     */
    public ReplaceCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplaceCardResultCodeDto }
     *     
     */
    public void setResultCode(ReplaceCardResultCodeDto value) {
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

    /**
     * Gets the value of the newPcid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNewPcid() {
        return newPcid;
    }

    /**
     * Sets the value of the newPcid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNewPcid(Long value) {
        this.newPcid = value;
    }

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

}
