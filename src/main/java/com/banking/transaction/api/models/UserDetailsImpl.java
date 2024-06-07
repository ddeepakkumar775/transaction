package com.banking.transaction.api.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private UserInfo userInfo;

	public UserDetailsImpl(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(userInfo.getRole()));
		return list;
	}

	@Override
	public String getPassword() {

		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getPassword();
	}

}
