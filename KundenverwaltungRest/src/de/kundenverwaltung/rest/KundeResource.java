package de.kundenverwaltung.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.kundenverwaltung.Kunde;
import de.kundenverwaltung.KundenService;

@Stateless
@Path("/Kunde")
public class KundeResource {

	@EJB
	private KundenService service;

	@GET
	@Path("holeKunde")
	@Produces("application/xml")
	public Response getKunde(@PathParam(value = "id") long id) {

		Kunde kunde = service.holeKunde(id);

		KundeXml kundeXml = new KundeXml(id, kunde.getVorname(), kunde.getNachname(), kunde.getEmail());

		return Response.ok().entity(kundeXml).build();
	}

	@PUT
	@Path("/neuerKunde")
	@Consumes("application/xml")
	public Response putKunde() {

		return Response.ok().entity("Kunde wurde hinzugefügt").build();

	}

}
