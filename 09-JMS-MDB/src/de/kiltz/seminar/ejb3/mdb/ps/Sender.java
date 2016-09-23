package de.kiltz.seminar.ejb3.mdb.ps;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Sender {

    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/topic/test";

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

        InitialContext ctx = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup(DEFAULT_CONNECTION_FACTORY);
		Connection con = factory.createConnection("benutzer", "benutzer-123");
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		con.start();
		
		TextMessage msg = session.createTextMessage();
		msg.setStringProperty("MeinKey", "MeinValue");
		msg.setText("Die Nachricht!");
		
		Destination ziel = (Destination)ctx.lookup(DEFAULT_DESTINATION);
		MessageProducer sender = session.createProducer(ziel);
		sender.send(msg, DeliveryMode.NON_PERSISTENT, 1, 1000000);
		System.out.println("gesendet..");

	}

}
