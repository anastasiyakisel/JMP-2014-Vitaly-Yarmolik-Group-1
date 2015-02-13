package com.epam.edp.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import com.epam.edp.model.CustomObject;
import com.epam.edp.model.LinkedItem;
import com.epam.scheduler.DeletionScheduler;

public class CustomCache {

	private static final int TIME_TO_LIVE = 60 * 1000;

	private static final int MAX_CACHE_SIZE = 2;

	@SuppressWarnings("unchecked")
	private static ConcurrentSkipListMap cache = new ConcurrentSkipListMap();

	private static DeletionScheduler scheduler = new DeletionScheduler();

	public static void addObject(CustomObject obj) {

		Integer itemId = obj.getId();
		LinkedItem newLItem = new LinkedItem();
		newLItem.setCustomObject(obj);
		newLItem.setExpirationTime(System.currentTimeMillis() + TIME_TO_LIVE);
		if (cache.size() >= MAX_CACHE_SIZE) {
			removeLessUsedItem();
		}
		newLItem.setLastAccessTime(System.currentTimeMillis());
		cache.put(itemId, newLItem);
		System.out.println("object is added");
		System.out.println(cache.keySet());
		scheduler.scheduleRemoval(newLItem);
	}

	public static CustomObject fetchObject(Integer id) {
		LinkedItem foundLinkedItem = (LinkedItem) cache.get(id);
		if (foundLinkedItem != null) {
			foundLinkedItem.setLastAccessTime(System.currentTimeMillis());
		}
		return (foundLinkedItem != null) ? foundLinkedItem.getCustomObject()
				: null;
	}

	public static List<LinkedItem> sortValuesOfCacheByLastAccessTime() {
		Collection values = (Collection) cache.values();
		List<LinkedItem> linkedItemList = new ArrayList<LinkedItem>(values);
		Collections.sort(linkedItemList, new Comparator<LinkedItem>() {
			@Override
			public int compare(LinkedItem o1, LinkedItem o2) {
				return (int) (o1.getLastAccessTime() - o2.getLastAccessTime());
			}
		});
		return linkedItemList;
	}

	public static void removeLessUsedItem() {
		List<LinkedItem> linkedItemList = sortValuesOfCacheByLastAccessTime();
		int removeId = linkedItemList.get(0).getCustomObject().getId();
		cache.remove(removeId);
		scheduler.removeById(removeId);
		System.out.println("after remove: " + cache.keySet());
	}

	public static void remove(Integer id) {
		cache.remove(id);
		System.out.println("Expired item " + id + " is removed: "
				+ cache.keySet());
	}

}
