package de.kiltz.seminar.ejb3.zeit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@Stateless
public class ZeitServiceImpl implements ZeitService {

	private static DateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Resource
	private SessionContext sc;

	@Override
	@Timeout
	public void doIt(Timer t) {

		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.println("Tue was: " + df.format(new Date()));
		System.out.println(t.getInfo());

	}

	@Override
	public String startTimer(long sekunden, boolean intervall) {
		if (intervall) {
			sc.getTimerService().createTimer(1000 * sekunden, 1000 * sekunden, "Intervall");
		} else {
			sc.getTimerService().createTimer(1000L * sekunden, "Einzel-Timer");
		}
		DateFormat df = DateFormat.getDateTimeInstance();
		String txt = "Timer gestartet: " + df.format(new Date());
		System.out.println(txt);

		return txt;
	}

	@Override
	public String stopTimer() {
		Collection<Timer> liste = sc.getTimerService().getTimers();
		StringBuffer txt = new StringBuffer();
		for (Timer timer : liste) {
			txt.append("Entferne Timer : " + timer.getInfo());
			txt.append(" Restzeit: " + timer.getTimeRemaining() + "\n");
			timer.cancel();
		}
		return txt.toString();
	}

	// Parameter Timer ist optional
	// Persistent ist default: Alle verlorenen Timeouts (durch Server-Down)
	// werden nachgeholt.
	// s. Spezifikation Kap. 13
	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	private void alles30Sekunden(Timer t) {
		System.out.println("Timer läuft um " + df.format(new Date()));
	}
}
