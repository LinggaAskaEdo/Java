
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Response to GetCardsRequest.
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
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}GetCardsResponseResultCodeDto"/>
 *         &lt;element name="cards" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardDto" maxOccurs="unbounded" minOccurs="0"/>
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
    "cards"
})
@XmlRootElement(name = "GetCardsResponse")
public class GetCardsResponse {

    @XmlElement(required = true)
    protected GetCardsResponseResultCodeDto resultCode;
    protected List<CardDto> cards;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link GetCardsResponseResultCodeDto }
     *     
     */
    public GetCardsResponseResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCardsResponseResultCodeDto }
     *     
     */
    public void setResultCode(GetCardsResponseResultCodeDto value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the cards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCards().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardDto }
     * 
     * 
     */
    public List<CardDto> getCards() {
        if (cards == null) {
            cards = new ArrayList<CardDto>();
        }
        return this.cards;
    }

}
