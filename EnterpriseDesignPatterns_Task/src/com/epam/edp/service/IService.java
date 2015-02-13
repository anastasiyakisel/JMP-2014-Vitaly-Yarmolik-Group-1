package com.epam.edp.service;

import com.epam.edp.exception.DAOException;

public interface IService<T> {
	
	T create(T obj) throws DAOException ;
	void remove(Integer id) throws DAOException ;
	void update(T t) throws DAOException ;
	T get(Integer id) throws DAOException;
}
