
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Transaction identification in the source system where the card transaction was originated.
 * 
 * <p>Java class for TransactionSourceIdDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionSourceIdDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceSystem" type="{http://homecredit.net/homerselect/ws/common/v2}SourceSystemEnumDto"/>
 *         &lt;element name="sourceTxId" type="{http://homecredit.net/homerselect/ws/common/v2}TransactionSourceCodeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionSourceIdDto", namespace = "http://homecredit.net/homerselect/ws/common/v2", propOrder = {
    "sourceSystem",
    "sourceTxId"
})
public class TransactionSourceIdDto {

    @XmlElement(required = true)
    protected SourceSystemEnumDto sourceSystem;
    @XmlElement(required = true)
    protected String sourceTxId;

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link SourceSystemEnumDto }
     *     
     */
    public SourceSystemEnumDto getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceSystemEnumDto }
     *     
     */
    public void setSourceSystem(SourceSystemEnumDto value) {
        this.sourceSystem = value;
    }

    /**
     * Gets the value of the sourceTxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceTxId() {
        return sourceTxId;
    }

    /**
     * Sets the value of the sourceTxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceTxId(String value) {
        this.sourceTxId = value;
    }

}
