package com.epam.activemq.main;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.epam.activemq.consumer.TopicConsumerAsync;
import com.epam.activemq.consumer.TopicConsumerSync;
import com.epam.activemq.producer.TopicProducerAsync;
import com.epam.activemq.producer.TopicProducerSync;

public class ActiveMQMain {
	
	public static void main(String [] args){

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		//Create the consumers. It will wait to listen to the topic.
		Thread topicConsumerSyncThread = new Thread (new TopicConsumerSync(connectionFactory));
		topicConsumerSyncThread.start();
		
		Thread topicConsumerAsyncThread = new Thread (new TopicConsumerAsync(connectionFactory));
		topicConsumerAsyncThread.start();
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		// Create a message. As soon as the message is published on the Topic,
		// it will be consumed by the consumer
		Thread topicProducerSyncThread = new Thread(new TopicProducerSync(connectionFactory));
		topicProducerSyncThread.start();
		
		Thread topicProducerAsyncThread = new Thread(new TopicProducerAsync(connectionFactory));
		topicProducerAsyncThread.start();
		
	}
}
