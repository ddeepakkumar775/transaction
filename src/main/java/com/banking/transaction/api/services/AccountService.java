package com.banking.transaction.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banking.transaction.api.models.Account;
import com.banking.transaction.api.repositories.AccountRepository;
import com.banking.transaction.api.repositories.TransactionRepository;
import com.banking.transaction.api.utils.CodeGenerator;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	public Account getAccount(String sortCode, String accountNumber) {
		Optional<Account> account = accountRepository.findBySortCodeAndAccountNumber(sortCode, accountNumber);

		account.ifPresent(value -> value
				.setTransactions(transactionRepository.findBySourceAccountIdOrderByInitiationDate(value.getId())));

		return account.orElse(null);
	}

	public Account getAccount(String accountNumber) {
		Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);

		return account.orElse(null);
	}

	public Account createAccount(String bankName, String ownerName) {
		CodeGenerator codeGenerator = new CodeGenerator();
		Account newAccount = new Account();
		newAccount.setBankName(bankName);
		newAccount.setOwnerName(ownerName);
		newAccount.setSortCode(codeGenerator.generateSortCode());
		newAccount.setAccountNumber(codeGenerator.generateAccountNumber());
		newAccount.setCurrentBalance(0.00);
		return accountRepository.save(newAccount);
	}
}
