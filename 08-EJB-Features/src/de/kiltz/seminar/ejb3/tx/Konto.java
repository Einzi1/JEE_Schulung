package de.kiltz.seminar.ejb3.tx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "konto")
public class Konto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nr;
	private float kontostand;

	public Konto() {

	}

	public Konto(String nr, float kontostand) {
		setNr(nr);
		setKontostand(kontostand);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public float getKontostand() {
		return kontostand;
	}

	public void setKontostand(float kontostand) {
		this.kontostand = kontostand;
	}

}
