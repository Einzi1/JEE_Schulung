package de.kiltz.seminar.ejb3.slsb4.aop;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless(name = "AspectService", mappedName = "ejb/AspectService")
public class AspectServiceImpl implements AspectService {

	@Override
	@Interceptors({ InfoAspect.class })
	public String ping(String txt) {
		System.out.println("Ping mit " + txt);
		return txt.toUpperCase();
	}

}
