package de.kiltz.kv.dao;

import javax.ejb.Local;

import de.kiltz.kv.entity.RechnungEntity;

@Local
public interface RechnungDao {
	
	Long save(RechnungEntity r);
	void update(RechnungEntity r);
	RechnungEntity getById(Long id);
	void delete(Long id);

}
