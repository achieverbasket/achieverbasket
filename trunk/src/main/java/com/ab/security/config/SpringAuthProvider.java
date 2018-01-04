package com.ab.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.ab.service.LoginService;
import com.ab.vo.User;
import com.ab.vo.login.Login;

@Component 
public class SpringAuthProvider implements AuthenticationProvider{
	
	@Autowired
	LoginService loginSerivceImpl;
	
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		Object password = authentication.getCredentials();
		if(null == name || null == password) {
			throw new BadCredentialsException("No record found");
		}	
		User user ;
		Login form = new Login();
		form.setUsername(name);form.setPassword(password.toString());
		try {
			user = loginSerivceImpl.loginUser(form);
		} catch (Exception e) {
			throw new BadCredentialsException("No record found");
		}
		if(null != user && null != user.getStatus()){
			if(user.getStatus().equalsIgnoreCase("Success")){
					return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
			}else if(user.getStatus().equalsIgnoreCase("Account not activated") ||
					user.getStatus().equalsIgnoreCase("Password incorrect")){
				throw new BadCredentialsException(user.getStatus());
			}else{
				throw new BadCredentialsException("User not found");
			}
		}else {
			throw new BadCredentialsException("No record found");
		}
	}

	
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
