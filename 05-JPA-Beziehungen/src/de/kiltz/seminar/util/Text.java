package de.kiltz.seminar.util;

public class Text {

	public static String erzeugeZufallsText(int laenge, boolean nurBuchstaben) {
		StringBuffer zeichen = new StringBuffer();
		if (!nurBuchstaben) {
			// die Zahlen
			for (int i = 48; i < 58; ++i) {
				char c = (char) (i);
				zeichen.append(c);
			}
		}
		// Buchstaben
		for (int i = 97; i < 123; ++i) {
			char c = (char) (i);
			zeichen.append(c);
		}

		StringBuffer pw = new StringBuffer();
		for (int i = 0; i < laenge; ++i) {

			int pos = (int) (Math.random() * zeichen.length());
			char c = (char) zeichen.charAt(pos);
			pw.append(c);
		}

		return pw.toString();

	}

}
