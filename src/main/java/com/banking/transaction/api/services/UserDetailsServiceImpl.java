package com.banking.transaction.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.transaction.api.models.UserDetailsImpl;
import com.banking.transaction.api.models.UserInfo;
import com.banking.transaction.api.repositories.UserInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserInfoRepository infoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = infoRepository.findByUserName(username);
		return new UserDetailsImpl(userInfo);
	}

}
