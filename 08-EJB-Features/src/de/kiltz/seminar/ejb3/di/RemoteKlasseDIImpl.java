package de.kiltz.seminar.ejb3.di;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name = "RemoteKlasseDI", mappedName = "ejb/RemoteKlasseDI")
public class RemoteKlasseDIImpl implements RemoteKlasseDI {

	@Resource
	private SessionContext sc;

	@EJB(beanName = "LocalKlasseDI")
	private LocalKlasseDI loc;

	@Override
	public String tuWas(String txt) {
		System.out.println("Hole Principal");
		Principal p = sc.getCallerPrincipal();
		System.out.println("Habe Principal " + p);

		return txt + " (" + loc.machWas() + ")";
	}

}
