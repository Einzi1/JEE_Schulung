package ws;

import javax.xml.ws.Endpoint;

/**
 * Publischer als Tomcat oder Application-Server Ersatz. Recht zum Testen ;-)
 * 
 * @author fk
 *
 */
public class Publisher {

	private final static String ADRESSE = "http://localhost:9090/ws";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Starte endpoint unter: " + ADRESSE);
		System.out.println("Die WSDL gibt es unter: " + ADRESSE + "?wsdl");

		Kommunikation k = new KommunikationImpl();

		Endpoint.publish(ADRESSE, k);

	}

}
