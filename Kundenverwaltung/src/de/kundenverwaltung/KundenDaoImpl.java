package de.kundenverwaltung;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "KundenDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class KundenDaoImpl implements KundenDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Kunde save(Kunde kunde) {
		entityManager.persist(kunde);
		return kunde;
	}

	@Override
	public Kunde update(Kunde kunde) {
		return entityManager.merge(kunde);
	}

	@Override
	public Kunde getById(Long id) {
		return entityManager.find(Kunde.class, id);
	}

	@Override
	public void delete(Long id) {
		Kunde alterKunde = getById(id);
		entityManager.remove(alterKunde);
	}

	public List<Kunde> getAll() {
		Query queryGetAll = entityManager.createNamedQuery("getAll");
		List<Kunde> kunden = queryGetAll.getResultList();
		return kunden;
	}

	@Override
	public Kunde getSingle(String suchBegriff) {
		Query namedQuery = entityManager.createNamedQuery("getSingle");
		namedQuery.setParameter("email", suchBegriff);
		Kunde kunde = (Kunde) namedQuery.getSingleResult();
		return kunde;
	}

}
