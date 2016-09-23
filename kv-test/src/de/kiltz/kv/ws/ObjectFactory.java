
package de.kiltz.kv.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.kiltz.kv.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HoleKunde_QNAME = new QName("http://ws.kv.kiltz.de/", "holeKunde");
    private final static QName _HoleKundeResponse_QNAME = new QName("http://ws.kv.kiltz.de/", "holeKundeResponse");
    private final static QName _NeuerKunde_QNAME = new QName("http://ws.kv.kiltz.de/", "neuerKunde");
    private final static QName _NeuerKundeResponse_QNAME = new QName("http://ws.kv.kiltz.de/", "neuerKundeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.kiltz.kv.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NeuerKunde }
     * 
     */
    public NeuerKunde createNeuerKunde() {
        return new NeuerKunde();
    }

    /**
     * Create an instance of {@link KundeXML }
     * 
     */
    public KundeXML createKundeXML() {
        return new KundeXML();
    }

    /**
     * Create an instance of {@link NeuerKundeResponse }
     * 
     */
    public NeuerKundeResponse createNeuerKundeResponse() {
        return new NeuerKundeResponse();
    }

    /**
     * Create an instance of {@link HoleKunde }
     * 
     */
    public HoleKunde createHoleKunde() {
        return new HoleKunde();
    }

    /**
     * Create an instance of {@link HoleKundeResponse }
     * 
     */
    public HoleKundeResponse createHoleKundeResponse() {
        return new HoleKundeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HoleKunde }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.kv.kiltz.de/", name = "holeKunde")
    public JAXBElement<HoleKunde> createHoleKunde(HoleKunde value) {
        return new JAXBElement<HoleKunde>(_HoleKunde_QNAME, HoleKunde.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HoleKundeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.kv.kiltz.de/", name = "holeKundeResponse")
    public JAXBElement<HoleKundeResponse> createHoleKundeResponse(HoleKundeResponse value) {
        return new JAXBElement<HoleKundeResponse>(_HoleKundeResponse_QNAME, HoleKundeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NeuerKunde }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.kv.kiltz.de/", name = "neuerKunde")
    public JAXBElement<NeuerKunde> createNeuerKunde(NeuerKunde value) {
        return new JAXBElement<NeuerKunde>(_NeuerKunde_QNAME, NeuerKunde.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NeuerKundeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.kv.kiltz.de/", name = "neuerKundeResponse")
    public JAXBElement<NeuerKundeResponse> createNeuerKundeResponse(NeuerKundeResponse value) {
        return new JAXBElement<NeuerKundeResponse>(_NeuerKundeResponse_QNAME, NeuerKundeResponse.class, null, value);
    }

}
