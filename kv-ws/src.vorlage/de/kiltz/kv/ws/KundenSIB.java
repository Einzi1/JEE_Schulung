package de.kiltz.kv.ws;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * SIB Servie Implementation Bean
 *  
 * 
 */
@Stateless
@Remote(KundenSEI.class)
@WebService(endpointInterface="de.kiltz.kv.ws.KundenSEI")
public class KundenSIB implements KundenSEI {

	@Override
	public KundeXML neuerKunde(KundeXML k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KundeXML holeKunde(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
