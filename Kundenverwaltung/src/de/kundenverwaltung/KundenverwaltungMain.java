package de.kundenverwaltung;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class KundenverwaltungMain {

	public static void main(String[] args) throws NamingException, PflichtfeldException {

		Kunde kunde1 = new Kunde("vorname", "nachname", "vorname@nachname.de");
		Kunde kunde2 = new Kunde("vorname2", "nachname2", "vorname2@nachname2.de");
		Kunde kunde3 = new Kunde("vorname3", "nachname3", "vorname3@nachname3.de");

		Rechnung rechnung = new Rechnung(12345L);

		InitialContext initialContext = new InitialContext();
		KundenService service = (KundenService) initialContext.lookup(EJBTools.machJNDIName(KundenService.class));

		service.neuerKunde(kunde1);

		service.neuerKunde(kunde2);

		service.neuerKunde(kunde3);

		List<Kunde> result = service.getAll();
		result.forEach((Kunde k) -> System.out.println("Multi: " + k.toString()));

		Kunde kunde = service.getSingle("vorname3@nachname3.de");
		System.out.println("Single: " + kunde.toString());

		Rechnung erzeugeRechnung = service.erzeugeRechnung(rechnung);

		service.setzteKundeInRechnung(erzeugeRechnung, "vorname3@nachname3.de");

	}

}
