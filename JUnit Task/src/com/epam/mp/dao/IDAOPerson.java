package com.epam.mp.dao;

import java.util.ArrayList;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Person;

public interface IDAOPerson {
	Person create() throws DAOException;

	boolean update(String id) throws DAOException;

	boolean delete(String id) throws DAOException;

	Person read(String id) throws DAOException;

	ArrayList<Person> getAll() throws DAOException;
}
