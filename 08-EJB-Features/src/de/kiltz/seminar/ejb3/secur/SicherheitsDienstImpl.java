package de.kiltz.seminar.ejb3.secur;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless(name = "SicherheitsDienst")
// @SecurityDomain("other")
public class SicherheitsDienstImpl implements SicherheitsDienst {

	@Resource
	private SessionContext sc;

	@Override
	// @DenyAll
	@PermitAll
	public String fuerAlle(String txt) {
		System.out.println("Für Alle");
		System.out.println("Hole Principal");
		Principal p = sc.getCallerPrincipal();
		System.out.println("\n\n*********\n");
		System.out.println("Habe Principal " + p);
		if (p != null) {
			System.out.print("Aufruf von " + p.getName());
			if (sc.isCallerInRole("admin")) {
				System.out.print(" (ist ein Admin)");
			}
		}
		System.out.println("\n\n*********\n");
		return "Alle " + txt.toUpperCase();
	}

	@Override
	@RolesAllowed({ "user", "admin" })
	public String fuerUserUndAdmin(String txt) {
		System.out.println("Für User");
		return "User " + txt.toUpperCase();
	}

	@Override
	@RolesAllowed("admin")
	public String fuerAdmins(String txt) {
		System.out.println("Für Admin");
		return "Admin " + txt.toUpperCase();
	}

}
