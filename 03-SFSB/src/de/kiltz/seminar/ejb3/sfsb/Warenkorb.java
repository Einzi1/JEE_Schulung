package de.kiltz.seminar.ejb3.sfsb;

import javax.ejb.Remote;

@Remote
public interface Warenkorb {

	void legeInWarenkorb(int anzahl, String artikelnummer);

	void entferneAusWarenkorb(String artikelnummer);

	float geheZurKasse();
}
