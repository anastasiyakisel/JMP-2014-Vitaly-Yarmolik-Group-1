package com.epam.edp.fetcher;

import com.epam.edp.exception.DAOException;
import com.epam.edp.model.CustomObject;
import com.epam.edp.service.CustomService;

public class ObjectFetcher {

	public static CustomObject fetchObject(Integer id) throws DAOException{
		CustomService service = new CustomService(); 
		return service.get(id);
	}
}
