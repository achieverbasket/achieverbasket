package com.ab.spring.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
public class UserRegistrationForm implements Serializable{
	
	private static final long serialVersionUID = 3380104871991834440L;
	@Size(min=2,message="First Name is mandatory")
	private String firstname;
	@Size(min=2,message="First Name is mandatory")
	private String lastname;
	@NotEmpty(message="Email is mandatory")
	private String email;
	@Size(min=2,message="First Name is mandatory")
	private String password;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
