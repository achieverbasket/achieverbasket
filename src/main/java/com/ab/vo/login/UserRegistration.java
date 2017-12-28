package com.ab.vo.login;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ab.type.UserType;

public class UserRegistration{
	
	@Size(min=2,message="Kindly provide First Name")
	private String firstName;
	
	@Size(min=2,message="Kindly provide Last Name")
	private String lastName;
	
	@NotEmpty(message="Kindly provide Email")
	private String email;

	@NotEmpty(message="Kindly provide mobile")
	private String mobile;

	@Size(min=6,message="Kindly provide Password")
	private String password;
	
	@Size(min=6,message="Kindly re-enter Password")
	private String password1;

	private UserType userType = UserType.CANDIDATE;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
