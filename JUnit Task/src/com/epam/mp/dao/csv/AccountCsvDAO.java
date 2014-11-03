package com.epam.mp.dao.csv;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.mp.dao.IDAOAccount;
import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Account;
import com.epam.mp.util.FileUtil;

public class AccountCsvDAO implements IDAOAccount{
	
	private String ACCOUNT_CSV_PATH="src/resources/Account.csv";
	
	public Account create(Account account) throws DAOException  {
		File file = new File(ACCOUNT_CSV_PATH);
		FileWriter fw=null;
		BufferedWriter writer=null;
		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter( fw );
			writer.write(account.getId()+",");
			writer.write(account.getAmount()+",");
			writer.write(account.getBankId()+",");
			writer.write(account.getPersonId());			
		} catch (IOException e) {
			throw new DAOException(e.getMessage());
		} finally{
			try {
				writer.flush();
				writer.close();
			    fw.close();
			} catch (IOException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return account;
	}

	@SuppressWarnings("resource")
	@Override
	public List<Account> getAll() throws DAOException {
		List <Account> accountList = new ArrayList<Account>();
		File file = new File(ACCOUNT_CSV_PATH);
		Scanner input=null;
		try {
			input = new Scanner(file);
			String delims = ",";
			input.nextLine();
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    Account account = new Account();
			    account.setId(tokens[0]);
			    account.setAmount(Integer.parseInt(tokens[1]));
			    account.setBankId(tokens[2]);	
			    account.setPersonId(tokens[3]);
			    accountList.add(account);
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		}	
		return accountList;
	}

	@SuppressWarnings("resource")
	@Override
	public Account read(String id) throws DAOException  {
		File file = new File(ACCOUNT_CSV_PATH);
		Scanner input=null;
		Account account=null;
		try {
			input = new Scanner(file);
			String delims = ",";
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    if (tokens[0].equals(id.toString())){
			    	account = new Account();
			    	account.setId(tokens[0]);
			    	account.setAmount(Integer.parseInt(tokens[1]));
			    	account.setBankId(tokens[2]);
			    	account.setPersonId(tokens[3]);
			    	return account;
			    }
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		}		
		return null;
	}

	@Override
	public boolean update(Account account, Integer newAmountValue) throws DAOException {
		if (newAmountValue!=null){
			account.setAmount(newAmountValue);
		}
		return FileUtil.replaceString(ACCOUNT_CSV_PATH, account);
	}

	@Override
	public boolean delete(String id) throws DAOException {
		return FileUtil.deleteString(ACCOUNT_CSV_PATH, id);
	}

}
