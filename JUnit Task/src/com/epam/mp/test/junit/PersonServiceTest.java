package com.epam.mp.test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Person;
import com.epam.mp.service.impl.PersonService;


public class PersonServiceTest {
	
	@Test(expected=LogicException.class)
	public void updateTest() throws LogicException {
		String personId="1000";
		PersonService personService = new PersonService();
		boolean result = personService.update(personId);
		//assertTrue("Update of bank failed.", result);
	}

	@Test
	public void deleteTest() throws LogicException {
		String bankId="36a33f4c-5c7e-455e-8fc4-14cfef4e2dd1";
		PersonService personService = new PersonService();
		boolean result = personService.delete(bankId);
		assertTrue("Delete of person failed.", result);
	}
	
	@Test
	public void createTest() {
		PersonService personService = new PersonService();
		Person resultPerson = personService.create();
		assertNotNull("Person creation failed.", resultPerson);
	}

	@Test(expected=LogicException.class)
	public void readTest() throws LogicException {
		String personId="fakeId";
		PersonService personService = new PersonService();
		personService.read(personId);		
	}

	@Test
	public void getAllTest() {
		PersonService personService = new PersonService();
		ArrayList<Person> personList = personService.getAll();
		assertNotNull("Retrieval of all persons in the system is failed", personList);
	}

}
