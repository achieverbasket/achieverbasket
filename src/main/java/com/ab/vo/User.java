package com.ab.vo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.ab.form.CustomRoleForm;
import com.ab.type.UserType;

public class User extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private byte[] image;
	private String email;
	private Long mobileNumber;
	private String hintQ;
	private String hintA;
	private UserType userType;
	private String status;
	private List<CustomRoleForm> customRoleForm;
	
	public User(String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			String status,UserType userType,String firstName, String lastName,String email, Long mobileNumber, Long userId
			) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.status = status;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.userId = userId;
		this.userName = username;
		this.password = password;
	}
	

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

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", image=" + Arrays.toString(image) + ", email=" + email
				+ ", hintQ=" + hintQ + ", hintA=" + hintA +  ", userType=" + userType + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CustomRoleForm> getCustomRoleForm() {
		return customRoleForm;
	}

	public void setCustomRoleForm(List<CustomRoleForm> customRoleForm) {
		this.customRoleForm = customRoleForm;
	}
}
