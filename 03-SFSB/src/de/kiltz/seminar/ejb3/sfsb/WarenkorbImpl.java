package de.kiltz.seminar.ejb3.sfsb;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Remove;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

@Stateful(name = "Warenkorb")
public class WarenkorbImpl implements Warenkorb, SessionSynchronization {

	private Map<String, Postion> positionen;

	@PostConstruct
	void init() {
		System.out.println("Erzeuge Positionen");
		positionen = new HashMap<String, Postion>();
	}

	public void entferneAusWarenkorb(String artikelnummer) {
		positionen.remove(artikelnummer);

	}

	@Remove
	public float geheZurKasse() {
		// Tue etwas...
		return positionen.size();
	}

	public void legeInWarenkorb(int anzahl, String artikelnummer) {
		// hole Ware
		// baue Position
		// füge Position zur Liste
		positionen.put(artikelnummer, new Postion());

	}

	public void afterBegin() throws EJBException, RemoteException {
		System.out.println("SessionSynchronization->afterBegin()");

	}

	public void afterCompletion(boolean committed) throws EJBException, RemoteException {
		System.out.println("SessionSynchronization->afterCompletion() (" + committed + ")");

	}

	public void beforeCompletion() throws EJBException, RemoteException {
		System.out.println("SessionSynchronization->beforeCompletion()");

	}

}
