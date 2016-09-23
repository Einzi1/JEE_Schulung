package de.kundenverwaltungjsf;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.kundenverwaltung.Kunde;
import de.kundenverwaltung.KundenService;
import de.kundenverwaltung.PflichtfeldException;

@ManagedBean(name = "kunde")
@SessionScoped
public class KundeBean {

	@EJB
	private KundenService kundenService;

	private String vorname;
	private String nachname;
	private String email;
	private String info;

	public String save() throws PflichtfeldException {
		kundenService.neuerKunde(new Kunde(vorname, nachname, email));
		setInfo("Ein neuer Kunde wurde hinzugefügt");
		return "/kunde.jsf";
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
