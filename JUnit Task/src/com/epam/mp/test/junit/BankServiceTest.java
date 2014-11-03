package com.epam.mp.test.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.epam.mp.exception.DAOException;
import com.epam.mp.exception.LogicException;
import com.epam.mp.generator.NumberGenerator;
import com.epam.mp.model.Bank;
import com.epam.mp.service.IBankService;
import com.epam.mp.service.impl.BankService;

public class BankServiceTest {
	
	@Test
	public void createTest() throws DAOException{
		Bank bank = new Bank();
		bank.setId(UUID.randomUUID().toString());
		bank.setName("Bank#"+NumberGenerator.generateNumber());
		
		IBankService bankService = new BankService();
		Bank resultBank = bankService.create(bank);
		assertNotNull("Bank creation failed.", resultBank);
	}
	
	@Test
	public void getAllTest() throws DAOException {
		IBankService bankService = new BankService();
		List<Bank> bankList = bankService.getAll();
		assertNotNull("Retrieval of all banks in the system is failed", bankList);
	}

	@Test
	public void readTest() throws LogicException, DAOException{
		String bankId="8";
		IBankService accountService = new BankService();
		Bank resultBank = accountService.read(bankId);
		assertNotNull("Bank reading failed.", resultBank);	
	}
	
	@Test
	public void updateTest() throws LogicException, DAOException{
		IBankService bankService = new BankService();
		Bank bank = bankService.read("ff344df4-be08-4cb8-9b6c-01f580f7fafa");
		
		boolean result = bankService.update(bank, "New bank name");
		assertTrue("Update of bank failed.", result);
	}
	
	@Test(expected=LogicException.class)
	public void deleteTest() throws LogicException, DAOException {
		String bankId="100";
		IBankService bankService = new BankService();
		bankService.delete(bankId);
	}
}
