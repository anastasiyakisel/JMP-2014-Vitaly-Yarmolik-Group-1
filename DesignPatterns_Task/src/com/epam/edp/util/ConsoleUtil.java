package com.epam.edp.util;

import java.util.Scanner;

public class ConsoleUtil {
	
	public static int readCount(){
		System.out.println("Enter ID of CustomObject :");
		Scanner in = new Scanner(System.in); 
		return in.nextInt();
	}
	
}
