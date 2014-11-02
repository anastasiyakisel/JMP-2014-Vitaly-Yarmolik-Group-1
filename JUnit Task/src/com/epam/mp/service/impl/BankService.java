package com.epam.mp.service.impl;

import java.util.ArrayList;

import com.epam.mp.dao.IDAOBank;
import com.epam.mp.dao.csv.BankCsvDAO;
import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Bank;
import com.epam.mp.service.IBankService;

public class BankService implements IBankService{

	@Override
	public Bank create() {
		IDAOBank bankDAO = new BankCsvDAO();
		Bank bank = null;
		try {
			bank=bankDAO.create();
			if (bank != null) {
				System.out.println("Bank # " + bank.getId() + " "
						+ bank.getName() + " has been created successfully");
			}
		
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return bank;
	}

	@Override
	public boolean update(String id) throws LogicException {
		IDAOBank bankDAO = new BankCsvDAO();
		try {
			boolean result = bankDAO.update(id);
			if (result){
				System.out.println("Bank with id # "+id+"has been successfully updated.");
				return true;
			} else {
				throw new LogicException("There is no such bank");
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws LogicException {
		IDAOBank bankDAO = new BankCsvDAO();
		try {
			boolean result = bankDAO.delete(id);
			if (result){
				System.out.println("Bank with id # "+id+"has been successfully deleted.");
				return true;				
			} else {
				throw new LogicException("There is no such bank");
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public Bank read(String id) throws LogicException {
		IDAOBank bankDAO = new BankCsvDAO();
		Bank bank = null;
		try {
			bank = bankDAO.read(id);
			if (bank != null) {
				System.out.println("Bank # " + bank.getId() + " "
						+ bank.getName() + " has been red successfully");
			}else {
				throw new LogicException("ERROR : There is no Bank with id "+id);
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return bank;
	}

	@Override
	public ArrayList<Bank> getAll() {
		IDAOBank bankDAO = new BankCsvDAO();
		ArrayList<Bank> bankList=null;
		try {
			bankList = bankDAO.getAll();
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}		
		return bankList;
	}
	
}
