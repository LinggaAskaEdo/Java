
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Card data transfer object.
 * 
 * <p>Java class for CardDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId"/>
 *         &lt;element name="truncatedPan" type="{http://homecredit.net/homerselect/ws/card/common/v2}TruncatedPan"/>
 *         &lt;element name="holderCuid" type="{http://homecredit.net/homerselect/ws/card/common/v2}Cuid"/>
 *         &lt;element name="holderName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName"/>
 *         &lt;element name="activeFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="cardType" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardTypeDto"/>
 *         &lt;element name="status" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardStatusDto"/>
 *         &lt;element name="account" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountDto"/>
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "pcid",
    "truncatedPan",
    "holderCuid",
    "holderName",
    "activeFrom",
    "cardType",
    "status",
    "account",
    "validTo"
})
@XmlSeeAlso({
    CardDetailDto.class
})
public class CardDto {

    protected long pcid;
    @XmlElement(required = true)
    protected String truncatedPan;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long holderCuid;
    @XmlElement(required = true, nillable = true)
    protected String holderName;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar activeFrom;
    @XmlElement(required = true)
    protected CardTypeDto cardType;
    @XmlElement(required = true)
    protected CardStatusDto status;
    @XmlElement(required = true, nillable = true)
    protected AccountDto account;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;

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
     * Gets the value of the holderCuid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHolderCuid() {
        return holderCuid;
    }

    /**
     * Sets the value of the holderCuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHolderCuid(Long value) {
        this.holderCuid = value;
    }

    /**
     * Gets the value of the holderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets the value of the holderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolderName(String value) {
        this.holderName = value;
    }

    /**
     * Gets the value of the activeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveFrom() {
        return activeFrom;
    }

    /**
     * Sets the value of the activeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveFrom(XMLGregorianCalendar value) {
        this.activeFrom = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link CardTypeDto }
     *     
     */
    public CardTypeDto getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardTypeDto }
     *     
     */
    public void setCardType(CardTypeDto value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CardStatusDto }
     *     
     */
    public CardStatusDto getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardStatusDto }
     *     
     */
    public void setStatus(CardStatusDto value) {
        this.status = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link AccountDto }
     *     
     */
    public AccountDto getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountDto }
     *     
     */
    public void setAccount(AccountDto value) {
        this.account = value;
    }

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidTo(XMLGregorianCalendar value) {
        this.validTo = value;
    }

}
