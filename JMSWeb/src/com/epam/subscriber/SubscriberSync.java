package com.epam.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

public class SubscriberSync {
	private TopicSubscriber topicSubscriber = null;

	public SubscriberSync(TopicSession session, Topic topic)
			throws JMSException {
		this.topicSubscriber = session.createDurableSubscriber(topic, "Sync", "name='syncc'", true);
	}
	
	public TextMessage receiveSyncMessage() throws JMSException{
		 Message message = this.topicSubscriber.receive();
		 TextMessage textMsg = (TextMessage) message;
		 return textMsg;
	}
}
