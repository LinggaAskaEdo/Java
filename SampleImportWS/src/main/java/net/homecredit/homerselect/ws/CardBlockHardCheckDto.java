
package net.homecredit.homerselect.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardBlockHardCheckDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardBlockHardCheckDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://homecredit.net/homerselect/ws/card/management/v2}HardCheckDto">
 *       &lt;sequence>
 *         &lt;element name="blockTypes" type="{http://homecredit.net/homerselect/ws/card/common/v2}NewCardBlockDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardBlockHardCheckDto", propOrder = {
    "blockTypes"
})
public class CardBlockHardCheckDto
    extends HardCheckDto
{

    protected List<NewCardBlockDto> blockTypes;

    /**
     * Gets the value of the blockTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewCardBlockDto }
     * 
     * 
     */
    public List<NewCardBlockDto> getBlockTypes() {
        if (blockTypes == null) {
            blockTypes = new ArrayList<NewCardBlockDto>();
        }
        return this.blockTypes;
    }

}
