package de.kiltz.seminar.ejb3.slsb2.local;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "EntfernterDienst", mappedName = "ejb/EntfernterDienst")
public class EntfernterDienstImpl implements EntfernterDienst {

	@EJB
	private LokalerDienst locService;

	public EntfernterDienstImpl() {
		System.out.println("Konstruktor: " + locService);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("PostConstruct: " + locService);
	}

	public String ping(String text) {

		return "vom Entfernten und ein " + locService.lokaleMethode() + " " + text;
	}

}
