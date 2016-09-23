package rest.basic.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * curl -i http://127.0.0.1:8080/rs/services/Basic/ping -d txt=test
 * 
 */
public class Tester {

	final static String UrlBase = "http://127.0.0.1:8080/rs/services/Basic/";

	public static void main(String[] args) {
		try {
			// erzeuge Client:

			ClientConfig cc = new DefaultClientConfig();
			Client c = Client.create(cc);
			WebResource wr = c.resource(UrlBase);

			System.out.println("Führe Ping aus...");
			wr = wr.queryParam("text", "Ein Test");
			String ret = wr.path("ping/").accept("text/plain")
					.get(String.class);
			System.out.println(String.format("Rückgabe: :\n%s", ret));

		} catch (Exception e) {
			System.out.println("TEST FAILED! :-(");
			e.printStackTrace(System.out);
		}
	}
}
