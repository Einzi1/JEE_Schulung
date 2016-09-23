package de.kiltz.jpa.features.framework;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date erzeugtAm;
	private Date letzeAenderung;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getErzeugtAm() {
		return erzeugtAm;
	}

	public void setErzeugtAm(Date erzeugtAm) {
		this.erzeugtAm = erzeugtAm;
	}

	public Date getLetzeAenderung() {
		return letzeAenderung;
	}

	public void setLetzeAenderung(Date letzeAenderung) {
		this.letzeAenderung = letzeAenderung;
	}

	@PrePersist
	public void vorPersist() {
		setErzeugtAm(new Date());
	}

	// funktioniert nicht: merge != update
	@PreUpdate
	public void vorUpdate() {
		setLetzeAenderung(new Date());
	}

}
