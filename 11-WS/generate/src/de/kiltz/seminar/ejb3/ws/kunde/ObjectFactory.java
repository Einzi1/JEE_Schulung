
package de.kiltz.seminar.ejb3.ws.kunde;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.kiltz.seminar.ejb3.ws.kunde package. 
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

    private final static QName _Exception_QNAME = new QName("http://kunde.ws.ejb3.seminar.kiltz.de/", "Exception");
    private final static QName _NeuerKunde_QNAME = new QName("http://kunde.ws.ejb3.seminar.kiltz.de/", "neuerKunde");
    private final static QName _NeuerKundeResponse_QNAME = new QName("http://kunde.ws.ejb3.seminar.kiltz.de/", "neuerKundeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.kiltz.seminar.ejb3.ws.kunde
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
     * Create an instance of {@link NeuerKundeResponse }
     * 
     */
    public NeuerKundeResponse createNeuerKundeResponse() {
        return new NeuerKundeResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link KundeXSD }
     * 
     */
    public KundeXSD createKundeXSD() {
        return new KundeXSD();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kunde.ws.ejb3.seminar.kiltz.de/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NeuerKunde }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kunde.ws.ejb3.seminar.kiltz.de/", name = "neuerKunde")
    public JAXBElement<NeuerKunde> createNeuerKunde(NeuerKunde value) {
        return new JAXBElement<NeuerKunde>(_NeuerKunde_QNAME, NeuerKunde.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NeuerKundeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://kunde.ws.ejb3.seminar.kiltz.de/", name = "neuerKundeResponse")
    public JAXBElement<NeuerKundeResponse> createNeuerKundeResponse(NeuerKundeResponse value) {
        return new JAXBElement<NeuerKundeResponse>(_NeuerKundeResponse_QNAME, NeuerKundeResponse.class, null, value);
    }

}
