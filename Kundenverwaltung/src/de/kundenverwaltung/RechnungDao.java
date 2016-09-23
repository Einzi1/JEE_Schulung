package de.kundenverwaltung;

public interface RechnungDao {

	public Rechnung save(Rechnung rechnung);

	public Rechnung update(Rechnung rechnung);

	public Rechnung getById(Long id);

	public void delete(Long id) throws Exception;

}
