package com.banking.transaction.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banking.transaction.api.constants.BankingAppConstants;
import com.banking.transaction.api.models.Account;
import com.banking.transaction.api.services.AccountService;
import com.banking.transaction.api.utils.AccountInput;
import com.banking.transaction.api.utils.CreateAccountInput;
import com.banking.transaction.api.utils.InputValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class AccountRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

	private final AccountService accountService;

	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkAccountBalance(
			@Valid @RequestBody AccountInput accountInput) {
		LOGGER.debug("Triggered AccountRestController.accountInput");

		// Validate input
		if (InputValidator.isSearchCriteriaValid(accountInput)) {
			// Attempt to retrieve the account information
			Account account = accountService.getAccount(accountInput.getSortCode(), accountInput.getAccountNumber());

			// Return the account details, or warn that no account was found for given input
			if (account == null) {
				return new ResponseEntity<>(BankingAppConstants.NO_ACCOUNT_FOUND, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(account, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(BankingAppConstants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountInput createAccountInput) {
		LOGGER.debug("Triggered AccountRestController.createAccountInput");

		// Validate input
		if (InputValidator.isCreateAccountCriteriaValid(createAccountInput)) {
			// Attempt to retrieve the account information
			Account account = accountService.createAccount(createAccountInput.getBankName(),
					createAccountInput.getOwnerName());

			// Return the account details, or warn that no account was found for given input
			if (account == null) {
				return new ResponseEntity<>(BankingAppConstants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(account, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(BankingAppConstants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
