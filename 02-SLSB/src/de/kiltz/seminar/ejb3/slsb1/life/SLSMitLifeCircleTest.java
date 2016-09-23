package de.kiltz.seminar.ejb3.slsb1.life;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class SLSMitLifeCircleTest {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		// Kontext erzeugen
		/* ersetzt durch jndi.properties:
		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "localhost:1099");
		InitialContext context = new InitialContext(props);
		*/
		InitialContext context = new InitialContext();

		//Referenz auf den Stub holen
		
		String jndiName = EJBTools.machJNDIName(SLSMitLifeCircle.class);
		System.out.println(jndiName);
		SLSMitLifeCircle service = (SLSMitLifeCircle)context.lookup(jndiName );
		
		System.out.println(service.ping("den Fisch"));

	}

}
