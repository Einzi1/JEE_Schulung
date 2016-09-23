package de.kiltz.seminar.ejb3.beziehungen;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "benutzer")
public class Benutzer extends SuperEntity {

	private String name;
	private String passwort;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	// Beziehung für 1:N
	@ManyToOne
	@JoinColumn(name = "idGruppe") // Standard wäre gruppe_id
	private Gruppe gruppe;
	// Beziehung für N:N
	@ManyToMany
	@JoinTable(name = "benutzer_rollen", 
			joinColumns = { @JoinColumn(name = "idBenutzer") }, 
			inverseJoinColumns = {@JoinColumn(name = "idRolle") })
	private List<Rolle> rollen;

	// Beziehung 1:1 bidirektional
	@OneToOne(cascade = CascadeType.ALL)
	private Kontaktdaten kontaktdaten;

	// Hibernate benötigt den Standard-Konstruktor
	public Benutzer() {
	}

	public Benutzer(String name, String email, String passwort) {
		setName(name);
		setEmail(email);
		setPasswort(passwort);
	}

	public Gruppe getGruppe() {
		return gruppe;
	}

	public void setGruppe(Gruppe gruppe) {
		this.gruppe = gruppe;
	}

	public List<Rolle> getRollen() {
		return rollen;
	}

	public void setRollen(List<Rolle> rollen) {
		this.rollen = rollen;
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

	public Kontaktdaten getKontaktdaten() {
		return kontaktdaten;
	}

	public void setKontaktdaten(Kontaktdaten kontaktdaten) {
		this.kontaktdaten = kontaktdaten;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Benutzer [name=");
		builder.append(name);
		builder.append(", passwort=");
		builder.append(passwort);
		builder.append(", email=");
		builder.append(email);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", gruppe=");
		builder.append(gruppe);
		builder.append(", rollen=");
		builder.append(rollen);
		builder.append(", kontaktdaten=");
		builder.append(kontaktdaten);
		builder.append("]");
		return builder.toString();
	}

}
