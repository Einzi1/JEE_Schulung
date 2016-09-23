package de.kiltz.seminar.ejb3.secur;

import javax.ejb.Remote;

@Remote
public interface SicherheitsDienst {

	String fuerAlle(String txt);

	String fuerUserUndAdmin(String txt);

	String fuerAdmins(String txt);
}
