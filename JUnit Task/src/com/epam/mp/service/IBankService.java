package com.epam.mp.service;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Bank;

public interface IBankService {
	
	public Bank create(Bank bank) throws DAOException;

	public boolean update(Bank bank, String newBankName) throws LogicException, DAOException;

	public boolean delete(String id) throws LogicException, DAOException;

	public Bank read(String id)  throws LogicException, DAOException;

	public List<Bank> getAll() throws DAOException;

}
