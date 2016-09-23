package de.kiltz.kv.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.kiltz.kv.domain.Kunde;
import de.kiltz.kv.service.KundenVerwaltung;
import de.kiltz.kv.service.PflichtfeldException;

@Path("/")
@Stateless
public class KundenResource {

	@EJB
	private KundenVerwaltung service;

	@GET
	@Path("/ping")
	public String ping() {
		return "lebe..." + service;
	}

	@PUT
	@Produces({ "application/json", "application/xml" })
	@Consumes("application/json")
	public Kunde neuerKunde(Kunde k) throws PflichtfeldException {
		k = service.neuerKunde(k);
		return k;
	}

	@POST
	@Produces({ "application/json", "application/xml" })
	@Consumes("application/json")
	public Kunde aendereKunde(Kunde k) throws PflichtfeldException {
		k = service.aendereKunde(k);
		return k;
	}

	@GET
	@Path("/{id}")
	@Consumes("application/json")
	public Kunde holeKunde(@PathParam("id") Long id) {
		return service.holeKunde(id);
	}

	@DELETE
	@Path("/{id}")
	public void loescheKunde(@PathParam("id") Long id) {
		service.loescheKunde(id);
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	public List<Kunde> sucheKunde(@QueryParam("suche") String suchBegriff) {
		return service.sucheKunde(suchBegriff);
	}

}
