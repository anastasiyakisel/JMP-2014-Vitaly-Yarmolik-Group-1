package com.epam.edp.model;

public class LinkedItem{ //implements Comparable<LinkedItem>{

	private CustomObject customObject;

	private Long expirationTime;

	private Long lastAccessTime;

	public CustomObject getCustomObject() {
		return customObject;
	}

	public void setCustomObject(CustomObject customObject) {
		this.customObject = customObject;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	@Override
	public String toString(){
		return "LinkedItem : last access time"+this.lastAccessTime+", expiration time"+expirationTime+", "+" CustomID"+customObject.getId()+", "+customObject.getName();
		
	}

}
