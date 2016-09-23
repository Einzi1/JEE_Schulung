package de.kiltz.seminar.ejb3.sfsb.merker;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

@Stateful(name = "Merker", mappedName = "ejb/Merker")
@Remote(Merker.class)
// @Interceptors(LogInterceptor.class)
public class MerkerImpl implements Merker {

	private static String passiver = "";
	private static String aktive = "";

	private String name;

	@Override
	@Remove
	public void beende() {
		System.out.println("Beende " + name);
	}

	@Override
	public void setName(String name) {
		this.name = name;
		System.out.println("Neuer Name: " + name);

	}

	@Override
	public String getName() {
		return name + " (" + passiver + "/" + aktive + ")";
	}

	@PostActivate
	public void aktiviert(InvocationContext ctx) {
		aktive = name;
		System.out.println("\n\n*******************\nPostActivate " + getName());
	}

	@PrePassivate
	public void passiviert(InvocationContext ctx) {
		passiver = name;
		System.out.println("\n\n*******************\nPrePassivate " + getName());
	}

}
