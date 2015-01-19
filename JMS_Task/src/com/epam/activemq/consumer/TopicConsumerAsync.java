package com.epam.activemq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumerAsync implements Runnable {

	ActiveMQConnectionFactory connectionFactory = null;

	public TopicConsumerAsync(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void run() {
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination topicDestination = session.createTopic("testTopic2"); 

			MessageConsumer messageConsumer = session.createConsumer(
					topicDestination, "name='async'");

			MessageListener listener = new MessageListener() {
				@Override
				public void onMessage(Message message) {
					if (message instanceof TextMessage) { 
						TextMessage textMessage = (TextMessage) message;
						try {
							System.out.println(textMessage.getText());
						} catch (JMSException e) {
							System.out.println(e);
						}
					}
				}
			};
			messageConsumer.setMessageListener(listener);

			session.close();
			connection.close();
		} catch (JMSException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
