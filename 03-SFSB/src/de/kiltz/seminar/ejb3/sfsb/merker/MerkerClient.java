package de.kiltz.seminar.ejb3.sfsb.merker;

import javax.naming.InitialContext;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class MerkerClient {

	/**
	 * Benötigt folgende Änderungen in der standalone.xml im subsystem ejb3
                <stateful default-access-timeout="500" cache-ref="passivating"/>
	 *		und 
                <file-passivation-store name="file" max-size="5"/>
	 * 
	 */
	public static void main(String[] args) throws Exception {
		int anzahlInstanzen = 10;
		InitialContext context = new InitialContext();
		Merker[] instanzen = new Merker[anzahlInstanzen];
		for (int i = 0; i < anzahlInstanzen; ++i) {
			instanzen[i] = (Merker) context.lookup(EJBTools.machJNDIName(Merker.class));
			instanzen[i].setName("Nummer " + (i + 1));
			System.out.println(instanzen[i].getName());
			Thread.sleep(1000);
		}

		for (int i = 0; i < anzahlInstanzen; ++i) {
			System.out.println("Runde 2: " + instanzen[i].getName());
			Thread.sleep(1000);
		}

		for (int i = 0; i < anzahlInstanzen; ++i) {
			instanzen[i].beende();
		}

	}

}
