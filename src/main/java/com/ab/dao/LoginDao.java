package com.ab.dao;

import com.ab.datastructure.TwoTuple;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.login.Login;

public interface LoginDao {
	User loginUser(Login login);
	
	Candidate getCandidate(Long userId);

	Issuer getIssuer(Long userId);

	TwoTuple<Boolean, String> registerUser(User user);
	
	boolean activateUser(Long userId);
}
