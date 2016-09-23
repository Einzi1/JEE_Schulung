package de.kiltz.seminar.ejb3.di;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class LocalDITester {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws Exception {

		InitialContext context = new InitialContext();

		// Referenz holen
		RemoteKlasseDI service = (RemoteKlasseDI) context.lookup(EJBTools.machJNDIName(RemoteKlasseDI.class));

		System.out.println(service.tuWas("Für Remote "));

	}

}
