package de.kiltz.seminar.ejb3.tx;

import javax.ejb.Remote;

@Remote
public interface KontoService {

	void init();

	Konto neuesKonto(String nr, float kontostand);

	void buche(String kontoNr1, String kontoNr2, float betrag) throws KontoZuKleinException;

	float holeKontostand(String nr);

}