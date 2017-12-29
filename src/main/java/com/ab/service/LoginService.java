package com.ab.service;

import com.ab.datastructure.TwoTuple;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.login.IssuerRegistration;
import com.ab.vo.login.Login;
import com.ab.vo.login.UserRegistration;


public interface LoginService {
	
	User loginUser(Login loginForm) throws Exception;

	Candidate getCandidateDetail(User form) throws Exception;

	Issuer getIssuerDetail(User form) throws Exception;

	Candidate getCandidate(long userId) throws Exception;
	
	Issuer getIssuer(long userId) throws Exception;

	TwoTuple<Boolean, String> registerNewUser(UserRegistration form);
	
	TwoTuple<Boolean, String> registerNewIssuer(IssuerRegistration form);
	
}
