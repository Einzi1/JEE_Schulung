package de.kiltz.seminar.ejb3.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.kiltz.seminar.ejb3.jpa.domain.FunnyEnum;
import de.kiltz.seminar.ejb3.jpa.framework.LifeListener;

/**
 * Entity mit unterschiedlichen Feldern.
 *
 */
@Entity(name = "Sammlung")
@EntityListeners(LifeListener.class)
@Table(name = "SAMMLUNG")
@NamedQuery(name = "suche", query = "select s from Sammlung s where s.text like :suche")
public class SammlungEntity implements Serializable {

	@Id
	@GeneratedValue
	// @Column(name = "id")
	private Integer id;

	private String text;
	private float zahl;
	@Temporal(TemporalType.DATE)
	private Date datum;
	
	private Integer funZahl;
	private FunnyEnum funEnum;

	@PostLoad
	public void nachLaden() {
		System.out.println(getId() + " wurde geladen!");
	}

	@PrePersist
	public void vorDemSpeichern() {
		System.out.println("Speichere gleich: " + getId());
	}
	@PreUpdate
	public void vorDemAktualisieren() {
		System.out.println("Aktualisiere gleich: " + getId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getZahl() {
		return zahl;
	}

	public void setZahl(float zahl) {
		this.zahl = zahl;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Integer getFunZahl() {
		return funZahl;
	}

	public void setFunZahl(Integer funZahl) {
		this.funZahl = funZahl;
	}

	public FunnyEnum getFunEnum() {
		return funEnum;
	}

	public void setFunEnum(FunnyEnum funEnum) {
		this.funEnum = funEnum;
	}

	@Override
	public String toString() {
		return "SammlungEntity [id=" + id + ", text=" + text + ", zahl=" + zahl + ", datum=" + datum + ", funZahl="
				+ funZahl + ", funEnum=" + funEnum + "]";
	}
}
