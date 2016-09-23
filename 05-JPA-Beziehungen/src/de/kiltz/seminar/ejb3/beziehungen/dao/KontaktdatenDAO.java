package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Local;

import de.kiltz.seminar.ejb3.beziehungen.Kontaktdaten;

@Local
public interface KontaktdatenDAO {

	Kontaktdaten save(Kontaktdaten o) throws Exception;

	Kontaktdaten getById(Integer id) throws Exception;

	void update(Kontaktdaten o) throws Exception;

	void deleteById(Kontaktdaten o) throws Exception;

	List<Kontaktdaten> getAll() throws Exception;;

}
