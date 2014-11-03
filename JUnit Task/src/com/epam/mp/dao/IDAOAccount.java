package com.epam.mp.dao;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Account;

public interface IDAOAccount {
	
	public Account create(Account account) throws DAOException;

	public boolean update(Account account, Integer newAmountValue) throws DAOException;

	public boolean delete(String id) throws DAOException;

	public Account read(String id) throws DAOException;

	public List<Account> getAll() throws DAOException;
}
