package de.kiltz.seminar.ejb3.beziehungen.service;

import java.util.List;

import javax.ejb.Remote;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;
import de.kiltz.seminar.ejb3.beziehungen.Gruppe;
import de.kiltz.seminar.ejb3.beziehungen.Rolle;

@Remote
public interface BenutzerService {

	Benutzer erzeugeBenutzer(Benutzer ben) throws Exception;
	Gruppe erzeugeGruppe(Gruppe gruppe) throws Exception;
	Rolle erzeugeRolle(Rolle rolle) throws Exception;
	
	void fuegeRolleZuBenutzer(Benutzer ben, String rolle) throws Exception;
	void setzeGruppe(Benutzer ben, String gruppe) throws Exception;
//	void erzeugeKontaktdaten(Benutzer ben, Kontaktdaten daten) throws Exception;
	
	Benutzer holeBenutzer(int id) throws Exception;
	List<Benutzer> sucheBenutzer(String name) throws Exception;
	List<Benutzer> getGeburtstagsListe();
	
}
