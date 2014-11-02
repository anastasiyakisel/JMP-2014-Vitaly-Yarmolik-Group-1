package com.epam.mp.dao;

import java.util.ArrayList;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Bank;

public interface IDAOBank {
	
	Bank create() throws DAOException;

	boolean update(String id) throws DAOException;

	boolean delete(String id) throws DAOException;

	Bank read(String id) throws DAOException;

	ArrayList<Bank> getAll() throws DAOException;
}
