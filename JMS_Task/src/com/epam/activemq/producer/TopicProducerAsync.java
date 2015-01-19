package com.epam.activemq.producer;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducerAsync implements Runnable{

	ActiveMQConnectionFactory connectionFactory = null;
	
	public TopicProducerAsync (ActiveMQConnectionFactory connectionFactory){
		this.connectionFactory=connectionFactory;
	}
	
	@Override
	public void run() {
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createTopic("testTopic2");
			
			MessageProducer producer = session.createProducer(destination);
			
			String text = "Asynchronous message.";
			TextMessage message = session.createTextMessage(text);
			message.setStringProperty("name", "async");
			producer.send(message);

			session.close();
			connection.close();
		} catch (JMSException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
