package com.ab.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.ab.spring.service.LoginService;
import com.ab.vo.login.Login;


public class SpringAuthProvider{
	
	@Autowired
	LoginService loginSerivceImpl;
	
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(authentication.getCredentials() + authentication.getName());
		
		String name = authentication.getName();
		Object password = authentication.getCredentials();
		try {
			if(null != name && null != password) {
				Login form = new Login();
				form.setUserName(name);form.setPassword(password.toString());
				loginSerivceImpl.signinUser(form);
			}else {
				throw new BadCredentialsException("");
			}
		}catch (Exception e) {
			throw new BadCredentialsException("");
		}
		return null;
	}

	
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
