package de.kiltz.seminar.ejb3.slsb3.ex;

import javax.ejb.Remote;

@Remote
public interface FehlerMacher {

	void machApplicationFehler() throws EinfachSoException;

	void machSystemFehler();

	void machCheckedSystemFehler();
}
