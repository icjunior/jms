package br.com.caelum.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorTopico {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination topico = (Destination) context.lookup("loja");
		MessageProducer producer = session.createProducer(topico);
		
		for (int i = 0; i < 1000; i++) {
			Message message = session.createTextMessage("<pedido><id>123</id></pedido>");
			message.setBooleanProperty("ebook", false);
			producer.send(message);
		}		
		session.close();
		connection.close();
		context.close();
	}
}
