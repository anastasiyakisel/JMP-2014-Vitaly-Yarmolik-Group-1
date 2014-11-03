package com.epam.mp.dao.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.mp.dao.IDAOPerson;
import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Person;
import com.epam.mp.util.FileUtil;

public class PersonCsvDAO implements IDAOPerson{
	
	private String PERSON_CSV_PATH = "src/resources/Person.csv";

	@Override
	public Person create(Person person) throws DAOException {
		File file = new File(PERSON_CSV_PATH);
		FileWriter fw=null;
		BufferedWriter writer=null;
		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter( fw );
			writer.write(person.getId()+",");
			writer.write(person.getFirstName()+",");	
			writer.write(person.getLastName());
		}catch (IOException e) {
			throw new DAOException(e.getMessage());
		} finally{
			try {
				writer.flush();
				writer.close();
			    fw.close();
			} catch (IOException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return person;
	}

	@SuppressWarnings("resource")
	@Override
	public Person read(String id) throws DAOException {
		File file = new File(PERSON_CSV_PATH);
		Scanner input=null;
		Person person = null;
		try {
			input = new Scanner(file);
			String delims = ",";			
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    if (tokens[0].equals(id.toString())){
			    	person = new Person();
			    	person.setId(tokens[0]);
			    	person.setFirstName(tokens[1]);
			    	person.setLastName(tokens[2]);
			    	return person;
			    }
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		} 
		return person;
	}

	@SuppressWarnings("resource")
	@Override
	public List<Person> getAll() throws DAOException {
		List <Person> personList = new ArrayList<Person>();
		File file = new File(PERSON_CSV_PATH);
		Scanner input=null;
		try {
			input = new Scanner(file);
			String delims = ",";
			input.nextLine();
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    Person person = new Person();
			    person.setId(tokens[0]);
			    person.setFirstName(tokens[1]);
			    person.setLastName(tokens[2]);
			    personList.add(person);
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		}	
		return personList;
	}

	@Override
	public boolean update(Person person, String newFirstName, String newLastName) throws DAOException {
		if (newFirstName!=null){
			person.setFirstName(newFirstName);
		}
		if (newLastName!=null){
			person.setLastName(newLastName);
		}
		return FileUtil.replaceString(PERSON_CSV_PATH, person);
	}

	@Override
	public boolean delete(String id) throws DAOException {
		return FileUtil.deleteString(PERSON_CSV_PATH, id);
	}
	
	
}
