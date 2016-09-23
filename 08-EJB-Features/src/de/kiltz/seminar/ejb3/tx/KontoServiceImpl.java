package de.kiltz.seminar.ejb3.tx;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(name = "KontoService", mappedName = "ejb/KontoService")
public class KontoServiceImpl implements KontoService {

	@EJB(beanName = "KontoDAO")
	private KontoDAO kontoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoService#init()
	 */

	@PostConstruct
	public void init() {
		System.out.println("Leere die Tabelle...");
		kontoDAO.deleteAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoService#neuesKonto(java.lang.String,
	 * float)
	 */
	public Konto neuesKonto(String nr, float kontostand) {
		Konto k = new Konto(nr, kontostand);
		try {
			k = kontoDAO.save(k);
		} catch (Exception e) {
			System.out.println("Kann Konto nicht erzeugen: " + e.getMessage());
			e.printStackTrace();
		}
		return k;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kiltz.seminar.ejb3.tx.KontoService#buche(java.lang.String,
	 * java.lang.String, float)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void buche(String kontoNr1, String kontoNr2, float betrag) throws KontoZuKleinException {
		Konto k1 = null;
		Konto k2 = null;
		try {
			k1 = kontoDAO.getByNr(kontoNr1);
			k2 = kontoDAO.getByNr(kontoNr2);
			k1.setKontostand(k1.getKontostand() + betrag);
			k2.setKontostand(k2.getKontostand() - betrag);
		} catch (Exception e) {
			System.out.println("Fehler beim Buchen: " + e.getMessage());
		}
		if (k2.getKontostand() < 0) {
			throw new KontoZuKleinException("Zu wenig Kontodeckung");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kiltz.seminar.ejb3.tx.KontoService#holeKontostand(java.lang.String)
	 */
	public float holeKontostand(String nr) {
		float ret = 0;
		try {
			Konto k = kontoDAO.getByNr(nr);
			ret = k.getKontostand();
		} catch (Exception e) {
			System.out.println("Fehler beim Kontostand: " + e.getMessage());
		}
		return ret;
	}
}
