package com.epam.mp.test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Bank;
import com.epam.mp.service.impl.BankService;

public class BankServiceTest {
	
	@Test
	public void createTest(){
		BankService bankService = new BankService();
		Bank resultBank = bankService.create();
		assertNotNull("Bank creation failed.", resultBank);
	}
	
	@Test
	public void getAllTest() {
		BankService bankService = new BankService();
		ArrayList<Bank> bankList = bankService.getAll();
		assertNotNull("Retrieval of all banks in the system is failed", bankList);
	}

	@Test
	public void readTest() throws LogicException{
		String bankId="8";
		BankService accountService = new BankService();
		Bank resultBank = accountService.read(bankId);
		assertNotNull("Bank reading failed.", resultBank);	
	}
	
	@Test
	public void updateTest() throws LogicException{
		String bankId="20b04224-f431-483c-8b17-fdfba33b7d57";
		BankService bankService = new BankService();
		boolean result = bankService.update(bankId);
		assertTrue("Update of bank failed.", result);
	}
	
	@Test(expected=LogicException.class)
	public void deleteTest() throws LogicException {
		String bankId="100";
		BankService bankService = new BankService();
		boolean result = bankService.delete(bankId);
		//assertTrue("Delete of bank failed.", result);
	}
}
