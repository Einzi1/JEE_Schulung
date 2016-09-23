package de.kiltz.seminar.ejb3.slsb2.local;

import javax.ejb.Remote;

@Remote
public interface EntfernterDienst {

	String ping(String text);

}
