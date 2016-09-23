
package de.kiltz.seminar.ejb3.ws.kunde;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für neuerKunde complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="neuerKunde">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kunde" type="{http://kunde.ws.ejb3.seminar.kiltz.de/}kundeXSD" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "neuerKunde", propOrder = {
    "kunde"
})
public class NeuerKunde {

    protected KundeXSD kunde;

    /**
     * Ruft den Wert der kunde-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KundeXSD }
     *     
     */
    public KundeXSD getKunde() {
        return kunde;
    }

    /**
     * Legt den Wert der kunde-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KundeXSD }
     *     
     */
    public void setKunde(KundeXSD value) {
        this.kunde = value;
    }

}
