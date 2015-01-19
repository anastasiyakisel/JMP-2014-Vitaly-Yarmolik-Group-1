package com.epam.activemq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumerSync implements Runnable{

	ActiveMQConnectionFactory connectionFactory = null;
	
	public TopicConsumerSync (ActiveMQConnectionFactory connectionFactory){
		this.connectionFactory=connectionFactory;
	}

	@Override
	public void run() {
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination topicDestination = session.createTopic("testTopic");
			
			MessageConsumer messageConsumer = session.createConsumer(topicDestination, "name='sync'");

			Message message = messageConsumer.receive();
			TextMessage textMessage = (TextMessage) message;
			System.out.println(textMessage.getText());
			
			session.close();
			connection.close();			
		} catch (JMSException e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	
}
