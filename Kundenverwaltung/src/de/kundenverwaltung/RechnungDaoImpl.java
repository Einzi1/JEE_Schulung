package de.kundenverwaltung;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "RechnungDao")
public class RechnungDaoImpl implements RechnungDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Rechnung save(Rechnung rechnung) {
		entityManager.persist(rechnung);
		return rechnung;
	}

	@Override
	public Rechnung update(Rechnung rechnung) {
		entityManager.merge(rechnung);
		return rechnung;
	}

	@Override
	public Rechnung getById(Long id) {
		return (Rechnung) entityManager.find(Rechnung.class, id);
	}

	@Override
	public void delete(Long id) throws Exception {
		entityManager.remove(getById(id));
	}

}
