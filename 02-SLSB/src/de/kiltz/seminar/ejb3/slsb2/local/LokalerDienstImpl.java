package de.kiltz.seminar.ejb3.slsb2.local;

import javax.ejb.Stateless;

@Stateless(name = "LokalerDienst")
public class LokalerDienstImpl implements LokalerDienst {

	public String lokaleMethode() {

		return "Lokaler text";
	}

}
