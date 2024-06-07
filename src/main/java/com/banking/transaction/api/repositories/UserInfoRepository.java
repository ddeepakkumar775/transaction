package com.banking.transaction.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.transaction.api.models.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByUserName(String userName);

}
