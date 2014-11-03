package com.epam.mp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.mp.exception.DAOException;
import com.epam.mp.model.Account;
import com.epam.mp.model.Bank;
import com.epam.mp.model.Person;

public class FileUtil {

	public static boolean deleteString(String filePath, String id)
			throws DAOException {
		File inFile = new File(filePath);
		boolean isExists = false;
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				String delims = ",";
				if (!line.split(delims)[0].equals(id.toString())) {
					pw.println(line);
					pw.flush();
				} else {
					isExists = true;
				}
			}
			pw.close();
			br.close();

			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}

			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
				return false;
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		} catch (IOException e) {
			throw new DAOException(e.getMessage());
		}

		return isExists;
	}

	public static boolean replaceString(String filePath, Object object)
			throws DAOException {
		String newLine = null;
		String id = null;
		if (object instanceof Account) {
			Account account = (Account) object;
			id = account.getId();
			newLine = account.getId() + "," + account.getAmount() + ","
					+ account.getBankId() + "," + account.getPersonId();
		} else if (object instanceof Bank) {
			Bank bank = (Bank) object;
			id = bank.getId();
			newLine = bank.getId() + "," + bank.getName();
		} else if (object instanceof Person) {
			Person person = (Person) object;
			id = person.getId();
			newLine = person.getId() + "," + person.getFirstName() + ","
					+ person.getLastName();
		}
		File inFile = new File(filePath);
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		boolean isExists = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			String delims = ",";
			while ((line = br.readLine()) != null) {

				if (!line.split(delims)[0].equals(id.toString())) {
					pw.println(line);
					pw.flush();
				} else {
					isExists = true;
					line = newLine;
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
				return false;
			}
		} catch (FileNotFoundException e) {
			throw new DAOException(e.getMessage());
		} catch (IOException e) {
			throw new DAOException(e.getMessage());
		}

		return isExists;
	}

}
