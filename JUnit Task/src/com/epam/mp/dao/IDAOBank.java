package com.epam.mp.dao;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Bank;

public interface IDAOBank {
	
	public Bank create(Bank bank) throws DAOException;

	public boolean update(Bank bank, String newBankName) throws DAOException;

	public boolean delete(String id) throws DAOException;

	public Bank read(String id) throws DAOException;

	public List<Bank> getAll() throws DAOException;
}
