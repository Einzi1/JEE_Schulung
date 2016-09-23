package de.kiltz.seminar.ejb3.tx;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "KontoDAO")
public class KontoDAOImpl implements KontoDAO {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoDAO#getByNr(java.lang.String)
	 */
	public Konto getByNr(String nr) throws Exception {
		Query q = em.createQuery("select k from Konto as k where k.nr = :nr");
		q.setParameter("nr", nr);
		Konto ret = (Konto) q.getSingleResult();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoDAO#deleteAll()
	 */
	public void deleteAll() {
		Query q = em.createQuery("delete from Konto ");
		q.executeUpdate();
	}

	// Die allgemeinen Methoden
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoDAO#getById(java.lang.Integer)
	 */
	public Konto getById(Integer id) throws Exception {
		return (Konto) em.find(Konto.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kiltz.seminar.ejb3.tx.KontoDAO#save(de.kiltz.seminar.ejb3.tx.Konto)
	 */
	public Konto save(Konto o) throws Exception {
		em.persist(o);
		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kiltz.seminar.ejb3.tx.KontoDAO#update(de.kiltz.seminar.ejb3.tx.Konto)
	 */
	public void update(Konto o) throws Exception {
		em.merge(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kiltz.seminar.ejb3.tx.KontoDAO#deleteById(de.kiltz.seminar.ejb3.tx.
	 * Konto)
	 */
	public void deleteById(Konto o) throws Exception {
		em.remove(o);
	}

}
