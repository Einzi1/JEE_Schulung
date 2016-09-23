package de.kiltz.seminar.ejb3.tools;

public class EJBTools {
	private static final String APP_NAME = ""; // Name EAR-File
	private static final String MODUL_NAME = "EJB3SemiSLSB/"; // Name Jar-File
	private static final String DISTINCT_NAME = ""; // Zusatz in Konfig für
													// JBoss7

	public static String machJNDIName(Class klasse) {
		String beanName = klasse.getSimpleName();
		String viewClassName = klasse.getName();
		return "ejb:/" + APP_NAME + MODUL_NAME + DISTINCT_NAME + beanName + "!" + viewClassName;// +
																								// "?stateless";
	}

}
