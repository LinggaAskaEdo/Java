
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Request of Cards.
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
 *         &lt;element name="holderCuid" type="{http://homecredit.net/homerselect/ws/card/common/v2}Cuid" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountNumber" minOccurs="0"/>
 *         &lt;element name="holderName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://homecredit.net/homerselect/ws/card/common/v2}Currency" minOccurs="0"/>
 *         &lt;element name="contractCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}ContractCode" minOccurs="0"/>
 *         &lt;element name="reservationCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}ReservationCode" minOccurs="0"/>
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
    "holderCuid",
    "accountNumber",
    "holderName",
    "currency",
    "contractCode",
    "reservationCode"
})
@XmlRootElement(name = "GetCardsRequest")
public class GetCardsRequest {

    protected Long holderCuid;
    protected Long accountNumber;
    protected String holderName;
    protected String currency;
    protected String contractCode;
    protected Long reservationCode;

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
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAccountNumber(Long value) {
        this.accountNumber = value;
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
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the contractCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * Sets the value of the contractCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractCode(String value) {
        this.contractCode = value;
    }

    /**
     * Gets the value of the reservationCode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReservationCode() {
        return reservationCode;
    }

    /**
     * Sets the value of the reservationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReservationCode(Long value) {
        this.reservationCode = value;
    }

}
