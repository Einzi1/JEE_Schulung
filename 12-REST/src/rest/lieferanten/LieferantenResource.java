package rest.lieferanten;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Produces("application/xml")
@Path("/Lieferanten")
public class LieferantenResource {

    LieferantenService service;
    
    public LieferantenResource() {
        service = EdelFactory.getLieferantenService();
    }


    /**
     * Lesen eines Lieferanten. Rückgabe des Lieferanten in XML.
     * 
     * Nutzt die Service Methode:
     * public Lieferant getByNr(String nr) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten/l321 
     */
    @GET
    @Path("{nr}")
    public Lieferant getLieferant(@PathParam("nr") String liefNr) 
                throws Exception 
    {
        Lieferant l = service.getByNr(liefNr);
        if ( l == null )
        {
            throw new WebApplicationException(404);
        }
        return l;
    }
    /**
     * Lesen eines Lieferanten. Rückgabe des Lieferanten mit Hilfe 
     * eines eigenen Providers.
     * 
     * Nutzt die Service Methode:
     * public Lieferant getByNr(String nr) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten/l321 -H "ACCEPT:application/de.traudel.lieferanten+xml"
     */
    @GET
    @Path("{nr}")
    @Produces("application/de.traudel.lieferanten+xml")
    public Lieferant getLieferantPro(@PathParam("nr") String liefNr) 
        throws Exception
    {
        Lieferant l = service.getByNr(liefNr);
        if ( l == null )
        {
            throw new WebApplicationException(404);
        }
        return l;
    }
    
    /**
     * Lesen eines Lieferanten. Rückgabe des Lieferanten als Text.
     * 
     * Nutzt die Service Methode:
     * public Lieferant getByNr(String nr) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten/l321 -H "ACCEPT:text/plain"
     */
    @GET
    @Path("{nr}")
    @Produces("text/plain")
    public String getLieferantString(@PathParam("nr") String liefNr) 
        throws Exception
    {
        Lieferant l = service.getByNr(liefNr);
        if ( l == null )
        {
            throw new WebApplicationException(404);
        }
        return l.toString();
    }

    /**
     * Erzeugen eines neuen Lieferanten. 
     * Übergabe der Parameter als Formularinhalte.
     * 
     * Nutzt die Service Methode:
     * public void neu(Lieferant l) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten 
     *                                   -d name=Neu -d nr=l322 
     * 
     */
  
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response neu(@FormParam("name") String name, 
            @FormParam("nr") String nr)
    {
        Lieferant l = new Lieferant(nr, name);
        Response ret = null;
        try {
            service.create(l);
            ret = Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ret = Response.status(400).
                entity(e.getMessage()+"\n").
                type("text/plain").build();
        }
        return ret;
    }
    /**
     * Aktualisiert einenLieferanten. 
     * Übergabe der Parameter als Formularinhalte.
     * 
     * Nutzt die Service Methode:
     * public void update(Integer id, Lieferant l) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten/1 -d name=Neu -d nr=l322 
     * 
     */
    @POST
    @Path("{id}")
    @Consumes("application/x-www-form-urlencoded")
    public Response update(@PathParam("id") Integer id, MultivaluedMap<String, String> formParams)
    {
        Lieferant l = new Lieferant(formParams.getFirst("nr"), formParams.getFirst("name"));
        Response ret = null;
        try {
            service.update(id, l);
            ret = Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(400);
        }
        return ret;
    }    
//  public void delete(Integer id) throws Exception;
    
    /**
     * Suchen von Lieferanten. Rückgabe des Ergebnisses in XML.
     * 
     * Nutzt die Service Methode:
     * public List<Lieferant> getByName(String name) throws Exception;
     * 
     * Test mit 
     * curl -i http://127.0.0.1:8080/rs/services/Lieferanten?s=farms 
     * 
     */
    @GET
    public List<Lieferant> getLieferanten(
            @QueryParam("s") String suchbegriff) throws Exception
    {
        
        return service.getByName(suchbegriff);
    }
}
