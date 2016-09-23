package de.kiltz.jpa.erbe.single;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("In")
public class InternerBenutzerA extends BenutzerA {

	private String mitarbeiterNr;
	private String durchwahl;
	private String abteilung;

	public InternerBenutzerA() {
	}

	public InternerBenutzerA(String name, String passwort, String email, Date lastLogin, String mitarbeiterNr,
			String durchwahl, String abteilung) {
		super(name, passwort, email, lastLogin);
		this.mitarbeiterNr = mitarbeiterNr;
		this.durchwahl = durchwahl;
		this.abteilung = abteilung;
	}

	public String getMitarbeiterNr() {
		return mitarbeiterNr;
	}

	public void setMitarbeiterNr(String mitarbeiterNr) {
		this.mitarbeiterNr = mitarbeiterNr;
	}

	public String getDurchwahl() {
		return durchwahl;
	}

	public void setDurchwahl(String durchwahl) {
		this.durchwahl = durchwahl;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

}
