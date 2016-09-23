package de.kiltz.seminar.ejb3.beziehungen.test;

/**
 * Zurücksetzen:
	delete from benutzer_rollen ;
	delete from benutzer ;
	delete from gruppe ;
	delete from kontaktdaten ;
	delete from rolle ;
 */
import java.util.List;

import javax.naming.InitialContext;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;
import de.kiltz.seminar.ejb3.beziehungen.service.BenutzerService;
import de.kiltz.seminar.framework.EJBTools;

public class GeburtstagsTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();
		BenutzerService service = (BenutzerService) ctx.lookup(EJBTools.machJNDIName(BenutzerService.class));
		List<Benutzer> liste = service.getGeburtstagsListe();
		System.out.println(liste.size());
		for (Benutzer benutzer : liste) {
			System.out.println(benutzer.getName() + ": " + benutzer.getKontaktdaten().getGeburtstag());
		}
	}

}
