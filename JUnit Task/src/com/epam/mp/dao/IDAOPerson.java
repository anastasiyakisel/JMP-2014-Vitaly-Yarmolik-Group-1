package com.epam.mp.dao;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Person;

public interface IDAOPerson {
	
	public Person create(Person person) throws DAOException;

	public boolean update(Person person, String newFirstName, String newLastName) throws DAOException;

	public boolean delete(String id) throws DAOException;

	public Person read(String id) throws DAOException;

	public List<Person> getAll() throws DAOException;
}
