package com.ab.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ab.dao.LoginDao;
import com.ab.dao.SocialActivityDao;
import com.ab.datastructure.TwoTuple;
import com.ab.form.CustomRoleForm;
import com.ab.service.LoginService;
import com.ab.type.UserType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.login.IssuerRegistration;
import com.ab.vo.login.Login;
import com.ab.vo.login.UserRegistration;
import com.google.common.collect.Lists;

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
		User user = loginDaoImpl.loginUser(loginForm);
		return user;
	}


	@Override
	public Candidate getCandidateDetail(User form) throws Exception {
		return loginDaoImpl.getCandidate(form.getUserId());
	}


	@Override
	public Issuer getIssuerDetail(User form) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TwoTuple<Boolean, String> registerNewUser(UserRegistration registrationForm){
		User user = fromRegistration(registrationForm);
		System.out.println("in registerNewUser of loginServiceImpl for: "+user);
		return loginDaoImpl.registerUser(user);
	}
	
	@Override
	public TwoTuple<Boolean, String> registerNewIssuer(IssuerRegistration form){
		User user = fromIssuerRegistration(form);
		System.out.println("in registerNew issuer of loginServiceImpl for: "+user);
		return loginDaoImpl.registerUser(user);
	}

	private User fromRegistration(UserRegistration registration) {
		CustomRoleForm customRoleForm = new CustomRoleForm();
		customRoleForm.setName("ROLE_USER");
		List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();
		
		grantedAuthorityList.add(customRoleForm);
		User user = new User(registration.getFirstName()+ registration.getLastName(), registration.getPassword(),
				true, true, 
				true, true, grantedAuthorityList, null, 
				registration.getUserType(), registration.getFirstName(), registration.getLastName(), registration.getEmail(), null);
	
		return user;
	}

	private User fromIssuerRegistration(IssuerRegistration registration) {
		CustomRoleForm customRoleForm = new CustomRoleForm();
		customRoleForm.setName("ROLE_ISSUER");
		List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

		User user = new User(registration.getFirstName()+ registration.getLastName(), registration.getPassword(),
				true, true, 
				true, true, grantedAuthorityList, null, 
				registration.getUserType(), registration.getFirstName(), registration.getLastName(), registration.getEmail(), null);
		return user;
	}

	@Override
	public Candidate getCandidate(long userId) throws Exception {
		return loginDaoImpl.getCandidate(userId);
	}
	
	@Override
	public Issuer getIssuer(long userId) throws Exception {
		return loginDaoImpl.getIssuer(userId);
	}
}
