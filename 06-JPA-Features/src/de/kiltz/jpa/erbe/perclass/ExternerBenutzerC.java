package de.kiltz.jpa.erbe.perclass;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ExternerBenutzerC extends BenutzerC {

	private String firma;
	private String vertragsArt;

	public ExternerBenutzerC() {
	}

	public ExternerBenutzerC(String name, String passwort, String email, Date lastLogin, String firma,
			String vertragsArt) {
		super(name, passwort, email, lastLogin);
		this.firma = firma;
		this.vertragsArt = vertragsArt;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getVertragsArt() {
		return vertragsArt;
	}

	public void setVertragsArt(String vertragsArt) {
		this.vertragsArt = vertragsArt;
	}

}
