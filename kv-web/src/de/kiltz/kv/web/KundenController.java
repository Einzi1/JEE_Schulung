package de.kiltz.kv.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;

@ManagedBean(name="kunden")	
@SessionScoped
public class KundenController {
	
	private String info;
	private String fehlerMeldung;
	private String suchBegriff;
	private String neuerName;
	private List<Kunde> liste;
	
	@EJB
	private KundenVerwaltung service;
	
	@PostConstruct
	public void init() {
		info = "Lebe!";
		liste = new ArrayList<Kunde>();
	}
	
	public String suche() {
		liste = service.sucheKunde(suchBegriff);
		setInfo("Suche nach "+suchBegriff+" "+liste.size()+" Treffer");
		return "/liste.jsf";
	}
	public String neu() {
		try {
			service.neuerKunde(new Kunde(neuerName));
			setNeuerName("");
		} catch (PflichtfeldException e) {
			fehlerMeldung = e.getMessage();
		}
		return suche();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSuchBegriff() {
		return suchBegriff;
	}

	public void setSuchBegriff(String suchBegriff) {
		this.suchBegriff = suchBegriff;
	}

	public String getNeuerName() {
		return neuerName;
	}

	public void setNeuerName(String neuerName) {
		this.neuerName = neuerName;
	}

	public String getFehlerMeldung() {
		return fehlerMeldung;
	}

	public void setFehlerMeldung(String fehlerMeldung) {
		this.fehlerMeldung = fehlerMeldung;
	}

	public List<Kunde> getListe() {
		return liste;
	}

	public void setListe(List<Kunde> liste) {
		this.liste = liste;
	}

}
