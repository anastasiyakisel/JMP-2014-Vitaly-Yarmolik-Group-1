package com.epam.mp.service;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Account;

public interface IAccountService {
	
	public Account create(Account account) throws LogicException, DAOException;

	public boolean update(Account account, Integer newAmountValue) throws LogicException, DAOException;

	public boolean delete(String id) throws LogicException, DAOException;

	public Account read(String id)  throws LogicException, DAOException;

	public List<Account> getAll() throws DAOException;
}
