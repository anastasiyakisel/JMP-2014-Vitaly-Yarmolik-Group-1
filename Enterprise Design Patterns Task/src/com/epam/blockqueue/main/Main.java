package com.epam.blockqueue.main;

import com.epam.blockqueue.consumer.Consumer;
import com.epam.blockqueue.producer.Producer;
import com.epam.blockqueue.queue.MyBlockingQueue;


public class Main {
	
	public static void main (String [] args){
		
		org.apache.log4j.PropertyConfigurator.configure("src/log4j.properties");
		
		MyBlockingQueue<String> blockingQueue = new MyBlockingQueue<>(10);
		
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();		
		consumerThread.start();
	}


	
}
