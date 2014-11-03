package com.epam.mp.dao.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.mp.dao.IDAOBank;
import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Bank;
import com.epam.mp.util.FileUtil;

public class BankCsvDAO implements IDAOBank{
		
	private String BANK_CSV_PATH="src/resources/Bank.csv";

	@SuppressWarnings("resource")
	@Override
	public Bank read(String id) throws DAOException {
		File file = new File(BANK_CSV_PATH);
		Scanner input=null;
		Bank bank=null;
		try {
			input = new Scanner(file);
			String delims = ",";
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    if (tokens[0].equals(id.toString())){
			    	bank = new Bank();
			    	bank.setId(tokens[0]);
			    	bank.setName(tokens[1]);
			    	return bank;
			    }
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		}		
		return bank;
	}

	@Override
	public Bank create(Bank bank) throws DAOException {
		File file = new File(BANK_CSV_PATH);
		FileWriter fw=null;
		BufferedWriter writer=null;
		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter( fw );
			writer.write(bank.getId()+",");
			writer.write(bank.getName());			
		}catch (IOException e) {
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
		return bank;
	}

	@Override
	public boolean update(Bank bank, String newBankName) throws DAOException {
		if (newBankName!=null){
			bank.setName(newBankName);
		}		
		return FileUtil.replaceString(BANK_CSV_PATH, bank);
	}

	@Override
	public boolean delete(String id) throws DAOException {
		return FileUtil.deleteString(BANK_CSV_PATH, id);
	}

	@SuppressWarnings("resource")
	@Override
	public List<Bank> getAll() throws DAOException {
		List <Bank> bankList = new ArrayList<Bank>();
		File file = new File(BANK_CSV_PATH);
		Scanner input=null;
		try {
			input = new Scanner(file);
			String delims = ",";
			input.nextLine();
			while(input.hasNext()) {
			    String nextLine = input.nextLine();
			    String[] tokens = nextLine.split(delims);
			    Bank bank = new Bank();
			    bank.setId(tokens[0]);
			    bank.setName(tokens[1]);
			    bankList.add(bank);
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		}	
		return bankList;
	}
}
