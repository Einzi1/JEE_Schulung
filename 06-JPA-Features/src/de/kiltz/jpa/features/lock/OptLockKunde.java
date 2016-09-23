package de.kiltz.jpa.features.lock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Domain-Klasse für einen Kunden.
 * 
 * @author tz
 * 
 */
@Entity
public class OptLockKunde implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** Kundennummer in Form KXXXX */
	@Column(name = "kdNr", length = 5, unique = true)
	private String kdNr;
	/** Nachname des Kunden */
	private String name;
	/** Vorname des Kunden */
	private String vorname;

	@Version
	private Integer version;

	/**
	 * Standard-Konstruktor für Bean-Operations
	 */
	public OptLockKunde() {

	}

	/**
	 * gemütlicher Konstruktor für die üblichen Verdächtigen.
	 * 
	 * Die Id wird vom System vergeben.
	 * 
	 * @param kdNr
	 * @param vorname
	 * @param name
	 */
	public OptLockKunde(String kdNr, String vorname, String name) {
		setKdNr(kdNr);
		setName(name);
		setVorname(vorname);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getKdNr() {
		return kdNr;
	}

	public void setKdNr(String kdNr) {
		this.kdNr = kdNr;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kdNr == null) ? 0 : kdNr.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OptLockKunde other = (OptLockKunde) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kdNr == null) {
			if (other.kdNr != null)
				return false;
		} else if (!kdNr.equals(other.kdNr))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}
}
