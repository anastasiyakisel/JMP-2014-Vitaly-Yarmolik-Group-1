package com.epam.mp.model;

public class Account {

	private String id;
	private int amount;
	private String personId;
	private String bankId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String toString() {
		return "Account "+id+" with amount "+amount+" , person id="+personId+", bank id "+bankId;
	}
}
