package com.ab.spring.form;

import java.io.Serializable;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 11/09/2017
 */
public class UserProfessionForm implements Serializable{

	
	private static final long serialVersionUID = -3748037507666955204L;
	
	private	int id;// primary key may not required
	private	String companyName;
	private String companyCity;
	private String designation;
	private String joiningYear;
	private String leavingYear;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getJoiningYear() {
		return joiningYear;
	}
	public void setJoiningYear(String joiningYear) {
		this.joiningYear = joiningYear;
	}
	public String getLeavingYear() {
		return leavingYear;
	}
	public void setLeavingYear(String leavingYear) {
		this.leavingYear = leavingYear;
	}	
	
}
