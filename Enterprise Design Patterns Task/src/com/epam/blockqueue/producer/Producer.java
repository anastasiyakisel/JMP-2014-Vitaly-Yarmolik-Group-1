package com.epam.blockqueue.producer;

import org.apache.log4j.Logger;

import com.epam.blockqueue.queue.MyBlockingQueue;

public class Producer implements Runnable{
	
	private static final Logger logger = Logger.getLogger(Producer.class);
	
	private MyBlockingQueue<String> queue;

	public Producer (MyBlockingQueue<String> q){
		this.queue=q;
	}	
	
	@Override
	public void run() {		
		for (int i=10; i>0; i--){
			try {
				Thread.sleep(i*100);
				queue.offer(String.valueOf(i));
				logger.info("Producer: produced "+String.valueOf(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
