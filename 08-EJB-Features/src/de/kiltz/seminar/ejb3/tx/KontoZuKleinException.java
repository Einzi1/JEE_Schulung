package de.kiltz.seminar.ejb3.tx;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class KontoZuKleinException extends Exception {

	private static final long serialVersionUID = 1L;

	public KontoZuKleinException(String msg) {
		super(msg);
	}

}
