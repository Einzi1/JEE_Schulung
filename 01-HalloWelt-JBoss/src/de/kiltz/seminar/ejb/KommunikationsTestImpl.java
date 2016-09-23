package de.kiltz.seminar.ejb;

import javax.ejb.Stateless;

@Stateless(name="KommunikationsTest")
public class KommunikationsTestImpl implements KommunikationsTest {

	@Override
	public String ping(String txt) {
		System.out.println("Ping: " + txt);
		return txt.toUpperCase();
	}

}
