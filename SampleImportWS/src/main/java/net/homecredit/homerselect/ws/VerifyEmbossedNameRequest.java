
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Verify embossed name request.
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
 *         &lt;element name="embossedName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName"/>
 *         &lt;element name="cardholderName" type="{http://homecredit.net/homerselect/ws/card/management/v2}CardHolderName"/>
 *         &lt;element name="cardType" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardTypeId"/>
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
    "embossedName",
    "cardholderName",
    "cardType"
})
@XmlRootElement(name = "VerifyEmbossedNameRequest")
public class VerifyEmbossedNameRequest {

    @XmlElement(required = true)
    protected String embossedName;
    @XmlElement(required = true)
    protected CardHolderName cardholderName;
    @XmlElement(required = true)
    protected String cardType;

    /**
     * Gets the value of the embossedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmbossedName() {
        return embossedName;
    }

    /**
     * Sets the value of the embossedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmbossedName(String value) {
        this.embossedName = value;
    }

    /**
     * Gets the value of the cardholderName property.
     * 
     * @return
     *     possible object is
     *     {@link CardHolderName }
     *     
     */
    public CardHolderName getCardholderName() {
        return cardholderName;
    }

    /**
     * Sets the value of the cardholderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardHolderName }
     *     
     */
    public void setCardholderName(CardHolderName value) {
        this.cardholderName = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

}
