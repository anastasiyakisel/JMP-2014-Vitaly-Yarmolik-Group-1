package com.epam.mp.dao.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import org.apache.log4j.BasicConfigurator;

import com.epam.mp.dao.IDAOPerson;
import com.epam.mp.exception.DAOException;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.model.Person;
import com.epam.mp.util.FileUtil;

public class PersonCsvDAO implements IDAOPerson{
	
	private String PERSON_CSV_PATH = "src/resources/Person.csv";

	@Override
	public Person create() throws DAOException {
		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setFirstName("Firstname "+NumberGenerator.generateNumber());
		person.setLastName("Lastname "+NumberGenerator.generateNumber());
		File file = new File(PERSON_CSV_PATH);
		FileWriter fw=null;
		BufferedWriter writer=null;
		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter( fw );
			//writer.newLine();
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
		if(person!=null){
			System.out.println("Person# "+person.getId()+" has been successfully created.");
		}
		return person;
	}

	@SuppressWarnings("resource")
	@Override
	public Person read(String id) throws DAOException {
		File file = new File(PERSON_CSV_PATH);
		Scanner input;
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
			    	System.out.println("Person # "+id+" has been successfully red.");
			    	return person;
			    }
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		} 	
		if(person!=null){
			System.out.println("Person# "+person.getId()+" has been successfully red.");
		}
		return person;
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<Person> getAll() throws DAOException {
		ArrayList <Person> personList = new ArrayList<Person>();
		File file = new File(PERSON_CSV_PATH);
		Scanner input;
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
		if (personList!=null){
			System.out.println("All persons have been retrieved successfully.");
		}
		return personList;
	}

	@Override
	public boolean update(String id) throws DAOException {
		return FileUtil.replaceString(PERSON_CSV_PATH, id);
	}

	@Override
	public boolean delete(String id) throws DAOException {
		return FileUtil.deleteString(PERSON_CSV_PATH, id);
	}
	
	
}
