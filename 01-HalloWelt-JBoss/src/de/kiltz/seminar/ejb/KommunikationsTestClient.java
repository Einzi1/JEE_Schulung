package de.kiltz.seminar.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class KommunikationsTestClient {

	private static final ServerName SERVER_NAME = ServerName.JBOSS7;
	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {

		InitialContext context = holeContext();
		String jndiName = holeJNDIName();
		
		KommunikationsTest service = (KommunikationsTest) context
				.lookup(jndiName);
		System.out.println(service.getClass().getName());
		System.out.println(service.ping("kleiner Test "));
		
	}

	private static String holeJNDIName() {
		String name = null; 
		switch (SERVER_NAME) {
		case JBOSS5:
			name = "KommunikationsTest/remote";
			break;
		case JBOSS7:
			String appName = ""; // Name EAR-File
			String modulName = "KommunikationsTest"; // Name Jar-File
			String beanName = "KommunikationsTest";
			// String beanName = KommunikationsTestImpl.class.getSimpleName();
			String viewClassName = KommunikationsTest.class.getName();
			
			name = "ejb:" + appName + "/" + modulName + "/"
					+ beanName + "!" + viewClassName
					;
			name = "ejb:/KommunikationsTest/KommunikationsTest!de.kiltz.seminar.ejb.KommunikationsTest";
			System.out.println(name);
			break;
		case GLASSFISH:
			name = KommunikationsTest.class.getName();
			break;
		}
		return name;
	}

	private static InitialContext holeContext() throws NamingException {
		Properties props = new Properties();
		switch (SERVER_NAME) {
		case JBOSS5:
			props.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			props.put("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");
			props.put("java.naming.provider.url", "127.0.0.1:1099");
			break;
		case JBOSS7:
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			break;
		case GLASSFISH:
//			props.put(Context.URL_PKG_PREFIXES,
//					"com.sun.enterprise.naming.impl.SerialInitContextFactory");
			break;
		}
		
		return new InitialContext(props);
	}

}

enum ServerName {
	JBOSS5, JBOSS7, GLASSFISH;
}
