package com.epam.mp.runnable;

import java.util.concurrent.Future;

public class CancelTask implements Runnable{

	private Future<?> future;
	
	public CancelTask(Future<?> future) {
		this.future = future;
	}
	
	@Override
	public void run() {
		future.cancel(true);		
	}

}
