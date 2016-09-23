package features.annotation;

public class LaengenNutzer {

	@Laenge
	private String langerString;

	@Laenge(max = 25)
	private String mittlererString;

	@Laenge(max = 10)
	private String kurzerString;

	private String egalerString;

	public String getLangerString() {
		return langerString;
	}

	public void setLangerString(String langerString) {
		this.langerString = langerString;
	}

	public String getMittlererString() {
		return mittlererString;
	}

	public void setMittlererString(String mittlererString) {
		this.mittlererString = mittlererString;
	}

	public String getKurzerString() {
		return kurzerString;
	}

	public void setKurzerString(String kurzerString) {
		this.kurzerString = kurzerString;
	}

	public String getEgalerString() {
		return egalerString;
	}

	public void setEgalerString(String egalerString) {
		this.egalerString = egalerString;
	}

	@Override
	public String toString() {
		return "LaengenNutzer [langerString=" + langerString + ", mittlererString=" + mittlererString
				+ ", kurzerString=" + kurzerString + ", egalerString=" + egalerString + "]";
	}

}
