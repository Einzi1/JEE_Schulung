package de.kiltz.seminar.ejb3.ws.basic.test;

import de.kiltz.seminar.ejb3.ws.basic.BasicWebService;
import de.kiltz.seminar.ejb3.ws.basic.BasicWebServiceImplService;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicWebServiceImplService erzeuger = new BasicWebServiceImplService();
		BasicWebService service = erzeuger.getBasicWebServiceImplPort();
		System.out.println(service.getClass().getName());
		System.out.println(service.ping("kleiner test"));

	}

}
