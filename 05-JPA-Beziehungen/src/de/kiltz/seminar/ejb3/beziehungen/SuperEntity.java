package de.kiltz.seminar.ejb3.beziehungen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class SuperEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Date erzeugtAm;
	private Date letzeAenderung;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	@PreUpdate
	public void vorUpdate() {
		setLetzeAenderung(new Date());
	}
	
}
