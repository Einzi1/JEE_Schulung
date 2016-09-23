package de.kiltz.seminar.ejb3.beziehungen;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rolle")
public class Rolle extends SuperEntity {

	private String name;
	private String beschreibung;

	public Rolle() {
	}

	public Rolle(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rolle [name=");
		builder.append(name);
		builder.append(", beschreibung=");
		builder.append(beschreibung);
		builder.append("]");
		return builder.toString();
	}

}
