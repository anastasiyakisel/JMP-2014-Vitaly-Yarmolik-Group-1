package com.epam.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	public static void writeToFile(String filename, String message) {
		File file = new File(filename);
		try {
			if (file.exists() == false) {
				System.out.println("We had to make a new file.");
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(file, true));
			out.println(message);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
