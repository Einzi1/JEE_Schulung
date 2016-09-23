package de.kiltz.seminar.ejb3.basic;

import javax.ejb.Remote;

@Remote
public interface KommunikationsTest {

	public String ping(String txt);
}
