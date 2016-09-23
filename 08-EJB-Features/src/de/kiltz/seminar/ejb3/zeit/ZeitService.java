package de.kiltz.seminar.ejb3.zeit;

import javax.ejb.Remote;
import javax.ejb.Timer;

@Remote
public interface ZeitService {

	String startTimer(long sekunden, boolean intervall);

	String stopTimer();

	void doIt(Timer t);
}
