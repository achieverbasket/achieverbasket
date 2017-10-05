package com.ab.spring.form;

import java.io.Serializable;

import javax.validation.constraints.Size;


/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
public class UserSigninForm implements Serializable{
	
	private static final long serialVersionUID = 3380104871991834440L;
	
	@Size(min=2,message="First Name is mandatory")
	private String account;
	@Size(min=2,message="First Name is mandatory")
	private String password;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
