package com.epam.mp.entity;

public class User {
	
	public static volatile Integer counter = 0;
	
	public User(){
		counter++;
		this.id=counter.toString();
	}

	private String id;
	
	private String firstName;
	
	private String lastName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
