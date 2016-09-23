package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Die Service Implementation Bean (SIB)
 * 
 * @author fk
 *
 */
@WebService(serviceName = "KommunikationService", name = "Kommunikation")
public class KommunikationImpl implements Kommunikation {

	@Override
	@WebMethod(operationName = "ping")
	public String ping(@WebParam(name = "txt") String txt) {
		// Zeig das wir hier waren
		return "Zurück: " + txt.toUpperCase();
	}

}
