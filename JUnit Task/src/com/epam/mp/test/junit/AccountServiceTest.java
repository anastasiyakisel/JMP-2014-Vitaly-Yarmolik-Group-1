package com.epam.mp.test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.mp.exception.LogicException;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.model.Account;
import com.epam.mp.service.impl.AccountService;

public class AccountServiceTest {
	
	@Test(expected=LogicException.class)
	public void createTest() throws LogicException {
		String bankId="Bank#"+NumberGenerator.generateNumber();//2
		String personId="Person#"+NumberGenerator.generateNumber();//"2";
		AccountService accountService = new AccountService();
		Account resultAccount = accountService.create(bankId, personId);
		//assertNotNull("Account creation failed.", resultAccount);
	}

	@Test
	public void updateTest() throws LogicException {
		String accountId="31";
		AccountService accountService = new AccountService();
		boolean result = accountService.update(accountId);
		assertTrue("Update of account failed.", result);
	}

	@Test
	public void deleteTest() throws LogicException {
		String accountId="c18e4a8d-cdb2-47f1-ab2e-9d5028c84ea7";
		AccountService accountService = new AccountService();
		boolean result = accountService.delete(accountId);
		assertTrue("Delete of account failed.", result);
	}

	@Test(expected=LogicException.class)
	public void readTest() throws LogicException {
		String accountId="lalala";
		AccountService accountService = new AccountService();
		accountService.read(accountId); 			
	}

	@Test
	public void getAllTest(){
		AccountService accountService = new AccountService();
		ArrayList<Account> accountList = accountService.getAll();
		assertNotNull("Retrieval of all accounts in the system is failed", accountList);
	}
}
