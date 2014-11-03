package com.epam.mp.service;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Person;

public interface IPersonService {
	
	public Person create(Person person) throws DAOException ;

	public boolean update(Person person, String newFirstName, String newLastName) throws LogicException, DAOException;

	public boolean delete(String id) throws LogicException, DAOException;

	public Person read(String id) throws LogicException, DAOException;

	public List<Person> getAll() throws DAOException ;
}
