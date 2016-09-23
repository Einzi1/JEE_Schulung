package de.kiltz.seminar.ejb3.slsb4.aop;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class AspectTester {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext ctx = new InitialContext();
		AspectService service = (AspectService) ctx.lookup(EJBTools.machJNDIName(AspectService.class));

		System.out.println(service.ping("Hallo Aspect!"));
	}

}
