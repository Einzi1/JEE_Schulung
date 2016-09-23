package rest.basic;

public class Daten {

	private String text;
	private int zahl;
	@Override
	public String toString() {
		return "Daten [text=" + text + ", zahl=" + zahl + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getZahl() {
		return zahl;
	}
	public void setZahl(int zahl) {
		this.zahl = zahl;
	}
}
