package de.kiltz.seminar.ejb3.jpa.dao;

import java.util.List;

import javax.ejb.Local;

import de.kiltz.seminar.ejb3.jpa.entity.SammlungEntity;

/**
 * Basis-Operationen CRUD
 *
 */
@Local
public interface SammlungDAO {

	Integer save(SammlungEntity o);

	void update(SammlungEntity o);

	void delete(Integer id);

	SammlungEntity getById(Integer id);

	List<SammlungEntity> suche(String suchBegriff);
}
