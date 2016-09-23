package de.kundenverwaltung.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.kundenverwaltung.Kunde;
import de.kundenverwaltung.KundenService;
import de.kundenverwaltung.PflichtfeldException;

@Stateless(name = "KundenSIB")
@WebService(serviceName = "KundenService", name = "KundenService", endpointInterface = "de.kundenverwaltung.webservice.KundenSIB")
public class KundenSIB implements KundenSEI {

	@EJB
	private KundenService service;

	@Override
	public void save(KundenXSD kundexsd) {

		Kunde kunde = new Kunde(kundexsd.getVorname(), kundexsd.getNachname(), kundexsd.getEmail());

		try {
			service.neuerKunde(kunde);
		} catch (PflichtfeldException e) {
			e.printStackTrace();
		}

	}

}
