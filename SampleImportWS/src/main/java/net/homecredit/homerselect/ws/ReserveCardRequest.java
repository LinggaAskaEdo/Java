
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Reserves instant card with the given reservation code.
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
 *         &lt;element name="reservationCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}ReservationCode"/>
 *         &lt;element name="embossedName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName" minOccurs="0"/>
 *         &lt;element name="cardHolderName" type="{http://homecredit.net/homerselect/ws/card/management/v2}CardHolderName" minOccurs="0"/>
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
    "reservationCode",
    "embossedName",
    "cardHolderName"
})
@XmlRootElement(name = "ReserveCardRequest")
public class ReserveCardRequest {

    protected long pcid;
    protected long reservationCode;
    protected String embossedName;
    protected CardHolderName cardHolderName;

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
     * Gets the value of the reservationCode property.
     * 
     */
    public long getReservationCode() {
        return reservationCode;
    }

    /**
     * Sets the value of the reservationCode property.
     * 
     */
    public void setReservationCode(long value) {
        this.reservationCode = value;
    }

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
     * Gets the value of the cardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link CardHolderName }
     *     
     */
    public CardHolderName getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the value of the cardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardHolderName }
     *     
     */
    public void setCardHolderName(CardHolderName value) {
        this.cardHolderName = value;
    }

}
