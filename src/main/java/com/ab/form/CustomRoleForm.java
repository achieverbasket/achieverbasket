package com.ab.form;

import org.springframework.security.core.GrantedAuthority;

public class CustomRoleForm implements GrantedAuthority{

	private static final long serialVersionUID = 1875205229565541916L;
	
	private String name;
	
	public String getAuthority() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
