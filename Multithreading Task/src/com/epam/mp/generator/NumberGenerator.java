package com.epam.mp.generator;

import java.util.Random;

public class NumberGenerator {

	public static int generateNumber(){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		return randomInt;		
	}
	
}
