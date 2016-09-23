package beans;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Produces;

public class CDIProducer {

	@Produces
	@UserList
	public Map<String, String> getUsers() {
		Map<String, String> liste = new HashMap<>();
		liste.put("Hägar", "Geheim");
		liste.put("Honi", "GanzGeheim");
		
		return liste;
		
	}
}
