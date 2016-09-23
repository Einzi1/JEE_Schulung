package de.kiltz.kv.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import de.kiltz.kv.aop.ZeitStopperAspect;
import de.kiltz.kv.dao.KundenDao;
import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.entity.KundeAssembler;
import de.kiltz.kv.entity.KundeEntity;

@Stateless(name="KundenVerwaltung")
@Remote(KundenVerwaltung.class)
@Interceptors({ZeitStopperAspect.class})
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class KundenVerwaltungImpl implements KundenVerwaltung{

	@EJB
	private KundenDao kundenDao;
	
	@Override
	public Kunde neuerKunde(Kunde k) throws PflichtfeldException {
		pruefe(k);
		KundeEntity kEnt = KundeAssembler.toEntity(k);
		kEnt = kundenDao.save(kEnt);
		return KundeAssembler.toDomain(kEnt);
	}

	@Override
	public Kunde aendereKunde(Kunde k) throws PflichtfeldException {
		pruefe(k);
		KundeEntity kEnt = KundeAssembler.toEntity(k);
		kEnt = kundenDao.update(kEnt);
		return KundeAssembler.toDomain(kEnt);
	}

	@Override
	public Kunde holeKunde(Long id) {
		
		return KundeAssembler.toDomain(kundenDao.getById(id));
	}

	@Override
	public void loescheKunde(Long id) {
		kundenDao.delete(id);
		
	}
	private void pruefe(Kunde k) throws PflichtfeldException {
		if( k.getName() == null || k.getName().isEmpty()) {
			throw new PflichtfeldException("Name fehlt!");
		}
	}

	@Override
	public List<Kunde> sucheKunde(String suchBegriff) {
		List<KundeEntity> liste = kundenDao.findByName(suchBegriff);
		return KundeAssembler.toDomainList(liste);
	}

}
