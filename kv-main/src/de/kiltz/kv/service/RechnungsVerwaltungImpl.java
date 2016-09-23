package de.kiltz.kv.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import de.kiltz.kv.aop.ZeitStopperAspect;
import de.kiltz.kv.dao.RechnungDao;
import de.kiltz.kv.domain.Position;
import de.kiltz.kv.domain.Rechnung;
import de.kiltz.kv.entity.RechnungEntity;
import de.kiltz.kv.entity.RechnungsAssembler;

@Stateless(name="RechnungsVerwaltung")
@Remote(RechnungsVerwaltung.class)
@Interceptors({ZeitStopperAspect.class})

public class RechnungsVerwaltungImpl implements RechnungsVerwaltung{

	@EJB
	private RechnungDao rechnungDao;
	
	@Override
	public Rechnung neueRechnung(Rechnung r) throws PflichtfeldException {
		
		pruefe(r);
		RechnungEntity r_neu = RechnungsAssembler.toEntity(r);
		System.out.println("********\r\tMöchte folgende Rechnung speichern: "+r_neu);
		Long id = rechnungDao.save(r_neu);
		r.setId(id);
		return r;
	}

	@Override
	public Rechnung aendereRechnung(Rechnung r) throws PflichtfeldException {
		pruefe(r);
		RechnungEntity r_neu = RechnungsAssembler.toEntity(r);
		rechnungDao.update(r_neu);
		r = RechnungsAssembler.toDomain(r_neu);
		return r;
	}

	@Override
	public Rechnung holeRechnung(Long id) {
		
		RechnungEntity re = rechnungDao.getById(id);
		Rechnung r = RechnungsAssembler.toDomain(re);
		
		return r;
	}

	@Override
	public void loescheRechnung(Long id) {
		rechnungDao.delete(id);
		
	}
	private void pruefe(Rechnung k) throws PflichtfeldException {
		if( k.getNummer() == null || k.getNummer().intValue() == 0) {
			throw new PflichtfeldException("Rechnungsnummer fehlt!");
		}
	}

	@Override
	public Rechnung addPosition(Position p, Long id) {
		RechnungEntity re = rechnungDao.getById(id);
		re.getPosition().add(RechnungsAssembler.toEntity(p));
		Rechnung r = RechnungsAssembler.toDomain(re);
		return r;
	}

	@Override
	public Rechnung loeschePosition(Position p, Long id) {
		RechnungEntity re = rechnungDao.getById(id);
		re.getPosition().remove(p);
		Rechnung r = RechnungsAssembler.toDomain(re);
		return r;
	}

}
