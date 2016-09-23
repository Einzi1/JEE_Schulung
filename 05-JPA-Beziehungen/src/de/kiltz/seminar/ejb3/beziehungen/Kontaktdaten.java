package de.kiltz.seminar.ejb3.beziehungen;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "kontaktdaten")
public class Kontaktdaten extends SuperEntity {

	@OneToOne
	@PrimaryKeyJoinColumn
	private Benutzer benutzer;
	@Column(name = "geburtstag")
	@Temporal(TemporalType.DATE)
	private Date geburtstag;

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kontaktdaten [geburtstag=");
		builder.append(geburtstag);
		builder.append("]");
		return builder.toString();
	}

}
