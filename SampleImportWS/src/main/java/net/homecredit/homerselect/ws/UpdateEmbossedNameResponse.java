
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Update embossed name response.
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
 *         &lt;element name="newEmbossedName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName"/>
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}UpdateEmbossedNameResultCodeDto"/>
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
    "newEmbossedName",
    "resultCode"
})
@XmlRootElement(name = "UpdateEmbossedNameResponse")
public class UpdateEmbossedNameResponse {

    protected long pcid;
    @XmlElement(required = true)
    protected String newEmbossedName;
    @XmlElement(required = true)
    protected UpdateEmbossedNameResultCodeDto resultCode;

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
     * Gets the value of the newEmbossedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewEmbossedName() {
        return newEmbossedName;
    }

    /**
     * Sets the value of the newEmbossedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewEmbossedName(String value) {
        this.newEmbossedName = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateEmbossedNameResultCodeDto }
     *     
     */
    public UpdateEmbossedNameResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateEmbossedNameResultCodeDto }
     *     
     */
    public void setResultCode(UpdateEmbossedNameResultCodeDto value) {
        this.resultCode = value;
    }

}
