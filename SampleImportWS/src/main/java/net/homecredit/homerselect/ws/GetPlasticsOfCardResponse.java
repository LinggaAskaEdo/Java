
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}GetPlasticsOfCardResultCodeDto"/>
 *         &lt;element name="plastics" type="{http://homecredit.net/homerselect/ws/card/common/v2}PlasticDto" maxOccurs="unbounded" minOccurs="0"/>
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
    "plastics"
})
@XmlRootElement(name = "GetPlasticsOfCardResponse")
public class GetPlasticsOfCardResponse {

    @XmlElement(required = true)
    protected GetPlasticsOfCardResultCodeDto resultCode;
    protected List<PlasticDto> plastics;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link GetPlasticsOfCardResultCodeDto }
     *     
     */
    public GetPlasticsOfCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetPlasticsOfCardResultCodeDto }
     *     
     */
    public void setResultCode(GetPlasticsOfCardResultCodeDto value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the plastics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plastics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlastics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlasticDto }
     * 
     * 
     */
    public List<PlasticDto> getPlastics() {
        if (plastics == null) {
            plastics = new ArrayList<PlasticDto>();
        }
        return this.plastics;
    }

}
