
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
 *         &lt;element name="pcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId"/>
 *         &lt;element name="cardTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suppressFee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transferPin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cancelOriginalCard" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "cardTypeId",
    "suppressFee",
    "transferPin",
    "cancelOriginalCard"
})
@XmlRootElement(name = "ReplaceCardRequest")
public class ReplaceCardRequest {

    protected long pcid;
    @XmlElement(required = true)
    protected String cardTypeId;
    protected boolean suppressFee;
    protected boolean transferPin;
    protected Boolean cancelOriginalCard;

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
     * Gets the value of the cardTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardTypeId() {
        return cardTypeId;
    }

    /**
     * Sets the value of the cardTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardTypeId(String value) {
        this.cardTypeId = value;
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

    /**
     * Gets the value of the transferPin property.
     * 
     */
    public boolean isTransferPin() {
        return transferPin;
    }

    /**
     * Sets the value of the transferPin property.
     * 
     */
    public void setTransferPin(boolean value) {
        this.transferPin = value;
    }

    /**
     * Gets the value of the cancelOriginalCard property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelOriginalCard() {
        return cancelOriginalCard;
    }

    /**
     * Sets the value of the cancelOriginalCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelOriginalCard(Boolean value) {
        this.cancelOriginalCard = value;
    }

}
