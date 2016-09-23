package de.webservice.publisher;

import javax.jws.WebService;

@WebService
public interface InfoService {

	public String getTime();

	public long getFreeMemory();

	public String getUser();

}
