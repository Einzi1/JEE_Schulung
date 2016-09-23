package de.kiltz.seminar.ejb3.sfsb.merker;

public interface Merker {

	/**
	 * Gibt den Namen zurück
	 */
	public String getName();

	/**
	 * Setzt den Namen
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Stateful-SessionBean ist zu Ende - kann entsorgt werden.
	 */
	public void beende();
}
