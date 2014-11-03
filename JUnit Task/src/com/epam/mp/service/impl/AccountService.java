package com.epam.mp.service.impl;

import java.util.List;

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

	public List<Account> getAll() throws DAOException {
		AccountCsvDAO accountDAO = new AccountCsvDAO();
		List<Account> accountList= accountDAO.getAll(); 
		return accountList;
	}

	@Override
	public Account create(Account account) throws LogicException, DAOException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		IDAOBank bankDAO = new BankCsvDAO();
		IDAOPerson personDAO = new PersonCsvDAO();
		Bank bank = null;
		Person person = null;
		bank = bankDAO.read(account.getBankId());
		person = personDAO.read(account.getPersonId());
		if(bank!=null||person!=null){
			account=accountDAO.create(account);
		} else {
			throw new LogicException("There is no such bank or person in the system");
		}
		return account;
	}

	@Override
	public boolean update(Account account, Integer newAmountValue) throws LogicException, DAOException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		boolean result = accountDAO.update(account, newAmountValue);
		if (result){
			return true;
		} else {
			throw new LogicException("There is no account #"+account.getId());
		}
	}

	@Override
	public boolean delete(String id) throws LogicException, DAOException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		boolean result = accountDAO.delete(id);
		if (result){
			return true;
		} else{
			throw new LogicException("There is no account #"+id);
		}		
	}

	@Override
	public Account read(String id) throws LogicException, DAOException {
		IDAOAccount accountDAO = new AccountCsvDAO();
		Account account = accountDAO.read(id);
		if (account == null) {
			throw new LogicException("ERROR : There is no Account with id "+id);
		}
		return account;
	}

}
