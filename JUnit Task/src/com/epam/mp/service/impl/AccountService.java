package com.epam.mp.service.impl;

import java.util.ArrayList;

import com.epam.mp.dao.IDAOAccount;
import com.epam.mp.dao.IDAOBank;
import com.epam.mp.dao.IDAOPerson;
import com.epam.mp.dao.csv.AccountCsvDAO;
import com.epam.mp.dao.csv.BankCsvDAO;
import com.epam.mp.dao.csv.PersonCsvDAO;
import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Account;
import com.epam.mp.model.Bank;
import com.epam.mp.model.Person;
import com.epam.mp.service.IAccountService;

public class AccountService implements IAccountService {

	public ArrayList<Account> getAll() {
		AccountCsvDAO accountDAO = new AccountCsvDAO();
		ArrayList<Account> accountList=null;
			try {
				accountList = accountDAO.getAll();				
			} catch (DAOException e) {
				System.out.println("ERROR"+e.getMessage());
			}
		return accountList;
	}

	@Override
	public Account create(String bankId, String personId) throws LogicException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		IDAOBank bankDAO = new BankCsvDAO();
		IDAOPerson personDAO = new PersonCsvDAO();
		Account account = null;
		Bank bank = null;
		Person person = null;
		try {
			bank = bankDAO.read(bankId);
			person = personDAO.read(personId);
		} catch (DAOException e1) {
			System.out.println(e1.getMessage());
		}
		if(bank!=null||person!=null){
			try {
				account=accountDAO.create(bankId, personId);
				if (account != null) {
					System.out.println("Account with " + account.getId()
							+ " has been created for person ID "
							+ account.getPersonId() + " and bank ID "
							+ account.getBankId());
				}
			} catch (DAOException e) {
				System.out.println("ERROR"+e.getMessage());
			}
		} else {
			throw new LogicException("There is no such bank or person in the system");
		}
		return account;
	}

	@Override
	public boolean update(String id) throws LogicException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		try {
			boolean result = accountDAO.update(id);
			if (result){
				System.out.println("Account with id # "+id+"has been successfully updated.");
				return true;
			} else {
				throw new LogicException("There is no account #"+id);
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws LogicException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		try {
			boolean result = accountDAO.delete(id);
			if (result){
				System.out.println("Account with id # "+id+"has been successfully deleted.");
				return true;
			} else{
				throw new LogicException("There is no account #"+id);
			}			
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return false;
	}

	@Override
	public Account read(String id) throws LogicException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		Account account = null;
		try {
			account = accountDAO.read(id);
			if (account != null) {
				System.out.println("Account with " + account.getId()
						+ " has been red for person ID "
						+ account.getPersonId() + " and bank ID "
						+ account.getBankId());
			} else {
				throw new LogicException("ERROR : There is no Account with id "+id);
			}
		} catch (DAOException e) {
			System.out.println("ERROR"+e.getMessage());
		}
		return account;
	}

}
