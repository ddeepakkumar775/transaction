package com.banking.transaction.api.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.banking.transaction.api.controllers.AccountRestController;
import com.banking.transaction.api.models.Account;
import com.banking.transaction.api.utils.AccountInput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class CheckBalanceIntegrationTest {

	@Autowired
	private AccountRestController accountRestController;

	@Test
	void givenAccountDetails_whenCheckingBalance_thenVerifyAccountCorrect() {
		// given
		var input = new AccountInput();
		input.setSortCode("53-68-92");
		input.setAccountNumber("73084635");

		// when
		var body = accountRestController.checkAccountBalance(input).getBody();

		// then
		var account = (Account) body;
		assertThat(account).isNotNull();
		assertThat(account.getOwnerName()).isEqualTo("Paul Dragoslav");
		assertThat(account.getSortCode()).isEqualTo("53-68-92");
		assertThat(account.getAccountNumber()).isEqualTo("73084635");
	}
}
