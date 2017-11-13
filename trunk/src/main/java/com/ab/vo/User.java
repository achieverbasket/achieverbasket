package com.ab.vo;

import com.ab.type.UserType;

public class User{
	private Long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private byte[] image;
	private String email;
	private String hintQ;
	private String hintA;
	private boolean enabled=false;
	private UserType userType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHintQ() {
		return hintQ;
	}

	public void setHintQ(String hintQ) {
		this.hintQ = hintQ;
	}

	public String getHintA() {
		return hintA;
	}

	public void setHintA(String hintA) {
		this.hintA = hintA;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}