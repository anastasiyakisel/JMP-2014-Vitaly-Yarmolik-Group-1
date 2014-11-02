package com.epam.mp.main;

import java.util.ArrayList;

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
		
		ArrayList<Account> accountList = accService.getAll();
		printList(accountList);
		
		ArrayList<Bank> bankList = bankService.getAll();
		printList(bankList);
		
		ArrayList<Person> personList = personService.getAll();
		printList(personList);
	}	
	
	private static void printList(ArrayList list){
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i)+"\r");
		}
		
	}
	
}
