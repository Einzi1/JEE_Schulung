package de.kundenverwaltung;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface KundenService {

	public Kunde neuerKunde(Kunde kunde) throws PflichtfeldException;

	public Kunde aendereKunde(Kunde kunde) throws PflichtfeldException;

	public Kunde holeKunde(Long id);

	public void loscheKunde(Long id);

	public List<Kunde> getAll();

	public Kunde getSingle(String suchBegriff);

	public Rechnung erzeugeRechnung(Rechnung rechnung);

	public void setzteKundeInRechnung(Rechnung rechnung, String kundenMail);
}
