package rest.basic;

import java.text.DateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ajax")
public class AjaxResource {

	@GET
	@Path("/meldung.html")
	public String zeigeMeldung() {
		return "Eine Meldung " + DateFormat.getDateTimeInstance().format(new Date());
	}

	@POST
	@Path("/form")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String formularAuswertung(@FormParam("text") String text, @FormParam("zahl") int zahl,
			@FormParam("gleitZahl") double gleitZahl, @FormParam("einArray") String[] einArray,
			@FormParam("eineListe") String[] eineListe, @FormParam("wahrOderFalsch") boolean wahrOderFalsch) {
		StringBuffer txt = new StringBuffer(
				"Formi " + text + " / " + zahl + " / " + gleitZahl  + " / " + wahrOderFalsch);
		for (String s : einArray) {
			txt.append(" / Arr: "+s);
		}
		for (String s : eineListe) {
			txt.append(" / Liste: "+s);
		}
		System.out.println(txt);
		return txt.toString();
	}

}
