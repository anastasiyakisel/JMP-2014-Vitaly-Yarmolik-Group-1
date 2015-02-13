package com.epam.edp.manager;

import com.epam.edp.cache.CustomCache;
import com.epam.edp.exception.DAOException;
import com.epam.edp.fetcher.ObjectFetcher;
import com.epam.edp.model.CustomObject;
import com.epam.edp.service.CustomService;

public class CacheManager {

	public static CustomObject fetchObject(Integer id) throws DAOException{
		CustomObject obj=null;
		obj=CustomCache.fetchObject(id);
		if (obj==null){
			obj=ObjectFetcher.fetchObject(id);
			CustomCache.addObject(obj);
		}
		return obj;
	}
}
