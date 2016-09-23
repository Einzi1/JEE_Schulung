package de.kiltz.seminar.ejb3.ws.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLCalendarTools {

	 private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(){
	        @Override
	        protected SimpleDateFormat initialValue()
	        {
	            return new SimpleDateFormat("dd.MM.yyyy");
	        }
	    };
		 private static final ThreadLocal<SimpleDateFormat> sdfMitZeit = new ThreadLocal<SimpleDateFormat>(){
		        @Override
		        protected SimpleDateFormat initialValue()
		        {
		            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		        }
		    };


    private static DatatypeFactory df = null;
    static {
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException("Kann keine DatatypeFactory erzeugen", e);
        }
    }

   
    public static XMLGregorianCalendar getDate(Date d) {
		if (d == null) {
			return null;
		}
		return getDatum(sdf.get().format(d));

	}
    public static XMLGregorianCalendar getDatum(String txt) {
        if (txt == null || txt.isEmpty()) {
            txt = sdf.get().format(new Date());
        }
        if (txt.indexOf(" ") > 0) {
            return getDatumUndZeit(txt);
        }
        if (txt.contains("-")) {
            txt = wandleDatumUm(txt);
        }
        XMLGregorianCalendar ret = null;
        String[] teile = txt.split("\\.");
        if (teile.length >= 3) {
            try {
                ret = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                ret.setDay(new Integer(teile[0].trim()));
                ret.setMonth(new Integer(teile[1].trim()));

                ret.setYear(new Integer(teile[2].trim()));
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static XMLGregorianCalendar getDatumUndZeit(String txt) {
        if (txt == null) {
            txt = sdfMitZeit.get().format(new Date());
        }
        XMLGregorianCalendar ret = null;
        String[] datumUndZeit = txt.split(" ");
        if (datumUndZeit.length == 1) {
            String datum = datumUndZeit[0];
            datumUndZeit = new String[2];
            datumUndZeit[0] = datum;
            datumUndZeit[1] = "11:11:11";
        }
        if (datumUndZeit.length == 2) {

            if (datumUndZeit[0].contains("-")) {
                datumUndZeit[0] = wandleDatumUm(datumUndZeit[0]);
            }
            String[] datum = datumUndZeit[0].split("\\.");
            String[] zeit = datumUndZeit[1].split(":");
            if (datum.length >= 3 && zeit.length == 3) {
                try {
                    ret = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                    ret.setDay(new Integer(datum[0].trim()));
                    ret.setMonth(new Integer(datum[1].trim()));
                    ret.setYear(new Integer(datum[2].trim()));

                    ret.setHour(new Integer(zeit[0].trim()));
                    ret.setMinute(new Integer(zeit[1].trim()));
                    ret.setSecond(new Integer(zeit[2].trim()));

                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    // YYYY-MM-dd => dd.MM.YYYY
    private static String wandleDatumUm(String value) {
        String[] teile = value.split("-");
        String ret = null;
        if (teile.length == 3) {
            ret = teile[2] + "." + teile[1] + "." + teile[0];
        }
        return ret;
    }

    public static XMLGregorianCalendar erzeugeXMLGregorianCalendar(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return df.newXMLGregorianCalendar(gc);
    }

    public static Date erzeugeDate(XMLGregorianCalendar xmlDate) {

        return xmlDate.toGregorianCalendar().getTime();
    }
}
