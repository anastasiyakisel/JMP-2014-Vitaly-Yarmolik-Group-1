package com.epam.activemq.producer;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducerSync implements Runnable{

	//Connection Factory which will help in connecting to ActiveMQ server
	ActiveMQConnectionFactory connectionFactory = null;
	
	public TopicProducerSync (ActiveMQConnectionFactory connectionFactory){
		this.connectionFactory=connectionFactory;
	}
	
	@Override
	public void run() {
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createTopic("testTopic");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			String text = "Synchronous message";
			TextMessage message = session.createTextMessage(text);
			message.setObjectProperty("name", "sync");
			producer.send(message);

			session.close();
			connection.close();
		} catch (JMSException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
