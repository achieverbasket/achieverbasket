package com.ab.application.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.ab.security.config.SpringAuthProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	SpringAuthProvider springAuthProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(springAuthProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Bean
	public CustomAuthFailureHandler getAuthFailure() {
		return new CustomAuthFailureHandler();
	}

	@Bean
	public CustomAccessDeniedHandler getAccessDenied() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder bw = new BCryptPasswordEncoder();
		return bw;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/login**" ,  "/resetpassword**")
				.permitAll()
				/*.antMatchers("/verify**")
				.permitAll()*/
				/*.antMatchers("/user/**", "/profile**", "/dashboard",
						"/uploadMedia", "/uploadUserMedia")
				.hasAnyAuthority("ROLE_AUTH")*/
				.antMatchers("/dashboard/**" , "/setting**" , "/profile**" , "/certificate**").hasAnyAuthority("ROLE_ISSUER, ROLE_USER")
				.antMatchers("/issuer/**").hasAnyAuthority("ROLE_ISSUER")
				/*.antMatchers("/services/**")
				.hasAnyAuthority("ROLE_AUTH, ROLE_USER")
				.antMatchers("/authtoken")
				.hasAnyAuthority("ROLE_AUTH, ROLE_USER")*/
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard").failureHandler(getAuthFailure())
				.and().logout().clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID")
				//.logoutUrl("/login?logout")
				.and()
				.exceptionHandling()
				.accessDeniedHandler(getAccessDenied())
				.and().sessionManagement().maximumSessions(1)
				.expiredUrl("/login?expired").and()
				.invalidSessionUrl("/login?session").and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

		http.csrf()
				.csrfTokenRepository(
						CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.logout().invalidateHttpSession(true);

	}
}
