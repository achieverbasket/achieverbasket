package com.ab.vo.login;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Registration implements Serializable{

	private static final long serialVersionUID = 8756830174620934593L;
	@Size(min=2,message="Kindly provide First Name")
	private String firstname;
	@Size(min=2,message="Kindly provide Last Name")
	private String lastname;
	@NotEmpty(message="Kindly provide Email")
	private String email;
	@Size(min=6,message="Kindly provide Password")
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
