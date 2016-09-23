package de.kiltz.kv.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.domain.Position;
import de.kiltz.kv.domain.Rechnung;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.RechnungsVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;
import de.kiltz.seminar.ejb3.tools.EJBTools;

public class RechnungsTester {

	private static RechnungsVerwaltung reService;
	private static KundenVerwaltung kuService;
	
	@BeforeClass
	public static void init() {
		try {
			InitialContext context = new InitialContext();
			reService = (RechnungsVerwaltung) context.lookup(EJBTools.machJNDIName(RechnungsVerwaltung.class));
			kuService = (KundenVerwaltung) context.lookup(EJBTools.machJNDIName(KundenVerwaltung.class));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testeNeuerRechnung() {
		Rechnung guteRechnung = new Rechnung(1234);
		List<Kunde> erg = kuService.sucheKunde("");
		Kunde k = null;
		if (erg.isEmpty()) {
			try {
				k = kuService.neuerKunde(new Kunde("TestKunde"));
			} catch (PflichtfeldException e) {
				fail("Sollte KEINE Ex werfen!");
			}
		} else {
			k = erg.get(0);
		}
		// Wir haben einen Kunden
		assertNotNull(k);
		guteRechnung.setKunde(k);
		guteRechnung.addPositionen(new Position(123, 3));
		guteRechnung.addPositionen(new Position(124, 5));
		System.out.println(guteRechnung);
		try {
			assertNull(guteRechnung.getId());
			guteRechnung = reService.neueRechnung(guteRechnung);
			assertNotNull(guteRechnung.getId());
		} catch (PflichtfeldException e) {
			e.printStackTrace();
			fail("Sollte KEINE Ex werfen!");
		}
		// und aufräumen!
		//service.loscheRechnung(guterRechnung.getId());
	}
}
