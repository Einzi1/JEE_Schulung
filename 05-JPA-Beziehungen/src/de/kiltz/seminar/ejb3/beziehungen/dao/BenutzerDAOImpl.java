package de.kiltz.seminar.ejb3.beziehungen.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.kiltz.seminar.ejb3.beziehungen.Benutzer;

@Stateless(name = "BenutzerDAO")
public class BenutzerDAOImpl implements BenutzerDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Benutzer> getAll() throws Exception {
		Query q = em.createQuery("select b from Benutzer as b");
		List<Benutzer> ret = q.getResultList();
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Benutzer> getByName(String name) throws Exception {
		System.out.println("getByName: Start");
		Query q = em.createQuery("select b from Benutzer as b where b.name like '%" + name + "%'");
		List<Benutzer> ret = q.getResultList();
		System.out.println("getByName: " + ret.size());
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Benutzer> getByGeburtstag(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String sql = "select b from Benutzer as b " + "where b.kontaktdaten.geburtstag like '%" + sdf.format(date)
				+ "'";
		Query q = em.createQuery(sql);
		List<Benutzer> ret = q.getResultList();
		return ret;
	}

	// Die allgemeinen Methoden
	public Benutzer getById(Integer id) throws Exception {
		return (Benutzer) em.find(Benutzer.class, id);
	}

	public Benutzer save(Benutzer o) throws Exception {
		em.persist(o);
		return o;
	}

	public void update(Benutzer o) throws Exception {
		em.merge(o);
	}

	public void deleteById(Benutzer o) throws Exception {
		em.remove(o);
	}

}
