package de.kiltz.jpa.erbe.joined;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ExternerBenutzerB extends BenutzerB {

	private String firma;
	private String vertragsArt;

	public ExternerBenutzerB() {
	}

	public ExternerBenutzerB(String name, String passwort, String email, Date lastLogin, String firma,
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
