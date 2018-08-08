
package net.homecredit.homerselect.ws;

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
 *         &lt;element name="resultCode" type="{http://homecredit.net/homerselect/ws/card/management/v2}FindCardResultCodeDto"/>
 *         &lt;element name="card" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardDetailDto" minOccurs="0"/>
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
    "card"
})
@XmlRootElement(name = "FindCardResponse")
public class FindCardResponse {

    @XmlElement(required = true)
    protected FindCardResultCodeDto resultCode;
    protected CardDetailDto card;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link FindCardResultCodeDto }
     *     
     */
    public FindCardResultCodeDto getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindCardResultCodeDto }
     *     
     */
    public void setResultCode(FindCardResultCodeDto value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the card property.
     * 
     * @return
     *     possible object is
     *     {@link CardDetailDto }
     *     
     */
    public CardDetailDto getCard() {
        return card;
    }

    /**
     * Sets the value of the card property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardDetailDto }
     *     
     */
    public void setCard(CardDetailDto value) {
        this.card = value;
    }

}
