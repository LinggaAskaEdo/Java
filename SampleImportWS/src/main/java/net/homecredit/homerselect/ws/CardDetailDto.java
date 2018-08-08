
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Card detail
 * 
 * <p>Java class for CardDetailDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardDetailDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://homecredit.net/homerselect/ws/card/common/v2}CardDto">
 *       &lt;sequence>
 *         &lt;element name="reservationCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="allowedOperations" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardOperationDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="limits" type="{http://homecredit.net/homerselect/ws/card/common/v2}LimitDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="plastics" type="{http://homecredit.net/homerselect/ws/card/common/v2}PlasticDto" maxOccurs="unbounded"/>
 *         &lt;element name="lastPinChangeRequestDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="activeBlocks" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardBlockDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardDetailDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "reservationCode",
    "allowedOperations",
    "limits",
    "plastics",
    "lastPinChangeRequestDate",
    "activeBlocks"
})
public class CardDetailDto
    extends CardDto
{

    protected Long reservationCode;
    protected List<CardOperationDto> allowedOperations;
    protected List<LimitDto> limits;
    @XmlElement(required = true)
    protected List<PlasticDto> plastics;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastPinChangeRequestDate;
    protected List<CardBlockDto> activeBlocks;

    /**
     * Gets the value of the reservationCode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReservationCode() {
        return reservationCode;
    }

    /**
     * Sets the value of the reservationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReservationCode(Long value) {
        this.reservationCode = value;
    }

    /**
     * Gets the value of the allowedOperations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allowedOperations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllowedOperations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardOperationDto }
     * 
     * 
     */
    public List<CardOperationDto> getAllowedOperations() {
        if (allowedOperations == null) {
            allowedOperations = new ArrayList<CardOperationDto>();
        }
        return this.allowedOperations;
    }

    /**
     * Gets the value of the limits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the limits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLimits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LimitDto }
     * 
     * 
     */
    public List<LimitDto> getLimits() {
        if (limits == null) {
            limits = new ArrayList<LimitDto>();
        }
        return this.limits;
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

    /**
     * Gets the value of the lastPinChangeRequestDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPinChangeRequestDate() {
        return lastPinChangeRequestDate;
    }

    /**
     * Sets the value of the lastPinChangeRequestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPinChangeRequestDate(XMLGregorianCalendar value) {
        this.lastPinChangeRequestDate = value;
    }

    /**
     * Gets the value of the activeBlocks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activeBlocks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActiveBlocks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardBlockDto }
     * 
     * 
     */
    public List<CardBlockDto> getActiveBlocks() {
        if (activeBlocks == null) {
            activeBlocks = new ArrayList<CardBlockDto>();
        }
        return this.activeBlocks;
    }

}
