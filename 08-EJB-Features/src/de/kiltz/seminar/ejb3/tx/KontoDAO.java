package de.kiltz.seminar.ejb3.tx;

import javax.ejb.Local;

@Local
public interface KontoDAO {

	Konto getByNr(String nr) throws Exception;

	void deleteAll();

	// Die allgemeinen Methoden
	Konto getById(Integer id) throws Exception;

	Konto save(Konto o) throws Exception;

	void update(Konto o) throws Exception;

	void deleteById(Konto o) throws Exception;

}