package de.kundenverwaltung.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KundeXml {

	private long id;
	private String vorname;
	private String nachname;
	private String email;

	public KundeXml() {
	}

	public KundeXml(Long id, String vorname, String nachname, String email) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
