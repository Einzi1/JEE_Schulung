package rest.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatenObjekt  
{
	private String txt;
	private int zahl;
	private float gleitZahl;
	private Date datum;
	private List<String> liste;
	private List<DatenObjekt2> komplexeListe;
	
	public DatenObjekt() {
	}
	public void populate()
	{
        double d = Math.random();
        setTxt("Text: "+d);
        setZahl((int)(d*6+1));
        setGleitZahl((float)d); 
        datum = new Date();
        liste = new ArrayList<String>();
        liste.add("Eins");
        liste.add("Zwei");
        liste.add("Drei");
        
        
        komplexeListe = new ArrayList<DatenObjekt2>();
        for (int i = 0; i < 3;++i)
        {
            DatenObjekt2 o2 = new DatenObjekt2();
            o2.populate();
            komplexeListe.add(o2);
        }
	    
	}
	
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public int getZahl() {
		return zahl;
	}
	public void setZahl(int zahl) {
		this.zahl = zahl;
	}
	public float getGleitZahl() {
		return gleitZahl;
	}
	public void setGleitZahl(float gleitZahl) {
		this.gleitZahl = gleitZahl;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public List<String> getListe() {
		return liste;
	}
	public void setListe(List<String> liste) {
		this.liste = liste;
	}
	public List<DatenObjekt2> getKomplexeListe() {
		return komplexeListe;
	}
	public void setKomplexeListe(List<DatenObjekt2> komplexeListe) {
		this.komplexeListe = komplexeListe;
	}
    
}
