package de.kiltz.kv.ws.test;

import static org.junit.Assert.*;

import javax.xml.ws.Holder;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kiltz.kv.ws.KundeXML;
import de.kiltz.kv.ws.KundenSEI;
import de.kiltz.kv.ws.KundenSIBService;

public class WebServiceTest {

	private static KundenSEI ws;
	
	@BeforeClass
	public static void init() {
		KundenSIBService service = new KundenSIBService();
		ws = service.getKundenSIBPort();
	}
	
	@Test
	public void testeWebServce() {
		KundeXML neuer = new KundeXML();
		neuer.setName("per WebService");
		Holder<KundeXML> h = new Holder<KundeXML>(neuer); 
		ws.neuerKunde(h);
	}
	
	@Test
	public void testeHolen() {
		KundeXML gespeichert = ws.holeKunde(9L);
		assertNotNull(gespeichert);
		assertNotNull(gespeichert.getId());
	}
}
