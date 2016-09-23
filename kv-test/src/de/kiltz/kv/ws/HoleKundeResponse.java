
package de.kiltz.kv.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for holeKundeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="holeKundeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kunde" type="{http://ws.kv.kiltz.de/}kundeXML" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "holeKundeResponse", propOrder = {
    "kunde"
})
public class HoleKundeResponse {

    protected KundeXML kunde;

    /**
     * Gets the value of the kunde property.
     * 
     * @return
     *     possible object is
     *     {@link KundeXML }
     *     
     */
    public KundeXML getKunde() {
        return kunde;
    }

    /**
     * Sets the value of the kunde property.
     * 
     * @param value
     *     allowed object is
     *     {@link KundeXML }
     *     
     */
    public void setKunde(KundeXML value) {
        this.kunde = value;
    }

}
