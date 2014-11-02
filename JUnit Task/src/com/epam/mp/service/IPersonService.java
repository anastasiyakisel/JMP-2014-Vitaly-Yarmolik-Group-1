package com.epam.mp.service;

import java.util.ArrayList;

import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Person;

public interface IPersonService {
	Person create() ;

	boolean update(String id) throws LogicException;

	boolean delete(String id) throws LogicException;

	Person read(String id) throws LogicException;

	ArrayList<Person> getAll();
}
