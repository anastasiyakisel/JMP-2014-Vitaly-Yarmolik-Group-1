package com.epam.mp.service;

import java.util.ArrayList;

import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Account;

public interface IAccountService {
	
	Account create(String bankId, String personId) throws LogicException;

	boolean update(String id) throws LogicException;

	boolean delete(String id) throws LogicException;

	Account read(String id)  throws LogicException;

	ArrayList<Account> getAll();
}
