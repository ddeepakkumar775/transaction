package com.banking.transaction.api.utils;

import java.util.Random;

public class CodeGenerator {

	public CodeGenerator() {
	}

	public String generateSortCode() {
		int nextInt = new Random().nextInt(5);
		return String.valueOf(nextInt);
	}

	public String generateAccountNumber() {
		int nextInt = new Random().nextInt(10);
		return String.valueOf(nextInt);
	}

}
