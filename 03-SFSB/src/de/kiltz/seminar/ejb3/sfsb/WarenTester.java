package de.kiltz.seminar.ejb3.sfsb;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.tools.EJBTools;

public class WarenTester {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();

		//Referenz auf EJB holen
		Warenkorb service = (Warenkorb)context.lookup(EJBTools.machJNDIName(Warenkorb.class)); 
		
		service.legeInWarenkorb(5, "a1");
		service.legeInWarenkorb(5, "a2");
		service.entferneAusWarenkorb("a1");
		System.out.println(service.geheZurKasse());
		// nach Kasse ist SFSB nicht mehr ansprechbar
//		service.legeInWarenkorb(5, "a3");
		

	}

}
