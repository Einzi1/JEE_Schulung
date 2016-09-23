package de.kiltz.seminar.ejb3.ws.basic.ejbclient;

import javax.ejb.Remote;

@Remote
public interface ConsumerService {

	public String test(String txt);
}
