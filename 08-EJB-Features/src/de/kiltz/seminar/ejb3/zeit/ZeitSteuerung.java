package de.kiltz.seminar.ejb3.zeit;

import javax.naming.InitialContext;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class ZeitSteuerung {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();
		ZeitService s = (ZeitService) ctx.lookup(EJBTools.machJNDIName(ZeitService.class));
		int sekunden = 10;
		// Intervall
//		System.out.println(s.startTimer(sekunden, true));
	 	System.out.println(s.stopTimer());
		// Einzel-Timer
		System.out.println(s.startTimer(sekunden, false));

	}

}
