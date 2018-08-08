
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AccountDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}ContractCode"/>
 *         &lt;element name="number" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountNumber"/>
 *         &lt;element name="iban" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountIban" minOccurs="0"/>
 *         &lt;element name="type" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountTypeDto"/>
 *         &lt;element name="currency" type="{http://homecredit.net/homerselect/ws/common/v2}CurrencyCodeType"/>
 *         &lt;element name="contractSignatureDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ownerCuid" type="{http://homecredit.net/homerselect/ws/card/common/v2}Cuid"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "contractCode",
    "number",
    "iban",
    "type",
    "currency",
    "contractSignatureDate",
    "ownerCuid"
})
public class AccountDto {

    @XmlElement(required = true)
    protected String contractCode;
    protected long number;
    protected String iban;
    @XmlElement(required = true)
    protected AccountTypeDto type;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar contractSignatureDate;
    protected long ownerCuid;

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
     * Gets the value of the number property.
     * 
     */
    public long getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     */
    public void setNumber(long value) {
        this.number = value;
    }

    /**
     * Gets the value of the iban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets the value of the iban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AccountTypeDto }
     *     
     */
    public AccountTypeDto getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountTypeDto }
     *     
     */
    public void setType(AccountTypeDto value) {
        this.type = value;
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
     * Gets the value of the contractSignatureDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractSignatureDate() {
        return contractSignatureDate;
    }

    /**
     * Sets the value of the contractSignatureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractSignatureDate(XMLGregorianCalendar value) {
        this.contractSignatureDate = value;
    }

    /**
     * Gets the value of the ownerCuid property.
     * 
     */
    public long getOwnerCuid() {
        return ownerCuid;
    }

    /**
     * Sets the value of the ownerCuid property.
     * 
     */
    public void setOwnerCuid(long value) {
        this.ownerCuid = value;
    }

}
