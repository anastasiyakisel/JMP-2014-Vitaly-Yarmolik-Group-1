package com.epam.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import com.epam.util.FileUtil;

public class SubscriberAsync {

	private TopicSubscriber topicSubscriber = null;
	private MessageListener listener=null;
	
	private static final String ASYNC_FILENAME="/Users/user/Documents/workspace/JMSWeb/src/AsyncFile.txt";
	
	public SubscriberAsync(TopicSession session, Topic topic) throws JMSException{
		listener = new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if (message instanceof TextMessage) {
					TextMessage textMsgAsync = (TextMessage) message;
					try {
						FileUtil.writeToFile(ASYNC_FILENAME, textMsgAsync.getText());
					} catch (JMSException e) {
						System.out.println(e);
					}
				}
			}
		};
		this.topicSubscriber=session.createDurableSubscriber(topic, "Async", "name='2'", true);
		this.topicSubscriber.setMessageListener(listener);
	}
	
	
}
