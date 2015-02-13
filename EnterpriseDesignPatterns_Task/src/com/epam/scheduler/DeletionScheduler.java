package com.epam.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.epam.edp.cache.CustomCache;
import com.epam.edp.model.LinkedItem;

public class DeletionScheduler extends Thread{

	private Map<Integer, LinkedItem> expirationMap = new 	ConcurrentHashMap<Integer, LinkedItem>();
	
	public DeletionScheduler(){
		Thread.currentThread().setName("DeletionScheduler");
		start();
	}
	
	public void scheduleRemoval(LinkedItem linkedItem){		
		expirationMap.put(linkedItem.getCustomObject().getId(), linkedItem);
	}
	
	private List sortAscByExpirationTime(){
		Collection values = (Collection) expirationMap.values();
		List<LinkedItem> linkedItemList = new ArrayList<LinkedItem>(values);
		Collections.sort(linkedItemList, new Comparator<LinkedItem>() {
			@Override
			public int compare(LinkedItem o1, LinkedItem o2) {
				return (int) ( o1.getExpirationTime()-o2.getExpirationTime());
			}
		});
		return linkedItemList;
	}
	
	public void removeById(Integer id){
		expirationMap.remove(id);
	}
	
	public synchronized void run() {
		long now;
		try{
			while (!isInterrupted()){
				Thread.currentThread().sleep(500);
				if(expirationMap.size()!=0){
					
					now = System.currentTimeMillis();
					List <LinkedItem> list = sortAscByExpirationTime();
					long difference = list.get(0).getExpirationTime()-now;
					if (difference<=0){
						
						Integer expiredId = list.get(0).getCustomObject().getId();
						expirationMap.remove(expiredId);
						CustomCache.remove(expiredId);
					}
				}
			}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
