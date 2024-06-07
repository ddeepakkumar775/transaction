package com.banking.transaction.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.transaction.api.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // TODO Limit to recent transactions and implement separate endpoint to view old transactions
	List<Transaction> findBySourceAccountIdOrderByInitiationDate(long id);
}
