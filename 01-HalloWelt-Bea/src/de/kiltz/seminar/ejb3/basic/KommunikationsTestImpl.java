package de.kiltz.seminar.ejb3.basic;

import javax.ejb.Stateless;

@Stateless(name="KommunikationsTest", mappedName="ejb/KommunikationsTest")
public class KommunikationsTestImpl implements KommunikationsTest {

	public String ping(String txt) {
		System.out.println("Ping mit '"+txt+"'");
		return txt.toUpperCase();
	}

}
