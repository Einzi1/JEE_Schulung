package de.kiltz.seminar.ejb3.ws.basic;

import javax.jws.WebService;

@WebService
public interface BasicWebService {

	public String ping(String txt);
	
}
