
package de.kiltz.kv.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "KundenSIBService", targetNamespace = "http://ws.kv.kiltz.de/", wsdlLocation = "file:/D:/Java/workspace/kv-ws/gen/KundenSIB.wsdl")
public class KundenSIBService
    extends Service
{

    private final static URL KUNDENSIBSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(de.kiltz.kv.ws.KundenSIBService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = de.kiltz.kv.ws.KundenSIBService.class.getResource(".");
            url = new URL(baseUrl, "file:/D:/Java/workspace/kv-ws/gen/KundenSIB.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/D:/Java/workspace/kv-ws/gen/KundenSIB.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        KUNDENSIBSERVICE_WSDL_LOCATION = url;
    }

    public KundenSIBService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public KundenSIBService() {
        super(KUNDENSIBSERVICE_WSDL_LOCATION, new QName("http://ws.kv.kiltz.de/", "KundenSIBService"));
    }

    /**
     * 
     * @return
     *     returns KundenSEI
     */
    @WebEndpoint(name = "KundenSIBPort")
    public KundenSEI getKundenSIBPort() {
        return super.getPort(new QName("http://ws.kv.kiltz.de/", "KundenSIBPort"), KundenSEI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns KundenSEI
     */
    @WebEndpoint(name = "KundenSIBPort")
    public KundenSEI getKundenSIBPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.kv.kiltz.de/", "KundenSIBPort"), KundenSEI.class, features);
    }

}