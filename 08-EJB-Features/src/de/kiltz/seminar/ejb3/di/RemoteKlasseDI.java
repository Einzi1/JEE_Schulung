package de.kiltz.seminar.ejb3.di;

import javax.ejb.Remote;

@Remote
public interface RemoteKlasseDI {

	String tuWas(String txt);
}
