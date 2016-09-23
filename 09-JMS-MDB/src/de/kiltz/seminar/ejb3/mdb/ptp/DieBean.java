package de.kiltz.seminar.ejb3.mdb.ptp;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "DieBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/test"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "Typ='Bean'") })
public class DieBean implements MessageDrivenBean, MessageListener {

	@PostConstruct
	public void init() {
		System.out.println("Initialisiere MDB");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void onMessage(Message m) {
		TextMessage msg = (TextMessage) m;
		System.out.println("---------------\nDie Bean hat eine Msg:\n------------------");
		try {
			System.out.println(msg.getText());
			System.out.println(msg.getStringProperty("Typ"));
		} catch (JMSException e) {
			System.out.println("Fehler bei MDB: " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void ejbRemove() throws EJBException {
		System.out.println("..und Tschüß!");

	}

	@Override
	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
		System.out.println("Erhalte Context: " + ctx.toString());

	}

}
