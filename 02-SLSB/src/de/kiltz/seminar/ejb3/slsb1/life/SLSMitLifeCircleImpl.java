package de.kiltz.seminar.ejb3.slsb1.life;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name = "SLSMitLifeCircle", mappedName = "ejb/SLSMitLifeCircle")
@Remote(SLSMitLifeCircle.class)
public class SLSMitLifeCircleImpl implements SLSMitLifeCircle {

	@PostConstruct
	public void init() {
		System.out.println("Bin gerade frisch erzeugt worden...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Werde nicht mehr gebraucht und entsorgt...");
	}

	public String ping(String text) {
		System.out.println("SLSMitLifeCircle mit " + text);
		return "Danke für: " + text.toUpperCase();
	}

}
