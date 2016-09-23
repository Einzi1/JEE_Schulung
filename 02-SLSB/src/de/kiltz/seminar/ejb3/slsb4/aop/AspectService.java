package de.kiltz.seminar.ejb3.slsb4.aop;

import javax.ejb.Remote;

@Remote
public interface AspectService {

	public String ping(String txt);
}
