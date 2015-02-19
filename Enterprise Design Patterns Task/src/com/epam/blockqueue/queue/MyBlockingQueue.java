package com.epam.blockqueue.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.blockqueue.consumer.Consumer;

public class MyBlockingQueue<T> {
	
	private static final Logger logger = Logger.getLogger(MyBlockingQueue.class);

	private List<T> queue = new LinkedList();
	private volatile int size;

	public MyBlockingQueue(final int size) {
		this.size = size;
	}

	public synchronized boolean offer(T obj) {

		while (this.queue.size() == this.size) {
			try {
				logger.info("Producer : waiting. Queue : full.");
				wait();
			} catch (InterruptedException ex) {

			}
		}
		if (this.queue.size() == 0) {
			notifyAll();// wakes all threads in the waiting set
		}
		boolean status = this.queue.add(obj);
		if (status) {
			logger.info("Queue : added element.");
		}

		return status;

	}
	
	public synchronized T poll() {		

		while (this.queue.size() == 0) {
			try {
				logger.info("Queue is empty. Consumer : waiting. ");
				wait();				
			} catch (InterruptedException ex) {

			}
		}
		if (this.queue.size() == this.size) {
			notifyAll(); 
			logger.info("Queue : empty.");
		}
		logger.info("Queue : deleted element.");
		return (T) this.queue.remove(0);
	}

	
	
	
}
