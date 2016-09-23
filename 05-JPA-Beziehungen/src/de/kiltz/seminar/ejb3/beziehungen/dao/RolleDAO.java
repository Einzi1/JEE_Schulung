package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Local;

import de.kiltz.seminar.ejb3.beziehungen.Rolle;

@Local
public interface RolleDAO {

	Rolle getByName(String nameDerRolle) throws Exception;

	Rolle save(Rolle o) throws Exception;

	Rolle getById(Integer id) throws Exception;

	void update(Rolle o) throws Exception;

	void deleteById(Rolle o) throws Exception;

	List<Rolle> getAll() throws Exception;;

}
