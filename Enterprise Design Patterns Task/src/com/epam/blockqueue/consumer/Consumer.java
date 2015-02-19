package com.epam.blockqueue.consumer;

import org.apache.log4j.Logger;

import com.epam.blockqueue.producer.Producer;
import com.epam.blockqueue.queue.MyBlockingQueue;

public class Consumer implements Runnable{
	
	private static final Logger logger = Logger.getLogger(Consumer.class);
	
	private MyBlockingQueue<String> queue;
	
	public Consumer(MyBlockingQueue<String> q){
		this.queue=q;
	}

	@Override
	public void run() {
		for (int i=10;i>0; i--){
			Object data = queue.poll();
			if (data!=null){
				logger.info("Consumer : consumed "+data);
			}
		}
	}

}
