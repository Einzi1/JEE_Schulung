package de.kiltz.seminar.hibernate.basic;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "benutzer")
public class Benutzer {

	/*
	 * Da Hibernate zwischen Update und Create entscheidet, indem er den
	 * Primary-Key auf Null prüft, ist Integer dem int vorzuziehen. Bei größeren
	 * Datenbeständen Long.
	 * 
	 * Definition der ID und seiner der Generierungsart. AUTO=Nutzt die Optimale
	 * Strategie, abhängig vom DB-System.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "nachname")
	private String name;
	private String passwort;
	@Basic(optional = false)
	@Column(name = "email", length = 40, unique = true)
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	// Hibernate ben�tigt den Standard-Konstruktor
	public Benutzer() {
	}

	public Benutzer(String name, String email, String passwort) {
		setName(name);
		setEmail(email);
		setPasswort(passwort);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Benutzer [id=" + id + ", name=" + name + ", passwort=" + passwort + ", email=" + email + ", lastLogin="
				+ lastLogin + "]";
	}

}
