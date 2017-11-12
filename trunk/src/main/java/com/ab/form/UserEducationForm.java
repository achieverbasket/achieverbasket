package com.ab.form;

import java.io.Serializable;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 11/09/2017
 */
public class UserEducationForm implements Serializable{

	
	private static final long serialVersionUID = -3748037507666955204L;
	
	private	int id;// primary key may not required
	private	String instituteName;
	private String instituteCity;
	private String qualification;
	private String registrationYear;
	private String passoutYear;
	private String percentmarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getInstituteCity() {
		return instituteCity;
	}
	public void setInstituteCity(String instituteCity) {
		this.instituteCity = instituteCity;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getRegistrationYear() {
		return registrationYear;
	}
	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}
	public String getPassoutYear() {
		return passoutYear;
	}
	public void setPassoutYear(String passoutYear) {
		this.passoutYear = passoutYear;
	}
	public String getPercentmarks() {
		return percentmarks;
	}
	public void setPercentmarks(String percentmarks) {
		this.percentmarks = percentmarks;
	}
	
	
	
	
}
