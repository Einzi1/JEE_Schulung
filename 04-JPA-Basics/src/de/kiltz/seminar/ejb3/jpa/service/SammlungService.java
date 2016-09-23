package de.kiltz.seminar.ejb3.jpa.service;

import java.util.List;

import javax.ejb.Remote;

import de.kiltz.seminar.ejb3.jpa.domain.Sammlung;

@Remote
public interface SammlungService {

	String teste();

	Sammlung neu(Sammlung s);

	Sammlung hole(Integer id);

	List<Sammlung> suche(String s);
}
