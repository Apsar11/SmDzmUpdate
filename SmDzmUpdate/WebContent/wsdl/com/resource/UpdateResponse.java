
package com.resource;

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
 *         &lt;element name="updateReturn" type="{http://resource.com}Message"/>
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
    "updateReturn"
})
@XmlRootElement(name = "updateResponse")
public class UpdateResponse {

    @XmlElement(required = true)
    protected Message updateReturn;

    /**
     * Gets the value of the updateReturn property.
     * 
     * @return
     *     possible object is
     *     {@link Message }
     *     
     */
    public Message getUpdateReturn() {
        return updateReturn;
    }

    /**
     * Sets the value of the updateReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Message }
     *     
     */
    public void setUpdateReturn(Message value) {
        this.updateReturn = value;
    }

}
