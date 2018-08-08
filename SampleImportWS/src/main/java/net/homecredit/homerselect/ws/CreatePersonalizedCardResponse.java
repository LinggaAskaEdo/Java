
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Create card response.
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
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}CreatePersonalizedCardResultCodeDto"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="pcid" type="{http://homecredit.net/homerselect/ws/card/common/v2}PaymentCardId"/>
 *           &lt;element name="truncatedPan" type="{http://homecredit.net/homerselect/ws/card/common/v2}TruncatedPan"/>
 *         &lt;/sequence>
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
    "resultCode",
    "pcid",
    "truncatedPan"
})
@XmlRootElement(name = "CreatePersonalizedCardResponse")
public class CreatePersonalizedCardResponse {

    @XmlElement(required = true)
    protected CreatePersonalizedCardResultCodeDto resultCode;
    protected Long pcid;
    protected String truncatedPan;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePersonalizedCardResultCodeDto }
     *     
     */
    public CreatePersonalizedCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePersonalizedCardResultCodeDto }
     *     
     */
    public void setResultCode(CreatePersonalizedCardResultCodeDto value) {
        this.resultCode = value;
    }

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

}
