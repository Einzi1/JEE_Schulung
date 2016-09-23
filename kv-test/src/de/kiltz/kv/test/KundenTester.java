package de.kiltz.kv.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;
import de.kiltz.seminar.ejb3.tools.EJBTools;

public class KundenTester {

	private static KundenVerwaltung service;
	
	@BeforeClass
	public static void init() {
		try {
			InitialContext context = new InitialContext();
			service = (KundenVerwaltung) context.lookup(EJBTools.machJNDIName(KundenVerwaltung.class));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testeNeuerKunde() {
		Kunde falscherKunde = new Kunde();
		try {
			service.neuerKunde(falscherKunde);
			fail("Sollte Ex werfen!");
		} catch (PflichtfeldException e) {
			// Alles Gut!
		}
		Kunde guterKunde = new Kunde("Mieser Max");
		try {
			assertNull(guterKunde.getId());
			guterKunde = service.neuerKunde(guterKunde);
			assertNotNull(guterKunde.getId());
		} catch (PflichtfeldException e) {
			fail("Sollte KEINE Ex werfen!");
		}
		// und aufräumen!
		service.loescheKunde(guterKunde.getId());
	}
	
	@Test
	public void testeUpdateUndRead() {
		Kunde k = new Kunde("Mieser Max");
		try {
			assertNull(k.getId());
			k = service.neuerKunde(k);
			assertNotNull(k.getId());
		} catch (PflichtfeldException e) {
			fail("Sollte KEINE Ex werfen!");
		}
		Kunde geholt = service.holeKunde(k.getId());
		assertNotNull(geholt);
		assertEquals(k.getName(), geholt.getName());
		
		// Ändern
		geholt.setName("Bruder Olaf");
		try {
			geholt = service.aendereKunde(geholt);
		} catch (PflichtfeldException e) {
			fail("Sollte KEINE Ex werfen!");
		}
		assertNotNull(geholt);
		assertEquals("Bruder Olaf", geholt.getName());
		
		// und aufräumen!
//		service.loscheKunde(k.getId());
		
	}
}
