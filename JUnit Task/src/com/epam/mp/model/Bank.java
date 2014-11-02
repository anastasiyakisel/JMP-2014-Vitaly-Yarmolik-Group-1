package com.epam.mp.model;

public class Bank {
	
	private String id;
	
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public String toString(){
		return "Bank # "+id+" , name = "+name;
	}
}
