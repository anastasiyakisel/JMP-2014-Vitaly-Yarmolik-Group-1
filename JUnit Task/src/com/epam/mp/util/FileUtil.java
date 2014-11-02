package com.epam.mp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.BasicConfigurator;

import com.epam.mp.exception.DAOException;
import com.epam.mp.generator.NumberGenerator;

public class FileUtil {

	public static boolean deleteString(String filePath, String id) throws DAOException{
		File inFile = new File(filePath);
		boolean isExists=false;
		// Construct the new file that will later be renamed to the original
		// filename.
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				String delims = ",";
				if (!line.split(delims)[0].equals(id.toString())) {
					pw.println(line);
					pw.flush();
				}else{
					isExists=true;
				}
			}
			pw.close();
			br.close();
			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}
			// Rename the new file to the filename the original file had.
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
	
	public static boolean replaceString(String filePath, String id) throws DAOException{
		File inFile = new File(filePath);
		// Construct the new file that will later be renamed to the original
		// filename.
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		boolean isExists=false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			String delims = ",";
			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				
				if (!line.split(delims)[0].equals(id.toString())) {
					pw.println(line);
					pw.flush();
				} else{
					isExists=true;
					Integer newLineId=NumberGenerator.generateNumber();
					String oldLineId = line.split(delims)[0];
					line=line.replace(oldLineId, newLineId.toString());
					pw.println(line);
					//pw.flush();
				}
			}
			pw.close();
			br.close();
			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}
			// Rename the new file to the filename the original file had.
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
