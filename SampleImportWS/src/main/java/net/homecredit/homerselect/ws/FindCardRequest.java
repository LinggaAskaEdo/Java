
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Get all information for a card by given cuid, validity and last 4 pan numbers
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
 *         &lt;element name="holderCuid" type="{http://homecredit.net/homerselect/ws/card/common/v2}Cuid"/>
 *         &lt;element name="plasticValidTo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="panLast4Digits" type="{http://homecredit.net/homerselect/ws/card/common/v2}Digits4"/>
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
    "plasticValidTo",
    "panLast4Digits"
})
@XmlRootElement(name = "FindCardRequest")
public class FindCardRequest {

    protected long holderCuid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar plasticValidTo;
    @XmlElement(required = true)
    protected String panLast4Digits;

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
     * Gets the value of the plasticValidTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlasticValidTo() {
        return plasticValidTo;
    }

    /**
     * Sets the value of the plasticValidTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlasticValidTo(XMLGregorianCalendar value) {
        this.plasticValidTo = value;
    }

    /**
     * Gets the value of the panLast4Digits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPanLast4Digits() {
        return panLast4Digits;
    }

    /**
     * Sets the value of the panLast4Digits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPanLast4Digits(String value) {
        this.panLast4Digits = value;
    }

}
