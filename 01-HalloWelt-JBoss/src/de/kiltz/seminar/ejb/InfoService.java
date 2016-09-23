package de.kiltz.seminar.ejb;

import javax.ejb.Remote;

@Remote
public interface InfoService {
	public String getTime();

	public String getFreeMemory();

	public String getUser();
}
