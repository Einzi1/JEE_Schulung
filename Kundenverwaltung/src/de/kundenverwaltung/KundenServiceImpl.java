package de.kundenverwaltung;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Stateless(name = "KundenService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class KundenServiceImpl implements KundenService {

	@Resource
	private Validator validator;
	@EJB
	private KundenDao kundenDao;
	@EJB
	private RechnungDao rechnungDao;

	@Override
	public Kunde neuerKunde(Kunde kunde) throws PflichtfeldException {
		System.out.println("Kunde erstellt!");
		pruefe(kunde);

		return kundenDao.save(kunde);
	}

	@Override
	public Kunde aendereKunde(Kunde kunde) throws PflichtfeldException {
		System.out.println("Kunde geändert!");
		pruefe(kunde);
		if (kunde.getId() == null) {
			throw new PflichtfeldException("keine ID vorhanden");
		}
		return kundenDao.update(kunde);
	}

	@Override
	public Kunde holeKunde(Long id) {
		System.out.println("Kunde geholt!");
		return kundenDao.getById(id);
	}

	@Override
	public void loscheKunde(Long id) {
		System.out.println("Kunde gelöscht!");
		kundenDao.delete(id);
	}

	public List<Kunde> getAll() {
		System.out.println("Hole alle Kunden");
		return kundenDao.getAll();
	}

	private boolean nullOrEmpty(String s) {
		if (s == null) {
			return true;
		}
		if (s.isEmpty()) {
			return true;
		}
		return false;
	}

	private void pruefe(Kunde kunde) throws PflichtfeldException {
		Set<ConstraintViolation<Kunde>> fehler = validator.validate(kunde);
		for (ConstraintViolation<Kunde> constraintViolation : fehler) {
			System.out.println("Fehler bei der Validierung: " + constraintViolation.getMessage());
		}

		if (nullOrEmpty(kunde.getNachname())) {
			throw new PflichtfeldException("Name fehlt!");
		}
		if (nullOrEmpty(kunde.getVorname())) {
			throw new PflichtfeldException("Vorname fehlt!");
		}
	}

	@Override
	public Kunde getSingle(String suchBegriff) {
		return kundenDao.getSingle(suchBegriff);
	}

	@Override
	public Rechnung erzeugeRechnung(Rechnung rechnung) {
		Rechnung tempRechnung = rechnungDao.save(rechnung);
		return tempRechnung;
	}

	@Override
	public void setzteKundeInRechnung(Rechnung rechnung, String kundenMail) {
		Kunde kunde = kundenDao.getSingle(kundenMail);
		rechnung = rechnungDao.getById(rechnung.getId());
		rechnung.setKunde(kunde);
		rechnungDao.update(rechnung);

	}
}
