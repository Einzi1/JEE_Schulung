package de.kiltz.kv.service;

import de.kiltz.kv.domain.Position;
import de.kiltz.kv.domain.Rechnung;

public interface RechnungsVerwaltung {
	Rechnung neueRechnung(Rechnung r) throws PflichtfeldException;
	Rechnung aendereRechnung(Rechnung r) throws PflichtfeldException;
	Rechnung holeRechnung(Long id);
	void loescheRechnung(Long id);
	Rechnung addPosition(Position p, Long id);
	Rechnung loeschePosition(Position p, Long id);

}
