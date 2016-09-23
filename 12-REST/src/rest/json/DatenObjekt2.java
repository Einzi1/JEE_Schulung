package rest.json;

public class DatenObjekt2  
{
	private String txt;
	private int zahl;
	private float gleitZahl;
	
	public DatenObjekt2() {
	}
	public void populate()
	{
        double d = Math.random();
        setTxt("Text: "+d);
        setZahl((int)(d*6+1));
        setGleitZahl((float)d); 
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

}
