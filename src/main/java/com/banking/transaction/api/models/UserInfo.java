package com.banking.transaction.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@NotBlank(message = "UserName is mindatory.")
	private String userName;

	@NotBlank(message = "Password is mindatory.")
	private String password;

	@NotBlank(message = "Email is mindatory.")
	private String email;

	private String role;

	private boolean isActive;

}
