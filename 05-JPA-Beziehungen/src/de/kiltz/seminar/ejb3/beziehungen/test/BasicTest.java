package de.kiltz.seminar.ejb3.beziehungen.test;

/**
 * Zurücksetzen:
	delete from benutzer_rollen ;
	delete from benutzer ;
	delete from gruppe ;
	delete from kontaktdaten ;
	delete from rolle ;
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;
import de.kiltz.seminar.ejb3.beziehungen.Gruppe;
import de.kiltz.seminar.ejb3.beziehungen.Kontaktdaten;
import de.kiltz.seminar.ejb3.beziehungen.Rolle;
import de.kiltz.seminar.ejb3.beziehungen.service.BenutzerService;
import de.kiltz.seminar.framework.EJBTools;

public class BasicTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();
		BenutzerService service = (BenutzerService) ctx.lookup(EJBTools.machJNDIName(BenutzerService.class));
		Gruppe g = new Gruppe("Tester");
		service.erzeugeGruppe(g);

		Benutzer b = new Benutzer("Der Name", "email@domain.de", "geheim");
		g = new Gruppe("Datenpfleger");
		g = service.erzeugeGruppe(g);
		b.setGruppe(g);
		Kontaktdaten k = new Kontaktdaten();
		k.setGeburtstag(new Date());
		k.setBenutzer(b);
		b.setKontaktdaten(k);
		b.setKontaktdaten(k);
		Rolle r1 = new Rolle("Entwickler");
		Rolle r2 = new Rolle("Designer");
		r1 = service.erzeugeRolle(r1);
		r2 = service.erzeugeRolle(r2);
		List<Rolle> rollen = new ArrayList<Rolle>();
		rollen.add(r1);
		rollen.add(r2);
		b.setRollen(rollen);
		System.out.println(b.getName() + ": " + b.getId());
		System.out.println("\n\n-------------------\n\n");
		b = service.erzeugeBenutzer(b);
		System.out.println("\n\n-------------------\n\n");
		System.out.println(b);

		List<Benutzer> liste = service.sucheBenutzer(b.getName());
		System.out.println(liste.size());

	}

}
