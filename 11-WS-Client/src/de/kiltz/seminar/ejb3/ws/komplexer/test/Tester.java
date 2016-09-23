package de.kiltz.seminar.ejb3.ws.komplexer.test;

import de.kiltz.seminar.ejb3.ws.komplexer.DomainObject;
import de.kiltz.seminar.ejb3.ws.komplexer.DomainService;
import de.kiltz.seminar.ejb3.ws.komplexer.DomainServiceImplService;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DomainServiceImplService erzeuger = new DomainServiceImplService();
		DomainService service = erzeuger.getDomainServiceImplPort();
		DomainObject obj = service.getDomainObject();
		System.out.println(obj.getText());

	}

}
