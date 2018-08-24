
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Response containing list of privileges with associated true/false flag whether privilege is allowed
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
 *         &lt;element name="markCardLost" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="markCardStolen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="blockCardWithBlockReasons" type="{http://homecredit.net/homerselect/ws/card/management/v2}UserPrivilegeToBlockUnblockCardWithReasonDto" maxOccurs="unbounded"/>
 *         &lt;element name="unblockCardWithBlockReasons" type="{http://homecredit.net/homerselect/ws/card/management/v2}UserPrivilegeToBlockUnblockCardWithReasonDto" maxOccurs="unbounded"/>
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
    "markCardLost",
    "markCardStolen",
    "blockCardWithBlockReasons",
    "unblockCardWithBlockReasons"
})
@XmlRootElement(name = "GetAllowedCardOperationsForUserResponse")
public class GetAllowedCardOperationsForUserResponse {

    protected boolean markCardLost;
    protected boolean markCardStolen;
    @XmlElement(required = true)
    protected List<UserPrivilegeToBlockUnblockCardWithReasonDto> blockCardWithBlockReasons;
    @XmlElement(required = true)
    protected List<UserPrivilegeToBlockUnblockCardWithReasonDto> unblockCardWithBlockReasons;

    /**
     * Gets the value of the markCardLost property.
     * 
     */
    public boolean isMarkCardLost() {
        return markCardLost;
    }

    /**
     * Sets the value of the markCardLost property.
     * 
     */
    public void setMarkCardLost(boolean value) {
        this.markCardLost = value;
    }

    /**
     * Gets the value of the markCardStolen property.
     * 
     */
    public boolean isMarkCardStolen() {
        return markCardStolen;
    }

    /**
     * Sets the value of the markCardStolen property.
     * 
     */
    public void setMarkCardStolen(boolean value) {
        this.markCardStolen = value;
    }

    /**
     * Gets the value of the blockCardWithBlockReasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockCardWithBlockReasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockCardWithBlockReasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserPrivilegeToBlockUnblockCardWithReasonDto }
     * 
     * 
     */
    public List<UserPrivilegeToBlockUnblockCardWithReasonDto> getBlockCardWithBlockReasons() {
        if (blockCardWithBlockReasons == null) {
            blockCardWithBlockReasons = new ArrayList<UserPrivilegeToBlockUnblockCardWithReasonDto>();
        }
        return this.blockCardWithBlockReasons;
    }

    /**
     * Gets the value of the unblockCardWithBlockReasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unblockCardWithBlockReasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnblockCardWithBlockReasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserPrivilegeToBlockUnblockCardWithReasonDto }
     * 
     * 
     */
    public List<UserPrivilegeToBlockUnblockCardWithReasonDto> getUnblockCardWithBlockReasons() {
        if (unblockCardWithBlockReasons == null) {
            unblockCardWithBlockReasons = new ArrayList<UserPrivilegeToBlockUnblockCardWithReasonDto>();
        }
        return this.unblockCardWithBlockReasons;
    }

}
