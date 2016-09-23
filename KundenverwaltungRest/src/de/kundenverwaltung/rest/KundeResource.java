package de.kundenverwaltung.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import de.kundenverwaltung.Kunde;
import de.kundenverwaltung.KundenService;
import de.kundenverwaltung.PflichtfeldException;

@Stateless
@Path("/Kunde")
public class KundeResource {

	@EJB
	private KundenService service;

	@GET
	@Path("holeKunde")
	@Produces("application/xml")
	public Response getKunde(@QueryParam(value = "id") long id) {

		Kunde kunde = service.holeKunde(id);

		KundeXml kundeXml = new KundeXml(id, kunde.getVorname(), kunde.getNachname(), kunde.getEmail());

		return Response.ok().entity(kundeXml).build();
	}

	@POST
	@Path("/neuerKunde")
	public Response putKunde(@QueryParam(value = "vorname") String vorname,
			@QueryParam(value = "nachname") String nachname, @QueryParam(value = "email") String email) {

		try {
			service.neuerKunde(new Kunde(vorname, nachname, email));
		} catch (PflichtfeldException e) {
			e.printStackTrace();
		}

		return Response.ok().entity("Kunde wurde hinzugefügt").build();

	}

	@POST
	@Path("/neuerKunde")
	@Consumes("application/xml")
	public Response putKunde(KundeXml kundeXml) {

		try {
			service.neuerKunde(new Kunde(kundeXml.getVorname(), kundeXml.getNachname(), kundeXml.getEmail()));
		} catch (PflichtfeldException e) {
			e.printStackTrace();
		}

		return Response.ok().entity("Kunde wurde hinzugefügt").build();

	}

}
