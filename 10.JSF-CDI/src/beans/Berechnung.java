package beans;

public class Berechnung {

	private double z1;
	private double z2;
	private double erg;
	
	public Berechnung() {
	}

	
	
	public Berechnung(double zahl1, double zahl2) {
		super();
		this.z1 = zahl1;
		this.z2 = zahl2;
		this.erg = zahl1 + zahl2;
	}



	public double getZ1() {
		return z1;
	}



	public void setZ1(double z1) {
		this.z1 = z1;
	}



	public double getZ2() {
		return z2;
	}



	public void setZ2(double z2) {
		this.z2 = z2;
	}



	public double getErg() {
		return erg;
	}



	public void setErg(double erg) {
		this.erg = erg;
	}

}
