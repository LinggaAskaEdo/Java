
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
 *         &lt;element name="card" type="{http://homecredit.net/homerselect/ws/card/common/v2}CardDetailDto"/>
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
    "card"
})
@XmlRootElement(name = "GetCardResponse")
public class GetCardResponse {

    @XmlElement(required = true)
    protected CardDetailDto card;

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
