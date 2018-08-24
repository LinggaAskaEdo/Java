
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Request to find all privileges regarding card (un)blocking for given user, if pcid is null.
 *                     If pcid is not null privileges for unused blocking types are requested.
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
 *         &lt;element name="pcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId" minOccurs="0"/>
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
    "pcid"
})
@XmlRootElement(name = "GetAllowedCardOperationsForUserRequest")
public class GetAllowedCardOperationsForUserRequest {

    protected Long pcid;

    /**
     * Gets the value of the pcid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPcid() {
        return pcid;
    }

    /**
     * Sets the value of the pcid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPcid(Long value) {
        this.pcid = value;
    }

}
