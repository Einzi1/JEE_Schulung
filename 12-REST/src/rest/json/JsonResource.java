package rest.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/json")
public class JsonResource {

	private static DatenObjekt obj;

	@GET
	@Produces("application/json")
	public DatenObjekt getJson() {

		obj = new DatenObjekt();
		obj.populate();
		System.out.println("Sende Objekt:\n" + obj.getGleitZahl());
		return obj;
	}

	@GET
	@Produces("application/xml")
	public DatenObjekt getXML() {

		obj = new DatenObjekt();
		obj.populate();
		System.out.println("Sende Objekt:\n" + obj.getGleitZahl());
		return obj;
	}

	@PUT
	@Consumes("application/json")
	public void setJson(DatenObjekt obj) throws Exception {
		System.out.println("Empfange Objekt:\n" + obj.getGleitZahl());

	}

}
