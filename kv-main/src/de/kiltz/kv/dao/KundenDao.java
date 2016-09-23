package de.kiltz.kv.dao;

import java.util.List;

import javax.ejb.Local;

import de.kiltz.kv.entity.KundeEntity;

@Local
public interface KundenDao {
	
	KundeEntity save(KundeEntity k);
	KundeEntity update(KundeEntity k);
	KundeEntity getById(Long id);
	void delete(Long id);

	List<KundeEntity> findByName(String name);
}
