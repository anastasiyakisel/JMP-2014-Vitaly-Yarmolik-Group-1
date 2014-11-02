package com.epam.mp.service;

import java.util.ArrayList;

import com.epam.mp.exception.LogicException;
import com.epam.mp.model.Bank;

public interface IBankService {
	Bank create();

	boolean update(String id) throws LogicException;

	boolean delete(String id) throws LogicException;

	Bank read(String id)  throws LogicException;

	ArrayList<Bank> getAll();

}
