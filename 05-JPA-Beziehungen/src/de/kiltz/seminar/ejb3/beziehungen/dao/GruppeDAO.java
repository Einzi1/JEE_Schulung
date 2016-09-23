package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Local;

import de.kiltz.seminar.ejb3.beziehungen.Gruppe;

@Local
public interface GruppeDAO {

	Gruppe getByName(String nameDerGruppe) throws Exception;

	Gruppe save(Gruppe o) throws Exception;

	Gruppe getById(Integer id) throws Exception;

	void update(Gruppe o) throws Exception;

	void deleteById(Gruppe o) throws Exception;

	List<Gruppe> getAll() throws Exception;;

}
