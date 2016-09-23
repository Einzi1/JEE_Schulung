package de.kiltz.seminar.ejb3.beziehungen.test;

import java.util.Calendar;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;
import de.kiltz.seminar.ejb3.beziehungen.Kontaktdaten;
import de.kiltz.seminar.ejb3.beziehungen.service.BenutzerService;
import de.kiltz.seminar.ejb3.beziehungen.service.BenutzerServiceImpl;
import de.kiltz.seminar.framework.EJBTools;
import de.kiltz.seminar.util.Text;
import de.kiltz.seminar.util.ZeitStopper;

/**
 * Tester für eine Menge Daten in verscheidenen Threads.
 * 
 * vorher: 
delete from benutzer_rollen ;
delete FROM benutzer ;
delete from kontaktdaten ;

 * nachher: SELECT count(id) as anz FROM BENUTZER where name like '%(1-%';
 * 
 * @author f
 * 
 */
public class ThreadTester implements Runnable {

	private static int threads = 10;
	private int saetze = 10;

	private int threadNr;
	private static BenutzerService service;

	public ThreadTester(int threadNr) {
		this.threadNr = threadNr;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			service = (BenutzerService)ctx.lookup(EJBTools.machJNDIName(BenutzerService.class));
			Thread t = new Thread(this);
			t.start();
		} catch (NamingException e) {
			System.out.println("Fehler bei Thread Nr: "+threadNr);
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		service = new BenutzerServiceImpl();
		for (int i = 0; i < threads; i++) {
			new ThreadTester(i);
		}
	}

	private void erzeugeBenutzer(int nr) throws Exception {
		String name = Text.erzeugeZufallsText(20, true) + "(" + threadNr + "-"
				+ nr + ")";
		String email = ""+threadNr;
		String passwort = ""+nr;
		Benutzer ben = new Benutzer(name, email, passwort);
		ben.setLastLogin(new Date());
		// Kontaktdaten
		Kontaktdaten daten = new Kontaktdaten();
		//daten.setBenutzer(ben);
		ben.setKontaktdaten(daten);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, (int) (Math.random() * 360) * -1);
		c.add(Calendar.YEAR, (int) (Math.random() * 40) * -1);
		Date geburtstag = c.getTime();

		
		daten.setGeburtstag(geburtstag);
		// Ben speichern
		ben = service.erzeugeBenutzer(ben);
		//service.erzeugeKontaktdaten(ben, daten);
		// Gruppe
		service.setzeGruppe(ben, "Datenpfleger");
		// Rolle
		service.fuegeRolleZuBenutzer(ben, "Entwickler");
		service.fuegeRolleZuBenutzer(ben, "Designer");

	}

	public void run() {
		ZeitStopper s = new ZeitStopper();
		for (int i = 0; i < saetze; i++) {
			try {
				erzeugeBenutzer(i);
			} catch (Exception e) {
				System.out.println("Fehler bei " + threadNr + "/" + i);
				e.printStackTrace();
			}
		}
		System.out.println("Zeit für " + threadNr + ": " + s.stop());
	}
}

/*
 * Windows 10 x 100
 * 
Zeit f�r 4: 29281 ms.
Zeit f�r 3: 30375 ms.
Zeit f�r 0: 30610 ms.
Zeit f�r 1: 30719 ms.
Zeit f�r 9: 30688 ms.
Zeit f�r 5: 30890 ms.
Zeit f�r 8: 30969 ms.
Zeit f�r 7: 30969 ms.
Zeit f�r 6: 31000 ms.
Zeit f�r 2: 31094 ms.

zweiter Durchlauf
Zeit f�r 4: 22015 ms.
Zeit f�r 0: 22438 ms.
Zeit f�r 8: 22516 ms.
Zeit f�r 2: 22750 ms.
Zeit f�r 5: 22921 ms.
Zeit f�r 7: 22906 ms.
Zeit f�r 3: 23031 ms.
Zeit f�r 9: 23094 ms.
Zeit f�r 6: 23094 ms.
Zeit f�r 1: 23250 ms.

Linux
Zeit f�r 2: 16145 ms.
Zeit f�r 5: 16222 ms.
Zeit f�r 4: 16282 ms.
Zeit f�r 6: 16069 ms.
Zeit f�r 1: 16475 ms.
Zeit f�r 0: 16557 ms.
Zeit f�r 9: 16079 ms.
Zeit f�r 3: 16498 ms.
Zeit f�r 7: 16265 ms.
Zeit f�r 8: 16447 ms.


 * 
 */