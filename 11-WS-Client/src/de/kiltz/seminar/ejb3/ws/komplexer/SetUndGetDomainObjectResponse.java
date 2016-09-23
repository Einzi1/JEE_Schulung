
package de.kiltz.seminar.ejb3.ws.komplexer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setUndGetDomainObjectResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setUndGetDomainObjectResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://komplexer.ws.ejb3.seminar.kiltz.de/}domainObject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setUndGetDomainObjectResponse", propOrder = {
    "_return"
})
public class SetUndGetDomainObjectResponse {

    @XmlElement(name = "return")
    protected DomainObject _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link DomainObject }
     *     
     */
    public DomainObject getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainObject }
     *     
     */
    public void setReturn(DomainObject value) {
        this._return = value;
    }

}
