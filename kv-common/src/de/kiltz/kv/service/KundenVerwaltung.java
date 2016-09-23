package de.kiltz.kv.service;

import java.util.List;

import de.kiltz.kv.domain.Kunde;

public interface KundenVerwaltung {
	Kunde neuerKunde(Kunde k) throws PflichtfeldException;
	Kunde aendereKunde(Kunde k) throws PflichtfeldException;
	Kunde holeKunde(Long id);
	void loescheKunde(Long id);
	public List<Kunde> sucheKunde(String suchBegriff);
}
