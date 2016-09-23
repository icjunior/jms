package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidorTopicoComercial {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext(); 

        //imports do package javax.jms
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID("comercial");
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topico = (Destination) context.lookup("loja");
        //MessageConsumer consumer = session.createConsumer(topico);
        MessageConsumer consumer = session.createDurableSubscriber((Topic) topico, "assinatura");
        
        consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage  = (TextMessage)message;
				System.out.println(textMessage);
			}
		});
        
        new Scanner(System.in).nextLine(); //parar o programa para testar a conexao
        
        session.close();
        connection.close();
        context.close();
	}
}
