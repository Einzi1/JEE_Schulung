package de.kiltz.seminar.framework;


public class EJBTools {
	private static final String APP_NAME = ""; // Name EAR-File
	private static final String MODUL_NAME = "EJB3JPA"; // Name Jar-File

	@SuppressWarnings("rawtypes")
	public static String machJNDIName(Class klasse) {
		String beanName = klasse.getSimpleName();
		// String beanName = KommunikationsTestImpl.class.getSimpleName();
		String viewClassName = klasse.getName();
		return "ejb:/" + APP_NAME + MODUL_NAME + "/" + beanName + "!" + viewClassName;
	}

}
