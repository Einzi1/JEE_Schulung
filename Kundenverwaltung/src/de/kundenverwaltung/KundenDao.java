package de.kundenverwaltung;

import java.util.List;

import javax.ejb.Local;

@Local
public interface KundenDao {

	public Kunde save(Kunde kunde);

	public Kunde update(Kunde kunde);

	public Kunde getById(Long id);

	public void delete(Long id);

	public List<Kunde> getAll();

	public Kunde getSingle(String suchBegriff);
}
