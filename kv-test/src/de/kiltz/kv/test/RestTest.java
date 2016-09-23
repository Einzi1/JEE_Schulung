package de.kiltz.kv.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.kiltz.kv.domain.Kunde;

public class RestTest {

	final static String UrlBase = "http://127.0.0.1:8080/kvRest/rs/";
	private Client c;

	@Test
	public void pingTest() {
		c = new Client();
		WebResource wr = c.resource(UrlBase);
		String ping = wr.path("/ping").get(String.class);
		assertNotNull(ping);
	}

	@Test
	public void kundenTest() {
		c = new Client();
		WebResource wr = c.resource(UrlBase);
		Kunde k = new Kunde("Rest-Kunde");
		wr.type("application/json").put(k);

	}
}
