
package net.homecredit.homerselect.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Confirms instant card and associate it with account.
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
 *         &lt;element name="holderCuid" type="{http://homecredit.net/homerselect/ws/card/common/v2}Cuid"/>
 *         &lt;element name="account" type="{http://homecredit.net/homerselect/ws/card/common/v2}AccountDto"/>
 *         &lt;element name="serviceCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}ServiceCode"/>
 *         &lt;element name="approvedCreditLimit" type="{http://homecredit.net/homerselect/ws/card/common/v2}ApprovedCreditLimit"/>
 *         &lt;element name="posDeliveryAddress" type="{http://homecredit.net/homerselect/ws/card/common/v2}PosDeliveryAddressDto"/>
 *         &lt;element name="renewalCardTypeId" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardType"/>
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
    "holderCuid",
    "account",
    "serviceCode",
    "approvedCreditLimit",
    "posDeliveryAddress",
    "renewalCardTypeId"
})
@XmlRootElement(name = "ConfirmInstantCardRequest")
public class ConfirmInstantCardRequest {

    protected long pcid;
    protected long holderCuid;
    @XmlElement(required = true)
    protected AccountDto account;
    @XmlElement(required = true)
    protected String serviceCode;
    @XmlElement(required = true)
    protected BigDecimal approvedCreditLimit;
    @XmlElement(required = true)
    protected PosDeliveryAddressDto posDeliveryAddress;
    @XmlElement(required = true)
    protected String renewalCardTypeId;

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
     * Gets the value of the holderCuid property.
     * 
     */
    public long getHolderCuid() {
        return holderCuid;
    }

    /**
     * Sets the value of the holderCuid property.
     * 
     */
    public void setHolderCuid(long value) {
        this.holderCuid = value;
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
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the approvedCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getApprovedCreditLimit() {
        return approvedCreditLimit;
    }

    /**
     * Sets the value of the approvedCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setApprovedCreditLimit(BigDecimal value) {
        this.approvedCreditLimit = value;
    }

    /**
     * Gets the value of the posDeliveryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link PosDeliveryAddressDto }
     *     
     */
    public PosDeliveryAddressDto getPosDeliveryAddress() {
        return posDeliveryAddress;
    }

    /**
     * Sets the value of the posDeliveryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link PosDeliveryAddressDto }
     *     
     */
    public void setPosDeliveryAddress(PosDeliveryAddressDto value) {
        this.posDeliveryAddress = value;
    }

    /**
     * Gets the value of the renewalCardTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalCardTypeId() {
        return renewalCardTypeId;
    }

    /**
     * Sets the value of the renewalCardTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalCardTypeId(String value) {
        this.renewalCardTypeId = value;
    }

}
