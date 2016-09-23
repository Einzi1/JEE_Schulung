package de.kundenverwaltung;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Kunde")
@Table(name = "KUNDE")
@NamedQueries(value = { @NamedQuery(name = "getAll", query = "select k from Kunde k"),
		@NamedQuery(name = "getSingle", query = "select k from Kunde k where k.email = :email") })
public class Kunde implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Der Vorname darf natürlich nicht null sein! Jeder braucht doch einen Namen!")
	private String vorname;

	@NotNull(message = "Auch ein Nachname ist ziemlich wichtig!")
	private String nachname;

	@Column(unique = true)
	private String email;

	public Kunde() {
	}

	public Kunde(String vorname, String nachname, String email) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Kunde [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", email=" + email + "]";
	}

}
