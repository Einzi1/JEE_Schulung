package beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("rechner")
@SessionScoped
public class RechnerBean implements Serializable {

	private String zahl1;
	private String zahl2;
	
	private String ergebnis;

	private String info;
	
	private List<Berechnung> berechnungen;
	
	private static NumberFormat nf = NumberFormat.getInstance();
	
	@PostConstruct
	public void init() {
		berechnungen = new ArrayList<Berechnung>();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}

	public void rechne() {
		try {
			double z1 = nf.parse(zahl1).doubleValue();
			double z2 = nf.parse(zahl2).doubleValue();
			ergebnis = nf.format(z1 + z2);
			info = "Alle Zahlen ok";
			berechnungen.add(new Berechnung(z1, z2));
			
		} catch (ParseException e) {
			ergebnis = "0,00";
			info = e.getMessage();
		}
	}
	
	public String getZahl1() {
		return zahl1;
	}

	public void setZahl1(String zahl1) {
		this.zahl1 = zahl1;
	}

	public String getZahl2() {
		return zahl2;
	}

	public void setZahl2(String zahl2) {
		this.zahl2 = zahl2;
	}

	public String getErgebnis() {
		return ergebnis;
	}

	public void setErgebnis(String ergebnis) {
		this.ergebnis = ergebnis;
	}

	public List<Berechnung> getBerechnungen() {
		return berechnungen;
	}

	public void setBerechnungen(List<Berechnung> berechnungen) {
		this.berechnungen = berechnungen;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
