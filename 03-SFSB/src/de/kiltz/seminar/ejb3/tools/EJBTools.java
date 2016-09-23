package de.kiltz.seminar.ejb3.tools;

public class EJBTools {
	private static final String APP_NAME = ""; // Name EAR-File
	private static final String MODUL_NAME = "EJB3SemiSFSB"; // Name Jar-File

	public static String machJNDIName(Class klasse) {
		String beanName = klasse.getSimpleName();
		// String beanName = KommunikationsTestImpl.class.getSimpleName();
		String viewClassName = klasse.getName();
		return "ejb:/" + APP_NAME + MODUL_NAME + "/" + beanName + "!" + viewClassName + "?stateful";
	}

}
