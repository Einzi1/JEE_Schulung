package de.kiltz.jpa.features.framework;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPADAOImpl<Obj extends SuperEntity> implements BasicDAO<Obj> {

	@PersistenceContext(unitName = "ProUnit")
	protected EntityManager em;
	private Class<Object> objKlasse;
	private String tabelle;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPADAOImpl(Class objKlasse, String tabelle) {
		this.objKlasse = objKlasse;
		this.tabelle = tabelle;
	}

	public void delete(Obj o) {
		em.remove(o);
	}

	@SuppressWarnings("unchecked")
	public List<Obj> getAll() {
		Query query = em.createQuery("from " + tabelle + " f");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Obj getById(Integer id) throws Exception {
		return (Obj) em.find(objKlasse, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Obj getById(Long id) {
		return (Obj) em.find(objKlasse, id);
	}

	@Override
	public Obj save(Obj o) {
		em.persist(o);
		return o;
	}

	@Override
	public Obj update(Obj o) {
		o.setLetzeAenderung(new Date());
		em.merge(o);
		return o;

	}

	@Override
	public void deleteById(Long id) {
		Obj o = getById(id);
		if (o != null) {
			delete(o);
		}

	}

}
