package com.epam.publisher;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

public class PublisherAsync {

	private TopicPublisher topicPublisher=null;
	
	public PublisherAsync(TopicSession session, Topic topic) throws JMSException{
		this.topicPublisher=session.createPublisher(topic);
	}
	
	public void sendAsyncMessage(TopicSession session) throws JMSException{
		String textAsync = "Asynchronous message";
		TextMessage asyncMessage = session.createTextMessage(textAsync);
		asyncMessage.setStringProperty("name", "2");		
		this.topicPublisher.publish(asyncMessage);
	}
}
