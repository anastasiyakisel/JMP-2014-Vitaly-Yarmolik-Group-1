package com.epam.mp.test.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.model.Person;
import com.epam.mp.service.IPersonService;
import com.epam.mp.service.impl.PersonService;


public class PersonServiceTest {
	
	@Test(expected=LogicException.class)
	public void updateTest() throws LogicException, DAOException {
		IPersonService personService = new PersonService();
		Person person = personService.read("f46aeffd-18db-4def-b9c4-4b3f722ba91e");		
		personService.update(person, "Brian", "Mayyy");
	}

	@Test
	public void deleteTest() throws LogicException, DAOException {
		String bankId="3b3a8dfe-b194-4ee1-92b7-d28d43bf3c88";
		IPersonService personService = new PersonService();
		boolean result = personService.delete(bankId);
		assertTrue("Delete of person failed.", result);
	}
	
	@Test
	public void createTest() throws DAOException {
		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setFirstName("LALAFirstname "+NumberGenerator.generateNumber());
		person.setLastName("Lastname "+NumberGenerator.generateNumber());
		
		IPersonService personService = new PersonService();
		Person resultPerson = personService.create(person);
		assertNotNull("Person creation failed.", resultPerson);
	}

	@Test(expected=LogicException.class)
	public void readTest() throws LogicException, DAOException {
		String personId="fakeId";
		IPersonService personService = new PersonService();
		personService.read(personId);		
	}

	@Test
	public void getAllTest() throws DAOException {
		IPersonService personService = new PersonService();
		List<Person> personList = personService.getAll();
		assertNotNull("Retrieval of all persons in the system is failed", personList);
	}

}
