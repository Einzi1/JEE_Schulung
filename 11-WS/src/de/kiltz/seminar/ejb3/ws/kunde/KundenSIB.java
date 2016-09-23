package de.kiltz.seminar.ejb3.ws.kunde;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "KundenClientService", portName = "KundenWebService")
@Stateless
public class KundenSIB implements KundenWebService {

	@Override
	public void neuerKunde(@WebParam(name = "kunde") KundeXSD k) throws Exception_Exception {
		// KundeXSD => Kunde

		// service neuerKunde

	}

}
