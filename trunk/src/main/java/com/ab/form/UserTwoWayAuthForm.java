package com.ab.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class UserTwoWayAuthForm  implements Serializable{
	
	
	private static final long serialVersionUID = 2093246403210859275L;
	@Size(min=6,message="Enter the Token recieved by Email / SMS")
	private String twowayauth;

	public String getTwowayauth() {
		return twowayauth;
	}

	public void setTwowayauth(String twowayauth) {
		this.twowayauth = twowayauth;
	}
}
