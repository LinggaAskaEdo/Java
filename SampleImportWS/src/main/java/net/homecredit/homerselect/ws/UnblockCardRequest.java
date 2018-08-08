
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Unblock card request specifies blocks to be cleared.
 *                     The Card may still stay blocked for different reason(s).
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
 *         &lt;element name="pcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId"/>
 *         &lt;element name="cardBlockTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suppressFee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "pcid",
    "cardBlockTypeId",
    "suppressFee"
})
@XmlRootElement(name = "UnblockCardRequest")
public class UnblockCardRequest {

    protected long pcid;
    @XmlElement(required = true)
    protected String cardBlockTypeId;
    protected boolean suppressFee;

    /**
     * Gets the value of the pcid property.
     * 
     */
    public long getPcid() {
        return pcid;
    }

    /**
     * Sets the value of the pcid property.
     * 
     */
    public void setPcid(long value) {
        this.pcid = value;
    }

    /**
     * Gets the value of the cardBlockTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardBlockTypeId() {
        return cardBlockTypeId;
    }

    /**
     * Sets the value of the cardBlockTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardBlockTypeId(String value) {
        this.cardBlockTypeId = value;
    }

    /**
     * Gets the value of the suppressFee property.
     * 
     */
    public boolean isSuppressFee() {
        return suppressFee;
    }

    /**
     * Sets the value of the suppressFee property.
     * 
     */
    public void setSuppressFee(boolean value) {
        this.suppressFee = value;
    }

}
