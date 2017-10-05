package com.ab.spring.dao;

import java.util.Optional;

import com.ab.datastructure.Tuple;
import com.ab.vo.User;
import com.ab.vo.login.Login;

public interface LoginDao {
	Tuple<Boolean, String, Optional<User>> signinUser(Login login);
	
	User getUser(Long userId);
	
	User registerUser(User user);
	
	boolean activateUser(Long userId);
}
