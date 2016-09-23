package de.kiltz.seminar.ejb3.slsb2.local;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class LocalTester {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();

		// Referenz auf EJB holen
		EntfernterDienst service = (EntfernterDienst) context.lookup(EJBTools.machJNDIName(EntfernterDienst.class));

		System.out.println(service.ping("Der entfernte Test"));

	}

}
