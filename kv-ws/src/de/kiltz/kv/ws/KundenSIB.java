package de.kiltz.kv.ws;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;

@WebService(endpointInterface="de.kiltz.kv.ws.KundenSEI")
@Stateless
@Remote(KundenSEI.class)
public class KundenSIB implements KundenSEI{

	@EJB
	private KundenVerwaltung service;
	
	@Override
	@WebMethod
	@WebResult(name = "kunde", targetNamespace = "")
	@RequestWrapper(localName = "holeKunde", targetNamespace = "http://ws.kv.kiltz.de/", className = "de.kiltz.kv.ws.HoleKunde")
	@ResponseWrapper(localName = "holeKundeResponse", targetNamespace = "http://ws.kv.kiltz.de/", className = "de.kiltz.kv.ws.HoleKundeResponse")
	public KundeXML holeKunde(
			@WebParam(name = "id", targetNamespace = "") Long id) {
		
		// Delegieren an den Serivce
		Kunde k = service.holeKunde(id);
		
		// Ausgleichen der Datentypen
		KundeXML ret = new KundeXML();
		ret.setName(k.getName());
		ret.setId(k.getId());
		return ret;
	}

	@Override
	@WebMethod
	@WebResult(name = "kunde", targetNamespace = "")
	@RequestWrapper(localName = "neuerKunde", targetNamespace = "http://ws.kv.kiltz.de/", className = "de.kiltz.kv.ws.NeuerKunde")
	@ResponseWrapper(localName = "neuerKundeResponse", targetNamespace = "http://ws.kv.kiltz.de/", className = "de.kiltz.kv.ws.NeuerKundeResponse")
	public KundeXML neuerKunde(
			@WebParam(name = "kunde", targetNamespace = "", mode = Mode.INOUT) Holder<KundeXML> kunde) {
		
		Kunde k = new Kunde(kunde.value.getName());
		try {
			k = service.neuerKunde(k);
		} catch (PflichtfeldException e) {
			throw new EJBException(e);
		}
		
		KundeXML ret = new KundeXML();
		ret.setName(k.getName());
		ret.setId(k.getId());
		return ret ;
	}

}
