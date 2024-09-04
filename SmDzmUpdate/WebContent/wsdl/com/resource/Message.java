
package com.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Message">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dzm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uniqueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "dzm",
    "result",
    "sm",
    "uniqueId"
})
public class Message {

    @XmlElement(required = true, nillable = true)
    protected String dzm;
    @XmlElement(required = true, nillable = true)
    protected String result;
    @XmlElement(required = true, nillable = true)
    protected String sm;
    @XmlElement(required = true, nillable = true)
    protected String uniqueId;

    /**
     * Gets the value of the dzm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDzm() {
        return dzm;
    }

    /**
     * Sets the value of the dzm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDzm(String value) {
        this.dzm = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Gets the value of the sm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSm() {
        return sm;
    }

    /**
     * Sets the value of the sm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSm(String value) {
        this.sm = value;
    }

    /**
     * Gets the value of the uniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets the value of the uniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueId(String value) {
        this.uniqueId = value;
    }

}
