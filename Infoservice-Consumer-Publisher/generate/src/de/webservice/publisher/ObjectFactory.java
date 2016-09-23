
package de.webservice.publisher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.webservice.publisher package. 
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

    private final static QName _GetFreeMemoryResponse_QNAME = new QName("http://publisher.webservice.de/", "getFreeMemoryResponse");
    private final static QName _GetTime_QNAME = new QName("http://publisher.webservice.de/", "getTime");
    private final static QName _GetUser_QNAME = new QName("http://publisher.webservice.de/", "getUser");
    private final static QName _GetTimeResponse_QNAME = new QName("http://publisher.webservice.de/", "getTimeResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://publisher.webservice.de/", "getUserResponse");
    private final static QName _GetFreeMemory_QNAME = new QName("http://publisher.webservice.de/", "getFreeMemory");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.webservice.publisher
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTimeResponse }
     * 
     */
    public GetTimeResponse createGetTimeResponse() {
        return new GetTimeResponse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetFreeMemory }
     * 
     */
    public GetFreeMemory createGetFreeMemory() {
        return new GetFreeMemory();
    }

    /**
     * Create an instance of {@link GetFreeMemoryResponse }
     * 
     */
    public GetFreeMemoryResponse createGetFreeMemoryResponse() {
        return new GetFreeMemoryResponse();
    }

    /**
     * Create an instance of {@link GetTime }
     * 
     */
    public GetTime createGetTime() {
        return new GetTime();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeMemoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getFreeMemoryResponse")
    public JAXBElement<GetFreeMemoryResponse> createGetFreeMemoryResponse(GetFreeMemoryResponse value) {
        return new JAXBElement<GetFreeMemoryResponse>(_GetFreeMemoryResponse_QNAME, GetFreeMemoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getTime")
    public JAXBElement<GetTime> createGetTime(GetTime value) {
        return new JAXBElement<GetTime>(_GetTime_QNAME, GetTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getTimeResponse")
    public JAXBElement<GetTimeResponse> createGetTimeResponse(GetTimeResponse value) {
        return new JAXBElement<GetTimeResponse>(_GetTimeResponse_QNAME, GetTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeMemory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publisher.webservice.de/", name = "getFreeMemory")
    public JAXBElement<GetFreeMemory> createGetFreeMemory(GetFreeMemory value) {
        return new JAXBElement<GetFreeMemory>(_GetFreeMemory_QNAME, GetFreeMemory.class, null, value);
    }

}
