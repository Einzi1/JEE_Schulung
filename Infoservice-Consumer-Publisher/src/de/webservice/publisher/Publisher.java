package de.webservice.publisher;

import javax.xml.ws.Endpoint;

public class Publisher {

	private final static String ADRESSE = "http://localhost:9090/infoservice";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Starte endpoint unter: " + ADRESSE);
		System.out.println("Die WSDL gibt es unter: " + ADRESSE + "?wsdl");

		InfoService infoservice = new InfoserviceImpl();

		Endpoint.publish(ADRESSE, infoservice);

	}

}
