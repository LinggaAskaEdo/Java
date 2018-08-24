
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Block card request specifies blocks.
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
 *         &lt;element name="block" type="{http://homecredit.net/homerselect/ws/card/common/v2}NewCardBlockDto"/>
 *         &lt;element name="suppressFee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "block",
    "suppressFee"
})
@XmlRootElement(name = "BlockCardRequest")
public class BlockCardRequest {

    protected long pcid;
    @XmlElement(required = true)
    protected NewCardBlockDto block;
    protected boolean suppressFee;

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
     * Gets the value of the block property.
     * 
     * @return
     *     possible object is
     *     {@link NewCardBlockDto }
     *     
     */
    public NewCardBlockDto getBlock() {
        return block;
    }

    /**
     * Sets the value of the block property.
     * 
     * @param value
     *     allowed object is
     *     {@link NewCardBlockDto }
     *     
     */
    public void setBlock(NewCardBlockDto value) {
        this.block = value;
    }

    /**
     * Gets the value of the suppressFee property.
     * 
     */
    public boolean isSuppressFee() {
        return suppressFee;
    }

    /**
     * Sets the value of the suppressFee property.
     * 
     */
    public void setSuppressFee(boolean value) {
        this.suppressFee = value;
    }

}
