package de.kiltz.seminar.ejb3.ws.basic;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(endpointInterface = "de.kiltz.seminar.ejb3.ws.basic.BasicWebService")
@Remote(BasicWebService.class)
public class BasicWebServiceImpl implements BasicWebService {

		
	public String ping(String txt) {

		return txt.toUpperCase();
	}

}
