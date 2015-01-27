package com.epam.publisher;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

public class PublisherSync {
	private TopicPublisher topicPublisher = null;

	public PublisherSync(TopicSession session, Topic topic)
			throws JMSException {
		this.topicPublisher = session.createPublisher(topic);
	}

	public void sendSyncMessage(TopicSession session) throws JMSException {
		String textSync = "Synchronous message";
		TextMessage syncMessage = session.createTextMessage(textSync);
		syncMessage.setStringProperty("name", "syncc");
		this.topicPublisher.publish(syncMessage);
	}
}
