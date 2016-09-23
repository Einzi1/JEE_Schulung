package de.kiltz.seminar.ejb3.ws.basic.ejbclient;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class Tester {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();

		ConsumerService service = (ConsumerService)context.lookup(EJBTools.machJNDIName(ConsumerService.class));
		System.out.println(service.test("test"));
		
	}

}
