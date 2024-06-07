package com.banking.transaction.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.transaction.api.models.UserInfo;
import com.banking.transaction.api.repositories.UserInfoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserInfo userInfo) {
		userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return ResponseEntity.<String>ok("user has been registered..");
	}

}
