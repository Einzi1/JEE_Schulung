package de.kiltz.seminar.ejb3.beziehungen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;
import de.kiltz.seminar.ejb3.beziehungen.Gruppe;
import de.kiltz.seminar.ejb3.beziehungen.Rolle;
import de.kiltz.seminar.ejb3.beziehungen.dao.BenutzerDAO;
import de.kiltz.seminar.ejb3.beziehungen.dao.GruppeDAO;
import de.kiltz.seminar.ejb3.beziehungen.dao.KontaktdatenDAO;
import de.kiltz.seminar.ejb3.beziehungen.dao.RolleDAO;

@Stateless(name = "BenutzerService", mappedName = "ejb/BenutzerService")
public class BenutzerServiceImpl implements BenutzerService {

	@EJB(beanName = "BenutzerDAO")
	private BenutzerDAO benutzerDAO;
	@EJB(beanName = "GruppeDAO")
	private GruppeDAO gruppeDAO;
	@EJB(beanName = "RolleDAO")
	private RolleDAO rolleDAO;
	@EJB(beanName = "KontaktdatenDAO")
	private KontaktdatenDAO kontaktdatenDAO;

	public Benutzer erzeugeBenutzer(Benutzer ben) throws Exception {
		// TODO Überprüfung der Inhalte
		/*
		 * if (ben.getKontaktdaten() != null) {
		 * kontaktdatenDAO.save(ben.getKontaktdaten()); }
		 */
		Benutzer ret = benutzerDAO.save(ben);
		return ret;
	}

	public Gruppe erzeugeGruppe(Gruppe gruppe) throws Exception {
		// TODO Überprüfung der Inhalte
		Gruppe ret = gruppeDAO.save(gruppe);
		return ret;
	}

	/*
	 * public void erzeugeKontaktdaten(Benutzer ben, Kontaktdaten daten) throws
	 * Exception { daten.setBenutzer(ben); kontaktdatenDAO.save(daten); }
	 */
	public Rolle erzeugeRolle(Rolle rolle) throws Exception {
		Rolle ret = rolleDAO.save(rolle);
		return ret;
	}

	public void fuegeRolleZuBenutzer(Benutzer ben, String nameDerRolle) throws Exception {
		Rolle rolle = rolleDAO.getByName(nameDerRolle);
		ben = benutzerDAO.getById(ben.getId());
		if (ben.getRollen() == null) {
			ben.setRollen(new ArrayList<Rolle>());
		}
		ben.getRollen().add(rolle);
		benutzerDAO.update(ben);
	}

	public Benutzer holeBenutzer(int id) throws Exception {
		Benutzer ret = benutzerDAO.getById(id);
		return ret;
	}

	public void setzeGruppe(Benutzer ben, String nameDerGruppe) throws Exception {
		Gruppe gruppe = gruppeDAO.getByName(nameDerGruppe);
		ben = benutzerDAO.getById(ben.getId());
		ben.setGruppe(gruppe);
		benutzerDAO.update(ben);
	}

	public List<Benutzer> sucheBenutzer(String name) throws Exception {
		List<Benutzer> liste = benutzerDAO.getByName(name);
		System.out.println("sucheBenutzer " + liste.size());
		return liste;
	}

	public List<Benutzer> getGeburtstagsListe() {
		List<Benutzer> liste = benutzerDAO.getByGeburtstag(new Date());
		return liste;
	}

}
