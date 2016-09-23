package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.seminar.ejb3.beziehungen.Rolle;

@Stateless(name = "RolleDAO")
public class RolleDAOImpl implements RolleDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Rolle> getAll() throws Exception {
		Query q = em.createQuery("select r from Rolle as r");
		List<Rolle> ret = q.getResultList();
		return ret;
	}

	public Rolle getByName(String nameDerRolle) throws Exception {
		Query q = em.createQuery("select r from Rolle as r where r.name = :name");
		q.setParameter("name", nameDerRolle);
		Rolle ret = (Rolle) q.getSingleResult();
		return ret;
	}

	// Die delegierten Methoden
	public Rolle getById(Integer id) throws Exception {
		return (Rolle) em.find(Rolle.class, id);
	}

	public Rolle save(Rolle o) throws Exception {
		em.persist(o);
		return o;
	}

	public void update(Rolle o) throws Exception {
		em.merge(o);
	}

	public void deleteById(Rolle o) throws Exception {
		em.remove(o);
	}

}
