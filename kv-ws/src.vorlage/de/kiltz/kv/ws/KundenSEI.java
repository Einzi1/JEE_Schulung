package de.kiltz.kv.ws;

import javax.jws.WebService;

/**
 * SEI Service Endpoint Interface
 * 
 * @author user1
 *
 */
@WebService
public interface KundenSEI {
	
	KundeXML neuerKunde(KundeXML k);
	
	KundeXML holeKunde(Long id);

	
}
