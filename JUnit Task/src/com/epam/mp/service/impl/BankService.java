package com.epam.mp.service.impl;

import java.util.List;

import com.epam.mp.dao.IDAOBank;
import com.epam.mp.dao.csv.BankCsvDAO;
import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Bank;
import com.epam.mp.service.IBankService;

public class BankService implements IBankService{

	@Override
	public Bank create(Bank bank) throws DAOException{
		IDAOBank bankDAO = new BankCsvDAO();
		bank=bankDAO.create(bank);
		return bank;
	}

	@Override
	public boolean update(Bank bank, String newBankName) throws LogicException, DAOException {
		IDAOBank bankDAO = new BankCsvDAO();
		boolean result = bankDAO.update(bank, newBankName);
		if (result){
			return true;
		} else {
			throw new LogicException("There is no such bank");
		}
	}

	@Override
	public boolean delete(String id) throws LogicException, DAOException {
		IDAOBank bankDAO = new BankCsvDAO();
		boolean result = bankDAO.delete(id);
		if (result){
			return true;				
		} else {
			throw new LogicException("There is no such bank");
		}
	}

	@Override
	public Bank read(String id) throws LogicException, DAOException {
		IDAOBank bankDAO = new BankCsvDAO();
		Bank bank = bankDAO.read(id);
		if (bank == null) {
			throw new LogicException("ERROR : There is no Bank with id "+id);
		}
		return bank;
	}

	@Override
	public List<Bank> getAll() throws DAOException {
		IDAOBank bankDAO = new BankCsvDAO();
		List<Bank> bankList=null;
		bankList = bankDAO.getAll();	
		return bankList;
	}
	
}
