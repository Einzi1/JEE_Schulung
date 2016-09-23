package de.kiltz.kv.ws;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;

@WebService(endpointInterface = "de.kiltz.kv.ws.KundenSEI")
@Stateless
public class KundenSIB implements KundenSEI {

	@EJB
	private KundenVerwaltung service;

	@Override
	public KundeXML holeKunde(Long id) {

		// Delegieren an den Serivce
		Kunde k = service.holeKunde(id);

		// Ausgleichen der Datentypen
		KundeXML ret = new KundeXML();
		ret.setName(k.getName());
		ret.setId(k.getId());
		return ret;
	}

	@Override
	public KundeXML neuerKunde(Holder<KundeXML> kunde) {

		Kunde k = new Kunde(kunde.value.getName());
		try {
			k = service.neuerKunde(k);
		} catch (PflichtfeldException e) {
			throw new EJBException(e);
		}

		KundeXML ret = new KundeXML();
		ret.setName(k.getName());
		ret.setId(k.getId());
		return ret;
	}

}
