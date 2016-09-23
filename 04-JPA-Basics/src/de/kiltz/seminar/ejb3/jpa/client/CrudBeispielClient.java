package de.kiltz.seminar.ejb3.jpa.client;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.kiltz.seminar.ejb3.jpa.domain.FunnyEnum;
import de.kiltz.seminar.ejb3.jpa.domain.Sammlung;
import de.kiltz.seminar.ejb3.jpa.service.SammlungService;
import de.kiltz.seminar.ejb3.tools.EJBTools;

public class CrudBeispielClient {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		String jndiName= EJBTools.machJNDIName(SammlungService.class);
		System.out.println(jndiName);
		SammlungService service = (SammlungService) context.lookup(jndiName);

		System.out.println(service.teste());

		Sammlung s = new Sammlung();
		s.setText("Neuer text");
		s.setZahl(12);
		s.setDatum(new Date());
		s.setFunZahl(Integer.valueOf(123));
		s.setFunEnum(FunnyEnum.HOCHZIEH);
		
		s = service.neu(s);
		System.out.println(s);

		Sammlung geholte = service.hole(s.getId());
		System.out.println(geholte);

		List<Sammlung> liste = service.suche("eu");
		for (Sammlung samml : liste) {
			System.out.println(samml.getId() + ": " + samml.getText());
		}

	}

}
