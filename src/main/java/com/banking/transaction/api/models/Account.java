package com.banking.transaction.api.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Account {

	@Id
	@GeneratedValue
	private long id;

	private String sortCode;

	private String accountNumber;

	private double currentBalance;

	private String bankName;

	private String ownerName;

	private transient List<Transaction> transactions;

}
