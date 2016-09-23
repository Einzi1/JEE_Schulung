package de.kiltz.seminar.ejb3.jpa.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import de.kiltz.seminar.ejb3.jpa.dao.SammlungDAO;
import de.kiltz.seminar.ejb3.jpa.domain.Sammlung;
import de.kiltz.seminar.ejb3.jpa.entity.SammlungEntity;
import de.kiltz.seminar.ejb3.jpa.framework.SammlungsAssembler;

@Stateless(name = "SammlungService", mappedName = "ejb/SammlungService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SammlungServiceImpl implements SammlungService {

	@EJB
	private SammlungDAO sammlungDAO;

	public String teste() {
		StringBuffer meldung = new StringBuffer();
		SammlungEntity s = new SammlungEntity();
		s.setDatum(new Date());
		s.setText("EinstiegsText");
		s.setZahl(1);
		// Objekt speichern
		Integer id = sammlungDAO.save(s);
		meldung.append("Neues Objekt id: " + id);
		s.setId(id);

		// Objekt ändern

		s.setText("Ganz Neuer Text");
		s.setZahl(2);
		sammlungDAO.update(s);

		// Objekt suchen
		s = sammlungDAO.getById(s.getId());
		meldung.append("\nGefunden: " + s.getText() + "\n" + s.getZahl());
		meldung.append("\nAltes Objekt id: " + id);

	//	sammlungDAO.delete(id);
		meldung.append("\nNach dem Löschen: " + sammlungDAO.getById(id));
		System.out.println("Teste: " + meldung);
		return meldung.toString();
	}

	public Sammlung neu(Sammlung s) {
		SammlungEntity ent = SammlungsAssembler.toEntity(s);
		Integer id = sammlungDAO.save(ent);
		ent = sammlungDAO.getById(id);
		s = SammlungsAssembler.toDomain(ent);
		return s;
	}

	@Override
	public Sammlung hole(Integer id) {
		SammlungEntity entity = sammlungDAO.getById(id);
		Sammlung ret = SammlungsAssembler.toDomain(entity);
		return ret;
	}

	@Override
	public List<Sammlung> suche(String s) {
		List<SammlungEntity> daos = sammlungDAO.suche(s);
		List<Sammlung> ret = SammlungsAssembler.toDomain(daos);
		return ret;
	}

}
