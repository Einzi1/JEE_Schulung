package de.kiltz.seminar.ejb;

import java.util.Calendar;

import javax.ejb.Stateless;

@Stateless(name = "InfoService")
public class InfoServiceImpl implements InfoService {

	@Override
	public String getTime() {
		// Date date = new Date(System.currentTimeMillis());
		Calendar rightNow = Calendar.getInstance();
		String hour = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(rightNow.get(Calendar.MINUTE));
		String sec = Integer.toString(rightNow.get(Calendar.SECOND));
		return hour + ":" + min + ":" + sec;
	}

	@Override
	public String getFreeMemory() {
		return "42";
	}

	@Override
	public String getUser() {
		return "G. Ott";
	}

}
