package com.ab.spring.service;

import java.util.Map;

import com.ab.vo.User;
import com.ab.vo.login.Login;
import com.ab.vo.login.Registration;


public interface LoginService {
	
	public User signinUser(Login form) throws Exception;
	public User getUserDetails(User form) throws Exception;
	
	public Map<String,String> registerNewUser(Registration form);
	
}
