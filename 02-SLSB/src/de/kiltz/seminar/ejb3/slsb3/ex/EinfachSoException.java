package de.kiltz.seminar.ejb3.slsb3.ex;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EinfachSoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EinfachSoException(String message) {
		super(message);
	}

}
