package de.kiltz.kv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.kv.entity.KundeEntity;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class KundenDaoImpl implements KundenDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public KundeEntity save(KundeEntity k) {
		em.persist(k);
		return k;
	}

	@Override
	public KundeEntity update(KundeEntity k) {
		k = em.merge(k);
		return k;
	}

	@Override
	public KundeEntity getById(Long id) {
		
		return em.find(KundeEntity.class, id);
	}

	@Override
	public void delete(Long id) {
		KundeEntity deliq = getById(id);
		em.remove(deliq);
		
	}

	@Override
	public List<KundeEntity> findByName(String name) {
		Query q = em.createQuery("select k from KundeEntity as k where k.name like :name");
		q.setParameter("name", "%"+name+"%");
		return q.getResultList();
	}


}


