package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

/**
 * DAO-Interface für die Basic-Operationen.
 *
 * @param <Obj>
 *            Das Business-Object/Domain Object
 */
public interface BasicDAO<Obj> {

	Obj save(Obj o) throws Exception;

	Obj getById(Integer id) throws Exception;

	void update(Obj o) throws Exception;

	void deleteById(Obj o) throws Exception;

	List<Obj> getAll() throws Exception;;
}
