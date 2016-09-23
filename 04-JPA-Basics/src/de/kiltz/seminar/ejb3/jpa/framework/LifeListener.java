package de.kiltz.seminar.ejb3.jpa.framework;

import javax.persistence.PostPersist;

import de.kiltz.seminar.ejb3.jpa.entity.SammlungEntity;

public class LifeListener {

	@PostPersist
	public void nachDemSpeichern(SammlungEntity s) {
		System.out.println("Speichere Los...." + s.getId());
	}
}
