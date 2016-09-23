package de.kiltz.seminar.ejb3.slsb3.ex;

import javax.ejb.Stateless;

@Stateless(name = "FehlerMacher", mappedName = "ejb/FehlerMacher")
public class FehlerMacherImpl implements FehlerMacher {

	public void machApplicationFehler() throws EinfachSoException {
		// Eine normale checked Exception
		throw new EinfachSoException("Ein Fehler");

	}

	@SuppressWarnings("null")
	public void machSystemFehler() {
		// Eine Runtime-Ex
		String nuller = null;
		// NullPointerException extends RuntimeException
		nuller.length();

	}

	public void machCheckedSystemFehler() {
		// Verpacken eines Checked-Systemfehlers

		try {
			throw new Exception("checked SystemFehler");
		} catch (Exception e) {
			// EJBException extends RuntimeException
			throw new RuntimeException(e);
		}

	}

}
