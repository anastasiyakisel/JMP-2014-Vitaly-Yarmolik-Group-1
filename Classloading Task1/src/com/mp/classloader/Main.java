package com.mp.classloader;

import java.lang.reflect.Method;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.mp.custom.classloader.JarClassloader;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	private static final String PACKAGE_NAME = "com.mp.classloader.";

	public static void main(String[] args) {
		BasicConfigurator.configure();
		System.out.println("Please enter classname without package name: ");
		try {
			String fullClassName = PACKAGE_NAME + getInputClassName();
			ClassLoader loader = new JarClassloader();
			Class<?> clazz = Class.forName(fullClassName, true, loader);
			
			Method[] methods = clazz.getMethods();
			System.out.println("Methods of class "+clazz.getName());
			for (Method method : methods) {
				System.out.println(method.getName());
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getLocalizedMessage(), e);
			System.out.println("Class hasn't been found");
		} catch (Exception e){
			LOGGER.error(e.getLocalizedMessage(), e);
			System.out.println("An exception occured during programm execution. For more details see log file.");
		}

	}

	private static String getInputClassName() {
		Scanner sc = new Scanner(System.in);
		try {
			if (sc.hasNextLine()) {
				return sc.nextLine();
			}
		} finally {
			if (sc!=null){
				sc.close();
			}
		}  
		return null;
 }

}