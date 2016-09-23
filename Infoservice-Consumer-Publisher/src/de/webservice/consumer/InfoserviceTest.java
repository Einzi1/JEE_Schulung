package de.webservice.consumer;

import de.webservice.publisher.InfoserviceGen;
import de.webservice.publisher.InfoserviceService;

public class InfoserviceTest {

	public static void main(String[] args) {

		InfoserviceService service = new InfoserviceService();
		InfoserviceGen infoserviceGen = service.getInfoserviceGenPort();
		System.out.println("User: " + infoserviceGen.getUser());
		System.out.println("Datum: " + infoserviceGen.getTime());
		System.out.println("Free Memory: " + infoserviceGen.getFreeMemory());
	}
}
