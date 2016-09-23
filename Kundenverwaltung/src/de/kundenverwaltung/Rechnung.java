package de.kundenverwaltung;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Rechnung")
@Table(name = "rechnung")
public class Rechnung implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private Long rechnungsnummer;

	@ManyToOne
	@JoinColumn(name = "idKunde")
	private Kunde kunde;

	public Rechnung() {

	}

	public Rechnung(Long rechnungsnummer, Kunde kunde) {
		this.rechnungsnummer = rechnungsnummer;
		this.kunde = kunde;
	}

	public Rechnung(Long rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRechnungsnummer() {
		return rechnungsnummer;
	}

	public void setRechnungsnummer(Long rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "Rechung [id=" + id + ", rechnungsnummer=" + rechnungsnummer + ", kunde=" + kunde.toString() + "]";
	}

}
