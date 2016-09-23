package rest.json.test;

import rest.json.DatenObjekt;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


/**
 *
 */
public class RestTest {

	final static String UrlBase = "http://127.0.0.1:8080/rs/services/json";

	public static void main(String[] args) {
		try {
			// erzeuge Client:

			// ClientConfig cc = new DefaultClientConfig();
			// Client c = Client.create(cc);

			Client c = new Client();
			WebResource wr = c.resource(UrlBase);

			// Hole das Objekt

			System.out.println("Hole Objekt...");
			// DatenObjekt obj =
			// wr.accept("application/json").get(DatenObjekt.class);
			DatenObjekt obj = wr.accept("application/xml").get(
					DatenObjekt.class);
			System.out.println(String.format("Rückgabe:\n%s", obj
					.getGleitZahl()));
			System.out.println("-----");

			wr.type("application/json").put(obj);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
