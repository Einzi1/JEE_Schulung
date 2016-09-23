package de.kiltz.seminar.ejb;

import javax.ejb.Remote;

@Remote
public interface KommunikationsTest {

	public String ping(String txt);
}
