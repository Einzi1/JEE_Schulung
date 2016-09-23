package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;

@Local
public interface BenutzerDAO {

	List<Benutzer> getByName(String name) throws Exception;

	List<Benutzer> getByGeburtstag(Date date);

	Benutzer save(Benutzer o) throws Exception;

	Benutzer getById(Integer id) throws Exception;

	void update(Benutzer o) throws Exception;

	void deleteById(Benutzer o) throws Exception;

	List<Benutzer> getAll() throws Exception;;

}
