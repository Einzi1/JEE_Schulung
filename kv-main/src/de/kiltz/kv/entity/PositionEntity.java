package de.kiltz.kv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Entity mit unterschiedlichen Feldern.
 *
 */
@Entity
@Table(name = "Positionen")
public class PositionEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "id")
	private Long id;
	private Integer artikelnummer;
	private Integer anzahl;
	
	public Integer getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}
 
	@PostLoad
	public void nachLaden()
	{
		System.out.println(getId()+" wurde geladen!");
	}
	
	@PrePersist
	public void vorDemSpeichern()
	{
		System.out.println("Speichere gleich: "+getId());
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(Integer artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	@Override
	public String toString() {
		return "PositionEntity [id=" + id + ", artikelnummer=" + artikelnummer
				+ ", anzahl=" + anzahl + "]";
	}
}
