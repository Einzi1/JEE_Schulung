
package de.kiltz.seminar.ejb3.ws.komplexer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für setDomainObject complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="setDomainObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="obj" type="{http://komplexer.ws.ejb3.seminar.kiltz.de/}domainObject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setDomainObject", propOrder = {
    "obj"
})
public class SetDomainObject {

    protected DomainObject obj;

    /**
     * Ruft den Wert der obj-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DomainObject }
     *     
     */
    public DomainObject getObj() {
        return obj;
    }

    /**
     * Legt den Wert der obj-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainObject }
     *     
     */
    public void setObj(DomainObject value) {
        this.obj = value;
    }

}
