package com.epam.mp.service.impl;

import java.util.List;

import com.epam.mp.dao.IDAOPerson;
import com.epam.mp.dao.csv.PersonCsvDAO;
import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Person;
import com.epam.mp.service.IPersonService;

public class PersonService implements IPersonService{

	@Override
	public Person create(Person person) throws DAOException {
		IDAOPerson personDAO = new PersonCsvDAO();
		person=personDAO.create(person);
		return person;
	}

	@Override
	public boolean update(Person person, String newFirstName, String newLastName) throws LogicException, DAOException {
		IDAOPerson personDAO = new PersonCsvDAO();
		boolean result = personDAO.update(person, newFirstName, newLastName);
		if (result){
			return true;
		} else {
			throw new LogicException("There is no such person.");
		}
	}

	@Override
	public boolean delete(String id) throws LogicException, DAOException {
		IDAOPerson personDAO = new PersonCsvDAO();
		boolean result = personDAO.delete(id);
		if (result){
			return true;
		} else {
			throw new LogicException("There is no such person.");
		}
	}

	@Override
	public Person read(String id) throws LogicException, DAOException{
		IDAOPerson personDAO = new PersonCsvDAO();
		Person person = personDAO.read(id);
		if (person != null) {
			return person;
		}
		else {
			throw new LogicException("ERROR : There is no Person with id "+id);
		}

	}

	@Override
	public List<Person> getAll() throws DAOException  {
		IDAOPerson personDAO = new PersonCsvDAO();
		List<Person> personList=null;
		personList = personDAO.getAll();
		return personList;
	}
	
}
