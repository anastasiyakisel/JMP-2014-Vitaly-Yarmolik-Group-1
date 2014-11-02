package com.epam.mp.dao;

import java.util.ArrayList;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Account;

public interface IDAOAccount {
	
	Account create(String bankId, String personId) throws DAOException;

	boolean update(String id) throws DAOException;

	boolean delete(String id) throws DAOException;

	Account read(String id) throws DAOException;

	ArrayList<Account> getAll() throws DAOException;
}
