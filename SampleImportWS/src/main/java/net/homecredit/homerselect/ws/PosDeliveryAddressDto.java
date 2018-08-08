
package net.homecredit.homerselect.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PosDeliveryAddressDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PosDeliveryAddressDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sellerPlaceCode" type="{http://homecredit.net/homerselect/ws/card/common/v2}SellerPlaceCode"/>
 *         &lt;element name="zip" type="{http://homecredit.net/homerselect/ws/card/common/v2}Zip" minOccurs="0"/>
 *         &lt;element name="region" type="{http://homecredit.net/homerselect/ws/card/common/v2}Region" minOccurs="0"/>
 *         &lt;element name="district" type="{http://homecredit.net/homerselect/ws/card/common/v2}District" minOccurs="0"/>
 *         &lt;element name="town" type="{http://homecredit.net/homerselect/ws/card/common/v2}Town" minOccurs="0"/>
 *         &lt;element name="street" type="{http://homecredit.net/homerselect/ws/card/common/v2}Street" minOccurs="0"/>
 *         &lt;element name="houseNumber" type="{http://homecredit.net/homerselect/ws/card/common/v2}HouseNumber" minOccurs="0"/>
 *         &lt;element name="blockNumber" type="{http://homecredit.net/homerselect/ws/card/common/v2}BlockNumber" minOccurs="0"/>
 *         &lt;element name="flatNumber" type="{http://homecredit.net/homerselect/ws/card/common/v2}FlatNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PosDeliveryAddressDto", namespace = "http://homecredit.net/homerselect/ws/card/common/v2", propOrder = {
    "sellerPlaceCode",
    "zip",
    "region",
    "district",
    "town",
    "street",
    "houseNumber",
    "blockNumber",
    "flatNumber"
})
public class PosDeliveryAddressDto {

    @XmlElement(required = true)
    protected String sellerPlaceCode;
    protected String zip;
    protected String region;
    protected String district;
    protected String town;
    protected String street;
    protected String houseNumber;
    protected String blockNumber;
    protected String flatNumber;

    /**
     * Gets the value of the sellerPlaceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellerPlaceCode() {
        return sellerPlaceCode;
    }

    /**
     * Sets the value of the sellerPlaceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellerPlaceCode(String value) {
        this.sellerPlaceCode = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNumber(String value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the blockNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockNumber() {
        return blockNumber;
    }

    /**
     * Sets the value of the blockNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockNumber(String value) {
        this.blockNumber = value;
    }

    /**
     * Gets the value of the flatNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlatNumber() {
        return flatNumber;
    }

    /**
     * Sets the value of the flatNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlatNumber(String value) {
        this.flatNumber = value;
    }

}
