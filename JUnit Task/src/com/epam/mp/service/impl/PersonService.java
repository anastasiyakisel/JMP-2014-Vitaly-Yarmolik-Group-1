package com.epam.mp.service.impl;

import java.util.ArrayList;

import com.epam.mp.dao.IDAOPerson;
import com.epam.mp.dao.csv.PersonCsvDAO;
import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Person;
import com.epam.mp.service.IPersonService;

public class PersonService implements IPersonService{

	@Override
	public Person create() {
		IDAOPerson personDAO = new PersonCsvDAO();
		Person person = null;
		try {
			person=personDAO.create();
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return person;
	}

	@Override
	public boolean update(String id) throws LogicException {
		IDAOPerson personDAO = new PersonCsvDAO();
		try {
			boolean result = personDAO.update(id);
			if (result){
				System.out.println("Person with id # "+id+"has been successfully updated.");//LOGGER.info	
			} else {
				throw new LogicException("There is no such person.");
			}
			return true;
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws LogicException {
		IDAOPerson personDAO = new PersonCsvDAO();
		try {
			boolean result = personDAO.delete(id);
			if (result){
				System.out.println("Person with id # "+id+"has been successfully deleted.");
				return true;
			} else {
				throw new LogicException("There is no such person.");
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public Person read(String id) throws LogicException{
		IDAOPerson personDAO = new PersonCsvDAO();
		Person person = null;
		try {
			person = personDAO.read(id);
			if (person != null) {
				System.out.println("Bank # " + person.getId()+ " has been red successfully ");
			}
			else {
				throw new LogicException("ERROR : There is no Person with id "+id);
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return person;
	}

	@Override
	public ArrayList<Person> getAll()  {
		IDAOPerson personDAO = new PersonCsvDAO();
		ArrayList<Person> personList=null;
		try {
			personList = personDAO.getAll();
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		
		return personList;
	}
	
}
