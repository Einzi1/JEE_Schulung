package rest.basic;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Basic")
public class KommunikationsTestResource {

    @MatrixParam("info")
    @DefaultValue("Life")
    @Encoded
    private String info;
    /**
     * Testmethode für Überprüfung der POST-Kommunikation
     * Test mit:
     * curl -i http://127.0.0.1:8080/rs/services/Basic/ping -d txt=test
     * 
     * @param txt
     * @return
     */
    @POST
    @Path("ping")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
    public Response pingPost(@FormParam("txt") String txt)
    {
        if (info != null)
        {
        	txt = txt + " ("+info+")";
        }
        return Response.ok(txt.toUpperCase(), MediaType.TEXT_PLAIN).build();
    }
    /**
     * Testmethode für Überprüfung der Kommunikation
     * @param txt
     * @return
     */
    @GET
    @Path("ping")
    @Produces( MediaType.TEXT_PLAIN)
    public Response ping(@QueryParam("text") String text)
    {
        return Response.ok(text.toUpperCase(), MediaType.TEXT_PLAIN).build(); 
    }
    /**
     * Testmethode für Überprüfung der Kommunikation
     * @param txt
     * @return
     */
    @GET
    @Path("ping")
    @Produces("text/html")
    public String pingHTML(@QueryParam("s") String txt)
    {
        return "<h2>"+txt.toLowerCase()+"</h2>";
    }
    /**
     * Testmethode für Überprüfung der Kommunikation
     * @param txt
     * @return
     */
    @POST
    @Path("formPing")
    @Produces("text/html")
    @Consumes("application/x-www-form-urlencoded")
    public String pingForm(@FormParam("x") String x, @FormParam("y") String y)
    {
        
        return "Eingaben x: "+x+ " y: "+y ;
    }

    @GET
    @Path("images/{image}")
    @Produces("image/*")
    public Response getImage(@PathParam("image") String image) {
        File f = new File(image);

        if (!f.exists()) {
            throw new WebApplicationException(404);
        }

        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

  

}
