package de.kiltz.jpa.erbe.single;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ex")
public class ExternerBenutzerA extends BenutzerA {

	private String firma;
	private String vertragsArt;

	public ExternerBenutzerA() {
	}

	public ExternerBenutzerA(String name, String passwort, String email, Date lastLogin, String firma,
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
