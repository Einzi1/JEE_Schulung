package de.kiltz.seminar.ejb3.mdb.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/**
 * Publish Subscribe
 * @author fk
 *
 */
public class Empfaenger implements Runnable {

    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/topic/test";
	private Thread t;
	private String name;
	public Empfaenger(String name)
	{
		this.name = name;
		t = new Thread(this);
		t.start();
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new Empfaenger("Eins");
		new Empfaenger("Zwei");
		new Empfaenger("Drei");
	}
	public void run() {
		try {
			InitialContext ctx = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) ctx.lookup(DEFAULT_CONNECTION_FACTORY);
			Connection con = factory.createConnection("benutzer", "benutzer-123");
			Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			con.start();
			
			Destination postfach = (Destination)ctx.lookup(DEFAULT_DESTINATION);
			MessageConsumer empf = session.createConsumer(postfach);
			System.out.println("Empfänger " + name + ": warten auf Msg... ");
			TextMessage msg = (TextMessage)empf.receive();

			System.out.println("Empfänger " + name + ": "+msg.getText());
			
			// Überprüfen
			msg.acknowledge();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
