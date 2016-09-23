package de.kiltz.seminar.ejb3.ws.basic.ejbclient;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

import de.kiltz.seminar.ejb3.ws.basic.BasicWebService;
import de.kiltz.seminar.ejb3.ws.basic.BasicWebServiceImplService;

@Stateless(name="ConsumerService", mappedName="ejb/ConsumerService")
public class ConsumerServiceImpl implements ConsumerService{

	@WebServiceRef
	private BasicWebServiceImplService erzeuger;
	
	private BasicWebService service;
	
	@PostConstruct
	private void init()
	{
		System.out.println("Erzeuge Service...");
		service = erzeuger.getBasicWebServiceImplPort();
		System.out.println("..."+service);
	}
	
	@Override
	public String test(String txt) {
		
		return service.ping(txt);
	}

}
