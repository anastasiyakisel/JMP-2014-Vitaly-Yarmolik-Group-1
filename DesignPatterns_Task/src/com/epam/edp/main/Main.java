package com.epam.edp.main;

import com.epam.edp.exception.DAOException;
import com.epam.edp.manager.CacheManager;
import com.epam.edp.model.CustomObject;
import com.epam.edp.util.ConsoleUtil;

public class Main {

	public static void main(String[] args) {
		while (true) {
			try {
				int id = ConsoleUtil.readCount();
				CustomObject obj = CacheManager.fetchObject(id);
				System.out.println(obj.getId()+" "+obj.getName());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}
}


