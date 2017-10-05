package com.ab.spring.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ab.spring.security.config.SpringAuthProvider;

@Configuration

public class SpringSecurityConfig{
	
	
	////@Autowired
	//SpringAuthProvider springAuthProvider;
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		//auth.authenticationProvider(springAuthProvider);
	}
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
        .antMatchers("/" , "/signin" , "/register/**").permitAll()
        .antMatchers("/api/**").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/signin").usernameParameter("loginId");
	}*/
}
