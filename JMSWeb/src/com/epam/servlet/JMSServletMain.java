package com.epam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.publisher.PublisherAsync;
import com.epam.publisher.PublisherSync;
import com.epam.subscriber.SubscriberAsync;
import com.epam.subscriber.SubscriberSync;
import com.epam.util.FileUtil;

public class JMSServletMain extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String TOPIC_NAME = "TOPIC6";
	private static final String CLIENTID_ASYNC="durableAsyc";
	private static final String CLIENTID_SYNC="durableSync";
	public static final String SYNC_FILENAME="/Users/user/Documents/workspace/JMSWeb/src/SyncFile.txt";

	private TopicConnectionFactory topConFactory = null;
	private Topic topic = null;
	
	public void init(){
		
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		props.setProperty("topic.MyTopic", TOPIC_NAME);
		
		try {
			Context  jndiContext = new InitialContext(props);
			topConFactory = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
			topic = (javax.jms.Topic) jndiContext.lookup("MyTopic");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
		


	}
	
	public void doGet(HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TopicConnection connectionAsync = topConFactory.createTopicConnection();
			connectionAsync.setClientID(CLIENTID_ASYNC);
			connectionAsync.start();
			
			TopicConnection connectionSync = topConFactory.createTopicConnection();
			connectionSync.setClientID(CLIENTID_SYNC);			
			connectionSync.start();
			
			TopicSession sessionSync = connectionSync.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSession sessionAsync = connectionAsync.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			SubscriberAsync consumerAsync=new SubscriberAsync(sessionAsync, topic);
			SubscriberSync consumerSync=new SubscriberSync(sessionSync, topic);
			
			PublisherAsync publisherAsync = new PublisherAsync(sessionAsync, topic);
			PublisherSync publisherSync = new PublisherSync(sessionSync, topic);
			
			publisherSync.sendSyncMessage(sessionSync);
			publisherAsync.sendAsyncMessage(sessionAsync);
			
			
			TextMessage syncMsg=consumerSync.receiveSyncMessage();
			FileUtil.writeToFile(SYNC_FILENAME, syncMsg.getText());
				
			sessionSync.close();
			connectionSync.close();
			sessionAsync.close();
			connectionAsync.close();

		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}

	
}
