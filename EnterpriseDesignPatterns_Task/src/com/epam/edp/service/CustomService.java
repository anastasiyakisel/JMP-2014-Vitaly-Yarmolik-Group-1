package com.epam.edp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.epam.edp.dao.CustomDAO;
import com.epam.edp.exception.DAOException;
import com.epam.edp.model.CustomObject;

public class CustomService implements IService<CustomObject> {

	@Override
	public CustomObject create(CustomObject obj) throws DAOException {
		CustomDAO dao = new CustomDAO();
		return dao.create(obj);
	}

	@Override
	public void remove(Integer id) throws DAOException {
		CustomDAO dao = new CustomDAO();
		dao.remove(id);		
	}

	@Override
	public void update(CustomObject obj) throws DAOException {
		CustomDAO dao = new CustomDAO();
		dao.update(obj);
	}

	@Override
	public CustomObject get(Integer id) throws DAOException{
		CustomDAO dao = new CustomDAO();
		return dao.get(id);
	}

}
