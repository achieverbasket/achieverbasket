package com.ab.spring.service;

import java.util.Map;

import com.ab.datastructure.TwoTuple;
import com.ab.vo.User;
import com.ab.vo.login.Login;
import com.ab.vo.login.Registration;


public interface LoginService {
	
	User loginUser(Login loginForm) throws Exception;

	User getUserDetails(User form) throws Exception;
	
	TwoTuple<Boolean, String> registerNewUser(Registration form);
	
}
