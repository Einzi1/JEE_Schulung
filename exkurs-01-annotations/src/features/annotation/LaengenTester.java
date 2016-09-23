package features.annotation;

import java.util.List;

public class LaengenTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LaengenNutzer obj = new LaengenNutzer();
		obj.setEgalerString("Egal");
		obj.setKurzerString("Mehr als 10 Zeichen");
		obj.setMittlererString("Weniger wie 25 Zeichen");
		obj.setLangerString("255 Zeichen sind möglich!");

		LaengenPruefer pruefer = new LaengenPruefer();
		List<String> fehler = pruefer.pruefe(obj);
		System.out.println("Fehlerhafte Felder sind:");
		for (String name : fehler) {
			System.out.println(" - " + name);
		}
	}

}
