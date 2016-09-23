package de.kiltz.kv.domain;

import java.io.Serializable;

/**
 * 
 * Positionsklasse
 * 
 */
public class Position implements Serializable {

	private Integer artikelnummer;
	private Long id;
	private Integer anzahl;

	public Position() {
	}

	public Position(Integer nummer, Integer anzahl) {
		super();
		this.artikelnummer = nummer;
		this.anzahl = anzahl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}

	public Integer getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(Integer artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	@Override
	public String toString() {
		return "Position [artikelnummer=" + artikelnummer + ", id=" + id
				+ ", anzahl=" + anzahl + "]";
	}

}
