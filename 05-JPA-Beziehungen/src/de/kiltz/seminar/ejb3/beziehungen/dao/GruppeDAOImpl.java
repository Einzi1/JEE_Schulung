package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.seminar.ejb3.beziehungen.Gruppe;

@Stateless(name = "GruppeDAO")
public class GruppeDAOImpl implements GruppeDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Gruppe> getAll() throws Exception {
		Query q = em.createQuery("select g from Gruppe as g");
		List<Gruppe> ret = q.getResultList();
		return ret;
	}

	public Gruppe getByName(String nameDerGruppe) throws Exception {
		Query q = em.createQuery("select g from Gruppe g where g.name = :name");
		q.setParameter("name", nameDerGruppe);
		Gruppe ret = (Gruppe) q.getSingleResult();
		return ret;
	}

	// Die delegierten Methoden
	public Gruppe getById(Integer id) throws Exception {
		return (Gruppe) em.find(Gruppe.class, id);
	}

	public Gruppe save(Gruppe o) throws Exception {
		em.persist(o);
		return o;
	}

	public void update(Gruppe o) throws Exception {
		em.merge(o);

	}

	public void deleteById(Gruppe o) throws Exception {
		em.remove(o);
	}

}
