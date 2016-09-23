package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.seminar.ejb3.beziehungen.Kontaktdaten;

@Stateless(name = "KontaktdatenDAO")
public class KontaktdatenDAOImpl implements KontaktdatenDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Kontaktdaten> getAll() throws Exception {
		Query q = em.createQuery("select k from Kontaktdaten as k");
		List<Kontaktdaten> ret = q.getResultList();
		return ret;
	}

	// Die delegierten Methoden
	public Kontaktdaten getById(Integer id) throws Exception {
		return (Kontaktdaten) em.find(Kontaktdaten.class, id);
	}

	public Kontaktdaten save(Kontaktdaten o) throws Exception {
		em.persist(o);
		return o;
	}

	public void update(Kontaktdaten o) throws Exception {
		em.merge(o);

	}

	public void deleteById(Kontaktdaten o) throws Exception {
		em.remove(o);
	}

}
