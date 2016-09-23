package de.kiltz.seminar.ejb3.tx;

import javax.naming.InitialContext;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class KontoTester {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		InitialContext ctx = new InitialContext();
		KontoService service = (KontoService) ctx.lookup(EJBTools.machJNDIName(KontoService.class));
		service.init();
		service.neuesKonto("1", 10);
		service.neuesKonto("2", 10);
		service.buche("1", "2", 5);
		System.out.println("Kontostände: 1: " + service.holeKontostand("1") + "\t2:" + service.holeKontostand("2"));

		try {
			service.buche("1", "2", 45);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Kontostände: 1: " + service.holeKontostand("1") + "\t2:" + service.holeKontostand("2"));

	}

}
