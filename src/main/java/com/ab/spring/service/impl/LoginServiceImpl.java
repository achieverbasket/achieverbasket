package com.ab.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.datastructure.TwoTuple;
import com.ab.spring.dao.LoginDao;
import com.ab.spring.dao.SocialActivityDao;
import com.ab.spring.service.LoginService;
import com.ab.vo.User;
import com.ab.vo.login.Login;
import com.ab.vo.login.Registration;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDaoImpl;
	
	@Autowired
	SocialActivityDao seqDao;
	
	@Override
	public User loginUser(Login loginForm) throws Exception {
		System.out.println("In Signin User========");
		return loginDaoImpl.signinUser(loginForm).getZ().orElse(null);
	}


	@Override
	public User getUserDetails(User form) throws Exception {
		return loginDaoImpl.getUser(form.getUserId());
	}


	@Override
	public TwoTuple<Boolean, String> registerNewUser(Registration registrationForm){
		User user = fromRegistration(registrationForm);
		return loginDaoImpl.registerUser(user);
	}


	private User fromRegistration(Registration registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(registration.getPassword());
		user.setUserName(registration.getFirstName()+ registration.getLastName());
		user.setUserType(registration.getUserType());
		return user;
	}

}
