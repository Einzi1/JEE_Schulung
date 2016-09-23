package de.kundenverwaltung;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KundenServiceImplTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	public KundenService service;

	@Before
	public void lookup() throws NamingException {
		InitialContext initialContext = new InitialContext();
		service = (KundenService) initialContext.lookup(EJBTools.machJNDIName(KundenService.class));

	}

	@Test
	public void neuerKunde() throws PflichtfeldException {
		service.neuerKunde(new Kunde("vorname", "nachname", "vorname@nachname.de"));
	}

	@Test
	public void neuerKunde2() throws PflichtfeldException {
		service.neuerKunde(new Kunde("vorname2", "nachname2", "vorname2@nachname2.de"));
	}

	@Test
	public void neuerKunde3() throws PflichtfeldException {
		service.neuerKunde(new Kunde("vorname3", "nachname3", "vorname3@nachname3.de"));
	}

	@Test
	public void aendereKunde() throws PflichtfeldException {
		expectedException.expect(PflichtfeldException.class);
		service.aendereKunde(new Kunde("vorname", "nachname", "vorname1@nachname1.de"));
	}

	@Test
	public void holeKunde() throws PflichtfeldException {
		service.holeKunde(1L);
	}

	@Test
	public void loescheKunde() {
		service.loscheKunde(1L);
	}

	@Test
	public void neuerKundeNull() throws PflichtfeldException {
		expectedException.expect(PflichtfeldException.class);
		service.neuerKunde(new Kunde());
	}

	@Test
	public void getAll() {
		List<Kunde> result = service.getAll();
		result.forEach((Kunde k) -> System.out.println(k.toString()));
	}

	@Test
	public void getSingle() {
		Kunde kunde = service.getSingle("vorname3@nachname3.de");
		System.out.println(kunde.toString());

	}

}
