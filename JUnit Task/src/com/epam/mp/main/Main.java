package com.epam.mp.main;

import java.util.List;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Account;
import com.epam.mp.model.Bank;
import com.epam.mp.model.Person;
import com.epam.mp.service.impl.AccountService;
import com.epam.mp.service.impl.BankService;
import com.epam.mp.service.impl.PersonService;

public class Main {

	public static void main (String [] args){
		AccountService accService = new AccountService();
		PersonService personService = new PersonService();
		BankService bankService = new BankService();
		
		try {
			List<Account> accountList = accService.getAll();
			printList(accountList);
			
			List<Bank> bankList = bankService.getAll();
			printList(bankList);
			
			List<Person> personList = personService.getAll();
			printList(personList);
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
		
	}	
	
	private static void printList(List<?> list){
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i)+"\r");
		}
		
	}
	
}
