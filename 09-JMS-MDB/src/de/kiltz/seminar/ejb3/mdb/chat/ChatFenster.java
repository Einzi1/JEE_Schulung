package de.kiltz.seminar.ejb3.mdb.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatFenster implements ActionListener, Runnable {

	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/topic/test";
	private JFrame fenster;
	private JTextArea taAusgabe;
	private JTextField tfEingabe;
	private Session session;
	private MessageProducer sender;
	private MessageConsumer empf;

	private String name;

	public ChatFenster(String name) {
		this.name = name;
		init();
		try {
			initJMS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread t = new Thread(this);
		t.start();
	}

	private void initJMS() throws Exception {
		InitialContext ctx = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup(DEFAULT_CONNECTION_FACTORY);
		Connection con = factory.createConnection("benutzer", "benutzer-123");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Session empfaengerSession = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		con.start();

		Destination ziel = (Destination) ctx.lookup(DEFAULT_DESTINATION);
		sender = session.createProducer(ziel);
		empf = empfaengerSession.createConsumer(ziel);

	}

	private void init() {
		fenster = new JFrame("Chat " + name);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setSize(300, 400);
		taAusgabe = new JTextArea();
		JScrollPane scrollie = new JScrollPane(taAusgabe);

		fenster.add(scrollie, BorderLayout.CENTER);

		JPanel unten = new JPanel();
		tfEingabe = new JTextField(15);
		unten.add(tfEingabe);
		JButton bSende = new JButton("Sende");
		unten.add(bSende);
		bSende.addActionListener(this);
		fenster.add(unten, BorderLayout.SOUTH);
		fenster.getRootPane().setDefaultButton(bSende);
		fenster.setVisible(true);
		tfEingabe.requestFocus();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatFenster("Simon");
		// new ChatFenster("zwei");
		// new ChatFenster("drei");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TextMessage msg;
		try {
			msg = session.createTextMessage();
			msg.setText(name + ": " + tfEingabe.getText());
			sender.send(msg);
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				TextMessage msg = (TextMessage) empf.receive();
				taAusgabe.append(msg.getText() + "\n");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
