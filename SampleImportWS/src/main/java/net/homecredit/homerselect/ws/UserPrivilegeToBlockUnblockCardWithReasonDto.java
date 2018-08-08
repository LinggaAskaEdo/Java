
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specifies whether operator has privilege to block/unblock card with given block type
 * 
 * <p>Java class for UserPrivilegeToBlockUnblockCardWithReasonDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserPrivilegeToBlockUnblockCardWithReasonDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cardBlockTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserPrivilegeToBlockUnblockCardWithReasonDto", propOrder = {
    "allowed",
    "cardBlockTypeId"
})
public class UserPrivilegeToBlockUnblockCardWithReasonDto {

    protected boolean allowed;
    @XmlElement(required = true)
    protected String cardBlockTypeId;

    /**
     * Gets the value of the allowed property.
     * 
     */
    public boolean isAllowed() {
        return allowed;
    }

    /**
     * Sets the value of the allowed property.
     * 
     */
    public void setAllowed(boolean value) {
        this.allowed = value;
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

}
