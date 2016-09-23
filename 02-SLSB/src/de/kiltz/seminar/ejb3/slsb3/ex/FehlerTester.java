package de.kiltz.seminar.ejb3.slsb3.ex;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class FehlerTester {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		FehlerMacher service = (FehlerMacher) context.lookup(EJBTools.machJNDIName(FehlerMacher.class));
		System.out.println("ApplicationFehler");
		try {
			service.machApplicationFehler();
		} catch (Exception e) {
			 e.printStackTrace();
		}

		// System.out.println("SystemFehler");
		// service.machSystemFehler();
		System.out.println("checked SystemFehler");
		service.machCheckedSystemFehler();
	}

}
