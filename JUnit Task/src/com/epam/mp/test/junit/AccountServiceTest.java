package com.epam.mp.test.junit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.model.Account;
import com.epam.mp.service.IAccountService;
import com.epam.mp.service.impl.AccountService;

public class AccountServiceTest {
	
	@Test(expected=LogicException.class)
	public void createTest() throws LogicException, DAOException {
		Account account = new Account();
		account.setId(UUID.randomUUID().toString());
		account.setAmount(NumberGenerator.generateNumber());
		String bankId="Bank#"+NumberGenerator.generateNumber();//2
		String personId="Person#"+NumberGenerator.generateNumber();//"2";
		account.setBankId(bankId);
		account.setPersonId(personId);
		
		IAccountService accountService = new AccountService();
		accountService.create(account);
	}

	@Test
	public void updateTest() throws LogicException, DAOException {
		IAccountService accountService = new AccountService();
		Account account = accountService.read("0985cf1f-76b0-4288-a835-a23a38f1703a"); // account with this ID should always exist

		boolean result = accountService.update(account, 11111); //change your parameter here
		assertTrue("Update of account failed.", result);
	}

	@Test
	public void deleteTest() throws LogicException, DAOException {
		String accountId="62c89ced-60bb-4e4c-8173-451a512cd48d";
		IAccountService accountService = new AccountService();
		boolean result = accountService.delete(accountId);
		assertTrue("Delete of account failed.", result);
	}

	@Test(expected=LogicException.class)
	public void readTest() throws LogicException, DAOException {
		String accountId="lalala";
		IAccountService accountService = new AccountService();
		accountService.read(accountId); 			
	}

	@Test
	public void getAllTest() throws DAOException{
		IAccountService accountService = new AccountService();
		List<Account> accountList = accountService.getAll();
		assertNotNull("Retrieval of all accounts in the system is failed", accountList);
	}
}
