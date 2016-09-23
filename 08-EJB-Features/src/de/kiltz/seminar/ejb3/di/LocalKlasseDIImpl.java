package de.kiltz.seminar.ejb3.di;

import javax.ejb.Stateless;

@Stateless(name = "LocalKlasseDI")
public class LocalKlasseDIImpl implements LocalKlasseDI {

	@Override
	public String machWas() {
		return "Vom Local";
	}

}
