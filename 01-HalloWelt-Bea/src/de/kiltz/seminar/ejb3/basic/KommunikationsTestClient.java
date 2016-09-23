package de.kiltz.seminar.ejb3.basic;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class KommunikationsTestClient {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {

		// Kontext erzeugen
		Properties props = new Properties();

		props.put("java.naming.factory.initial",
				"weblogic.jndi.WLInitialContextFactory");
		props.put("java.naming.provider.url", "t3://127.0.0.1:7001");

		InitialContext context = new InitialContext(props);

		Object o = context.lookup("ejb/KommunikationsTest#"+ KommunikationsTest.class.getName());
		KommunikationsTest service = (KommunikationsTest) o;

		System.out.println(service.ping("sag Was Großes"));
	}

}
