
package de.kundenverwaltung.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "KundenSEI", targetNamespace = "http://webservice.kundenverwaltung.de/")
@XmlSeeAlso({ ObjectFactory.class })
public interface KundenSEI {

	/**
	 * 
	 * @param kunde
	 */
	@WebMethod
	@RequestWrapper(localName = "save", targetNamespace = "http://webservice.kundenverwaltung.de/", className = "de.kundenverwaltung.webservice.Save")
	@ResponseWrapper(localName = "saveResponse", targetNamespace = "http://webservice.kundenverwaltung.de/", className = "de.kundenverwaltung.webservice.SaveResponse")
	public void save(@WebParam(name = "kunde", targetNamespace = "") KundenXSD kunde);

}
