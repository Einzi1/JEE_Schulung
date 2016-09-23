package de.kiltz.jpa.features.framework;

import java.util.List;

/**
 * DAO-Interface für die Basic-Operationen.
 *
 * @param <Obj>
 *            die Entity
 */
public interface BasicDAO<Obj extends SuperEntity> {

	Obj save(Obj o);

	Obj getById(Long id);

	Obj update(Obj o);

	void delete(Obj o);

	void deleteById(Long id);

	List<Obj> getAll();
}
