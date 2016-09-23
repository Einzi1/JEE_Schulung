package de.webservice.publisher;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "InfoserviceService", name = "InfoserviceGen")
public class InfoserviceImpl implements InfoService {

	@Override
	@WebMethod(operationName = "getTime")
	public String getTime() {
		return new Date(System.currentTimeMillis()).toString();
	}

	@Override
	@WebMethod(operationName = "getFreeMemory")
	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	@Override
	@WebMethod(operationName = "getUser")
	public String getUser() {
		return System.getProperty("user.name");
	}

}
