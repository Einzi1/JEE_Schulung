package de.kiltz.seminar.ejb3.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.seminar.ejb3.jpa.entity.SammlungEntity;

/**
 * Beispiel für CRUD Create, Read Update und Delete
 *
 */
@Stateless(name = "CrudBeispiel", mappedName = "ejb/CrudBeispiel")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SammlungDAOImpl implements SammlungDAO {

	@PersistenceContext
	private EntityManager em;

	public void delete(Integer id) {
		SammlungEntity o = getById(id);
		em.remove(o);

	}

	public SammlungEntity getById(Integer id) {
		return em.find(SammlungEntity.class, id);
	}

	public Integer save(SammlungEntity o) {
		em.persist(o);
		System.out.println("Speichere " + o.getId());
		return o.getId();
	}

	public void update(SammlungEntity o) {
		em.merge(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SammlungEntity> suche(String suchBegriff) {
		// Query q = em.createQuery("select s from Sammlung s where s.text like
		// :suche", SammlungEntity.class);
		Query q = em.createNamedQuery("suche");
		q.setParameter("suche", "%" + suchBegriff + "%");
		List<SammlungEntity> ergebnis = q.getResultList();
		return ergebnis;
	}

}
