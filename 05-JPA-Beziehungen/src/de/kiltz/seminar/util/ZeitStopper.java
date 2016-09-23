package de.kiltz.seminar.util;

public class ZeitStopper {

	private long start;

	public ZeitStopper() {
		start = System.currentTimeMillis();
	}

	public String stop() {
		long zeit = System.currentTimeMillis() - start;
		return zeit + " ms.";
	}
}
