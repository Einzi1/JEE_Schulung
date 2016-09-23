package features.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LaengenPruefer {

	private static final boolean verbose = true;

	public List<String> pruefe(Object o) {
		List<String> fehler = new ArrayList<String>();
		List<Field> annoFelder = getLaengenFelder(o);
		sage(annoFelder.size() + " Felder gefunden!");

		try {
			for (Field feld : annoFelder) {
				feld.setAccessible(true);
				Laenge anno = feld.getAnnotation(Laenge.class);
				String inhalt = (String) feld.get(o);
				String name = feld.getName();
				sage("Prüfe Feld '" + name + "' mit maximale Länge: " + anno.max() + " Inhalt: '" + inhalt + "' ("
						+ inhalt.length() + " Zeichen)");
				if (inhalt != null && inhalt.length() > anno.max()) {
					sage("Feld '" + name + "' ist zu lang!");
					fehler.add(name);
				}
			}
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
		}
		return fehler;
	}

	private List<Field> getLaengenFelder(Object o) {
		List<Field> annoFelder = new ArrayList<Field>();
		Field[] felder = o.getClass().getDeclaredFields();
		for (int i = 0; i < felder.length; i++) {
			if (felder[i].isAnnotationPresent(Laenge.class)) {
				annoFelder.add(felder[i]);
			}
		}

		return annoFelder;

	}

	private void sage(String txt) {
		if (verbose) {
			System.out.println(txt);
		}

	}
}
