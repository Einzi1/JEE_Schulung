
package de.kiltz.seminar.ejb3.ws.komplexer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.kiltz.seminar.ejb3.ws.komplexer package. 
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

    private final static QName _SetUndGetDomainObject_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "setUndGetDomainObject");
    private final static QName _GetDomainObject_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "getDomainObject");
    private final static QName _SetDomainObject_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "setDomainObject");
    private final static QName _GetDomainObjectResponse_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "getDomainObjectResponse");
    private final static QName _SetDomainObjectResponse_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "setDomainObjectResponse");
    private final static QName _SetUndGetDomainObjectResponse_QNAME = new QName("http://komplexer.ws.ejb3.seminar.kiltz.de/", "setUndGetDomainObjectResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.kiltz.seminar.ejb3.ws.komplexer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DomainObject }
     * 
     */
    public DomainObject createDomainObject() {
        return new DomainObject();
    }

    /**
     * Create an instance of {@link GetDomainObject }
     * 
     */
    public GetDomainObject createGetDomainObject() {
        return new GetDomainObject();
    }

    /**
     * Create an instance of {@link SetUndGetDomainObject }
     * 
     */
    public SetUndGetDomainObject createSetUndGetDomainObject() {
        return new SetUndGetDomainObject();
    }

    /**
     * Create an instance of {@link SetDomainObject }
     * 
     */
    public SetDomainObject createSetDomainObject() {
        return new SetDomainObject();
    }

    /**
     * Create an instance of {@link GetDomainObjectResponse }
     * 
     */
    public GetDomainObjectResponse createGetDomainObjectResponse() {
        return new GetDomainObjectResponse();
    }

    /**
     * Create an instance of {@link SetUndGetDomainObjectResponse }
     * 
     */
    public SetUndGetDomainObjectResponse createSetUndGetDomainObjectResponse() {
        return new SetUndGetDomainObjectResponse();
    }

    /**
     * Create an instance of {@link SetDomainObjectResponse }
     * 
     */
    public SetDomainObjectResponse createSetDomainObjectResponse() {
        return new SetDomainObjectResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUndGetDomainObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "setUndGetDomainObject")
    public JAXBElement<SetUndGetDomainObject> createSetUndGetDomainObject(SetUndGetDomainObject value) {
        return new JAXBElement<SetUndGetDomainObject>(_SetUndGetDomainObject_QNAME, SetUndGetDomainObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDomainObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "getDomainObject")
    public JAXBElement<GetDomainObject> createGetDomainObject(GetDomainObject value) {
        return new JAXBElement<GetDomainObject>(_GetDomainObject_QNAME, GetDomainObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDomainObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "setDomainObject")
    public JAXBElement<SetDomainObject> createSetDomainObject(SetDomainObject value) {
        return new JAXBElement<SetDomainObject>(_SetDomainObject_QNAME, SetDomainObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDomainObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "getDomainObjectResponse")
    public JAXBElement<GetDomainObjectResponse> createGetDomainObjectResponse(GetDomainObjectResponse value) {
        return new JAXBElement<GetDomainObjectResponse>(_GetDomainObjectResponse_QNAME, GetDomainObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetDomainObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "setDomainObjectResponse")
    public JAXBElement<SetDomainObjectResponse> createSetDomainObjectResponse(SetDomainObjectResponse value) {
        return new JAXBElement<SetDomainObjectResponse>(_SetDomainObjectResponse_QNAME, SetDomainObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUndGetDomainObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://komplexer.ws.ejb3.seminar.kiltz.de/", name = "setUndGetDomainObjectResponse")
    public JAXBElement<SetUndGetDomainObjectResponse> createSetUndGetDomainObjectResponse(SetUndGetDomainObjectResponse value) {
        return new JAXBElement<SetUndGetDomainObjectResponse>(_SetUndGetDomainObjectResponse_QNAME, SetUndGetDomainObjectResponse.class, null, value);
    }

}
