
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CardBlockDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardBlockDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://homecredit.net/homerselect/ws/card/common/v2}NewCardBlockDto">
 *       &lt;sequence>
 *         &lt;element name="blockedFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="blockedTill" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardBlockDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "blockedFrom",
    "blockedTill",
    "createdBy"
})
public class CardBlockDto
    extends NewCardBlockDto
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar blockedFrom;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar blockedTill;
    @XmlElement(required = true)
    protected String createdBy;

    /**
     * Gets the value of the blockedFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBlockedFrom() {
        return blockedFrom;
    }

    /**
     * Sets the value of the blockedFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBlockedFrom(XMLGregorianCalendar value) {
        this.blockedFrom = value;
    }

    /**
     * Gets the value of the blockedTill property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBlockedTill() {
        return blockedTill;
    }

    /**
     * Sets the value of the blockedTill property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBlockedTill(XMLGregorianCalendar value) {
        this.blockedTill = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

}
