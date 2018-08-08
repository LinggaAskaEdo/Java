
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Create embossed name response.
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
 *         &lt;element name="embossedName" type="{http://homecredit.net/homerselect/ws/card/common/v2}HolderName"/>
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}CreateEmbossedNameResultCodeDto"/>
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
    "embossedName",
    "resultCode"
})
@XmlRootElement(name = "CreateEmbossedNameResponse")
public class CreateEmbossedNameResponse {

    @XmlElement(required = true)
    protected String embossedName;
    @XmlElement(required = true)
    protected CreateEmbossedNameResultCodeDto resultCode;

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
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link CreateEmbossedNameResultCodeDto }
     *     
     */
    public CreateEmbossedNameResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateEmbossedNameResultCodeDto }
     *     
     */
    public void setResultCode(CreateEmbossedNameResultCodeDto value) {
        this.resultCode = value;
    }

}
